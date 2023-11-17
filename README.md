# ForPin-SOAP



## Deskripsi Singkat
Forpin-SOAP berfungsi sebagai _backend_ untuk keseluruhan aplikasi forpin, menggunakan protokol SOAP. Aplikasi ini dikembangkan dengan menggunakan java dan _library_ JAX WS dan disusun untuk memenuhi Milestone 2 dari mata kuliath IF3110 Pengembangan Aplikasi Berbasis Web. 

## Skema Basis Data 
![image](https://github.com/Ainzw0rth/ForPin-Soap/assets/88926116/c78ce686-0060-4960-abb7-7f74ffb15107)

## Endpoint API 
Endpoint berada pada /subscription dan /premium 

Beberapa method yang terdapat pada endpoint /subscription
- subscriptionList untuk melihat data subscription
- newSubscription untuk menambahkan data subsription
- checkSubscription untuk mengecek status subscription
- updateSubscription untuk mengupdate status subscription
- notYetSubscribedUserList untuk menampilkan list user yang subscription statusnya di-_reject_

Beberapa method yang terdapat pada endpoint /premium
- newPremiumUser untuk menambahkan data premium user
- premiumList untuk melihat data user premium
- checkPremium untuk mengecek status premium user
- updatePremiumUser untuk mengupdate status premium user

## Pembagian Tugas
1. Initial Project Setup : 13521156
2. Security (Log and API Key) : 13521156
3. Subscription and Premium service : 13521156
4. Sync to PHP : 13521156
5. PHP to SOAP : 13521156
