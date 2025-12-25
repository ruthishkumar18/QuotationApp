package com.example.quotationapp.db

import android.provider.BaseColumns

object DatabaseContract {

    object HeaderTable : BaseColumns {
        const val TABLE_NAME = "company_header"
        const val COLUMN_ID = "id"
        const val COLUMN_IMAGE_URI = "image_uri"
    }

    object ComponentsTable : BaseColumns {
        const val TABLE_NAME = "components"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_IMAGE_URI = "image_uri"
    }

    object QuotationTable : BaseColumns {
        const val TABLE_NAME = "quotations"
        const val COLUMN_ID = "id"
        const val COLUMN_CUSTOMER_NAME = "customer_name"
        const val COLUMN_MOBILE = "customer_mobile"
        const val COLUMN_EMAIL = "customer_email"
        const val COLUMN_PDF_PATH = "pdf_path"
        const val COLUMN_DATE = "created_date"

        const val COLUMN_STATUS = "status" // NEW
    }

}
