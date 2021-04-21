package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    //Initializing the driver
    private final WebDriver driver;
    private final String url = "https://www.pedidosya.com.uy/";

    @FindBy(xpath = "//input[contains(@placeholder ,'Calle')]")
    WebElement address;

    @FindBy(xpath = "//input[contains(@placeholder ,'ej.')]")
    WebElement food;

    @FindBy(xpath = "//button[contains(@id ,'se')]")
    WebElement search;

    @FindBy(xpath = "//a[contains(@id,'confirm')]")
    WebElement confirm;

    @FindBy(xpath = "//a[contains(@class ,'mapSaveAddress')]")
    WebElement addressconfirm;


    @FindBy(xpath = "//a[contains(@class ,'arrivalName')]")
    List<WebElement> RestaurantsNames;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getUrl() {
        return url;
    }

    public void Search() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(search));
        address.clear();
        food.clear();
        address.sendKeys("Tomás Gómez 3711");
        food.sendKeys("Pizza");
        search.click();

        wait.until(ExpectedConditions.visibilityOf(addressconfirm));
        confirm.click();
        //wait.until(ExpectedConditions.elementToBeClickable(RestaurantsNames.get(0)));
    }
}
