package com.itmo.mibsystem.controllers;

import com.itmo.mibsystem.ConfProperties;
import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppController {
    public static WebDriver driver;
    @BeforeAll
    public static void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    void checkLoginTrue() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("trueLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));

        button.click();
        try {
            userName = driver.findElement(By.name("username"));
        }
        catch (NoSuchElementException e) {
            userName = null;
        }

        assertNull(userName);
    }

    @Test
    void checkLoginFalseLogin() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("researcherLogin"));
        password.sendKeys(ConfProperties.getProperty("truePassword"));
        button.click();

        try {
            userName = driver.findElement(By.name("username"));
        }
        catch (NoSuchElementException e) {
            userName = null;
        }

        assertNotNull(userName);
    }

    @Test
    void checkLoginFalsePassword() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("trueLogin"));
        password.sendKeys(ConfProperties.getProperty("falsePassword"));
        button.click();

        try {
            userName = driver.findElement(By.name("username"));
        }
        catch (NoSuchElementException e) {
            userName = null;
        }

        assertNotNull(userName);
    }

    @Test
    void checkLoginFalseLoginAndPassword() {
        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//input[@class='field w-100']"));

        userName.sendKeys(ConfProperties.getProperty("falseLogin"));
        password.sendKeys(ConfProperties.getProperty("falsePassword"));
        button.click();

        try {
            userName = driver.findElement(By.name("username"));
        }
        catch (NoSuchElementException e) {
            userName = null;
        }

        assertNotNull(userName);
    }
}
