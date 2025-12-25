package com.example.quotationapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotationapp.adapter.PrepareComponentsAdapter
import com.example.quotationapp.db.DatabaseManager
import com.example.quotationapp.model.ComponentModel

class PrepareQuotationActivity : AppCompatActivity() {

    private lateinit var etCustomerName: EditText
    private lateinit var etCustomerMobile: EditText
    private lateinit var etCustomerEmail: EditText
    private lateinit var etSearch: EditText
    private lateinit var rvComponents: androidx.recyclerview.widget.RecyclerView
    private lateinit var btnCreate: Button

    private lateinit var db: DatabaseManager
    private lateinit var adapter: PrepareComponentsAdapter

    private var fullList = mutableListOf<ComponentModel>()
    private var filteredList = mutableListOf<ComponentModel>()

    // Store model + qty → Pair<ComponentModel, Int>
    private val selectedComponents = mutableListOf<Pair<ComponentModel, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare_quotation)

        db = DatabaseManager(this)

        etCustomerName = findViewById(R.id.etCustomerName)
        etCustomerMobile = findViewById(R.id.etCustomerMobile)
        etCustomerEmail = findViewById(R.id.etCustomerEmail)
        etSearch = findViewById(R.id.etSearchComponent)
        rvComponents = findViewById(R.id.rvQuotationComponents)
        btnCreate = findViewById(R.id.btnCreateQuotation)

        // Load components
        fullList = db.getAllComponents()
        filteredList = fullList.toMutableList()

        setupRecycler()
        setupSearch()

        btnCreate.setOnClickListener { goToPDF() }
    }

    // ------------------------------------------------------------
    // SETUP RECYCLER VIEW
    // ------------------------------------------------------------
    private fun setupRecycler() {
        adapter = PrepareComponentsAdapter(
            filteredList,
            selectedComponents
        ) {
            // callback if needed when selection changes
        }

        rvComponents.layoutManager = LinearLayoutManager(this)
        rvComponents.adapter = adapter
    }

    // ------------------------------------------------------------
    // SEARCH / FILTER
    // ------------------------------------------------------------
    private fun setupSearch() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }
        })
    }

    private fun filterList(query: String) {
        filteredList.clear()

        filteredList.addAll(
            if (query.isEmpty()) fullList
            else fullList.filter { it.name.lowercase().contains(query.lowercase()) }
        )

        adapter.notifyDataSetChanged()
    }

    // ------------------------------------------------------------
    // GO TO PDF PAGE WITH SELECTED ITEMS
    // ------------------------------------------------------------
    private fun goToPDF() {
        val name = etCustomerName.text.toString().trim()
        val mobile = etCustomerMobile.text.toString().trim()
        val email = etCustomerEmail.text.toString().trim()

        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Enter all customer details", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedComponents.isEmpty()) {
            Toast.makeText(this, "Select at least one component", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, QuotationPDFActivity::class.java)
        intent.putExtra("customerName", name)
        intent.putExtra("customerMobile", mobile)
        intent.putExtra("customerEmail", email)

        // Convert Pair<Model, Qty> into a transferable array list
        val selectedArray = ArrayList<HashMap<String, String>>()

        for (pair in selectedComponents) {
            val map = HashMap<String, String>()
            map["id"] = pair.first.id.toString()
            map["name"] = pair.first.name
            map["price"] = pair.first.price
            map["imageUri"] = pair.first.imageUri
            map["qty"] = pair.second.toString()
            selectedArray.add(map)
        }

        intent.putExtra("selectedList", selectedArray)

        startActivity(intent)
    }
}
