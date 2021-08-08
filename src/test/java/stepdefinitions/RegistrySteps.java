package stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class RegistrySteps {
    private WebDriver driver;
    @Given("i open the website amazon.com for registry and gifting")
    public void i_open_the_website_amazon_com_for_registry_and_gifting() throws Throwable {
        //open the browser amazon.com
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }

    @And("I go to Registry page")
    public void i_go_to_Registry_page() throws Throwable {
        //navigate to Registry Page
        driver.navigate().to("https://www.amazon.com/registries");
    }

    @And("make sure i am at page registry and gifting")
    public void make_sure_i_am_at_page_registry_and_gifting() throws Throwable {
        //Verify find label "Registry & Gifting"
        Thread.sleep(10000);
        // explicit wait condition
        WebDriverWait w = new WebDriverWait(driver,60);
        // presenceOfElementLocated condition for label "Registry & Gifting"
        WebElement element = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gr-header gr-header--lg']")));
        // get text of element and print label "Registry & Gifting"
        System.out.println("Element present having text:" + element.getText());
    }

    @When("i find registry with name john and gift list type: birthday gift")
    public void i_find_registry_with_name_john_and_gift_list_type_birthday_gift() throws Throwable {
        //Input Registry name john
        String name = "john";
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
        //Select a registry or gift list type : Birthday Gift List
        Thread.sleep(10000);
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='searchUrl']")));
        dropdown.selectByVisibleText("Birthday Gift List");
        driver.findElement(By.xpath("//*[@id=\"gr-find-stripe__form\"]/div/div[4]/span/select/option[4]")).click();
        Thread.sleep(10000);
        //Click Button Search
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(10000);
    }

    @And("make sure the list result for john")
    public void make_sure_the_list_result_for_john() throws Throwable {
        //validate search result for john
        WebElement john = driver.findElement(By.xpath("//span[@class='current-search-name-slot']"));
        String text = john.getText();
        System.out.println("Search Result for: " + text);
        if (text.contains("john"))
        {
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }

    @When("^i search criteria by range month from january (\\d+) to april (\\d+)$")
    public void i_search_criteria_by_range_month_from_january_to_april(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^make sure the search result is an accordance with the specific time range$")
    public void make_sure_the_search_result_is_an_accordance_with_the_specific_time_range() throws Throwable {
        // Write code here that turns the phrase ab
        throw new PendingException();
    }

}
