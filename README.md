# 📄 QuotationApp – Android Quotation Generator

QuotationApp is a complete Android application designed for small businesses and freelancers to create, manage, and share professional quotations in PDF format.

---

## 🚀 Features

- Add, edit, and delete components
- Attach images to components
- Prepare quotations with quantity and pricing
- Generate professional PDF quotations
- Share quotation via WhatsApp, Email, etc.
- Quotation history with status tracking
- Accept / Reject quotations
- Show UPI QR code for payments
- Fully offline and free workflow

---

## 📱 Screens

- Login Screen
- Home Screen
- Add Company Header
- Add Components
- Prepare Quotation
- Quotation PDF
- Quotation History
- Payment QR Screen

---

## 🛠️ Tech Stack

- Kotlin
- SQLite
- RecyclerView
- PdfDocument API
- FileProvider
- Android Intents

---

## 🗂️ Project Structure

QuotationApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/quotationapp/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── ComponentsAdapter.kt
│   │   │   │   │   ├── PrepareComponentsAdapter.kt
│   │   │   │   │   └── QuotationHistoryAdapter.kt
│   │   │   │   │
│   │   │   │   ├── db/
│   │   │   │   │   ├── AppDatabase.kt
│   │   │   │   │   ├── DatabaseContract.kt
│   │   │   │   │   └── DatabaseManager.kt
│   │   │   │   │
│   │   │   │   ├── model/
│   │   │   │   │   ├── ComponentModel.kt
│   │   │   │   │   └── QuotationModel.kt
│   │   │   │   │
│   │   │   │   ├── AddCompanyHeaderActivity.kt
│   │   │   │   ├── AddComponentsActivity.kt
│   │   │   │   ├── HomeActivity.kt
│   │   │   │   ├── LoginActivity.kt
│   │   │   │   ├── PrepareQuotationActivity.kt
│   │   │   │   ├── QuotationHistoryActivity.kt
│   │   │   │   ├── QuotationPDFActivity.kt
│   │   │   │   └── PaymentQRActivity.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── build.gradle
│   │
│   └── build.gradle
│
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md


---

## 🧾 Database Tables

### Components Table
- id
- name
- price
- image_uri

### Quotations Table
- id
- customer_name
- customer_mobile
- customer_email
- pdf_path
- created_date
- status

---

## 🔄 App Flow

1. Add company header and components
2. Prepare quotation
3. Generate PDF
4. Share via WhatsApp
5. Customer replies manually
6. Seller accepts/rejects in app
7. If accepted, show UPI QR
8. Share QR for payment

---

## 📦 Installation

1. Clone repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or device

---

## 🔐 Permissions

- Storage access
- FileProvider
- Internet (for UPI apps)

---

## 🎓 Use Case

- Final year project
- Internship project
- Portfolio app
- Small business solution

---

## 👨‍💻 Developer

Ruthishkumar  
Android Developer – India

---

## ⭐ Support

Star the repository if you like this project.

---

## 🚀 Future Enhancements

- Firebase backup
- Invoice numbering
- Payment status tracking
- Customer database
