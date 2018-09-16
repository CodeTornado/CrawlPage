package com.xwservice.service.impl;

import com.xwservice.bean.VideoUrlInfo;
import com.xwservice.config.BilBilVideoInfoConfig;
import com.xwservice.config.WebDriverConfig;
import com.xwservice.service.GrabBilBilVideoInfoService;
import com.xwservice.service.VideoInfoService;
import com.xwservice.util.JQueryUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GrabBilBilVideoInfoServiceImpl implements GrabBilBilVideoInfoService {

    @Resource
    private VideoInfoService videoInfoService;

    @Autowired
    private BilBilVideoInfoConfig bilBilVideoInfoConfig;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private static long sleepTime = 100;

    @Override
    public boolean getBilBilVideoGameAreaPopularInfoToDB() {
        boolean falg = false;

        WebDriver driver = null;
        try {

            System.setProperty("webdriver.chrome.driver", webDriverConfig.getGoogleDriverPath());
            //google浏览器
            driver = new ChromeDriver();

            //隐式 等待  10秒超时  超时则报错
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            //打开游戏区排行榜链接
            driver.get(bilBilVideoInfoConfig.getGamingZoneLeaderboardUrl());
            //视频热度排序 点一下
            JQueryUtil.getInstance().runJs(driver, "$(\"#videolist_box div[class='vl-hd clearfix'] div[class='left'] ul[class='tab-list order-tab'] a:eq(1) li\").click();");
            //点一下展示方式 一列展示
            JQueryUtil.getInstance().runJs(driver, "$(\"#videolist_box div[class='vl-hd clearfix'] div[class='right'] ul[class='tab-list display-tab'] li[class='mod-1']\").click();");
            //获得一下总共多少页
            String countText = (String) JQueryUtil.getInstance().runJs(driver, "return $(\"#videolist_box div span[class='pagination-btn count']\").text();");

            for (int i = 0; i < 10; i++) {
                if (countText == null || "".equals(countText)) {
                    Thread.sleep(2000);

                    countText = (String) JQueryUtil.getInstance().runJs(driver, "return $(\"#videolist_box div span[class='pagination-btn count']\").text();");

                }
            }
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);

            //当前多少页
            String countAllText = countText.split("/")[0];

            Matcher mAll = p.matcher(countAllText);
            //当前多少页
            String trimCountNumStr = mAll.replaceAll("").trim();

//            String countAllText = countText.split("/")[1];
//            Matcher mAll = p.matcher(countAllText);

            //总共多少页
            String countAllNumStr = mAll.replaceAll("").trim();


            //有多生页翻多少页
            for (int index = 0; index < Integer.parseInt(countAllNumStr); index++) {


//            Thread.sleep(3 * 1000);
                //抓出本页全部内容  等待遍历解析   顶多20行

                //左侧 图片a标签
                List<WebElement> webLElements = (List<WebElement>) JQueryUtil.getInstance().runJs(driver, "return $(\"#videolist_box div[class='vd-list-cnt'] [class='vd-list mod-1'] li div[class='l'] div a\");");
                //标题和详解
                List<WebElement> webRElements = (List<WebElement>) JQueryUtil.getInstance().runJs(driver, "return $(\"#videolist_box div[class='vd-list-cnt'] [class='vd-list mod-1'] li div[class='r']\");");

                String mouseScrollingTopJsStr = "$('.scroll_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});     ";

//                String mouseScrollingTopJsStr = "var timerTop=setInterval(function(){       " +
//                        "            window.scrollBy(0,-30);        " +
//                        "            if(window.scrollY<=100){        " +
//                        "                clearInterval(timerTop);   " +
//                        "            }                              " +
//                        "        },10);                             ";
                JQueryUtil.getInstance().runJs(driver, mouseScrollingTopJsStr);


                for (int i = 0; i < webLElements.size(); i++) {


                    WebElement aElement = webLElements.get(i);


                    //视频链接
                    String href = aElement.getAttribute("href");

                    //找到图片标签
                    WebElement imgElement = aElement.findElement(By.tagName("div")).findElement(By.tagName("img"));


                    String mouseScrollingJsStr =
                            "var ynum = window.scrollY;             " +
                                    "                                       " +
                                    "var timer=setInterval(function(){      " +
                                    "  window.scrollBy(0,30);               " +
                                    "                                       " +
                                    "                                       " +
                                    "  if(window.scrollY === ynum){         " +
                                    "	clearInterval(timer);              " +
                                    "  }                                    " +
                                    "                                       " +
                                    "	ynum = window.scrollY;             " +
                                    "  },                                   " +
                                    "100);                                   ";
                    JQueryUtil.getInstance().runJs(driver, mouseScrollingJsStr);

                    //直接滚动到页面最底部
//                    JQueryUtil.getInstance().runJs(driver, "var h = $(document).height()-$(window).height();         $(document).scrollTop(h); ");


                    //图片竟然是瀑布流。。。。
                    //封面图片链接
                    String srcImgUrl = imgElement.getAttribute("src");
                    for (int j = 0; j < 5; j++) {


                        if (srcImgUrl == null && "".equals(srcImgUrl)) {
                            Thread.sleep(2 * 1000);
                            srcImgUrl = imgElement.getAttribute("src");
                        }
                    }


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


                    //视频信息bean
                    VideoUrlInfo videoInfo = new VideoUrlInfo();

                    videoInfo.setFromUrl(bilBilVideoInfoConfig.getGamingZoneLeaderboardUrl());

                    videoInfo.setImgUrl(srcImgUrl);
                    videoInfo.setVideoContent(text);

                    videoInfo.setVideoHref(href);

                    videoInfo.setTitle(alt);

                    videoInfo.setPlayNum(spanPlayNumStr);
                    videoInfo.setDMNum(spanDMNumStr);
                    videoInfo.setFavNum(spanFavNumStr);
                    videoInfo.setUpName(upName);

                    videoInfo.setUpIndexUrl(upHref);
                    videoInfo.setVideoDate(vDate);

                    videoInfo.setLabel("游戏区,热门视频");

                    videoInfoService.insertVideoInfo(videoInfo);


                }


                System.out.println("当前页数index = " + index + ">>>总页数=" + countAllNumStr);

                //翻页 下一页
                JQueryUtil.getInstance().runJs(driver, "$(\"#videolist_box div [class='pager pagination'] li[class='page-item next'] button\").click();");


                Thread.sleep(sleepTime);

            }

//            Thread.sleep(5000);

            falg = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }


        return falg;
    }
}
