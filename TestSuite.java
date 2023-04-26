import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TestSuite {

    static String expectedRegistrationCompleteMsg = "Thanks for registration";

    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void closeBrowser() {
        //close the browser
        driver.close();
    }

    //

    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully() {

        //  driver.findElement(By.className("ico-register")).click();
        clickOnElement(By.className("ico-register"));
        // driver.findElement(By.id("FirstName")).sendKeys("Tejas");
        typeText(By.id("FirstName"), "Tejas");
        // driver.findElement(By.id("LastName")).sendKeys("Vaddoriya");
        typeText(By.id("LastName"), "Vaddoriya");
        //driver.findElement(By.name("Email")).sendKeys("Tejasvadoriya+"+timestamp.getTime()+"@vmail.com");
        typeText(By.name("Email"), "Tejasvadoriya" + timestamp.getTime() + "@vmail.com");
        // driver.findElement(By.id("Password")).sendKeys("tejas1234");
        typeText(By.id("Password"), "tejas1234");
        //driver.findElement(By.id("ConfirmPassword")).sendKeys("tejas1234");
        typeText(By.id("ConfirmPassword"), "tejas1234");
        // driver.findElement(By.id("register-button")).click();
        clickOnElement(By.id("register-button"));


        //String actualmessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String actualmessage = getTextFormElement(By.xpath("//div[@class='result']"));
        System.out.println("My message:" + actualmessage);
        Assert.assertEquals(actualmessage, expectedRegistrationCompleteMsg, "Registration is not working");

    }

    @Test
    public static void verifyRegisteredUserShouldAbletoEmailafriend() {

        //click on product
        clickOnElement(By.xpath("//button[@class='button-2 product-box-add-to-cart-button'])[2]"));
        //click on email a friend

        clickOnElement(By.xpath("//button[@class='button-2 email-a-friend-button']"));
        //enter friends email
        typeText(By.name("friend-email"), "friends123@gmail.com");
        //enter your email
        typeText(By.name("your-email"), "myemail@gmail.com");
        //click on send button
        clickOnElement(By.name("send-email"));

        //check result

        String expected = "Your Email has been sent.";
        String actualmessage = getTextFormElement(By.xpath("//div[@class='result']"));
        System.out.println("My message:" + actualmessage);
        Assert.assertEquals(expected, actualmessage, "Email has not been sent successfully");


    }

    @Test
    public static void verifyRegisteredUserShouldAblToVoteSuccessfully() {
        //click on good button
        clickOnElement(By.id("pollanswers-2"));
        //click on votr
        clickOnElement(By.xpath("//button[@id='vote-poll-1']"));

        String expected = "Your vote has been successfull";


        String actualmessage = driver.findElement(By.xpath("//div[@class='poll-vote-error']")).getText();
        System.out.println("My message:" + actualmessage);
        Assert.assertEquals(expected, actualmessage, "only registered user can vote");

    }

    @Test
    public static void verifyProductsForComparison() {

        //add item for comparison
        clickOnElement(By.xpath("//button[@class='button-2 add-to-compare-list-button'])[3]"));
        //add another item for comparison
        clickOnElement(By.xpath("//button[@class='button-2 add-to-compare-list-button'])[4]"));
        //for compare a list
        clickOnElement(By.xpath("//*[@id=\"bar-notification\"]/div/p/a"));
        //print text

        String name1 = getTextFormElement(By.partialLinkText("$25 Virtual Gift Card"));
        System.out.println("First Product is: " + name1);
        String name2 = getTextFormElement(By.partialLinkText("HTC One M8 Android L 5.0 Lollipop"));
        System.out.println("Second Product is: " + name2);

        //clear comparison
        clickOnElement(By.className("clear-list"));
        String expectedResult = "no product for comparison";
        String actualResult = getTextFormElement(By.className("no-data"));
        System.out.println("My message:" + actualResult);
        //check the actual result
        Assert.assertEquals(actualResult, expectedResult, "no product for comparison");


    }

    @Test
    public static void verifyRegisteredUserShouldAbleToReferaProductToFriend() {
        //click on electronics
        clickOnElement(By.xpath("(//a[@title='Show products in category Electronics'])[1]"));
        //click on product
        clickOnElement(By.xpath("(//a[@title='Show products in category Camera & photo'])[1]"));
        //click on add to cart
        clickOnElement(By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])[2]"));
        //print name of the product
        String name1 = getTextFormElement(By.partialLinkText("Leica T Mirrorless Digital Camera"));
        System.out.println("product add in cart:" + name1);
        //go to cart
        clickOnElement(By.className("cart-label"));

        String expectedaresult = "Leics T Mirrorless Digital Camera";
        String actualResult = getTextFormElement(By.className("product-name"));
        System.out.println("product add in cart is:" + actualResult);
        Assert.assertEquals(actualResult, expectedaresult, "Same Product in cart");


    }

    @Test
    public static void verifyRegisteredUserShouldBeAbleToVoteSuccessfully() {
        String expectedResult = "18 year old Vote";

        //click on register button
        clickOnElement(By.className("ico-register"));
        //type the firstname
        typeText(By.id("FirstName"), "MyFirstTest");
        //type the lastname
        typeText(By.id("LastName"), "Automation");
        //type the email
        typeText(By.name("Email"), "myfirst" + datestamp() + "auto@gmail.com");
        //type password
        typeText(By.name("Password"), "auto1234");
        //confirm password
        typeText(By.id("ConfirmPassword"), "auto1234");
        //click on register
        clickOnElement(By.id("register-button"));
        //click on login
        clickOnElement(By.className("ico-login"));
        //type email
        typeText(By.xpath("//input[@name='Email']"), "myfirst" + datestamp() + "auto@gmail.com");
        //type password
        typeText(By.xpath("//input[@class='password']"), "tejas1234");
        //click on login
        clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
        //go to homepage
        clickOnElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //click on good button in community poll
        clickOnElement(By.xpath("//label[@for='pollanswers-2']"));
        //click on vote
        clickOnElement(By.xpath("//button[@class='button-2 vote-poll-button']"));

        String actualResult = getTextFormElement(By.className("poll-total-votes"));
        System.out.println(actualResult);
        //check if actual result is same as expected
        Assert.assertEquals(actualResult, expectedResult, "Total votes");
    }

    protected static WebDriver driver;


    static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFormElement(By by) {
        return driver.findElement(by).getText();
    }


    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static long datestamp() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-YYYY-hh:mm");
        return sd.hashCode();
    }

}



