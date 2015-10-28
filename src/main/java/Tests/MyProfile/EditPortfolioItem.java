package Tests.MyProfile;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.addNewPortfolioItem;
import static Actions.Writer.WriterGoToProfilePage.createTextForPortfolioItem;
import static Actions.Writer.WriterGoToProfilePage.createTitleForPortfolioItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 11.10.2015.
 */
public class EditPortfolioItem extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void EditPortfolioItem() throws InterruptedException {

        String title = createTitleForPortfolioItem();
        String editedTitle = createTitleForPortfolioItem();
        String editedText = createTextForPortfolioItem(70);

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);
        assertTrue(writerProfilePage.addedPortfolioItem(driver, title));

        writerProfilePage.openAddedPortfolioItem(driver, title);
        writerProfilePage.editPortfolioItem(driver, title, editedTitle, editedText);

        assertEquals(writerProfilePage.getPortfolioItemTitle(), editedTitle);
        assertEquals(writerProfilePage.getPortfolioItemText(), editedText);
        writerProfilePage.clickOnBackToPortfolioButton();

    }

}
