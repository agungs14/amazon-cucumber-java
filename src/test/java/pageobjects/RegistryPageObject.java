package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class RegistryPageObject {
    WebDriver driver;
    public RegistryPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }
    public String getRegistryText(){
        return driver.findElement(By.xpath("//div[@class='gr-header gr-header--lg']")).getText();
    }

}
