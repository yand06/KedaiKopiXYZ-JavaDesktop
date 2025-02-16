# ABC Coffee Shop - Point of Sale System
> Sales and inventory management system for coffee shops with POS, menu management, and reporting features.
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Description
Java-based desktop application to manage ABC Coffee Shop operations. This system helps coffee shop owners to manage inventory, sales transactions, and customers efficiently. This application is developed to facilitate the coffee shop business process from order recording to report generation.

### ✨ Key Features
- Login system with user authentication
- Dashboard with summary statistics (Item, Menu, Customer, Employee)
- Menu management with product images
- Ordering system with visual interface
- Shopping cart with automatic calculation
- Customer management
- Inventory management
- Payment system
- Transaction report generation
- Export report to PDF format

## 🚀 How to use
### Prerequisites
To run this application, you need:
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE 18 or higher
- MySQL Database
- JasperReports Library
- MySQL JDBC Connector

### Instalasi
1. Clone repository
```bash
git clone https://github.com/username/kedai-kopi-abc.git
```

2. Create a MySQL database with the name 'kedaikopi'

3. Import the provided SQL file
```bash
mysql -u username -p kedaikopi < database/kedaikopi.sql
```

4. Open project in NetBeans IDE

5. Customize the database configuration in the `connection.java` file

6. Build and run the application

## 💻 Technologies Used
- [Java](https://www.java.com/) - Main programming language
- [MySQL](https://www.mysql.com/) - Database management system
- [JasperReports](https://community.jaspersoft.com/) - Report generation
- [NetBeans IDE](https://netbeans.apache.org/) - Development IDE
- [Swing](https://docs.oracle.com/javase/tutorial/uiswing/) - GUI Framework

## 📸 Screenshot
### System Login

![Login Berhasil  Kedai Kopi](https://github.com/user-attachments/assets/08742a91-5bf4-4b47-8d41-0861ab9caebd)


### 2. Dashboard

![Dashboard Kedai Kopi](https://github.com/user-attachments/assets/d87f08f1-cef9-45c2-89e6-4460b2dfc003)

- Display statistics: 6 Items, 20 Menus, 12 Customers, 5 Employees
- Sales report


### 3. Menu Management

![Menu](https://github.com/user-attachments/assets/437414c6-45b2-46ad-b5a9-604df58b6c0d)

- CRUD operation for menu
- Product image upload
- Stock management


### 4. Goods Management

![Barang](https://github.com/user-attachments/assets/d1ed4c91-6105-43c9-b1cc-718620b17ac3)

- CRUD operations for goods
- Item management


### 5. Customer

![Pelanggan](https://github.com/user-attachments/assets/00ccd761-8fd9-4e3c-a5cb-fd12846526f6)

- CRUD operations for customers


### 6. Employee

![Pegawai](https://github.com/user-attachments/assets/b823219f-7807-4f45-b8cc-6d8a04d01942)

- CRUD operations for employees


### 7. Pemesanan

![Pemesanan](https://github.com/user-attachments/assets/19aef0ce-15ec-4352-a017-5e7dd33d52fa)

- Visual ordering interface
- Real-time shopping cart
- Payment status

  
### 8. Payment

![Pembayaran](https://github.com/user-attachments/assets/c828318c-afc7-4bfc-8af8-26afde8410c6)

- CRUD Operations for Payments
- Transaction management


### 9. Report

![Laporan](https://github.com/user-attachments/assets/fc763d21-0e8e-4c8a-b276-76664a02fc93)
![View Laporan](https://github.com/user-attachments/assets/41f2200f-f730-4ac6-a0c7-9e61a5dabd72)

- Menu Report 
- Export to PDF


![Laporan Barang](https://github.com/user-attachments/assets/8967d52d-653f-4752-8e06-b6fd7198bb81)
![View Laporan Barang](https://github.com/user-attachments/assets/002b76e3-ce7d-4170-9d50-d1fe947c47ed)

- Barang Report
- Export ke PDF


![Laporan Pelanggan](https://github.com/user-attachments/assets/bb830488-ff1e-4bc4-a11b-c3bd12cdbcdb)
![View Laporan Pelanggan](https://github.com/user-attachments/assets/e120d700-d971-418c-9cb0-44ffbd25c8ba)

- Pelanggan Report
- Export ke PDF


![Laporan Pembayaran](https://github.com/user-attachments/assets/f737385a-60be-4c5a-8c04-b3f33fa1e4ce)
![View Laporan Pembayaran](https://github.com/user-attachments/assets/b1485832-61aa-4c0d-b341-551d602b5381)

- Pembayaran Report
- Export ke PDF

## 🤝 Contributions
Contributions are always welcome. Please follow the standard steps:
1. Fork this project
2. Create your feature branch (`git checkout -b feature/FeatureNew`)
3. Commit your changes (`git commit -m 'Added new feature'`)
4. Push to the branch (`git push origin feature/FeatureNew`)
5. Open Pull Request
