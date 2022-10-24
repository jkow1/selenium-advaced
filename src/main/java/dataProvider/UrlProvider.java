package dataProvider;

public class UrlProvider {
    public static String baseUrl = System.getProperty("appUrl");
    public static String basketUrl = baseUrl + "/index.php?controller=cart&action=show";
}
