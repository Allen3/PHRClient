package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/28.
 */
public class NewsInfo {

    private String news_Type = null;
    private String news_Title = null;
    private String news_Content = null;

    public NewsInfo() {
    }

    public void setNews_Type(String news_Type) {
        this.news_Type = news_Type;
    }

    public void setNews_Title(String news_Title) {
        this.news_Title = news_Title;
    }

    public void setNews_Content(String news_Content) {
        this.news_Content = news_Content;
    }

    public String getNews_Type() {
        return news_Type;
    }

    public String getNews_Title() {
        return news_Title;
    }

    public String getNews_Content() {
        return news_Content;
    }

    @Override
    public String toString() {
        return news_Title;
    }
}
