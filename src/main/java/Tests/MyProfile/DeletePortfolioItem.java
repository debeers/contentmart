package Tests.MyProfile;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.addNewPortfolioItem;
import static GeneralHelpers.CreateNewOrderHelper.randomID;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class DeletePortfolioItem extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void DeletePortfolioItem() throws Exception {

        String title = "New automation item: " + randomID();
        System.out.println(title);
        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);

        writerProfilePage.clickOnDeletePortfolioItemButton(title);

        writerProfilePage.clickOnConfirmSweetAllertButton();
        Thread.sleep(3000);
        Assert.assertFalse(writerProfilePage.findPortfolioItem(title));

    }

}
