import Utilities.MainPage;
import Utilities.SearchResults;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PedidosYa extends Base{

    //Initializing objects
    private MainPage mainPage;
    private SearchResults searchResults;


    //Open shopcart, Log In and verify 4 items
    @BeforeTest
    public void initialize() {
        //Memory for the variables
        driver  = InitializeDriver();
        mainPage = new MainPage(driver);
        searchResults = new SearchResults(driver);
    }

    /*@BeforeMethod
    public void Conditions() {
        driver.get(mainPage.getUrl());
        mainPage.Search();
    }*/

    @Test
    public void countResult() throws InterruptedException{
        driver.get(mainPage.getUrl());
        mainPage.Search();
        searchResults.CountResult();
        //Thread.sleep(3000);
    }

    @Test
    public void Filters() throws InterruptedException{
        driver.get(mainPage.getUrl());
        mainPage.Search();
        searchResults.ResultWithFilter();
        //Thread.sleep(5000);
    }


    @Test
    public void Stars() throws InterruptedException{
        driver.get(mainPage.getUrl());
        mainPage.Search();
        searchResults.StarRating();
        //Thread.sleep(10000);
    }

    @Test
    public void FirsResult() throws InterruptedException{
        driver.get(mainPage.getUrl());
        mainPage.Search();
        searchResults.InformationFirstResult();
        //Thread.sleep(3000);
    }

    //Finish the test
    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
