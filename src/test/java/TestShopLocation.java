import com.checkfill.ELocation;
import com.checkfill.LocationDetails;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestShopLocation {


    @Before
    public void initializeDriverPath() {

        String exePath = "driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
    }

    /*
    Just testing Assert functions
     */
    @Test
    public void searchLocation() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.chick-fil-a.com");
        String homeUrl = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/a")).getAttribute("href");
        assertEquals(homeUrl, "https://www.chick-fil-a.com/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"find-location\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"location-search\"]")).sendKeys("New York");
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div[1]/div[2]/div[2]/form/button")).click();
        driver.switchTo().frame("locatoriframe");
        //Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/ol/li[1]/article/div[2]/div[1]/div[2]/a")).click();
        String Address = driver.findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[1]/p[1]")).getText();
        assertEquals(Address, "144 Fulton St, New York, NY 10038");
    }

    /*
    Testing the whole solution
     */
    @Test
    public void searchForMultipleLocations() throws Exception {

        try {
            FileWriter writer = new FileWriter("filelocation/locations.csv", true);//Please put a file location
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.chick-fil-a.com");
            LocationDetails locationDetails = new LocationDetails();
            List<String> locationValues = new ArrayList<String>();
            for (ELocation eLocation : ELocation.values()) {


                driver.findElement(By.xpath("//*[@id=\"find-location\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"location-search\"]")).sendKeys(eLocation.locName);
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div[1]/div[2]/div[2]/form/button")).click();
                driver.switchTo().frame("locatoriframe");
                //Thread.sleep(1000);
                WebDriverWait wait2 = new WebDriverWait(driver, 10);
                wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/ol/li[1]/article/div[2]/div[1]/div[2]/a")));
                driver.findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/ol/li[1]/article/div[2]/div[1]/div[2]/a")).click();
                String Address = driver.findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[1]/p[1]")).getText();

                String[] addresses = Address.split(",");

                locationDetails.setLocationName(addresses[0]);
                locationDetails.setCity(addresses[1]);
                locationDetails.setState(addresses[2]);
                locationDetails.setPhoneNumber(driver.findElement(By.id("LocationFrameId")).findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[2]/div[1]/div[2]/p")).getText());

                System.out.println("Address : " + locationDetails.getLocationName() + " City : " + locationDetails.getCity() + " State" + locationDetails.getState() + " Phone Number" + locationDetails.getPhoneNumber());

                locationValues.add(locationDetails.getLocationName());
                locationValues.add(locationDetails.getCity());
                locationValues.add(locationDetails.getState());
                locationValues.add(locationDetails.getPhoneNumber());

            }
            String collect = locationValues.stream().collect(Collectors.joining(","));
            System.out.println(collect);
            writer.write(collect + "\n");
            writer.close();


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
