import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.Color;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class elit {
    @Test
    public void positive_case(){
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Selenide.open("https://ee.ge/");
        $(byText("რეგისტრაცია")).click();
        $(byText("სწრაფი რეგისტრაცია")).shouldBe(Condition.visible);

        $("#firstName").setValue("alina");
        $("#firstName").shouldNotBe(Condition.empty);
        $("#lastName").setValue("bregvadze");
        $("#lastName").shouldNotBe(Condition.empty);
        $("#email").setValue("al.breg@gmail.com");
        $("#email").shouldNotBe(Condition.empty);
        $("#password").setValue("pass1234");
        $("#confirmPassword").setValue("pass1234");
        $("#singup").shouldBe(Condition.enabled);
    }
    @Test
    public void negative_Cases(){
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Selenide.open("https://ee.ge/");
        $(byText("რეგისტრაცია")).click();
        $(byText("სწრაფი რეგისტრაცია")).shouldBe(Condition.visible);

        $("#singup").shouldBe(Condition.disabled);

        $("#firstName").click();
        $("#lastName").click();
        $("#email").click();
        $("#password").click();
        $("#confirmPassword").click();
        $("#firstName").click();
        $(byText("სახელი სავალდებულოა")).shouldBe(Condition.visible);
        $(byText("გვარი სავალდებულოა")).shouldBe(Condition.visible);
        $(byText("ელ. ფოსტა სავალდებულოა")).shouldBe(Condition.visible);
        $(byText("პაროლი სავალდებულოა.")).shouldBe(Condition.visible);
        $(byText("პაროლის გამეორება სავალდებულოა")).shouldBe(Condition.visible);

        $("#email").setValue("test");
        $(byText("ელ. ფოსტა სავალდებულოა")).shouldBe(Condition.visible);
        $("#email").setValue("@");
        $(byText("ელ. ფოსტა სავალდებულოა")).shouldBe(Condition.visible);
        $("#email").setValue("gmail");
        $(byText("ელ. ფოსტა სავალდებულოა")).shouldBe(Condition.visible);
        $("#email").setValue(".");
        $(byText("ელ. ფოსტა სავალდებულოა")).shouldBe(Condition.visible);


        $("#password").setValue("pass");
        $(byText("პაროლი სავალდებულოა.")).shouldBe(Condition.visible);

        $("#password").setValue("12234");
        $("#confirmPassword").setValue("pass1234");
        $(byText("პაროლის გამეორება სავალდებულოა")).shouldBe(Condition.visible);
    }

    @Test
    public void cart(){
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Selenide.open("https://ee.ge/");

        $(".btn-cart").click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);
        $("#search_list").setValue("კომპიუტერი");

        Assert.assertEquals("კომპიუტერი", $("#search_list").getValue());

        element(".filter-select-list", 2).click();

        element(".add_to_cart", 0).click();

        $(".btn-cart").click();

//        $(byText("კალათა ცარიელია")).shouldNotBe(Condition.visible);
        Assert.assertTrue($(byText("კალათა ცარიელია")).is(Condition.visible));

        element(".fa-trash", 0).click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);

        $(byText("კალათა ცარიელია")).waitUntil(Condition.visible, 5000);

    }


    @Test
    public void colors(){
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Selenide.open("https://ee.ge/");

        Assert.assertEquals("#FF1E38", Color.fromString($(".search-btn").getCssValue("background-color")).asHex().toUpperCase());

    }

    @Test
    public void savedItems(){
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Selenide.open("https://ee.ge/");

        $(".btn-cart").click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);

        $(byText("შენახული ნივთები")).click();
        $(byText("ვერ მოიძებნა")).shouldBe(Condition.visible);

        $("#search_list").setValue("კომპიუტერი");
        element(".filter-select-list", 2).click();
        element(".add_to_cart", 0).click();

        $(".btn-cart").click();
//        $(byText("კალათა ცარიელია")).shouldNotBe(Condition.visible);
        Assert.assertFalse($(byText("კალათა ცარიელია")).is(Condition.visible));
        element(".save-icon", 0).click();
        $(byText("შენახული ნივთები")).click();
//        $(byText("ვერ მოიძებნა")).shouldNotBe(Condition.visible);
        Assert.assertFalse($(byText("ვერ მოიძებნა")).is(Condition.visible));

        $(".btn-cart").click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);

        $(byText("შენახული ნივთები")).click();
        element(".cross_icon", 0).click();
        $(byText("ვერ მოიძებნა")).shouldBe(Condition.visible);
    }
}
