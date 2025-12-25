package com.example.onlinefoodorderingapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.onlinefoodorderingapp.models.CartItem
import com.example.onlinefoodorderingapp.models.Food
import com.example.onlinefoodorderingapp.models.Order

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "FoodOrdering.db"
        private const val DATABASE_VERSION = 1

        // FOOD TABLE
        private const val TABLE_FOOD = "food"
        private const val FOOD_ID = "id"
        private const val FOOD_NAME = "name"
        private const val FOOD_PRICE = "price"

        // CART TABLE
        private const val TABLE_CART = "cart"
        private const val CART_ID = "id"
        private const val CART_FOOD_ID = "food_id"
        private const val CART_FOOD_NAME = "food_name"
        private const val CART_QTY = "quantity"
        private const val CART_PRICE = "price"

        // ORDER TABLE
        private const val TABLE_ORDER = "orders"
        private const val ORDER_ID = "id"
        private const val ORDER_QR = "order_qr"
        private const val ORDER_SUMMARY = "summary"
        private const val ORDER_TOTAL = "total"
        private const val ORDER_VERIFIED = "verified"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createFoodTable = """
            CREATE TABLE $TABLE_FOOD (
                $FOOD_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $FOOD_NAME TEXT UNIQUE,
                $FOOD_PRICE REAL
            )
        """

        val createCartTable = """
            CREATE TABLE $TABLE_CART (
                $CART_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $CART_FOOD_ID INTEGER,
                $CART_FOOD_NAME TEXT,
                $CART_QTY INTEGER,
                $CART_PRICE REAL
            )
        """

        val createOrderTable = """
            CREATE TABLE $TABLE_ORDER (
                $ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $ORDER_QR TEXT UNIQUE,
                $ORDER_SUMMARY TEXT,
                $ORDER_TOTAL REAL,
                $ORDER_VERIFIED INTEGER DEFAULT 0
            )
        """

        db.execSQL(createFoodTable)
        db.execSQL(createCartTable)
        db.execSQL(createOrderTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CART")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER")
        onCreate(db)
    }

    /* ---------------- FOOD METHODS ---------------- */

    fun addFood(name: String, price: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(FOOD_NAME, name)
            put(FOOD_PRICE, price)
        }
        db.insert(TABLE_FOOD, null, values)
        db.close()
    }

    fun getAllFoods(): List<Food> {
        val list = mutableListOf<Food>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_FOOD", null)

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Food(
                        id = cursor.getInt(0),
                        name = cursor.getString(1),
                        price = cursor.getDouble(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun deleteFood(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_FOOD, "$FOOD_ID=?", arrayOf(id.toString()))
        db.close()
    }

    /* ---------------- CART METHODS ---------------- */

    fun addToCart(food: Food) {
        val db = writableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_CART WHERE $CART_FOOD_ID=?",
            arrayOf(food.id.toString())
        )

        if (cursor.moveToFirst()) {
            val qty = cursor.getInt(cursor.getColumnIndexOrThrow(CART_QTY)) + 1
            val values = ContentValues().apply {
                put(CART_QTY, qty)
            }
            db.update(
                TABLE_CART,
                values,
                "$CART_FOOD_ID=?",
                arrayOf(food.id.toString())
            )
        } else {
            val values = ContentValues().apply {
                put(CART_FOOD_ID, food.id)
                put(CART_FOOD_NAME, food.name)
                put(CART_QTY, 1)
                put(CART_PRICE, food.price)
            }
            db.insert(TABLE_CART, null, values)
        }
        cursor.close()
        db.close()
    }

    fun getCartItems(): MutableList<CartItem> {
        val list = mutableListOf<CartItem>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CART", null)

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    CartItem(
                        id = cursor.getInt(0),
                        foodId = cursor.getInt(1),
                        foodName = cursor.getString(2),
                        quantity = cursor.getInt(3),
                        price = cursor.getDouble(4)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun removeFromCart(foodName: String) {
        val db = writableDatabase
        db.delete(TABLE_CART, "$CART_FOOD_NAME=?", arrayOf(foodName))
        db.close()
    }

    fun clearCart() {
        val db = writableDatabase
        db.delete(TABLE_CART, null, null)
        db.close()
    }

    /* ---------------- ORDER METHODS ---------------- */

    fun saveOrder(orderQr: String) {
        val cartItems = getCartItems()
        var summary = ""
        var total = 0.0

        for (item in cartItems) {
            summary += "${item.foodName} x ${item.quantity} = ₹${item.price * item.quantity}\n"
            total += item.price * item.quantity
        }

        val values = ContentValues().apply {
            put(ORDER_QR, orderQr)
            put(ORDER_SUMMARY, summary)
            put(ORDER_TOTAL, total)
            put(ORDER_VERIFIED, 0)
        }

        val db = writableDatabase
        db.insert(TABLE_ORDER, null, values)
        db.close()
    }

    fun verifyOrder(orderQr: String): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_ORDER WHERE $ORDER_QR=?",
            arrayOf(orderQr)
        )

        if (cursor.moveToFirst()) {
            val verified =
                cursor.getInt(cursor.getColumnIndexOrThrow(ORDER_VERIFIED))

            if (verified == 1) {
                cursor.close()
                db.close()
                return false
            }

            val values = ContentValues().apply {
                put(ORDER_VERIFIED, 1)
            }
            db.update(
                TABLE_ORDER,
                values,
                "$ORDER_QR=?",
                arrayOf(orderQr)
            )
            cursor.close()
            db.close()
            return true
        }

        cursor.close()
        db.close()
        return false
    }
}
