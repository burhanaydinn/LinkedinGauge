package com.linkedin.imp;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.linkedin.driver.BaseTest;
import com.linkedin.methods.Methods;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImp extends BaseTest {
    protected BaseTest baseTest;
    Methods methods = new Methods();
    public List<WebElement> list;
    protected WebElement webElement;
    public By by;

    public StepImp() {
        baseTest = new BaseTest();
    }

    @Step("<key> rastgele elemente tıklanır.")
    public void randomTikla(String key) {
        methods.randomElementClicker(methods.getElements(key));
    }

    @Step("<key> 'li elemente tıklanır.")
    public void click(String keyword) {
        methods.click(keyword);
    }

    @Step("<key> 'li elemente görünür ise  tıklanır.")
    public void clickIfExist(String keyword) {
        methods.clickIfExist(keyword);
    }

    @Step("<second> saniye kadar beklenir.")
    public void waitForsecond(int second) throws InterruptedException {
        methods.waitBySeconds(second);
    }

    @Step("<key> 'li elemente <text> degeri girilir.")
    public void sendKeys(String keyword, String text) {
        methods.sendKeys(keyword, text);
    }

    @Step("Yeni sekmeye geçilir.")
    public void testNewTab(){
        //driver.findElement(By.xpath("//span[text()='Yeterlilik belgesini göster']")).click();
        methods.findElement("CertificateOfCompetencyButton").click();
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        //driver.get("https://www.linkedin.com/in/test-hesab%C4%B1-testinium-69008224a/"); ---> url adresine gidilir.
        //assertTrue(driver.findElement(By.id("__qr_code__")).isDisplayed());
    }

    @Step("Sayfada aşağı kaydırma yapılır.")
    public void pageScroll() throws InterruptedException {
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,800)");
    }

    @Step("<key> elementinin üzerinde durulur.")
    public void mouseHover(String keyword) {
        methods.mouseHover(keyword);
    }

    @Step("Bir önceki sayfaya gidilir.")
    public void goBackPage() {
        methods.goBackPage();
    }

    @Step("Sayfayı yeniden yükler.")
    public void refreshPage() {methods.refreshPage();
    }

    @Step("Bir sonraki sayfaya gidilir.")
    public void goForwardPage() {
        methods.goForwardPage();
    }

    @Step("<key> adresine gidilir.")
    public void goToUrl(String url) {
        methods.goToUrl(url);
    }

    @Step("elementin <key> , <text> texti seçilir.")
    public void selectByText(String keyword, String text) {
        methods.selectByText(keyword, text);
    }

    @Step("Fare <key> elementinin üzerinde <x>,<y> kadar hareket ettirilir.")
    public void mouseMove(String keyword, int x, int y) {
        methods.mouseMoveOnElement(keyword, x, y);
    }

    @Step("Fare <x>,<y> kadar hareket ettirilir.")
    public void mouseMove( int x, int y) {
        methods.mouseMove( x, y);
    }

    @Step("Favorilerde olan en pahalı ürün ile alışverişe devam edilir.")
    public void getHighestPriceAndListOfElementSizeInFavorites(){
        methods.getListOfElementsSizeInFavorites();
        methods.deleteAllProductsExceptHighestPriceInFavorites();
    }
}
