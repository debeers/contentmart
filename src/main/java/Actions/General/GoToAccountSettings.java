package Actions.General;

import Entities.LoginObject;
import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.EmailNotificationsPage;
import PageObjects.General.MyOrdersPage;
import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.Messages.randomTextGeneratorLength;
import static Tests.BaseTest.driver;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class GoToAccountSettings {


    public static AccountDetailsPage goToEditProfile(WebDriver driver, LoginObject clientLogin) throws InterruptedException {

        MyOrdersPage defaultClientPage = RegistrationAndLogin.loginAs(driver, clientLogin);
        defaultClientPage.clickOnProfileFromTopMenu();
        AccountDetailsPage accountDetailsPage = defaultClientPage.clickOnAccountSettingsDropMenu();

        $WaitFor(
                accountDetailsPage.saveChangesButton,
                accountDetailsPage.biographyField
        );
        return accountDetailsPage;
    }


    public static Boolean isTriggersHaveNeededConditions(String status) {

        EmailNotificationsPage ep = new EmailNotificationsPage(driver);

        for (WebElement trigger : ep.triggers) {

            if (status == "OFF" && $(trigger).getAttribute("class").contains("switch toggle-on")) {
                return false;

            }else if(status == "ON" && $(trigger).getAttribute("class").contains("switch toggle-off")) {
                return false;
            }
        }
        return true;
    }


    public static void setUserData(UserObject user, AccountDetailsPage accountDetailsPage) throws InterruptedException {


        if($(accountDetailsPage.countryField).is(Condition.enabled)) {

            System.out.println("We are in the client account settings NEO");

            Select currency = new Select(accountDetailsPage.currencyField);
            Select timezone = new Select(accountDetailsPage.userTimeZone);
            Select country  = new Select(accountDetailsPage.countryField);

            $(accountDetailsPage.countryField).shouldBe(Condition.visible);
            country.selectByIndex(new Random().nextInt(country.getOptions().size()));

            $(accountDetailsPage.currencyField).shouldBe(Condition.visible);
            currency.selectByIndex(new Random().nextInt(currency.getOptions().size()));

            $(accountDetailsPage.userTimeZone).shouldBe(Condition.visible);
            timezone.selectByIndex(new Random().nextInt(timezone.getOptions().size()));

            user.setCurrency(accountDetailsPage.getUserCurrency());
            System.out.println("Currency: " + accountDetailsPage.getUserCurrency());

            user.setTimeZone(accountDetailsPage.getUserTimeZone());
            System.out.println("Timezone: " + accountDetailsPage.getUserTimeZone());

        }else System.out.println("We are in the writer account settings NEO");


        Select state  = new Select(accountDetailsPage.userState);
        Select city   = new Select(accountDetailsPage.userCity);


        $(accountDetailsPage.userState).shouldBe(Condition.visible);
        state.selectByIndex(new Random().nextInt(state.getOptions().size()));

        user.setNickname(accountDetailsPage.getUserNickNameFromProfileDropMenu());
        System.out.println("NickName: " + accountDetailsPage.getUserNickNameFromProfileDropMenu());

        user.setFirstname(accountDetailsPage.setFirstNameField(randomTextGeneratorLength(8)));
        System.out.println("FirstName: " + accountDetailsPage.getUserFirstName());

        user.setLastname(accountDetailsPage.setLastNameField(randomTextGeneratorLength(8)));
        System.out.println("LastName: " + accountDetailsPage.getUserLastName());

        user.setPhone(accountDetailsPage.setPhoneField(RandomStringUtils.randomNumeric(10)));
        System.out.println("Phone: " + accountDetailsPage.getUserPhone());

        user.setPan(accountDetailsPage.setPanField(panGenerator()));
        System.out.println("PAN: " + accountDetailsPage.getUserPan());

        user.setCountry(accountDetailsPage.getUserCountry());
        System.out.println("Country: " + accountDetailsPage.getUserCountry());

        user.setAddress(accountDetailsPage.setAddressField(randomTextGeneratorLength(10)));
        System.out.println("Address: " + accountDetailsPage.getUserAdress());

        user.setZip(accountDetailsPage.setZipField(RandomStringUtils.randomNumeric(6)));
        System.out.println("ZIP: " + accountDetailsPage.getUserZip());

        accountDetailsPage.userCity.click();
        Thread.sleep(2000); // server side wait
        accountDetailsPage.userCity.click();
        city.selectByIndex(1 + new Random().nextInt(city.getOptions().size() - 1));
        user.setCity(accountDetailsPage.getUserCity());
        System.out.println("City: " + accountDetailsPage.getUserCity());

        user.setState(accountDetailsPage.getUserState());
        System.out.println("State: " + accountDetailsPage.getUserState());
    }


    public static String panGenerator(){
        String fst = "TESTT";
        String num = RandomStringUtils.randomNumeric(4);
        String pan = fst + num + 'T';

        return pan;
    }

}
