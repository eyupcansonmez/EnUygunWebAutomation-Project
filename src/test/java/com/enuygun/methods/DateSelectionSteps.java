package com.enuygun.methods;

import com.thoughtworks.gauge.Step;
import com.enuygun.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class DateSelectionSteps extends BaseTest {

    public DateSelectionSteps() throws IOException {
    }


    @Step("Gidis tarihi sec: Gun <day>")
    public void selectDepartureDateByDay(String day) {
        // Gidiş tarihini açmak için etikete tıklar
        clickDepartureDate();

        // Parametrik olarak günü seçer
        selectDayWithJavaScript(day);
    }

    /**
     * Gidiş tarihine tıklar
     */
    private void clickDepartureDate() {
        WebElement departureDateLabel = driver.findElement(By.cssSelector("[data-testid='enuygun-homepage-flight-departureDate-label']"));
        departureDateLabel.click();
    }


    private void selectDayWithJavaScript(String day) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // burada 10, saniye cinsindendir.

        // Elementin görünür olmasını bekler
        WebElement dayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@data-day='" + day + "' and @data-testid='datepicker-active-day']")
        ));

        // JavaScript ile tıklama
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", dayElement);
    }

    @Step("Donus tarihi sec: Gun <returnDay>")
    public void selectReturnDateByDay(String returnDay) {
        // Dönüş tarihi alanını tıklar
        WebElement returnDateButton = driver.findElement(By.cssSelector("[data-testid='enuygun-homepage-flight-returnDate-label']"));
        returnDateButton.click();

        // İlgili günü seçer
        WebElement returnDayElement = driver.findElement(By.cssSelector("button[data-day='" + returnDay + "'][data-testid='datepicker-active-day']"));
        returnDayElement.click();
        logger.info(returnDay + " günü dönüş tarihi olarak seçildi.");
    }

}
