package Tests;


import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToProfilePage.addNewPortfolioItem;
import static Actions.Writer.WriterGoToProfilePage.createTitleForPortfolioItem;


/**
 * Created by DeBeers on 03.10.2015.
 */
public class AddPortfolioItemTest extends BaseTest {

    @Test
    public void AddPortfolioItemTest_Test() throws InterruptedException {

        String title = createTitleForPortfolioItem();

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        addNewPortfolioItem(writerProfilePage, title, 55);

        Assert.assertTrue(writerProfilePage.addedPortfolioItem(driver, title));
    }
}
