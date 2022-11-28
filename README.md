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

## Cakupan

## Kebutuhan Sistem

## Risiko

## Metodologi

## Use Case

## Activity Diagram

## ERD
![](foodcourt_erd.png)

## Link Video Demo

## Link Sheet Test Case

## Link Deploy

## Link Figma

## Kendala

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


