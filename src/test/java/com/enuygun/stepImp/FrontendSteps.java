package com.enuygun.stepImp;

import com.thoughtworks.gauge.Step;
import com.enuygun.base.BaseTest;
import com.enuygun.model.ElementInfo;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


   public class FrontendSteps extends BaseTest {
       public FrontendSteps() throws IOException {

       }

    public By getElementInfoBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        }
        return by;
    }

    WebElement findElement(String key) {

        By by = getElementInfoBy(findElementInfoByKey(key));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        return element;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoBy(findElementInfoByKey(key)));
    }

    private void clickTo(WebElement element) {
        element.click();
    }

    private void sendKeysTo(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void javaScriptClickTo(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

    }

    public WebElement findElementWithWait(String key) {
        WebDriverWait wait = new WebDriverWait(driver, 20); // Bekleme süresini örneğin 30 saniyeye çıkarın.
        By by = getElementInfoBy(findElementInfoByKey(key));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by)); // Elementin mevcut olmasını bekleyin.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

       @Step("Enter tusuna bas")
       public void pressEnter() {
           Actions actions = new Actions(driver);
           actions.sendKeys(Keys.RETURN).perform();
           logger.info("Enter tuşuna basıldı.");
       }
    @Step("<key> li elementi bul temizle ve <text> degerini yaz")
    public void sendKeys(String key, String text) {
        WebElement element = findElement(key);
        element.clear();
        sendKeysTo(element, text);
        logger.info("Element bulundu ve yazıldı: Key : " + key + " text : " + text);
    }

    @Step("Elementine tikla <key>")
    public void clickElement(String key) {
        clickTo(findElement(key));
        logger.info(key + " elementine tıklandı.");
    }

    @Step("<int> saniye bekle")
    public void waitSecond(int seconds) throws InterruptedException {
        try {
            logger.info(seconds + " saniye bekleniyor");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementinin disabled oldugunu kotrol et")
    public void checkDisabled(String key) {
        WebElement element = findElement(key);
        Assertions.assertTrue(element.isDisplayed(), " Element disabled değil");
        logger.info(key + " elementi disabled");
    }
    @Step("JavaScript ile <key> elementine tıkla")
    public void clickElementWithJavaScript(String key) {
        WebElement element = findElementWithWait(key);
        javaScriptClickTo(element);
        logger.info("JavaScript ile " + key + " elementine tıklandı.");
    }


    @Step("<key> elementi <expectedText> degerini iceriyor mu kontrol et")
    public void checkElementEqualsText(String key, String expectedText) {

        String actualText = findElement(key).getText();
        logger.info("Element str:" + actualText);
        logger.info("Expected str:" + expectedText);
        Assertions.assertEquals(actualText, expectedText, "Beklenen metni içermiyor " + key);
        logger.info(key + " elementi " + expectedText + " degerine eşittir.");
    }


       @Step("Slider baslangic konumunu <targetPercentage> olarak ayarla")
       public void moveSliderToPercentage(int targetPercentage) {
           WebElement sliderHandle = driver.findElement(By.xpath("//div[@role='slider' and contains(@class, 'rc-slider-handle-1')]"));

           // Slider'ın toplam genişliği (yüzde olarak 100 üzerinden)
           int sliderWidth = driver.findElement(By.cssSelector("div.rc-slider-track.rc-slider-track-1")).getSize().width;

           // Hedef konuma göre pixel olarak kaydırılacak mesafe hesaplanır
           int moveOffset = (sliderWidth * targetPercentage) / 100;

           // Slider'ı hareket ettir
           Actions actions = new Actions(driver);
           actions.clickAndHold(sliderHandle).moveByOffset(moveOffset, 0).release().perform();

           logger.info("Slider başlangıç konumu " + targetPercentage + "% olarak ayarlandı.");
       }

       @Step("İkinci slider handle'ini yuzde <percentage> konumuna kaydir")
       public void moveSecondSliderHandle(int percentage) {
           // İkinci slider handle elementini bulur
           WebElement secondSliderHandle = driver.findElement(By.cssSelector("div.rc-slider-handle.rc-slider-handle-2"));

           // Mevcut konumunu bulur
           String styleAttribute = secondSliderHandle.getAttribute("style");
           String currentLeft = styleAttribute.split("left: ")[1].replace("%;", "").replace("%", "").trim();

           // Hedeflenen konuma kaydırmak için farkı hesaplar
           int currentPosition = (int) Double.parseDouble(currentLeft);
           int offset = percentage - currentPosition;

           // Kaydırma işlemini gerçekleştirir
           Actions actions = new Actions(driver);
           actions.clickAndHold(secondSliderHandle).moveByOffset(offset, 0).release().perform();

           logger.info("İkinci slider handle'ı yüzde " + percentage + " konumuna kaydırıldı.");
       }

       @Step("Turk Hava Yollari ucuslarinin fiyatlarinin artan sirayla listelendigini kontrol et")
       public void checkPriceSortingForTurkishAirlines() {
           List<WebElement> priceElements = driver.findElements(By.cssSelector(".flight-item.ctx-airline-TK .summary-average-price .money-int"));
           List<Integer> prices = new ArrayList<>();

           for (WebElement priceElement : priceElements) {
               String priceText = priceElement.getText().replaceAll("[^0-9]", ""); // TL simgesi ve noktaları kaldır
               int price = Integer.parseInt(priceText);
               prices.add(price);
           }

           List<Integer> sortedPrices = new ArrayList<>(prices);
           Collections.sort(sortedPrices);

           Assertions.assertEquals(sortedPrices, prices, "Türk Hava Yolları uçuş fiyatları artan sırada değil!");
       }
   }







                                     /*
                                     @Step anatosyonu Gauge kütüphanesine ait bir anatosyondur. bunun ile testlerimizde sürekli olarak çağırabileceğimiz
                                     cümlecikler halinde metodlar oluşturuyoruz.
                                     Bu sınıfı BaseTest sınıfı ile extends ediyoruz çünkü BaseTest sınıfında driver nesnesini oluşturuyoruz.
                                     BaseTest de olması gerekenler burada da olmalıdır.
                                     Extend ederek bir başka sınıfın özelliklerini miras alıp kullanabiliriz.

                                     bir sınıf başka bir sınıfı "extend" ettiğinde, temel alınan sınıfın ("superclass" veya "parent class" olarak adlandırılır) tüm halka açık metotları ve özellikleri, türetilen sınıfa ("subclass" veya "child class") aktarılır.
                                     Bu işlem sayesinde, kod tekrarını önlemek ve kodun yeniden kullanılabilirliğini artırmak mümkün olur.

                                     Polimorfizm: Alt sınıflar, üst sınıfın metodlarını kendi ihtiyaçlarına göre "override" edebilir (üzerine yazabilir),
                                     böylece aynı metot adı farklı sınıflarda farklı davranışlar sergileyebilir.
                                      */

