package com.xwservice.util;

import org.openqa.selenium.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Jquery工具类
 */
public class JQueryUtil {

    /**
     * jqeruy文件字符串
     */
    private static String strJqueryMin = "";

    static {
        try {
            InputStream inStream = JQueryUtil.class.getResourceAsStream("/jquery-3.3.1.js");
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            strJqueryMin = new String(swapStream.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化一下jqeruy环境
     *
     * @param driver
     */
    public static void initJQuery(JavascriptExecutor driver) {
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
    public static Boolean jQueryLoaded(JavascriptExecutor driver) {
        Boolean loaded;
        try {
            loaded = (Boolean) driver.executeScript("if(typeof jQuery==\"undefined\"){return false;}else{return true;}");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    /**
     * 执行JS脚本
     *
     * @param driver
     * @param script
     * @param args
     * @return
     */
    public static Object runJs(WebDriver driver, String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        initJQuery(js);
        return js.executeScript(script, args);
    }
}