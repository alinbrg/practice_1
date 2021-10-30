import chrome.ChromeRunner;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Practise_2  extends ChromeRunner {
    @Test
    public void color_case(){
        Assert.assertEquals("#FF1E38", Color.fromString($(".search-btn").getCssValue("background-color")).asHex().toUpperCase());
    }

//    @Test
//    public void positive_case(){
//        $(byText("რეგისტრაცია")).click();
//        $(byText("სწრაფი რეგისტრაცია")).shouldBe(Condition.visible);
//
//        $("#firstName").setValue("alina");
//        $("#firstName").shouldNotBe(Condition.empty);
//        $("#lastName").setValue("bregvadze");
//        $("#lastName").shouldNotBe(Condition.empty);
//        $("#email").setValue("al.breg@gmail.com");
//        $("#email").shouldNotBe(Condition.empty);
//        $("#password").setValue("pass1234");
//        $("#confirmPassword").setValue("pass1234");
//        $("#singup").shouldNotBe(Condition.enabled);
//    }
}
