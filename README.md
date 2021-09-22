# Tutorial APAP

## Authors

* **Aisha Salsabila** - *1906399902* - *C*

---
## Tutorial 2
### Pertanyaan
1. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: 
http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx&jumlahStudio=10 
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

    Terjadi Whitelabel Error Page. Hal ini terjadi karena belum terdapat template yang dapat dirender.

2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja
@Autowired tersebut dalam konteks service dan controller yang telah kamu buat.

    Anotasi @Autowired pada class Controller merupakan implementasi dari konsep Dependency Injection. Dengan anotasi @Autowired, developer tidak perlu lagi menyediakan setter method maupun menambahkan argumen di constructor untuk class-class dengan anotasi seperti @Service dan @Controller dari package yang telah dicari menggunakan fitur component-scan pada Spring Framework. Setelah ditemukan, maka anotasi @Autowired akan melakukan inisialisasi terhadap class tersebut, dan lalu mengisi (inject) semua kebutuhannya (dependency). Semua field/property yang memiliki anotasi @Autowired akan diisikan oleh Spring dengan object bertipe-data sesuai.

3. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut:
http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx 
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

    Terjadi Whitelabel Error Page. Hal ini dikarenakan parameter request yang required dan diperlukan, yaitu parameter method 'jumlahStudio' dengan type int, tidak ada.

4. Jika Papa APAP ingin melihat Bioskop dengan nama Bioskop Maung, link apa yang harus diakses?

    http://localhost:8080/bioskop/view?idBioskop=2
    Dengan asumsi sudah dilakukan penambahan Bioskop MAUNG setelah bioskop PAPA APAP ditambahkan sehingga Bioskop MAUNG memiliki Id Bioskop: 2. Penambahan Bioskop MAUNG dilakukan dengan cara mengakses link berikut:
    http://localhost:8080/bioskop/add?idBioskop=2&namaBioskop=Bioskop%20MAUNG&alamat=Fasilkom%20UWIW&noTelepon=089xxx&jumlahStudio=10

5. Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall , apa yang akan ditampilkan? Sertakan
juga bukti screenshotmu.

    Setelah menambahkan bioskop lain, ketika mengakses link di atas akan muncul daftar seluruh bioskop yang telah didaftarkan beserta dengan detailnya, yaitu Bioskop PAPA APAP dan Bioskop MAUNG.
    ![Screenshot Pertanyaan 5](https://drive.google.com/file/d/1IZQldXfF7u3q9NL6LNbsWEMHxfi9djTG/view?usp=sharing)


### What I did not understand
- [ ] Pemakaian GitHub secara maksimal
- [ ] 



---
## Tutorial 1
### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda
juga boleh menambahkan catatan apapun di bagian ini)
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?

Issue Tracker memungkinkan developer melacak pekerjaannya di GitHub dimana tempat pengembangan terjadi. Saat me-mention sebuah issue dalam issue lain atau pull request, timeline issue mencerminkan referensi silang (the cross-reference) sehingga kita dapat melacak pekerjaan terkait. Untuk menunjukkan bahwa pekerjaan sedang berlangsung, kita dapat menautkan (link) issue ke pull request. Saat pull request di-merge, issues yang tertaut secara otomatis ditutup.

GitHub Issue tracker dapat digunakan untuk meng-track ide-ide, feedback, task, ataupun bug untuk pekerjaan di GitHub.

2. Apa perbedaan dari git merge dan git merge --squash?


3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?
### Spring
4. Apa itu library & dependency?

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?


### What I did not understand
- [ ] Kenapa saya harus belajar APAP?
- [ ] Pemakaian GitHub secara maksimal
- [ ] Penggunaan SpringBoot