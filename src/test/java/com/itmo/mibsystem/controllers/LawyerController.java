package com.itmo.mibsystem.controllers;

import com.itmo.mibsystem.ConfProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LawyerController {
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
    void checkCreateEarthDocument() {
        WebElement buttonEarthDocument = driver.findElement(By.name("add"));
        WebElement editEarthName = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editNation = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[3]/td[2]/select"));
        WebElement editTypeDocument = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editAlien = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[7]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[8]/td[2]/input"));

        buttonEarthDocument.click();
        editEarthName.sendKeys("testEarthName");
        editNation.sendKeys("222");
        editTypeDocument.sendKeys("888");
        editAlien.sendKeys("FSFA");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[2]"));
            if(key.getText().equals("testEarthName")){
                prov = true;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkDeleteEarthDocument() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[2]"));
            if(key.getText().equals("testEarthName")){
                WebElement deleteButton = table.get(i).findElement(By.name("delete"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div/div[6]/div/form/table/tbody/tr[2]/td[2]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        prov = true;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[2]"));
            if(key.getText().equals("testEarthName")){
                prov = false;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkUpdateEarthDocument() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        boolean prov = false;

        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[2]"));
            if(key.getText().equals("testEarthName")){
                WebElement deleteButton = table.get(i).findElement(By.name("edit"));
                deleteButton.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        WebElement editEarthName = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editNation = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[3]/td[2]/select"));
        WebElement editTypeDocument = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[4]/td[2]/select"));
        WebElement editAlien = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[5]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[7]/td/textarea"));
        WebElement buttonEdit = driver.findElement(By.xpath("/html/body/div/div[4]/form/table/tbody/tr[8]/td[2]/input"));

        editEarthName.clear();
        editEarthName.sendKeys("testEarthName2");
        editNation.sendKeys("333");
        editTypeDocument.sendKeys("777");
        editAlien.sendKeys("bfdnd");
        editDescription.clear();
        editDescription.sendKeys("testDescription2");
        buttonEdit.click();

        prov = false;
        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[2]"));
            if(key.getText().equals("testEarthName2")){
                prov = true;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkViewPassport() {
        String alienName, homePlanet, race, description, alienName2, homePlanet2, race2, description2;

        WebElement buttonTabPassport = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[1]"));
        buttonTabPassport.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
        alienName = table.get(table.size() - 1).findElement(By.xpath("./td[1]")).getText();
        homePlanet = table.get(table.size() - 1).findElement(By.xpath("./td[2]")).getText();
        race = table.get(table.size() - 1).findElement(By.xpath("./td[3]")).getText();
        description = table.get(table.size() - 1).findElement(By.xpath("./td[4]")).getText();

        WebElement viewButton = table.get(table.size() - 1).findElement(By.name("view"));
        viewButton.click();

        WebElement editAlienName = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editHomePlanet = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editRace = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[4]/td[2]/select"));
        Select s = new Select(editRace);
        WebElement editRaceText = s.getFirstSelectedOption();
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[6]/td/textarea"));

        alienName2 = editAlienName.getAttribute("value");
        homePlanet2 = editHomePlanet.getAttribute("value");
        race2 = editRaceText.getText();
        description2 = editDescription.getAttribute("value");

        WebElement closeButton = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[7]/td/input"));
        closeButton.click();

        assertTrue(alienName.equals(alienName2));
        assertTrue(homePlanet.equals(homePlanet2));
        assertTrue(race.equals(race2));
        assertTrue(description.equals(description2));

    }

    static void Login() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("LawyerLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));

        button.click();
    }
}
