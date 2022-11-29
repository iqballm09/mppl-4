# FoodCourt-Pay


<img src="https://drive.google.com/uc?export=view&id=1edvG7Dxubh60G7x6Mg6MJiagFwdSFVIV" width=50% height=50%>


|No. |Nama Anggota            |Role                   |
-----|------------------------|-----------------------|
|1.  |Fatimah Alfiatul Jannah |Project Manager        |
|2.  |M. Iqbal                |Backend Engineer       |      
|3.  |Farhan Faturrahman      |Mobile Engineer and QA |
|4.  |Ramadhanti Nisa P.      |UI/UX Engineer         |


## Latar Belakang
Food court atau yang biasa disebut food hall merupakan sebuah area tempat makan yang menawarkan berbagai macam aneka menu. Food court ini cenderung bersifat terbuka dan informal, biasanya berada di mall, pusat perbelanjaan, perkantoran, dan universitas. Food court merupakan salah satu destinasi kuliner yang sering dikunjungi ketika seseorang bepergian ke mall untuk berbelanja, menonton, atau kegiatan lainnya karena pilihan makanannya yang bervariasi. 

Konsep yang ditawarkan dalam food court adalah konsep makanan cepat saji. Suatu konsep yang mengarahkan pengunjung untuk langsung memesan makanan atau minuman pada merchant yang siap melayani. Produk-produk yang ditawarkan adalah produk-produk siap saji dimana maksimal waktu yang dibutuhkan untuk produksi dan penyajian sekitar 10-15 menit. Pengunjung akan melakukan transaksi langsung pada merchant dan hasil penjualan yang didapat pada hari itu akan dilaporkan kepada pemilik food court sehingga selanjutnya dapat dilakukan proses bagi hasil sesuai dengan kesepakatan awal. 

Namun, proses bagi hasil ini menimbulkan beberapa kekurangan, seperti tidak adanya transparansi antara pemilik food court dengan merchant dapat mengakibatkan kerugian pada pemilik food court karena proses pencatatan penjualan yang masih dilakukan secara manual, dan hasil penjualan akan rentan dimanipulasi karena pembayaran antara pelanggan dan merchant dilakukan secara langsung. Maka dari itu perlu dibuatnya suatu sistem untuk mencatat transaksi yang terjadi di food court, agar kecurangan transaksi dapat dikurangi. Salah satu metode yang umum digunakan adalah penggunaan alat transaksi yang dikhususkan pada food court tersebut, seperti kartu gesek atau chip.


## Tujuan
Tujuan dari pengembangan perangkat lunak ini adalah untuk memudahkan merchant dan pemilik food court dalam hal pencatatan penjualan serta memudahkan pelanggan dan merchant dalam proses transaksi jual beli.


## User Story
### Merchant
- Sebagai merchant saya ingin melakukan pencatatan penjualan agar besarnya keuntungan dapat dihitung
- Sebagai merchant saya ingin mengetahui saldo yang didapat agar besarnya pemasukan tercatat
### Pembeli
- Sebagai pembeli saya ingin melakukan transaksi melalui mobile agar proses lebih praktis
- Sebagai pembeli saya ingin mengisi saldo alat pembayaran dengan mudah agar lebih efektif
- Sebagai pembeli saya ingin melihat catatan pembelian yang telah dilakukan agar pengeluaran tercatat


## Cakupan
Sistem mampu menyimpan jumlah saldo dari user dan melakukan pencatatan seluruh transaksi di food court tersebut. Terdapat aplikasi yang dapat digunakan oleh user dan merchant food court. Berikut adalah fitur-fitur dan batasan pengembangan perangkat lunak:	
### Fitur-fitur yang akan dikembangkan:
- Top-up saldo
- Pembayaran
- Melihat histori transaksi
- Melihat informasi alat pembayaran
- Menghubungkan alat pembayaran
###  Fitur-fitur yang tidak akan dikembangkan:
- Menambah atau menghapus menu makanan/minuman
- Fasilitas membuat alat bayar baru
- Mengirimkan dan menerima saldo ke/dari user lain



## Kebutuhan Sistem
### User
Pembeli dan merchant
### Fitur utama
Pencatatan dan transaksi
### Lingkungan Pengembangan
- UI/UX:
Hardware:
RAM: 8GB
Processor: 1.1 GHz Dual-Core Intel Core M

Software:
High-fi design: Figma
Graphic editing: Gimp 2.10
Testing: Maze

- Backend:
Hardware:
Proc: Intel(R) Celeron(R) CPU N3350 @ 1.10GHz
RAM: 4 GB

Software:
OS: Linux Xubuntu 22.04 LTS
NodeJS: v18.12.1 LTS
Code Editor: VScode v1.73.1
Testing: Postman v9.31.23
Deploy: Railway

- Mobile dev:
Hardware:
Intel Core i7 8565u 
Ram 8 GB DDR4L 2400 mhz

Software:
OS: Linux Ubuntu 22.04 LTS
Android Studio Dolphin 2021.3.1
Testing : Samsung Galaxy A31 (Android 12, OneUI 4.1)

## Risiko
- Penambahan/perubahan fitur mendadak dari stakeholder
- Keterlambatan dalam pembuatan produk dari jadwal yang ditetapkan
- Klien tidak komunikatif, sehingga komunikasi berjalan lambat dan menghambat proses pekerjaan


## Metodologi
Metodologi yang digunakan dalam pengembangan perangkat lunak ini adalah waterfall, metode ini memiliki sifat yang bertahap. Tahap awal dimulai dari perencanaan perangkat lunak, lalu desain, Implementasi sistem, testing, dan terakhir delivering sistem ke client maupun pengguna. Setiap tahapan dalam metode waterfall ini tidak bisa dilakukan secara bersamaan, suatu tahapan tidak dapat dikerjakan jika tahapan sebelumnya belum selesai.

## Use Case
<img src="https://drive.google.com/file/d/1oNcDLLSXhZb-bKt2G93R_lUDjG5DV0H8" width=50% height=50%>


## Activity Diagram
<img src="https://drive.google.com/file/d/1T6qetKhPjTsr0Dkoj1KY2yCttPstsrie" width=50% height=50%>


## ERD
![Foodpay ERD](https://github.com/frhnfath/mppl-4/blob/a9ef9ad5c2c12f84b68a094833fbdce0c9e34ad6/foodpay_erd.png)

## Link Video Demo
https://drive.google.com/file/d/1Q6iaSp-4a3MGQD30k8qYuUEf_lmMwcqI/view?usp=sharing

## Link Sheet Test Case
https://docs.google.com/spreadsheets/d/18d3JeQ-5KjE9FHAaECmo2TDEciWVE6nUIr-fA43FsUo/edit?usp=sharing

## Link Deploy
APK                        : https://drive.google.com/drive/folders/1VC0-6K-rISZLlTweZRZoqTKWwoH-Ox81?usp=sharing 
Cashier Web-App (Frontend) : https://foodpay-cashier.vercel.app/ \
Backend (Server)           : https://foodpayapi.up.railway.app/

## Link Figma
https://www.figma.com/file/ulYsf0NmtXXMzjEr4Cn6xn/MPPL-highfi?node-id=0%3A1&t=YW2DmDC2s1RaYPyV-0

## Kendala
- Belum mengetahui jenis dan skenario testing yang sesuai
- Masih banyak kesibukan dari anggota tim yang mengganggu jalannya proses pengerjaan projek
- Beberapa task yang diselesaikan melebihi dari jadwal yang ditentukan
- Sulit dalam mencari responden untuk testing desain aplikasi merchant


## URL API
### User
Register: https://foodpayapinode.herokuapp.com/api/users/register \
Login: https://foodpayapinode.herokuapp.com/api/users/login \
Read All: https://foodpayapinode.herokuapp.com/api/users \
Update: https://foodpayapinode.herokuapp.com/api/users/id/edit \
Read By Id: https://foodpayapinode.herokuapp.com/api/users/id 
### Merchant (Admin)
Register: https://foodpayapinode.herokuapp.com/api/merchants/register \
Login: https://foodpayapinode.herokuapp.com/api/merchants/login \
Read All: https://foodpayapinode.herokuapp.com/api/merchants \
Update: https://foodpayapinode.herokuapp.com/api/merchants/id/edit \
Read By Id: https://foodpayapinode.herokuapp.com/api/merchants/id 
### Payment
Create: https://foodpayapinode.herokuapp.com/api/payments/cardID \
Read All: https://foodpayapinode.herokuapp.com/api/payments/cardID \
Read By Id: https://foodpayapinode.herokuapp.com/api/payments/id/cardID
### Withdraw
Create: https://foodpayapinode.herokuapp.com/api/withdraws/merchantID \
Read All: https://foodpayapinode.herokuapp.com/api/withdraws/merchantID \
Read By Id: https://foodpayapinode.herokuapp.com/api/withdraws/id/merchantID
### Topup - User
Create: https://foodpayapinode.herokuapp.com/api/userTopups/cardID \
Read All: https://foodpayapinode.herokuapp.com/api/userTopups/cardID \
Read By Id: https://foodpayapinode.herokuapp.com/api/userTopups/id/cardID
### Topup - Cashier (Merchant)
Create: https://foodpayapinode.herokuapp.com/api/cashierTopups/cardID/merchantID \
Read All: https://foodpayapinode.herokuapp.com/api/cashierTopups/merchantID \
Read By Id: https://foodpayapinode.herokuapp.com/api/cashierTopups/id/merchantID
### Card
Create: 
Read All: https://foodpayapinode.herokuapp.com/api/cards \
Read By Id: https://foodpayapinode.herokuapp.com/api/cards/id/userID \
Update: https://foodpayapinode.herokuapp.com/api/cards/id/userID/edit


