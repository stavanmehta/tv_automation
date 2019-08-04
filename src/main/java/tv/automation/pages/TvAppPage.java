package tv.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;

import javax.annotation.Nonnull;
import java.util.List;

public class TvAppPage extends BasePage {

    @AndroidFindBy(id = "browse_headers")
    private MobileElement browseHeaders;

    @AndroidFindBy(id = "container_list")
    private MobileElement containerList;

    @AndroidFindBy(id = "title_badge")
    private MobileElement titleBadge;

    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@content-desc='Articles']")
    private Articles articles;

    // Left Menu items
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Articles']")
    private MobileElement menuArticles;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Catalog']")
    private MobileElement menuProductCatalog;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Our Cafes']")
    private MobileElement menuOurCafes;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='About Us']")
    private MobileElement menuAboutUs;

    public MobileElement getBrowseHeaders() {
        return browseHeaders;
    }

    public MobileElement getContainerList() {
        return containerList;
    }

    public MobileElement getTitleBadge() {
        return titleBadge;
    }

    public MobileElement getMenuArticles() {
        return menuArticles;
    }

    public MobileElement getMenuProductCatalog() {
        return menuProductCatalog;
    }

    public MobileElement getMenuOurCafes() {
        return menuOurCafes;
    }

    public MobileElement getMenuAboutUs() {
        return menuAboutUs;
    }

    public Articles getArticles() {
        return articles;
    }

    public TvAppPage(AppiumDriver driver) {
        super(driver);
    }

    public static class Articles extends Widget {

        @AndroidFindAll({@AndroidBy(xpath = "//android.widget.FrameLayout")})
        private List<MobileElement> articleList;

        public Articles(@Nonnull MobileElement element) {
            super(element);
        }

        @AndroidFindBy(xpath = "//android.widget.FrameLayout")
        private Content articleContent;

        public List<MobileElement> getArticleList() {
            return articleList;
        }

        public void setArticleContent(MobileElement element) {
            Content content = new Content(element);
            this.articleContent = content;
        }

        public Content getArticleContent() {
            return articleContent;
        }
    }

    public static class Content extends Widget {

        @AndroidFindBy(id = "title_text")
        private MobileElement contentTitle;

        @AndroidFindBy(id = "content_text")
        private MobileElement contentText;

        public MobileElement getContentTitle() {
            return contentTitle;
        }

        public MobileElement getContentText() {
            return contentText;
        }

        public Content(@Nonnull MobileElement element) {
            super(element);
        }
    }
}
