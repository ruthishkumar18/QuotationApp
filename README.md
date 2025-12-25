# 📄 QuotationApp – Android Quotation Generator

QuotationApp is a complete Android application designed for small businesses and freelancers to **create, manage, and share professional quotations** in PDF format.  
The app supports **component management**, **PDF generation**, **quotation history**, **accept/reject workflow**, and **UPI payment via QR code**.

---

## 🚀 Features

### ✅ Core Features
- Add, edit, and delete product/components
- Attach component images
- Prepare quotations with quantity and pricing
- Generate **professional PDF quotations**
- Share quotation PDF via WhatsApp, Email, or other apps

### 📂 Quotation Management
- View quotation history
- Open and share previously generated PDFs
- Accept or reject quotations
- Automatic quotation status tracking:
  - `PENDING`
  - `ACCEPTED`
  - `REJECTED`

### 💳 Payment Integration
- Seller-controlled confirmation flow
- Show **UPI QR code** after quotation acceptance
- Open UPI apps (Google Pay, PhonePe, Paytm, etc.)
- 100% **offline and free** payment flow

---

## 📱 Screens Implemented

- Login Screen
- Home Dashboard
- Add Company Header
- Add Components Screen
- Prepare Quotation Screen
- PDF Preview Screen
- Quotation History Screen
- Payment QR Screen

---

## 🛠️ Tech Stack

| Technology | Usage |
|-----------|------|
| **Kotlin** | Android development |
| **SQLite** | Local database |
| **RecyclerView** | Dynamic lists |
| **PdfDocument API** | PDF generation |
| **FileProvider** | Secure file sharing |
| **Intent System** | WhatsApp & UPI integration |

---

QuotationApp/
│
├── app/
│ ├── java/com/example/quotationapp/
│ │ ├── adapter/
│ │ ├── db/
│ │ ├── model/
│ │ ├── ui/
│ │ └── activities/
│ │
│ ├── res/
│ │ ├── layout/
│ │ ├── drawable/
│ │ └── values/
│
├── AndroidManifest.xml
├── build.gradle
├── README.md



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
- status (`PENDING / ACCEPTED / REJECTED`)

---

## 🔄 Application Flow

1. Add company header and components
2. Prepare a new quotation
3. Select components with quantity
4. Generate PDF quotation
5. Share quotation via WhatsApp
6. Customer replies manually (Yes / No)
7. Seller updates status inside app
8. If accepted → show UPI QR code
9. Share QR for payment

---

## 📌 Why This App is Special

- ✔ No paid APIs
- ✔ No WhatsApp Business API required
- ✔ Works fully offline
- ✔ Ideal for small businesses
- ✔ Professional PDF layout
- ✔ Real-world workflow

---

## 📦 Installation & Setup

1. Clone the repository
```bash
git clone https://github.com/yourusername/QuotationApp.git


Open in Android Studio

Sync Gradle

Run on emulator or physical device

🔐 Permissions Used

Read external storage

Write external storage

Internet (for UPI apps)

File access via FileProvider

🧪 Tested On

Android 8.0 (Oreo) and above

Physical device & emulator

Google Pay, PhonePe, Paytm

## 🗂️ Project Structure

