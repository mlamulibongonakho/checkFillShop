package com.checkfill;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[]args) throws Exception {

       try{
           LocationDetails locationDetails=new LocationDetails();
           FileWriter writer = new FileWriter("FileLcation/locations.csv");//Please put a file location

           List<String> locationValues = new ArrayList<String>();

           //Please put a chromdriver location
            String exePath = "driverlocation/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", exePath);

            WebDriver driver = new ChromeDriver();
            driver.get("https://www.chick-fil-a.com");

           for(ELocation eLocation : ELocation.values())
           {
               /*
               Because of ([@id="js-yl-12104270"]) in my elements I couldn't make it dynamic enough for all locations
               that are declared on my Enum because that ID it keep on changing .
               but if you do them 1 by 1 it works.
                */

               driver.findElement(By.xpath("//*[@id=\"find-location\"]")).click();
               driver.findElement(By.xpath("//*[@id=\"location-search\"]")).sendKeys(eLocation.locName);
               driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div[1]/div[2]/div[2]/form/button")).click();
               driver.switchTo().frame("locatoriframe");
               //new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[1]/div[2]/a")));
               locationDetails.setLocationName(driver.findElement(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[2]/address/div[1]/span")).getText());
               locationDetails.setCity(driver.findElement(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[2]/address/div[2]/span[1]")).getText());
               locationDetails.setState(driver.findElement(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[2]/address/div[2]/span[1]")).getText());
               driver.findElement(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[1]/div[2]/a")).click();

               locationDetails.setPhoneNumber(driver.findElement(By.id("LocationFrameId")).findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[2]/div[1]/div[2]/p")).getText());
               System.out.println("Address : "+locationDetails.getLocationName() + " City : " +locationDetails.getCity() +" State"+ locationDetails.getState() +" State"+ locationDetails.getPhoneNumber());

               locationValues.add("address" + " "+locationDetails.getLocationName());
               locationValues.add("address" + " "+locationDetails.getCity());
               locationValues.add("address" + " "+locationDetails.getState());
               locationValues.add("address" + " "+locationDetails.getPhoneNumber());

           }

           String collect = locationValues.stream().collect(Collectors.joining(","));
           System.out.println(collect);

           writer.write(collect);
           writer.close();
        }
       catch (Exception e)
       {
           throw new Exception(e.getMessage());
       }
    }
}
