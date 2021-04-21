package Utilities;

import com.sun.tools.javac.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResults {
    //Initializing the driver
    private final WebDriver driver;

    //Page of search

    @FindBy(xpath = "//a[contains(@data-auto ,'pagination_right_arrow')]")
    WebElement btnNext;

    @FindBy(xpath = "//li[contains(@class ,'restaurant-wrapper peyaCard show ')]")
    List<WebElement> Resultados;

    @FindBy(xpath = "//span[contains(@class ,'rating rating')]")
    List<WebElement> ResultadosStars;

    @FindBy(xpath = "//a[contains(@class ,'arrivalName')]")
    List<WebElement> RestaurantsNames;

    //Delivery
    @FindBy(xpath = "//a[contains(@data-key ,'tracking')]//i[contains(@class ,'countFilters')]")
    WebElement Filter;

    //sort
    @FindBy(xpath = "//*[@id=\"drop1\"]/li[6]")
    WebElement alpha;

    @FindBy(xpath = "//li[contains(@data-auto ,'shoplist_ordercontainer')]")
    List <WebElement> container;

    //TITLE
    @FindBy(xpath = "//h1[contains(@itemprop ,'name')]")
    WebElement title;

    //Details (distance, open hours, rating points, etc)
    @FindBy(xpath = "//button[contains(@data-auto ,'shopdetails_tabs_info')]")
    WebElement information;

    @FindBy(xpath = "//span[contains(@itemprop ,'streetAddress')]")
    WebElement ubication;

    @FindBy(xpath = "//div[contains(@class ,'hourscol')]")
    List <WebElement> openHours;

    @FindBy(xpath = "//button[contains(@data-auto ,'shopdetails_tabs_reviews')]")
    WebElement btnReview;

    @FindBy(xpath = "//p[contains(@itemprop ,'description')]")
    List <WebElement> Reviews;


    public SearchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void CountResult () {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));
        int n = 0;
        int cantResultados = 0;

        System.out.println("There are: " + Resultados.size() + " results in the current page.");

        while(true) {
            wait.until(ExpectedConditions.elementToBeClickable(Resultados.get(0)));
            if (Resultados.size() == 50) {
                btnNext.click();
                n++;
            }
            else {
                cantResultados = n*50 + Resultados.size();
                System.out.println("There are: " + cantResultados + " results.");
                System.out.println("There are: " + Resultados.size() + " results in the current page.");
                break;
            }
        }
    }
    public void ResultWithFilter () {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));
        String n;

        //System.out.println("Result filter: "+Filter.getText());
        n = Filter.getText();
        wait.until(ExpectedConditions.elementToBeClickable(Filter));
        Filter.click();

        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));
        System.out.println("The indicated results in the page are: " + n);
        System.out.println("The calculeted results are: " + Resultados.size());
    }

    public void Alphabet() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));


        //System.out.println(container.size());
        //((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", container.get(5));
        //container.get(5).click();
    }

    public void StarRating() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));

        for (int i = 0 ; i<ResultadosStars.size() ; i++) {
            System.out.println("The star rating of result " + (i+1) + " is " + ResultadosStars.get(i).getAttribute("title"));
        }
    }

    public void InformationFirstResult() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));

        RestaurantsNames.get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(information));

        information.click();
        wait.until(ExpectedConditions.visibilityOf(ubication));
        System.out.println("The ubication is: " + ubication.getText());
        System.out.println("The open hours is: " + openHours.get(0).getText());

        wait.until(ExpectedConditions.elementToBeClickable(btnReview));
        btnReview.click();

        System.out.println("Review 1: " + Reviews.get(0).getText());
        System.out.println("Review 2: " + Reviews.get(1).getText());
        System.out.println("Review 3: " + Reviews.get(2).getText());

    }

}
