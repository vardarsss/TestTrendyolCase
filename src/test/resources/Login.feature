@Login
Feature: Login Test
  Scenario: Login Positive
    Given Trendyol Ana Sayfa Gidilir
    Given Reklam Kapatılır
    Given Giris Yap Butonu tıklanır
    Given Email olarak "testtrendyol@outlook.com" girilir
    Given Şifre olarak "Vardar!123" girilir
    Given Giris Yap tıklanır
    Given Giris Yapıldığı kontrol edilir


      Scenario:
        Given Trendyol Ana Sayfa Gidilir
        Given Reklam Kapatılır
        Given Giris Yap Butonu tıklanır
        Given Email olarak "testtrendyol@outlook.com" girilir
        Given Şifre olarak "Vardar!123" girilir
        Given Giris Yap tıklanır
        Then  Urun Arama çubuğunda aratılır
