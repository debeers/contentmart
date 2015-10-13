package Tests.MyProfile;

import Actions.Writer.GoToWriterProfile;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Writer.GoToWriterProfile.addNewPortfolioItem;
import static GeneralHelpers.CreateNewOrderHelper.randomID;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class DeletePortfolioItem extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void DeletePortfolioItem() throws InterruptedException {

        String title = "New automation item: " + randomID();

        WriterProfilePage writerProfilePage = GoToWriterProfile.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);
        writerProfilePage.clickOnDeletePortfolioItemButton(title);
        writerProfilePage.clickOnConfirmSweetAllertButton();

    }

}
