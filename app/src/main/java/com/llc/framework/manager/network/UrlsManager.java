package com.llc.framework.manager.network;

/**
 *@package  com.crm.framework.manager.network
 *@fileName UrlsManager
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe url配置
 *@company
 */

public class UrlsManager {
    private static final String BASIC_URL = "http://www.baidu.com";
    private static final String PORT = "80/";

    public static String getDefaultUrl() {
        return BASIC_URL + ":" + PORT;
    }
}
