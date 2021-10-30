import chrome.ChromeRunner;
import com.codeborne.selenide.Condition;
//import org.junit.Assert;
//import org.junit.Test;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Elit extends ChromeRunner {
    @Test
    public void positive_case(){

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
        $("#singup").shouldNotBe(Condition.enabled);
    }
    @Test
    public void negative_Cases(){
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
        $(".btn-cart").click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);
        $("#search_list").setValue("კომპიუტერი");

        Assert.assertEquals("კომპიუტერი", $("#search_list").getValue());

        element(".filter-select-list", 2).click();

        element(".add_to_cart", 0).click();

        $(".btn-cart").click();

//        $(byText("კალათა ცარიელია")).shouldNotBe(Condition.visible);
        Assert.assertFalse($(byText("კალათა ცარიელია")).is(Condition.visible));

        element(".fa-trash", 0).click();
        $(byText("კალათა ცარიელია")).shouldBe(Condition.visible);

        $(byText("კალათა ცარიელია")).waitUntil(Condition.visible, 5000);
    }


    @Test
    public void colors(){
        Assert.assertEquals("#FF1E38", Color.fromString($(".search-btn").getCssValue("background-color")).asHex().toUpperCase());
    }

    @Test
    public void savedItems(){
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
