package Tests;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.addNewPortfolioItem;
import static GeneralHelpers.CreateNewOrderHelper.randomID;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class DeletePortfolioItem extends BaseTest {


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void DeletePortfolioItem() throws Exception {

        String title = "New automation item: " + randomID();
        System.out.println(title);
        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);

        writerProfilePage.clickOnDeletePortfolioItemButton(title);
        Assert.assertFalse(writerProfilePage.findPortfolioItem(title));

    }

}
