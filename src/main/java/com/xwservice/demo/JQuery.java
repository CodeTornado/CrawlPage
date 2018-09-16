package com.xwservice.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class JQuery {

    static String DriverPath = "E:\\SeleniumApp\\chromedriver2.39.exe";

    private static JQuery jquery = null;
    private String strJqueryMin = "";

    private JQuery() throws IOException {
        InputStream inStream = this.getClass().getResourceAsStream("/jquery-3.3.1.js");
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        strJqueryMin = new String(swapStream.toByteArray(), "UTF-8");
    }

    private void initJQuery(JavascriptExecutor driver) {
        if (!jQueryLoaded(driver)) {
            driver.executeScript(strJqueryMin);
            driver.executeScript("window.jQuery=jQuery.noConflict();");
        }
    }

    /**
     * 判断当前页面是否加载了JQuery
     *
     * @param driver
     * @return
     */
    public Boolean jQueryLoaded(JavascriptExecutor driver) {
        Boolean loaded;
        try {
            loaded = (Boolean) driver.executeScript("if(typeof jQuery==\"undefined\"){return false;}else{return true;}");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public static JQuery getInstance() throws IOException {
        if (jquery == null) {
            jquery = new JQuery();
        }
        return jquery;
    }

    /**
     * 执行JS脚本
     *
     * @param driver
     * @param script
     * @param args
     * @return
     */
    public Object runJs(WebDriver driver, String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        initJQuery(js);
        return js.executeScript(script, args);
    }

    public static void main(String[] args) {
        /*WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", DriverPath);
            //google浏览器
            driver = new ChromeDriver();
            //隐式 等待  10秒超时  超时则报错
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            //driver.get("https://www.bilibili.com");

            driver.get("https://www.bilibili.com/v/game/esports/?spm_id_from=333.8.game_esports.17#/");


//            Thread.sleep(5 * 1000);
            //视频热度排序 点一下
            JQuery.getInstance().runJs(driver, "$(\"#videolist_box div[class='vl-hd clearfix'] div[class='left'] ul[class='tab-list order-tab'] a:eq(1) li\").click();");

//            Thread.sleep(5 * 1000);

            //点一下展示方式 一列展示
            JQuery.getInstance().runJs(driver, "$(\"#videolist_box div[class='vl-hd clearfix'] div[class='right'] ul[class='tab-list display-tab'] li[class='mod-1']\").click();");


            //获得一下总共多少页
            String countText = (String) JQuery.getInstance().runJs(driver, "return $(\"#videolist_box div span[class='pagination-btn count']\").text();");

            for (int i = 0; i < 10; i++) {
                if (countText == null || "".equals(countText)) {
                    Thread.sleep(1000);

                    countText = (String) JQuery.getInstance().runJs(driver, "return $(\"#videolist_box div span[class='pagination-btn count']\").text();");

                }
            }
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);

            //当前多少页
            String countNowText = countText.split("/")[0];

            Matcher nowM = p.matcher(countNowText);
            //当前多少页
            String trimCountNumStr = nowM.replaceAll("").trim();

            String countAllText = countText.split("/")[1];

            Matcher mAll = p.matcher(countAllText);

            //总共多少页
            String countAllNumStr = mAll.replaceAll("").trim();


            for (int index = 0; index < Integer.parseInt(countAllNumStr); index++) {


//            Thread.sleep(3 * 1000);
                //抓出本页全部内容  等待遍历解析   顶多20行

                //左侧 图片a标签
                List<WebElement> webLElements = (List<WebElement>) JQuery.getInstance().runJs(driver, "return $(\"#videolist_box div[class='vd-list-cnt'] [class='vd-list mod-1'] li div[class='l'] div a\");");
                //标题和详解
                List<WebElement> webRElements = (List<WebElement>) JQuery.getInstance().runJs(driver, "return $(\"#videolist_box div[class='vd-list-cnt'] [class='vd-list mod-1'] li div[class='r']\");");

                for (int i = 0; i < webLElements.size(); i++) {
                    WebElement aElement = webLElements.get(i);



                        //视频链接
                        String href = aElement.getAttribute("href");

                        //找到图片标签
                        WebElement imgElement = aElement.findElement(By.tagName("div")).findElement(By.tagName("img"));
                        //封面图片链接
                        String srcImgUrl = imgElement.getAttribute("src");

                        //标题全文本
                        String alt = imgElement.getAttribute("alt");



                    //右侧 此视频详细
                    WebElement rElement = webRElements.get(i);

                    //简介div
                    WebElement element = rElement.findElement(By.className("v-desc"));

                    //简介文本
                    String text = element.getText();


                    //浏览情况
                    WebElement vInfoElement = rElement.findElement(By.className("v-info"));

                    //播放视频详细
                    List<WebElement> spanObjs = vInfoElement.findElements(By.className("v-info-i"));

                    //观看数量
                    String spanPlayNumStr = spanObjs.get(0).findElement(By.tagName("span")).getText();
                    //弹幕数量
                    String spanDMNumStr = spanObjs.get(1).findElement(By.tagName("span")).getText();
                    //收藏数量
                    String spanFavNumStr = spanObjs.get(2).findElement(By.tagName("span")).getText();

                    //up主详细信息
                    WebElement upInfoElement = rElement.findElement(By.className("up-info"));

                    WebElement upA = upInfoElement.findElement(By.tagName("a"));

                    //up主 个人主页链接
                    String upHref = upA.getAttribute("href");
                    //up主名字
                    String upName = upA.getAttribute("title");

                    //视频发布日期
                    String vDate = upInfoElement.findElement(By.tagName("span")).getText();



                }

                //翻页 下一页
                JQuery.getInstance().runJs(driver, "$(\"#videolist_box div [class='pager pagination'] li[class='page-item next'] button\").click();");
            }

//            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }*/
    }

}