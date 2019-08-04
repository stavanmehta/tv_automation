import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tv.automation.pages.TvAppPage.Articles;

import java.util.List;

public class TestTvApp extends BaseTest {

    @Test
    public void testFirstTvaApp() {
        // Verify that Left menu items are visible
        Assert.assertTrue(tvApp.tvAppPage().getBrowseHeaders().isDisplayed());
        Assert.assertTrue(tvApp.tvAppPage().getMenuArticles().isDisplayed());
        Assert.assertTrue(tvApp.tvAppPage().getMenuProductCatalog().isDisplayed());
        Assert.assertTrue(tvApp.tvAppPage().getMenuOurCafes().isDisplayed());
        Assert.assertTrue(tvApp.tvAppPage().getMenuAboutUs().isDisplayed());

        // Verify that current focus is on Article scrollable content
        tvApp.tvAppPage().getMenuArticles().click();
        Articles articles = tvApp.tvAppPage().getArticles();
        Assert.assertTrue(doesConentCurrentlyFocused(articles.getArticleList()), "Articles are not currently focused");

        // Move down the arrow to Product catalog, and Verify that current focus is not on Articles scrollable content
        tvApp.tvAppPage().clickDownArrow();
        articles = tvApp.tvAppPage().getArticles();
        Assert.assertFalse(doesConentCurrentlyFocused(articles.getArticleList()), "Articles are currently focused");

        // Move up the arrow and set focus on Articles content, and click the 'Coffee Beverages Explained' content
        tvApp.tvAppPage().clickUpArrow();
        selectArticleContent(articles, "Coffee Beverages Explained", "18 Nov 2014");

        // Verify that Content details page is visible and it has content details action bar and content details
        // description
        // contentDetailsPage = new ContentDetailsPage(driver);
        Assert.assertTrue(tvApp.contentDetailsPage().getContentDetailsActionBar().isDisplayed(), "Content details action bar "
                + "is not displayed");
        Assert.assertTrue(tvApp.contentDetailsPage().getContentDetailsDescription().isDisplayed(), "Content details "
                + "description is not displayed");

        // Go back from content details page and it should go to Tv app main page, click down arrow two times, and
        // make sure that current focus is not set to Articles contents
        tvApp.contentDetailsPage().goBack();
        // tvAppPage = new TvAppPage(driver);
        tvApp.tvAppPage().clickDownArrow();
        tvApp.tvAppPage().clickDownArrow();
        articles = tvApp.tvAppPage().getArticles();
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
