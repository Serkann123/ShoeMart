<h1 align="center">👟 Android Ayakkabı Mağazası Uygulaması</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android" />
  <img src="https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Database-SQLite-003B57?style=for-the-badge&logo=sqlite" />
  <img src="https://img.shields.io/badge/IDE-Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio" />
</p>

<p align="center">
  Android platformunda geliştirilmiş, kullanıcıların ürünleri inceleyip sepete ekleyebildiği, yerel veri tabanı tabanlı pratik bir e-ticaret uygulaması.
</p>

<br>

## 📱 Ekran Görüntüleri

<p align="center">
  <img src="https://github.com/user-attachments/assets/72180574-f773-4a37-aad3-4e0099bf14c6" width="280" alt="Giriş Ekranı">
  <img src="https://github.com/user-attachments/assets/8b20e4ec-0afd-4266-a105-5a6f5cba78e6" width="285" alt="Ürün Listesi">
  <img src="https://github.com/user-attachments/assets/f6adbea1-0b4e-495a-b047-0de3a7a8078c" width="290" alt="Sepete Ekle"/>

  
</p>

---

## 🚀 Proje Hakkında

Bu proje, Android uygulama geliştirme sürecinde **kullanıcı arayüzü oluşturma**, **ekranlar arası geçiş** ve **yerel veri yönetimi** gibi temel konuların pratiğini yapmak amacıyla geliştirilmiştir.

Uygulama içerisinde kullanıcıların kayıt olup giriş yapabildiği, ayakkabı modellerini listeleyebildiği ve sepete ekleyerek toplam tutarı görüntüleyebildiği temel bir alışveriş akışı bulunmaktadır.

### 🎯 Projenin Amacı
Bu çalışma ile aşağıdaki Android kavramlarının öğrenilmesi ve pekiştirilmesi hedeflenmiştir:
* Activity yaşam döngüsü ve Intent yapısı.
* **SQLite** ile yerel veritabanı CRUD işlemleri.
* **RecyclerView** ve Adapter yapısının kullanımı.
* **SharedPreferences** ile basit oturum yönetimi.
* Kullanıcı dostu XML arayüz tasarımı.

---

## ✨ Uygulama Özellikleri

Proje aşağıdaki temel özellikleri içermektedir:

* 🔐 **Kullanıcı İşlemleri:** Kayıt olma ve güvenli giriş ekranı.
* 💾 **Oturum Yönetimi:** `SharedPreferences` kullanılarak beni hatırla/oturum saklama özelliği.
* 📋 **Ürün Listeleme:** `RecyclerView` kullanılarak ürünlerin dikey listede gösterimi.
* 🛒 **Sepet Yönetimi:** Ürünleri sepete ekleme ve dinamik toplam tutar hesaplama.
* ✅ **Sipariş Simülasyonu:** Sepeti onaylama ve siparişi tamamlama akışı.

---

## 🛠 Kullanılan Teknolojiler

Bu projede aşağıdaki teknoloji ve kütüphanelerden yararlanılmıştır:

| Teknoloji | Açıklama |
| :--- | :--- |
| **Java** | Ana programlama dili |
| **Android SDK** | Uygulama geliştirme kiti |
| **SQLite** | Yerel veritabanı yönetimi (Ürün ve Kullanıcı verileri için) |
| **RecyclerView** | Performanslı liste görünümü |
| **SharedPreferences** | Basit veri saklama (Login durumu vb.) |
| **XML** | Kullanıcı arayüzü tasarımı |

---

## 🔄 Uygulama Akışı

1.  **Giriş/Kayıt:** Kullanıcı uygulamayı açar, hesabı yoksa kayıt olur, varsa giriş yapar.
2.  **Liste:** Başarılı giriş sonrası ürünlerin listelendiği ana sayfaya yönlendirilir.
3.  **Sepet:** Beğendiği ürünleri sepete ekler ve sepet sayfasına geçer.
4.  **Özet:** Sepetteki ürünlerin toplam tutarı otomatik hesaplanır.
5.  **Onay:** "Sipariş Ver" butonu ile işlem simüle edilerek tamamlanır.

---

## ⚠️ Not

> Bu proje, **eğitim ve öğrenme amacıyla** geliştirilmiştir. Gerçek bir ödeme sistemi veya canlı sunucu (API) entegrasyonu içermemektedir. Veriler cihaz üzerinde yerel olarak tutulmaktadır.
