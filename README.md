# Uçuş Rezervasyon Otomasyonu - README

Bu README dosyası, Gauge framework kullanılarak uçuş rezervasyonu ve zamanlama bilgileri için geliştirilen otomasyon çerçevesinin genel bir özetini sunar. Uçuş planlama otomasyon süreçlerine ilişkin ana işlevler, yapılandırmalar ve test senaryolarını içerir.

## Proje Genel Bakış

Bu proje, uçuş rezervasyon sisteminin çeşitli yönlerini otomatikleştirmeyi amaçlamaktadır ve özellikle uçuş planlama, zamanlama doğrulama ve rezervasyon işlevlerine odaklanmaktadır. Gauge framework'ü, uçuş işlemlerinin otomasyonunu etkinleştiren esnek ve kolay bir test yapısı sağlar.

### Temel Özellikler

1. **Kalkış Noktası Doğrulaması**  
   Kullanıcı tarafından girilen kalkış noktasının, sistemdeki uçuş seçenekleriyle eşleştiğinden emin olur.

2. **Gidiş-Dönüş Seçimi Otomasyonu**  
   Gidiş-dönüş rezervasyon seçeneklerini otomatikleştirir; test senaryoları ile her iki yönde de rezervasyon doğrulaması yapar.

3. **Varış Noktası Doğrulaması**  
   Kullanıcı seçimi ile varış noktasının tutarlılığını ve doğruluğunu kontrol eder.

4. **Kalkış ve Varış Saatleri Doğrulaması**  
   Tüm uçuşlar için kalkış ve varış saatlerinin doğru görüntülendiğini doğrular; sistemdeki zaman bilgilerini test eder.

5. **Saat Kontrolü Otomasyonu**  
   Uçuş saatlerinin doğru ve güncel olup olmadığını doğrulamak için test senaryoları içerir.

6. **Uçuş Rezervasyon Doğrulaması**  
   Uçuş rezervasyon adımlarının doğru şekilde çalıştığını test eden senaryolar içerir.

### Kullanılan Araç ve Teknolojiler

- **Dil**: Java
- **Framework**: Gauge
- **Bağımlılık Yönetimi**: Maven
- **Sürekli Entegrasyon**: Git

### Test Senaryoları

1. **Uçuş Ara ve Seç Testi** - Kullanıcı tarafından belirlenen kriterlere göre uçuş arama ve seçme işlemini doğrular.
2. **Gidiş-Dönüş Seçimi Testi** - Gidiş-dönüş seçeneklerinin doğru çalıştığını doğrular.
3. **Kalkış ve Varış Saatleri Testi** - Kalkış ve varış saatlerinin doğru görüntülendiğini kontrol eder.
4. **Zamanlama Doğrulama Testi** - Tüm zaman bilgilerinin doğru ve güncel olduğundan emin olur.
5. **Uçuş Rezervasyon Doğrulama Testi** - Rezervasyon sürecinin tüm adımlarını doğrular ve kullanıcı dostu bir deneyim sağlar.


