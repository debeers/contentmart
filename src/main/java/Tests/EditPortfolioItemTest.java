package Tests;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 11.10.2015.
 */
public class EditPortfolioItemTest extends BaseTest {

    @Test
    public void EditPortfolioItem_Test() throws InterruptedException {

        String title = createTitleForPortfolioItem();
        String editedTitle = createTitleForPortfolioItem();
        String editedText = createTextForPortfolioItem(70);
        System.out.println(title);

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);
        assertTrue(writerProfilePage.addedPortfolioItem(driver, title));

        writerProfilePage.editPortfolioItem(driver, title, editedTitle, editedText);

        assertTrue(writerProfilePage.addedPortfolioItem(driver, editedTitle));
        assertEquals(writerProfilePage.getAddedPortfolioItemText(editedText), editedText);
    }
}
