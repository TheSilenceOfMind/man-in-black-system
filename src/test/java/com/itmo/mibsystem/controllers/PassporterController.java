package com.itmo.mibsystem.controllers;

import com.itmo.mibsystem.ConfProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PassporterController {
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
    void checkCreatePassport() {
        WebElement buttonCreatePassport = driver.findElement(By.name("add"));
        WebElement editName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editHomePlanet = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editIdRace = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[6]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id=\"add-modal\"]/form/table/tbody/tr[7]/td[2]/input"));

        buttonCreatePassport.click();
        editName.sendKeys("testName");
        editHomePlanet.sendKeys("testHomePlanet");
        editIdRace.sendKeys("456");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName")){
                prov = true;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkDeletePassport() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName")){
                WebElement deleteButton = table.get(i).findElement(By.name("delete"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div[3]/div/form/table/tbody/tr[2]/td[2]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        table = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/table//tr"));
        prov = true;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName")){
                prov = false;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkUpdatePassport() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/table//tr"));
        boolean prov = false;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName")){
                WebElement deleteButton = table.get(i).findElement(By.name("edit"));
                deleteButton.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        WebElement editName = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editHomePlanet = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editIdRace = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/table/tbody/tr[6]/td/textarea"));
        WebElement buttonEdit = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/table/tbody/tr[7]/td[2]/input"));

        editName.clear();
        editName.sendKeys("testName2");
        editHomePlanet.clear();
        editHomePlanet.sendKeys("testHomePlanet2");
        editIdRace.sendKeys("789");
        editDescription.clear();
        editDescription.sendKeys("testDescription2");
        buttonEdit.click();

        prov = false;
        table = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/table//tr"));
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName2")){
                prov = true;
                break;
            }
        }

        assertTrue(prov);
    }

    static void Login() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("passporterLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));

        button.click();
    }

}
