package com.llc.framework.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MakeUrl {

    public static String getUrl(String url, Map<String, Object> params) {
        params = getBaseParamsMap(params);
        if (params == null || params.size() == 0)
            return url;
        StringBuilder sburl = new StringBuilder(url);
        if (url.indexOf("?") < 0) sburl.append('?');
        for (String name : params.keySet()) {
            sburl.append('&');
            sburl.append(name);
            sburl.append('=');
            sburl.append(String.valueOf(params.get(name)));
        }
        url = sburl.toString().replace("?&", "?");
        Log.e("url", url);
        return url;
    }

    //同名参数需要单独传递
    public static String getUrl(String url, Map<String, Object> params, String key, String values) {
        params = getBaseParamsMap(params);
        if (params == null || params.size() == 0)
            return url;
        StringBuilder sburl = new StringBuilder(url);
        if (url.indexOf("?") < 0) sburl.append('?');
        for (String name : params.keySet()) {
            sburl.append('&');
            sburl.append(name);
            sburl.append('=');
            sburl.append(String.valueOf(params.get(name)));
        }
        sburl.append('&');
        sburl.append(key);
        sburl.append('=');
        sburl.append(values);
        url = sburl.toString().replace("?&", "?");
        Log.e("url", url);
        return url;
    }

//    public static String postUrl(String url, Map<String, String> params) {
//        try {
//            url = Urls.BASIC_URL + url + postParams(params); // 拼接URL
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return url;
//    }


    public static String postParams(Map params) {
        String mapJson = null;
        try {
            String jsonString = "";
            Set key = params.entrySet();
            Set<Map.Entry<String, String>> set = params.entrySet();
            mapJson = "{";
            for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
                    .hasNext(); ) {
                Map.Entry<String, String> entry = it
                        .next();
                mapJson = mapJson + entry.getKey() + ":" + "\""
                        + entry.getValue() + "\"";
//                mapJson = mapJson + entry.getKey() + ":" + "\""
//                        + Uri.encode(StringUtils.transcoding(entry.getValue())) + "\"";
                if (it.hasNext()) {
                    mapJson += ",";
                }
            }
            mapJson = mapJson + "}";
            System.out.println(mapJson);
            // mapJson=URLDecoder.decode(mapJson, "UTF-8");
            // mapJson=mapJson.replaceAll("\n", "\\n");
            Log.i("url", mapJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapJson;
    }


    public static Map<String, Object> getBaseParamsMap(Map<String, Object> params) {


        if (params == null) {
            params = new HashMap<String, Object>();
        }
//		params.putAll(new HashMap<String, Object>(){{
//    		put("userid", new UserContext(AppApplication.getApp()).getUserUid());
//    		put("token",  new UserContext(AppApplication.getApp()).getSessionId());
//    		put("deviceid", AppApplication.getApp().getsDeviceID());
//    		put("version", new UserContext(AppApplication.getApp()).getVersionName());
//    		put("ssid",Configs.SSID);
//    	}});
        return params;
    }


}
