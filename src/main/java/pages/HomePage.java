package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	
public WebDriver driver;
	


	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="(//span[contains(text(),'Sign in')])[1]/..")
	public WebElement signIn;
	
	@FindBy(xpath="(//div[@class='promotion-postcard__large'])[1]")
	public WebElement flights;
	
	@FindBy(xpath="(//span[text()='Show prices']/..)[1]")
	public WebElement flightSearch;
	
	@FindBy(xpath="//div[@id='username-note']")
	public WebElement usernameError;
	
	@FindBy(id="user-name")
	public WebElement userName;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(css="#login-button")
	public WebElement login;
	
	@FindBy(xpath="//button[text()='Add to cart']")
	public List<WebElement> addToCart;
	
	@FindBy(css="a.shopping_cart_link")
	public WebElement cart;
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	public List<WebElement> productNames;
	
	@FindBy(xpath="//button[text()='Remove']")
	public List<WebElement> removeProducts;
	
	@FindBy(xpath="//select[@class='product_sort_container']")
	public WebElement selectFilter;
	
	@FindBy(css="div.inventory_item_price")
	public List<WebElement> prices;
	
	@FindBy(xpath="//li[@class='social_twitter']/a")
	public WebElement twitterLink;
	
	
	@FindBy(xpath="//ul[@class='social']/li/a")
	public List<WebElement> socialLinks;
	
	
}
