package Tests.MyProfile;


import Actions.Writer.GoToWriterProfile;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.GoToWriterProfile.addNewPortfolioItem;
import static GeneralHelpers.CreateNewOrderHelper.randomID;


/**
 * Created by DeBeers on 03.10.2015.
 */
public class AddPortfolioItem extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void AddPortfolioItemTest() throws InterruptedException {

        String title = "New automation item: " + randomID();

        WriterProfilePage writerProfilePage = GoToWriterProfile.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);
        Assert.assertTrue(writerProfilePage.newPortfolioAppear(driver, title));
    }
}
