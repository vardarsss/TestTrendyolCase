package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import driver.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Steps  {
    @Given("Trendyol Ana Sayfa Gidilir")
    public void trendyolAnaSayfaGidilir() {
        Driver.getDriver().get(ConfigReader.getProperty("TrendyolURL"));
    }

    @Given("Reklam Kapatılır")
    public void reklamKapatılır() {
        Driver.getDriver().findElement(By.id("Rating-Review")).click();
    }
    @Given("Giris Yap Butonu tıklanır")
    public void girisYapButonuTıklanır() {
        Driver.getDriver().findElement(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[1]")).click();
    }

    @Given("Email olarak {string} girilir")
    public void emailOlarakGirilir(String arg0) {
        Driver.getDriver().findElement(By.id("login-email")).sendKeys(arg0);
    }

    @Given("Şifre olarak {string} girilir")
    public void şifreOlarakGirilir(String arg0) {
        Driver.getDriver().findElement(By.id("login-password-input")).sendKeys(arg0);
    }

    @Given("Giris Yap tıklanır")
    public void girisYapTıklanır() {
        Driver.getDriver().findElement(By.xpath("//*[@id=\"login-register\"]/div[3]/div[1]/form/button/span")).click();
    }

    @Given("Giris Yapıldığı kontrol edilir")
    public void girisYapıldığıKontrolEdilir() throws InterruptedException {

        Thread.sleep(3000);
        Assert.assertEquals("Hesabım", Driver.getDriver().findElement(By.className("link-text")).getText());
        Driver.closeDriver();
    }
    @Then("Urun Arama çubuğunda aratılır")
    public void aramaÇubuğundaAratılır() throws InterruptedException {

        List <String> urunListesi=new ArrayList<String>();

        Response response = given().accept("application/JSON").when().get("https://www.omdbapi.com/?s=Batman&page=2&apikey=b90b5808");


        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        List<Map<String, Object>> Search = response.jsonPath().getList("Search");
        for (Map<String, Object> object : Search) {
            System.out.println(object.get("Title"));
            String a= (String) object.get("Title");
            Driver.getDriver().findElement(By.className("search-box")).clear();

            Driver.getDriver().findElement(By.className("search-box")).sendKeys(a+ Keys.ENTER);
            Thread.sleep(3000);
            Assert.assertEquals(a, Driver.getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div[1]/div[2]/div[1]/div[1]/div/h1")).getText());
        }
    }
}
