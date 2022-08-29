package com.linkedin.methods;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.linkedin.driver.BaseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Methods extends BaseTest {
    //private final static String ELEMENTS_PATH = "src/test/resources/elements/elements.json";
    private final static String ELEMENTS_PATH = "element";
    protected static WebElement webElement;
    public By by;
    public List<WebElement> list;
    public Map<String, Object> elementsMap;
    JavascriptExecutor jsDriver;
    private String keyword;

    public Methods() {
        jsDriver = (JavascriptExecutor) driver;
        launchElementMap(getFileList());
    }

    public void waitBySeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement findElement(String keyword) {
        by = getByTypeWithMap(keyword);
        return webWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(String keyword) {
        findElement(keyword).click();
    }

    public void sendKeys(String keyword, String text) {
        WebElement element = findElement(keyword);
        element.clear();
        element.sendKeys(text);
    }

    public Select getSelectText(String keyword) {
        return new Select(findElement(keyword));
    }

    public By getByTypeWithMap(String keyword) {
        ElementInfo elements = (ElementInfo) elementsMap.get(keyword);
        Map<String, By> map = launchByMap(elements.getLocatorValue());
        return map.getOrDefault(elements.getLocatorType(), null);
    }

    public Map<String, By> launchByMap(String locatorValue) {
        Map<String, By> map = new HashMap<>();
        map.put("id", By.id(locatorValue));
        map.put("css", By.cssSelector(locatorValue));
        map.put("xpath", By.xpath(locatorValue));
        map.put("class", By.className(locatorValue));
        map.put("linktext", By.linkText(locatorValue));
        map.put("name", By.name(locatorValue));
        map.put("partial", By.partialLinkText(locatorValue));
        map.put("tagname", By.tagName(locatorValue));
        return map;
    }

    public void launchElementMap(File[] fileList) {
        elementsMap = new ConcurrentHashMap<>();
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList;
        for (File file : fileList) {
            try {
                elementInfoList = gson.fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream().forEach(elementInfo -> elementsMap.put(elementInfo.getKeyword(), elementInfo));
            } catch (FileNotFoundException e) {
            }
        }
    }

    public File[] getFileList() {
        File[] fileList = new File(this.getClass().getClassLoader().getResource(ELEMENTS_PATH).getFile()).listFiles(pathname -> !pathname.isDirectory() && pathname.getName().endsWith(".json"));
        if (fileList == null) {
            throw new NullPointerException("Belirtilen dosya bulunamadı.");
        }
        return fileList;
    }

    public void mouseMove(int x, int y) {
        Actions action = new Actions(driver);
        action.moveByOffset(x, y).perform();
    }

    public void selectByText(String keyword, String text) {
        getSelectText(keyword).selectByVisibleText(text);
    }

    public void mouseHover(String keyword) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(keyword)).perform();
    }

    public void mouseHover(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public void mouseMoveOnElement(String keyword, int x, int y) {
        Actions action = new Actions(driver);
        mouseHover(keyword);
        action.moveByOffset(x, y).perform();
    }

    public void mouseMoveOnElement(WebElement webElement, int x, int y) {
        Actions action = new Actions(driver);
        mouseHover(webElement);
        action.moveByOffset(x, y).perform();
    }

    public void goBackPage() {
        driver.navigate().back();
    }

    public void goForwardPage() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public List<WebElement> getElements(String keyword) {
        by = getByTypeWithMap(keyword);
        List<WebElement> l1 = driver.findElements(by);
        return l1;
    }

    public void randomElementClicker(List<WebElement> list) {
        Random rand = new Random();
        list.get(rand.nextInt(list.size())).click();
    }

    public boolean isElementVisible(String keyword) {
        by = getByTypeWithMap(keyword);
        try {
            webWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean isElementVisibleNoWait(String keyword) {
        by = getByTypeWithMap(keyword);
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String getText(String keyword) {
        return findElement(keyword).getText();
    }

    public void clickWithText(String keyword, String text) {
        click(String.valueOf(By.xpath("//" + keyword + "[text()='" + text + "']")));
    }

    public void clickIfExist(String keyword) {
        if (isElementVisible(keyword)) {
            click(keyword);
        }
    }

    public List<WebElement> getProducts(String keyword) {
        by = getByTypeWithMap(keyword);
        List<WebElement> links = driver.findElements(by);
        return links;
    }

    public void scrollWithAction(String keyword) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(keyword)).build().perform();
    }

    public void scrollWithJavaScript(String keyword) {
        jsDriver.executeScript("argument[0].scrollIntoView();", findElement(keyword));
    }

    public void scrollWithAction(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public void getListOfElementsSizeInFavorites() {
        boolean isChecked = false;
        List<WebElement> watchSelect = driver.findElements(By.xpath("//a[@data-dimension58='saat']"));
        int size = watchSelect.size();
        System.out.println("Size of the list:" + size);
        for (int i = 0; i < size; i++) {
            isChecked = watchSelect.get(i).isSelected();
            if (!isChecked) {
                watchSelect.get(i);
                waitBySeconds(1);
            }
        }
    }

    public void moveToElementWithJS(WebElement element) {
        webWait.until(ExpectedConditions.visibilityOf(element));
        getJSExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
    }

    public void deleteAllProductsExceptHighestPriceInFavorites() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='price-container']"));
        List<WebElement> images = driver.findElements(By.xpath("//div[@class='price-container']/parent::div/parent::div//*[@class='product-figure-wrap']"));
        List<WebElement> maxFav = driver.findElements(By.xpath("//div[@class='price-container']/parent::div/parent::div//*[@class='product-figure-wrap']//*[@class='ql-fav']//a[@data-delete-text='Favorilerde']"));
        WebElement maxPrices = findElement("FavProductImage");
        WebElement maxPricesImage = null;
        int enb = 0;
        int price = 0;

        for (int i = 0; i < prices.size(); i++) {
             price = Integer.parseInt(prices.get(i)
                    .getText()
                    .trim()
                    .replace(",", ".")
                    .replace(".", "")
                    .replace("TL", "")
                    .trim()) / 100;
            if (enb < price) {
                enb = price;
                maxPricesImage = images.get(i);
                maxPrices = maxFav.get(i);
            }
            moveToElementWithJS(images.get(i));
            mouseMoveOnElement(images.get(i), 5, 5);
            System.out.println(price);
        }
        System.out.println("en buyuk=" + enb);

        for (int i = 0; i < prices.size(); i++) {
            int priceElement = Integer.parseInt(prices.get(i)
                    .getText()
                    .trim()
                    .replace(",", ".")
                    .replace(".", "")
                    .replace("TL", "")
                    .trim()) / 100;
            if (enb == priceElement) {
                prices.remove(i);
                images.remove(i);
                maxFav.remove(i);
            }
        }

        for (int i = 0; i < prices.size(); i++) {
            moveToElementWithJS(images.get(i));
            mouseMoveOnElement(images.get(i), 5, 5);
            moveToElementWithJS(maxFav.get(i));
            maxFav.get(i).click();
        }

        driver.navigate().refresh();
        moveToElementWithJS(driver.findElement(By.cssSelector("[class='red-v1']")));
        waitBySeconds(4);
    }

    public static class ElementInfo {
        @Getter
        protected String keyword;
        @Getter
        protected String locatorValue;
        @Getter
        protected String locatorType;

        @Override
        public String toString() {
            return "Elements[" + "keyword=" + keyword + ",locatorType=" + locatorType + ",locatorValue=" + locatorValue + "]";
        }
    }
}

