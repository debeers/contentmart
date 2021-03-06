package Tests;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.addNewPortfolioItem;
import static Actions.Writer.WriterGoToProfilePage.createTitleForPortfolioItem;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class DeletePortfolioItemTest extends BaseTest {
    
    @Test
    public void DeletePortfolioItem_Test() throws Exception {

        String title = createTitleForPortfolioItem();
        System.out.println(title);
        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);

        writerProfilePage.clickOnDeletePortfolioItemButton(title);
        Assert.assertFalse(writerProfilePage.findPortfolioItem(title));
    }
}
