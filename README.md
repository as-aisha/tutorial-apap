# Tutorial APAP

## Authors

* **Aisha Salsabila** - *1906399902* - *C*

---
## Tutorial 8
### Pertanyaan
1. Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no. 1, dan mengapa kalian melakukan langkah - langkah tersebut?

   → Dari soal tutorial sudah tersedia source code yang dapat meng-solve apa yang ditanyakan pada Latihan No. 1. Kode tersebut berfungsi agar text field pada form add item menjadi kosong setiap kali pengguna akan menambah item baru. Berikut merupakan potongan kodenya.
   ```javascript
    async handleSubmitItem(event) {
        ...
        try {
            const data = {
                ...
            };
            ...
            this.setState({
                items: [],
                isLoading: false,
                isCreate: false,
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            ...
        } catch (error) {
            ...
        }
        ...
    }
   ```

2. Jelaskan fungsi dari async dan await!

   - async → merupakan salah satu function pada javascript yang berguna untuk mengubah function menjadi asynchronous atau menjadi bersifat non-blocking. Kembalian dari function async adalah semua Promise.
   - await → function await berguna untuk menunda jalannya sebuah kode sampai proses async berhasil. Serupa dengan async, function await juga akan mengembalikan sebuah Promise, namun kode yang ada pada function await baru dapat dijalankan apabila Promise sudah resolved.

3. Masukkan jawaban dari Screenshot yang diperintahkan di halaman 9 pada Component Lifecycle pada pertanyaan ini.

   - Kode program
     ![Screenshot kode program](/images/T8-1.jpg)
     ![Screenshot kode program](/images/T8-2.jpg)
   - Program dijalankan
     ![Screenshot program dijalankan](/images/T8-3.jpg)
   - shouldComponentUpdate() tidak muncul
     ![Screenshot shouldComponentUpdate() tidak muncul](/images/T8-4.jpg)
   - Perubahan pada kode program
     ![Screenshot perubahan pada kode program](/images/T8-5.jpg) 
     ![Screenshot perubahan pada kode program](/images/T8-6.jpg) 
     ![Screenshot perubahan pada kode program](/images/T8-7.jpg) 
   - Error pada shouldComponentUpdate()
     ![Screenshot Error pada shouldComponentUpdate()](/images/T8-8.jpg)
     - Setelah tombol change state ditekan
     ![Screenshot Error setelah changeState](/images/T8-9.jpg)


4. Jelaskan fungsi dari componentDidMount, shouldComponentUpdate, componentDidUpdate, componentWillReceiveProps, componentWillUnmount.
Notes : Penjelasan harus mencantumkan “kapan fungsi dipanggil” dan “use case apa saja yang biasanya menggunakan lifecycle method tersebut”

   - componentDidMount → Function ini dipanggil setelah function render dijalankan dan biasa digunakan untuk manipulasi DOM atau operasi pemanggilan data (ajax). Seluruh operasi DOM terjadi bukan pada function render, melainkan pada fase ini.
   - shouldComponentUpdate → Function ini selalu dipanggil sebelum function render sehingga memungkinkan untuk mengatur apakah re-rendering diperlukan atau tidak. Hal ini salah satunya dapat dilakukan dengan mengakses props dan state saat ini. Function shouldComponentUpdate harus memiliki nilai return yang berupa boolean, di mana ketika fungsi mengembalikan nilai true component akan di-render ulang, vice versa. Terdapat 2 parameter pada function ini, yaitu nextProps dan nextState.
   - componentDidUpdate → Function ini dipanggil setelah function render dam digunakan saat melakukan interaksi dengan environment non-React, seperti HTTP Request. Mirip dengan componentDidMount, function ini dapat digunakan untuk manipulasi DOM setelah data diperbarui.
   - componentWillReceiveProps → Function ini hanya dipanggil ketika props telah berubah dan tidak dipanggil pada saat rendering awal. Fungsi ini berguna untuk membandingkan props yang telah ada dengan props yang baru serta memeriksa apakah terdapat perubahan terhadap props tersebut. Function ini juga memungkinkan perubahan state berdasarkan pada props saat ini dan props baru tanpa memicu rendering ulang.
   - componentWillUnmount → Function ini dipanggil ketika komponen telah dibuang dari DOM. componentWillUnmount berfungsi ketika diperlukannya operasi-operasi yang beruhubungan dengan pembersihan. Sebagai contoh, membuang timer yang sebelumnya telah didefinisikan di componentDidMount, menghilangkan event listener, membatalkan network request, dsb.

**Referensi:**
- [Javascript Async/Await](https://www.kawankoding.id/javascript-async-await/)
- [React.Component](https://reactjs.org/docs/react-component.html)


### What I did not understand
- [ ] banyak T_T



---
## Tutorial 7
### Pertanyaan
1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot sebagai ilustrasi dari apa yang Anda jelaskan.

   - Latihan 1

     ![Screenshot Latihan 1](/images/T7-1.png)
     
     Pada latihan nomor 1, saya membuat function baru (handleDeleteItemFromCart) untuk menghapus sebuah item dari My Cart. Item akan terhapus ketika tombol tong sampah (delete) ditekan. Logic dari function-nya cukup mirip dengan function handleAddItemToCart, di mana saya mendefinisikan terlebih dahulu properti yang akan digunakan. Selain itu, salah satu hal utama dalam function ini adalah mendapatkan index item yang ingin dihapus, yang mana didapatkan melalui deklarasi const targetId serta menggunakan method splice yang dimiliki oleh Array JS untuk menghapus item pada list. Parameter pertama method splice adalah index item yang akan dihapus dan parameter kedua menunjukkan jumlah item yang akan dihapus. Setelah item dihapus dari newItems (yang mana merupakan component cartItems), dengan menggunakan method updateShopItem dan mengubah status item menjadi false, item akan dikembalikan ke list shopItems.

   - Latihan 2

     ![Screenshot Latihan 2](/images/T7-2.png)

     Untuk latihan nomor 2, saya menambahkan properti balance pada function handleAddItemToCart dan handleDeleteFromCart yang nantinya akan digunakan sesuai kebutuhan. Saya juga menambahkan operasi setState untuk memperbaharui properti balance yang dimiliki pengguna. Ketika pengguna memasukkan item ke dalam cart, nilai balance akan dikurangi dengan harga item tersebut dan ketika pengguna menghapus item dari cart, nilai balance akan ditambah sejumlah harga item yang dihapuskan. 
     
   - Latihan 3

     ![Screenshot Latihan 3](/images/T7-3.png)
     
     Pada latihan nomor 3, saya memanfaatkan kembali properti balance yang ada pada function handleAddItemToCart. Jika balance yang dimiliki pengguna nilainya lebih dari atau sama dengan harga item yang ingin dimasukkan, item akan ditambahkan ke dalam cart seperti biasa. Namun, jika nilai balance yang dimiliki kurang dari harga item yang ingin ditambahkan ke cart, akan ditampilkan pesan bahwa balance yang dimiliki tidak mencukupi untuk melakukan pembelian.

2. Menurut pemahaman kamu selama pengerjaan tutorial ini, apa perbedaan antara ***state*** dan ***props***?

   → *State* merupakan sebuah properti atau variabel yang didefinisikan dalam sebuah class, sedangkan *props* (kependekan dari *'properties'*) adalah properti atau variabel yang berasal dari luar class atau class parent. Perbedaan yang mendasar dari keduanya adalah pada props kita tidak dapat mengubah nilainya secara langsung, sedangkan pada state kita dapat melakukan hal tersebut. State bersifat privat atau tidak dapat digunakan di komponen lain, sedangkan props dapat digunakan di komponen lain (komponen child).

3. Menurut kamu, apakah sebaiknya kita menggunakan *component* (e.g. List, Item) dalam React? Sebutkan alasannya.

   → Ya, karena dengan menggunakan component kita dapat membuat UI dari bagian-bagian yang terpisah (building block) dan menyusunnya menjadi kesatuan (compose) serta di-render menjadi sebuah kode HTML. Hal ini tentunya akan memberikan kemudahan, di mana component dapat digunakan kembali (reusable). Sebagai contoh, component List dan Item pada tutorial 7 dapat digunakan secara langsung pada beberapa function yang berbeda.

4. Apa perbedaan *class component* dan *functional component*?

   → Perbedaan mendasar antara class component dan functional component adalah class component dapat menggunakan state dan props, sedangkan functional component hanya bisa menggunakan props. Hal itulah yang menyebabkan function component disebut stateless component atau biasa digunakan juga sebagai UI Component (komponen yang menangani tampilan).

5. Dalam React, apakah perbedaan *component* dan *element*?

   →  Komponen pada React secara konsep sama seperti function pada JavaScript, perbedaannya adalah jika function menerima sebarang input yang disebut parameter atau argumen dan me-return sebuah nilai, komponen menerima input yang disebut props dan me-return React Element yang menggambarkan apa yang akan ditampilkan di layar. Komponen memungkinkan developer untuk membuat UI yang independent, isolated, dan reusable sehingga mengurangi repetisi kode.
   
   →  Elemen adalah blok bangunan terkecil di React, yang menggambarkan apa yang akan dilihat oleh user di layar mereka. Secara sederhana, elemen dapat didefinisikan sebagai representasi virtual dari DOM. Elemen React bersifat immutable, artinya setelah elemen itu dibuat, maka attribute dan children-nya tidak bisa diubah, sehingga satu-satunya cara untuk meng-update UI adalah dengan membuat elemen baru, dan kemudian di-pass kembali ke ReactDOM.render().


**Referensi:**
- [Mengenal State Dan Props Pada React](https://www.dumetschool.com/blog/Mengenal-State-Dan-Props-Pada-React)
- [Modul 2-4 Props dan State](https://nusantech.com/modul-2-4-props-dan-state/)
- [Dasar React Component](https://medium.com/@ekaprasasti/dasar-react-component-ff6b4926e86c)
- [Class Component dan Functional Component Serta Import dan Export React JS](https://hobikoding.github.io/component-react/)
- [Mengenal Elemen, Komponen, dan Props di React](https://hasanmubarok.medium.com/mengenal-element-components-dan-props-di-react-bbff544dd201)


### What I did not understand
- [ ] banyak T_T



---
## Tutorial 6
### Pertanyaan
1. Jelaskan secara singkat perbedaan **Otentikasi** dan **Otorisasi**! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi?

   → Otentikasi diperlukan untuk melakukan verifikasi bahwa pengguna mempunyai akses untuk masuk ke dalam sistem, sedangkan otorisasi merupakan pengaturan hak pengguna yang telah memiliki otentikasi, di mana otorisasi akan menentukan fitur apa saja yang dapat diakses oleh seorang pengguna yang telah diotentikasi. Pengguna dengan role Admin tentunya memiliki hak otorisasi yang berbeda dengan pengguna yang memiliki role sebagai Staf biasa

   → Otentikasi diimplementasikan untuk fitur login dalam file WebSecurityConfig.java
    ```
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
    ```
    → Contoh implementasi otentikasi adalah untuk latihan nomor 1, 2, dan 3. Salah satu contohnya ada dalam file WebSecurityConfig.java, yaitu untuk membatasi yang dapat melihat, menambahkan, dan menghapus user hanya user dengan role ADMIN
    ```
    ...
        .antMatchers("/user/viewall").hasAuthority("ADMIN")
        .antMatchers("/user/add").hasAuthority("ADMIN")
        .antMatchers("/user/delete/*").hasAuthority("ADMIN")
    ...
    ```

2. Apa itu **BCryptPasswordEncoder**? Jelaskan secara singkat cara kerja dan tujuannya.

   → BCryptPasswordEncoder merupakan salah satu tools yang dapat mengubah password (password encoder) dengan fungsi hashing kuat yang dimiliki oleh BCrypt. Cara kerjanya dimulai dengan membuat objek BCryptPasswordEncoder. Selanjutnya, ketika program menerima input, akan dilakukan encode password dengan cara hashing menggunakan objek BCryptPasswordEncoder yang telah dibuat dengan tetap memanfaatkan method encode standar. Tujuan dari bycript adalah untuk melindungi dari serangan rainbow table dengan mengunakan salt. Selain itu, bcrypt juga berfungsi adaptif, di mana seiring waktu, jumlah iterasi dapat ditingkatkan untuk membuatnya lebih lambat sehingga semakin aman terhadap serangan pencarian brute-force bahkan dengan meningkatnya daya komputasi. bycript juga berfungsi sebagai algoritme hash password dasar untuk OpenBSD dan sistem lain termasuk beberapa distribusi Linux seperti SUSE Linux.

3. Apakah penyimpanan password sebaiknya menggunakan **encryption** atau **hashing**? Mengapa demikian?

   → Menurut saya, lebih baik penyimpanan password sebaiknya menggunakan hashing. Hal ini dikarenakan hashing bersifat satu arah atau well mapping sehingga akan sulit untuk dipulihkan dan mendapatkan string yang asli kembali. Jika menggunakan enkripsi yang bersifat dua arah atau reversible, ketika ada string yang rusak maka password akan lebih mudah dienkripsi jika memiliki kuncinya, di mana artinya akan lebih mudah untuk mengakses basis data menggunakan enkripsi. Lain halnya dengan hashing yang akan sulit untuk diakses melalui basis data bahkan cenderung tidak mungkin.

4. Jelaskan secara singkat apa itu **UUID** beserta penggunaannya!

   → Universally Unique Identifier atau UUID menciptakan sistem yang mampu mengidentifikasi sesuatu secara praktis dan unik. Dengan UUID, siapapun dapat mengidentifikasi sesuatu bahwa apa yang dikenalnya tidak akan sama dengan yang teridentifikasi oleh pihak lain. Dari kegunaan ini, informasi yang diberi label UUID dapat digabungkan dengan basis data tanpa perlu menyeelsaikan konflik identifier. Pada program, UUID biasa digunakan untuk menginisiasi ID User.

5. Apa kegunaan class **UserDetailsServiceImpl.java**? Mengapa harus ada class tersebut?

   → UserDetailsServiceImpl.java berguna untuk mengotentikasi user. Dalam class ini juga terdapat extend dari UserDetailsService yang di-import dari spring security yang dipanggil dengan memanfaatkan .core. Class ini harus ada agar menghasilkan objek UserDetail yang akan memberikan informasi terkait user yang terdapat pada database. Selain itu, class ini juga dapat berguna untuk mengotorisasi user dengan role yang dimiliki masing-masing user.


### What I did not understand
- [ ] constraint untuk password



---
## Tutorial 5
### Pertanyaan
1. Apa itu Postman? Apa kegunaannya?

   → Postman merupakan sebuah aplikasi yang berfungsi sebagai REST CLIENT untuk melakukan uji coba REST API. Postman biasa digunakan oleh developer pembuat API sebagai tools untuk menguji API yang telah mereka buat.
   Terdapat fitur-fitur pada Postman yang dapat membantu dalam proses development API, antara lain:
    - Collection
        Pengelompokan request API yang bisa disimpan atau diatur dalam bentuk folder. Memudahkan untuk pengelompokan request sesuai dengan proyek yang dikerjakan.
    - Environment
        Semacam config untuk menyimpan attribute dan attribute tersebut dapat digunakan ataupun dimanipulasi dalam proses request API.
    - Response
        Developer dapat membuat Mockup API sebelum benar-benar mengimplementasikan ke dalam proyek.
    - Mock Server
        Dengan fitur ini, Mockup API yang dibuat menggunakan fitur “example response” dapat diakses dari internet layaknya Mockup API tersebut sudah di implementasikan dan di deploy ke server.
    - Script Test
        Fitur untuk melakukan validasi respon, termasuk di dalamnya menuliskan test sesuai dengan kebutuhan.
    - Automated Test (Runner)
        Menjalakan request dalam satu collection secara otomatis, dengan menggunakan script test.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty

   → @JsonIgnoreProperties digunakan di tingkat class untuk menandai properti atau daftar properti yang akan diabaikan, sedangkan @JsonProperty digunakan untuk menandai method getter/setter non-standar pada properti JSON.

3. Apa kegunaan atribut WebClient?

   → WebClient berfungsi untuk mempermudah tasks umum seperti CRUD. Pada dasarnya, ia memiliki fungsi yang sama dengan HttpWebResponse, tetapi dengan level abstraksi yang lebih tinggi. Selain itu, tidak terdapat properti waktu dengan penggunaan WebClient.

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?

   → ResponseEntity merepresentasikan seluruh respons HTTP: kode status, header, dan isi sehingga ia dapat digunakan untuk mengonfigurasi respons HTTP sepenuhnya. Jika kita ingin menggunakannya, kita harus me-return-nya dari end point dan Spring akan mengurus sisanya.
   
   BindingResult merupakan objek Spring yang menyimpan hasil validation dan binding, serta berisi error-error yang mungkin terjadi. BindingResult harus muncul tepat setelah objek model yang divalidasi atau Spring akan gagal memvalidasi objek dan melempar exception. 

**Referensi:**
- [Postman](https://antares.id/id/postman.html#:~:text=Terima%20Data%20(Get)-,Pendahuluan,API%20yang%20telah%20mereka%20buat.)
- [Postman](https://medium.com/@novancimol12/postman-4f181d625fe1)
- [Jackson Annotations - @JsonIgnoreProperties](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonignoreproperties.htm)
- [Jackson Annotations - @JsonProperty](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonproperty.htm)
- [Using Spring ResponseEntity to Manipulate the HTTP Response](https://www.baeldung.com/spring-response-entity)
- [What is the use of BindingResult interface in spring MVC?](https://stackoverflow.com/questions/10413886/what-is-the-use-of-bindingresult-interface-in-spring-mvc/36715053)


### What I did not understand
- [ ] banyak T_T



---
## Tutorial 4
### Pertanyaan
1. Jelaskan perbedaan th:include dan th:replace!

   → Jika menggunakan th:include, fragment akan diletakkan di dalam tag yang melingkupi th:include tersebut. Sedangkan, apabila menggunakan th:replace, seluruh isi tag akan digantikan dengan fragment.

2. Jelaskan apa fungsi dari th:object!

   → th:object berfungsi untuk menjadikan atributnya sebagai object dari operasi-operasi di bawahnya. Sebagai contoh, dengan penulisan syntax th:object="${bioskop}", maka bioskop dapat digunakan sebagai object pada operasi selanjutnya hanya dengan menambahkan tanda bintang di depan atribut yang ingin digunakan.
    ```
    e.g. < span th:text=" * {namaBioskop}">GCV< /span>
    ```

3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?

   → Sintax asterisk (*) mengevaluasi ekspresi pada objek yang dipilih dan bukan pada keseluruhan konteks. Sedangkan, syntax dollar ($) membutuhkan konteks yang utuh dari objek yang dipilih. Berikut contoh pemakaian pemakaian kedua syntax.
    ```
    < div th:object = "$ {bioskop}" class="box">
        < p>< b>Nama Bioskop< /b> < span th:utext="* {namaBioskop}">< /span>< /p>
        < p>< b>Email:< /b> < span th:utext="$ {bioskop.noBioskop}">< /span>< /p>
    < /div>
    ```
   Titik (dot) pada ekspresi dengan syntax dollar digunakan untuk merujuk pada atribut/metode objek yang dipilih pada th:object. Pada ekspresi ${bioskop.noBioskop}, ekspresi tersebut mencoba menemukan objek model bioskop dan memanggil bioskop.getNoBioskop().


### What I did not understand
- [ ] Implementasi dynamic field pada thymeleaf



---
## Tutorial 3
### What I have learned today
### Pertanyaan
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model 
   (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)

   - @AllArgsConstructor → Meng-generate constructor untuk semua class field.
   - @NoArgsConstructor → Meng-generate constructor tanpa parameter.
   - @Setter → Secara otomatis Lombok akan generate default method setter untuk tiap atribut object.
   - @Getter → Secara otomatis Lombok akan menghasilkan default method getter untuk tiap atribut object.
   - @Entity → Mendefinisikan class sebagai class entity yang akan dipetakan sebagai table di database.
   - @Table → Digunakan untuk penamaan table/costum penamaan.

2. Pada class BioskopDB, terdapat method findByNoBioskop, apakah kegunaan dari method tersebut?

   → Mencari dan mengembalikan object Bioskop yang ada pada database BioskopDB berdasarkan nomor bioskopnya.

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn

   Anotasi @JoinTable melakukan pengiriman id antar entitas ke table yang berbeda. Anotasi ini berfungsi untuk mengatur relasi antar entitas di tabel yang berbeda serta melakukan normalisasi database (meminimalisir redundansi).
   Sedangkan, anotasi @JoinColumn melakukan pengiriman id antar entitas di kolom yang baru pada table yang sama. Anotasi ini berguna untuk meningkatkan performa dan digunakan ketika terdapat hubungan langsung atau direct relationship seperti foreign key antar 2 entitas.

4. Pada class PenjagaModel, digunakan anotasi @JoinColumn pada atribut bioskop, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull
   
   Dalam anotasi @JoinColumn, name, referencedColumnName, dan nullable pada PenjagaModel digunakan sebagai:
   - name → Nama dari kolom foreign key. Pada atribut bioskop dalam class PenjagaModel, kolom foreign key nya bernama "no_bioskop".
   - referencedColumnName → Nama kolom yang dirujuk kolom foreign key. Pada konteks ini, kolom foreign key yang dirujuk merupakan artribut noBioskop dari BIOSKOP
   - nullable → Memberikan nilai boolean untuk suatu foreign key apakah bisa null atau tidak. Digunakan pada parameter atau return value yang dapat berupa null. Value nullable pada anotasi atribut bioskop adalah false sehingga menjadi constraint bahwa atribut bioskop tidak boleh null atau kosong, dimana berarti tiap penjaga pasti berada di suatu bioskop.
   
   Perbedaan nullable dan anotasi @NotNull menciptakan hasil atau memiliki fungsi yang serupa, namun terdapat 3 perbedaan utama di antara keduanya, yakni:
   - Spesifikasi yang mendefinisikan anotasi dan dependensi yang diperlukan.
     Anotasi @Column (dimana nullable adalah salah satu elemennya) merupakan bagian dari spesifikasi JPA dan dengan JPA, user sudah menggunakan semua dependensi yang diperlukan. Sedangkan, anotasi @NotNull ditentukan oleh spesifikasi BeanValidation dan user perlu menambahkan dependency ke proyek Hibernate Validator atau ke implementasi lain dari spesifikasi BeanValidation.
   - Sistem yang melakukan pengecekan
     Anotasi @NotNull memicu validasi oleh implementasi BeanValidation, dimana validasi terjadi di dalam aplikasi Java ketika pra-update atau pre-persist lifecycle event dipicu. Sedangkan, pada atribut yang diberikan anotasi @Column(nullable = false), Hibernate tidak melakukan validasi apa pun. Anotasi ini hanya menambahkan constraint bukan null ke kolom database jika Hibernate membuat definisi tabel database dan anotasi @Column(nullable = false) tidak berpengaruh jika bukan Hibernate yang mendefinisikan tabel.
   - Waktu ketika pemeriksaan dilakukan.
     Anotasi @NotNull memberi tahu implementasi BeanValidation untuk memeriksa apakah atributnya bukan null. Hal ini terjadi ketika peristiwa pra-update atau pre-persist lifecycle event diproses. Jika validasi gagal, Hibernate tidak akan mengeksekusi statement SQL apa pun. Sedangkan, anotasi @Column(nullable = false) hanya menambahkan batasan not null ke definisi tabel. Hibernate atau framework lainnya tidak akan melakukan validasi apa pun pada atribut entitas. Hibernate hanya mengeksekusi statement SQL UPDATE dan database akan memvalidasi constraint. Jika atribut entitas adalah null, statement SQL akan gagal.
   
   source: [Hibernate Tips: What’s the difference between @Column(nullable = false) and @NotNull](https://thorben-janssen.com/hibernate-tips-whats-the-difference-between-column-nullable-false-and-notnull/)

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER

   - FetchType.LAZY → Digunakan jika user ingin melakukan fetch ketika ia membutuhkan datanya. FetchType ini biasanya digunakan untuk relasi one-to-many atau many-to-many.
   - CascadeType.ALL → Digunakan jika user ingin data yang akan di-fetch sudah ada saat ia membutuhkannya sehingga ia melakukan fetch dari seawal mungkin. FetchType ini biasanya digunakan untuk relasi many-to-one atau one-to-one.
   - FetchType.EAGER → Digunakan agar semua perubahan yang terjadi pada suatu entitas terjadi juga pada entitas yang memiliki relasi dengannya (atribut foreign key). Perubahan ini dapat terjadi karena DELETE, UPDATE, dsb.



---
## Tutorial 2
### What I have learned today
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

5. Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

    Setelah menambahkan bioskop lain, ketika mengakses link di atas akan muncul daftar seluruh bioskop yang telah didaftarkan beserta dengan detailnya, yaitu Bioskop PAPA APAP dan Bioskop MAUNG.
    ![Screenshot Pertanyaan 5](/images/T1.png)


### What I did not understand
- [ ] Pemakaian GitHub secara maksimal
- [ ] Mana logic yang harus diselesaikan di Service dan mana yang di Controller



---
## Tutorial 1
### What I have learned today
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