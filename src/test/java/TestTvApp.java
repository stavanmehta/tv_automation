import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tv.automation.pages.ContentDetailsPage;
import tv.automation.pages.TvAppPage;
import tv.automation.pages.TvAppPage.Articles;

import java.io.IOException;
import java.util.List;

public class TestTvApp extends BaseTest {

    private TvAppPage tvAppPage;
    private ContentDetailsPage contentDetailsPage;

    @Override
    @BeforeSuite
    public void globalSetup() throws IOException {
        super.globalSetup();
        tvAppPage = new TvAppPage(driver);
    }

    @Override
    @AfterClass
    public void globalTearDown() {
        super.globalTearDown();
    }

    @Test
    public void testFirstTvaApp() {
        // Verify that Left menu items are visible
        Assert.assertTrue(tvAppPage.getBrowseHeaders().isDisplayed());
        Assert.assertTrue(tvAppPage.getMenuArticles().isDisplayed());
        Assert.assertTrue(tvAppPage.getMenuProductCatalog().isDisplayed());
        Assert.assertTrue(tvAppPage.getMenuOurCafes().isDisplayed());
        Assert.assertTrue(tvAppPage.getMenuAboutUs().isDisplayed());

        // Verify that current focus is on Article scrollable content
        tvAppPage.getMenuArticles().click();
        Articles articles = tvAppPage.getArticles();
        Assert.assertTrue(doesConentCurrentlyFocused(articles.getArticleList()), "Articles are not currently focused");

        // Move down the arrow to Product catalog, and Verify that current focus is not on Articles scrollable content
        tvAppPage.clickDownArrow();
        articles = tvAppPage.getArticles();
        Assert.assertFalse(doesConentCurrentlyFocused(articles.getArticleList()), "Articles are currently focused");

        // Move up the arrow and set focus on Articles content, and click the 'Coffee Beverages Explained' content
        tvAppPage.clickUpArrow();
        selectArticleContent(articles, "Coffee Beverages Explained", "18 Nov 2014");

        // Verify that Content details page is visible and it has content details action bar and content details
        // description
        contentDetailsPage = new ContentDetailsPage(driver);
        Assert.assertTrue(contentDetailsPage.getContentDetailsActionBar().isDisplayed(), "Content details action bar "
                + "is not displayed");
        Assert.assertTrue(contentDetailsPage.getContentDetailsDescription().isDisplayed(), "Content details "
                + "description is not displayed");

        // Go back from content details page and it should go to Tv app main page, click down arrow two times, and
        // make sure that current focus is not set to Articles contents
        contentDetailsPage.goBack();
        tvAppPage = new TvAppPage(driver);
        tvAppPage.clickDownArrow();
        tvAppPage.clickDownArrow();
        articles = tvAppPage.getArticles();
        Assert.assertFalse(doesConentCurrentlyFocused(articles.getArticleList()), "Articles are currently focused");
    }

    private Boolean doesConentCurrentlyFocused(List<MobileElement> elements) {
        for (MobileElement element : elements) {
            if (element.getAttribute("focused").equalsIgnoreCase("true")) {
                return true;
            }
        }
        return false;
    }

    private void selectArticleContent(Articles articles, String contentTitle, String contentDescriptionText) {
        for (MobileElement element : articles.getArticleList()) {
            articles.setArticleContent(element);
            String contentTitleText = articles.getArticleContent().getContentTitle().getText();
            String contentText = articles.getArticleContent().getContentText().getText();

            if (contentTitleText.equalsIgnoreCase(contentTitle)
                    && contentText.equalsIgnoreCase(contentDescriptionText)) {
                element.click();
                return;
            }
        }
    }
}
