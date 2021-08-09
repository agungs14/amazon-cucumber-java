package stepdefinitions;

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
import org.testng.Assert;

import java.util.List;
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

    @When("i search criteria by range month from january 2021 to april 2021")
    public void i_search_criteria_by_range_month_from_january_to_april() throws Throwable {
        //edit search by criteria by date range month january 2021 to april 2021
        //First edit range date from January 2021
        //edit field month JANUARY
        Select dropdownMonthFr = new Select(driver.findElement(By.id("gr-search-from-month-dropdown")));
        dropdownMonthFr.selectByVisibleText("January");
        driver.findElement(By.xpath("//*[@id=\"gr-search-from-month-dropdown\"]/option[3]")).click();
        //edit field Year 2021
        Select dropdownYearFr = new Select(driver.findElement(By.id("gr-search-from-year-dropdown")));
        dropdownYearFr.selectByVisibleText("2021");
        driver.findElement(By.xpath("//*[@id=\"gr-search-from-year-dropdown\"]/option[5]")).click();
        Thread.sleep(10000);
        //Edit range date to April 2021
        //Edit field month to April
        Select dropdownMonthTo = new Select(driver.findElement(By.id("gr-search-to-month-dropdown")));
        dropdownMonthTo.selectByVisibleText("April");
        driver.findElement(By.xpath("//*[@id=\"gr-search-to-month-dropdown\"]/option[6]")).click();
        //Edit  Field Year to 2021
        Select dropdownYearTo = new Select(driver.findElement(By.id("gr-search-to-year-dropdown")));
        dropdownYearTo.selectByVisibleText("2021");
        driver.findElement(By.xpath("//*[@id=\"gr-search-to-year-dropdown\"]/option[5]")).click();
        Thread.sleep(10000);
        //Click Button "Search for a Birthday Giff list"
        driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
        Thread.sleep(10000);
    }

    @Then("make sure the search result is an accordance with the specific time range")
    public void make_sure_the_search_result_is_an_accordance_with_the_specific_time_range() throws Throwable {
        // Validate the search result is the specific time (Jan 2021 - April 2021)
        WebDriverWait w = new WebDriverWait(driver,60);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search-result-container']/li/div[1]/a")));

        List<WebElement> names = driver.findElements(By.xpath("//*[@id='search-result-container']/li/div[1]/a"));
        List<WebElement> dates = driver.findElements(By.xpath("//div[@class='gr-search-registry-date']"));
        dates.remove(0);
        if (names.size() > 1 && dates.size() > 1) {
            String getTextResult = driver.findElement(By.xpath("//*[@id='search-result-container']/li/div[1]/a")).getText();
            String getDateResult = driver.findElement(By.xpath("//div[@class='gr-search-registry-date']")).getText();
            if (getTextResult.contains("john") || getDateResult.contains("2021")) {
                System.out.println("Name " + getTextResult + " found with total data: " + names.size());
                System.out.println(getDateResult + " text corrected with total data: " + dates.size());
                Assert.assertEquals(names.size() > 1, getTextResult.contains("john"));
            }
        }
    }

}

