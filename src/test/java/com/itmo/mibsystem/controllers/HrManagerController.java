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
public class HrManagerController {
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
    void checkCreateMIBEmployee() {
        WebElement buttonMIBEmployee = driver.findElement(By.name("add"));
        WebElement editName = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editUsername = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editPassword = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[4]/td[2]/input"));
        WebElement editAge = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[5]/td[2]/input"));
        WebElement editRole = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[6]/td[2]/select"));
        WebElement editCurator = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[7]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[9]/td/textarea"));
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[10]/td[2]/input"));

        buttonMIBEmployee.click();
        editName.sendKeys("testName");
        editUsername.sendKeys("testUsername");
        editPassword.sendKeys("testPassword");
        editAge.sendKeys("10 age");
        editRole.sendKeys("HR");
        editCurator.sendKeys("k4 k4");
        editDescription.sendKeys("testDescription");
        buttonAdd.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));

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
    void checkDeleteMIBEmployee() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));

        boolean prov = false;
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName")){
                WebElement deleteButton = table.get(i).findElement(By.name("delete"));
                deleteButton.click();
                WebElement deleteButton2 = driver.findElement(By.xpath("/html/body/div/div[5]/div/form/table/tbody/tr[2]/td[3]/input"));
                deleteButton2.click();
                prov = true;
                break;
            }
        }

        assertTrue(prov);

        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
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
    void checkUpdateMIBEmployee() {
        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
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

        WebElement editName = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editUsername = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[3]/td[2]/input"));
        WebElement editPassword = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[4]/td[2]/input"));
        WebElement editAge = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[5]/td[2]/input"));
        WebElement editRole = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[6]/td[2]/select"));
        WebElement editCurator = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[7]/td[2]/select"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[9]/td/textarea"));
        WebElement buttonEdit = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[10]/td[2]/input"));

        editName.clear();
        editName.sendKeys("testName2");
        editUsername.clear();
        editUsername.sendKeys("testUsername2");
        editPassword.clear();
        editPassword.sendKeys("testPassword2");
        editAge.clear();
        editAge.sendKeys("11 age");
        editRole.sendKeys("LAWYER");
        editCurator.sendKeys("k5 k5");
        editDescription.clear();
        editDescription.sendKeys("testDescription2");
        buttonEdit.click();

        prov = false;
        table = driver.findElements(By.xpath("/html/body/div/div[1]/div[3]/div[2]/table//tr"));
        for (int i = 1; i < table.size(); i++) {
            WebElement key = table.get(i).findElement(By.xpath("./td[1]"));
            if(key.getText().equals("testName2")){
                prov = true;
                break;
            }
        }

        assertTrue(prov);
    }

    @Test
    void checkViewFreePersona() {
        String agentName, age, description, agentName2, age2, description2;

        WebElement buttonTabPassport = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/button[2]"));
        buttonTabPassport.click();

        List<WebElement> table = driver.findElements(By.xpath("/html/body/div/div[1]/div[4]/div[2]/table//tr"));
        agentName = table.get(table.size() - 1).findElement(By.xpath("./td[1]")).getText();
        age = table.get(table.size() - 1).findElement(By.xpath("./td[2]")).getText();
        description = table.get(table.size() - 1).findElement(By.xpath("./td[5]")).getText();

        WebElement addButton = table.get(table.size() - 1).findElement(By.name("add"));
        addButton.click();


        WebElement editName = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[2]/input"));
        WebElement editAge = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[5]/td[2]/input"));
        WebElement editDescription = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[9]/td/textarea"));

        agentName2 = editName.getAttribute("value");
        age2 = editAge.getAttribute("value");
        description2 = editDescription.getAttribute("value");

        WebElement closeButton = driver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[10]/td[1]/input"));
        closeButton.click();

        assertTrue(agentName.equals(agentName2));
        assertTrue(age.equals(age2));
        assertTrue(description.equals(description2));
    }

    static void Login() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("hpManagerLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));

        button.click();
    }
}
