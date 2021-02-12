package com.itmo.mibsystem.controllers;

import com.itmo.mibsystem.ConfProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DistributeTechnologyController {
    public static WebDriver driver;
    @BeforeAll
    public static void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));

        Login();
    }

    @Test
    void checkCreateSellTechnologyDocument() throws InterruptedException {
/*
        WebElement buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();
*/

        WebElement buttonSellTechnologyDocument = driver.findElement(By.name("addsellTech"));
        WebElement editTechnology = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[2]/select"));
        WebElement editCostForOne = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editCount = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[4]/td[2]/input"));
        WebElement editTypeContract = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editAlien = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[6]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[8]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[9]/td[2]/input"));

        buttonSellTechnologyDocument.click();
        editTechnology.sendKeys("rahtaebdfd");
        editCostForOne.sendKeys("10 rub");
        editCount.sendKeys("2");
        editTypeContract.sendKeys("338");
        editAlien.sendKeys("FSFA");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        Thread.sleep(1000);

        WebElement buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[4]"));
            if(key.getText().equals("testDescription")){
                prov = true;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkDeleteSellTechnologyDocument() throws InterruptedException {
        WebElement buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[4]"));
            if(key.getText().equals("testDescription")){
                WebElement deleteButton = table.get(i).findElement(By.name("deleteselltech"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div/div[10]/div/form/table/tbody/tr[2]/td[2]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        Thread.sleep(1000);

        buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();

        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
        prov = true;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[4]"));
            if(key.getText().equals("testDescription")){
                prov = false;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkUpdateSellTechnologyDocument() throws InterruptedException {
        WebElement buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
        boolean prov = false;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[4]"));
            if(key.getText().equals("testDescription")){
                WebElement deleteButton = table.get(i).findElement(By.name("editsellTech"));
                deleteButton.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        WebElement editTechnology = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[2]/td[2]/select"));
        WebElement editCostForOne = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editCount = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[4]/td[2]/input"));
        WebElement editTypeContract = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editAlien = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[6]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[8]/td/textarea"));
        WebElement buttonEdit = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[9]/td[2]/input"));


        editTechnology.sendKeys("sdfdbdgqad");
        editCostForOne.clear();
        editCostForOne.sendKeys("11 rub");
        editCount.clear();
        editCount.sendKeys("3");
        editTypeContract.sendKeys("339");
        editAlien.sendKeys("bfdnd");
        editDescription.clear();
        editDescription.sendKeys("testDescription2");
        buttonEdit.click();

        Thread.sleep(1000);

        buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabSellTechnologyDocument.click();

        prov = false;
        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[4]"));
            if(key.getText().equals("testDescription2")){
                prov = true;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkCreateBuyTechnologyDocument() throws InterruptedException {
        WebElement buttonBuyTechnologyDocument = driver.findElement(By.name("addBuyTech"));
        WebElement editTechnology = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editCount = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editPaymentType = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editDeliveryType = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[7]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[8]/td[2]/input"));

        buttonBuyTechnologyDocument.click();
        editTechnology.sendKeys("testTechnology");
        editCount.sendKeys("12");
        editPaymentType.sendKeys("118");
        editDeliveryType.sendKeys("228");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        Thread.sleep(1000);

        WebElement buttonTabBuyTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[2]"));
        buttonTabBuyTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testTechnology")){
                prov = true;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkDeleteBuyTechnologyDocument() throws InterruptedException {
        WebElement buttonTabSellTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[2]"));
        buttonTabSellTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testTechnology")){
                WebElement deleteButton = table.get(i).findElement(By.name("deleteBuyTech"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div/div[9]/div/form/table/tbody/tr[2]/td[2]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        Thread.sleep(1000);

        WebElement buttonTabBuyTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[2]"));
        buttonTabBuyTechnologyDocument.click();

        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        prov = true;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testTechnology")){
                prov = false;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkViewBuyTechnologyDocument() throws InterruptedException {
        String technologyName, deliveryType, paymentType, description, technologyName2, deliveryType2, paymentType2, description2;

        WebElement buttonTabBuyTechnologyDocument = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[2]"));
        buttonTabBuyTechnologyDocument.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        technologyName = table.get(table.size() - 1).findElement(By.xpath("./td[1]")).getText();
        deliveryType = table.get(table.size() - 1).findElement(By.xpath("./td[2]")).getText();
        paymentType = table.get(table.size() - 1).findElement(By.xpath("./td[3]")).getText();
        description = table.get(table.size() - 1).findElement(By.xpath("./td[4]")).getText();

        WebElement viewButton = table.get(table.size() - 1).findElement(By.name("viewBuyTech"));
        viewButton.click();

        WebElement editTechnologyName = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editDeliveryType = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[4]/td[2]/input"));
        WebElement editPaymentType = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[5]/td[2]/input"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[7]/td/textarea"));

        technologyName2 = editTechnologyName.getAttribute("value");
        deliveryType2 = editDeliveryType.getAttribute("value");
        paymentType2 = editPaymentType.getAttribute("value");
        description2 = editDescription.getAttribute("value");

        WebElement closeButton = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[8]/td/input"));
        closeButton.click();

        assertEquals(technologyName, technologyName2.replace("  ", " "));
        assertTrue(deliveryType.equals(deliveryType2));
        assertTrue(paymentType.equals(paymentType2));
        assertTrue(description.equals(description2));
    }

    @Test
    void checkCreateDistributeTechnologyItem() {
        WebElement buttonBuyDistributeTechnologyItem = driver.findElement(By.name("addDistributeTechnologyItem"));
        WebElement editUse = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editCount = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editTechnology = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editAgent = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[7]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/div[6]/form/table/tbody/tr[8]/td[2]/input"));

        buttonBuyDistributeTechnologyItem.click();
        editUse.sendKeys("testUse");
        editCount.sendKeys("15");
        editTechnology.sendKeys("rahtaebdfd");
        editAgent.sendKeys("k3 k3");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[6]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testUse")){
                prov = true;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkDeleteDistributeTechnologyItem() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[6]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testUse")){
                WebElement deleteButton = table.get(i).findElement(By.name("deleteDistributeTechnologyItem"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div/div[11]/div/form/table/tbody/tr[2]/td[2]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[6]/div[2]/table//tr"));
        prov = true;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testUse")){
                prov = false;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkUpdateDistributeTechnologyItem() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[6]/div[2]/table//tr"));
        boolean prov = false;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testUse")){
                WebElement deleteButton = table.get(i).findElement(By.name("editDistributeTechnologyItem"));
                deleteButton.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        WebElement editUse = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editCount = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editTechnology = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editAgent = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[7]/td/textarea"));
        WebElement buttonEdit = driver.findElement(By.xpath("/html/body/div/div[7]/form/table/tbody/tr[8]/td[2]/input"));

        editUse.clear();
        editUse.sendKeys("testUse2");
        editCount.clear();
        editCount.sendKeys("16");
        editTechnology.sendKeys("sdfdbdgqad");
        editAgent.sendKeys("k2 k2");
        editDescription.clear();
        editDescription.sendKeys("testDescription2");
        buttonEdit.click();

        prov = false;
        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[6]/div[2]/table//tr"));
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testUse2")){
                prov = true;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkViewBuyTechnologyMarket() {
        String technologyName,  technologyName2;

        WebElement buttonTabPassport = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[3]"));
        buttonTabPassport.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[5]/div[2]/table//tr"));
        technologyName = table.get(table.size() - 1).findElement(By.xpath("./td[1]")).getText();

        WebElement buyButton = table.get(table.size() - 1).findElement(By.name("addBuyTech"));
        buyButton.click();

        WebElement editTechnologyName = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[2]/td[2]/input"));

        technologyName2 = editTechnologyName.getAttribute("value");

        WebElement closeButton = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[8]/td[1]/input"));
        closeButton.click();

        assertTrue(technologyName.equals(technologyName2));
    }

    static void Login() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("DistributeTechnologyLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));

        button.click();
    }
}
