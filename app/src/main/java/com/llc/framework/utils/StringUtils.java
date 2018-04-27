package com.llc.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @author qk
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
    // private final static Pattern emailer =
    // Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern emailer = Pattern
            .compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})");
    private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
            "yyyy-MM-dd");
    private final static String[] PHONENUMBER_PREFIX = {"130", "131", "132",
            "145", "155", "156", "185", "186", "134", "135", "136", "137",
            "138", "139", "147", "150", "151", "152", "157", "158", "159",
            "182", "183", "187", "188", "133", "153", "189", "180", "181"};

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 过滤电话号码
     *
     * @param mobile
     */
    public static String replaceMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        mobile = replace(" ", "", mobile);
        mobile = replace("+", "", mobile);
        mobile = replace("-", "", mobile);
        if (mobile.startsWith("01")) {
            if (!mobile.startsWith("010")) {
                mobile = mobile.substring(1, mobile.length());
            }
        }
        if (mobile.startsWith("86")) {
            mobile = mobile.substring(2, mobile.length());
        }
        return mobile;
    }

    /**
     * 功能描述：判断输入的字符串是否大于等于6，小于等于18
     *
     * @param str 传入的字符窜
     * @return 如果是符合则返回true, 否则返回false
     */
    public static boolean isLength(String str) {
        int length = str.length();
        return length >= 6 && length <= 18;
    }

    /**
     * 功能描述：替换字符串
     *
     * @param from   String 原始字符串
     * @param to     String 目标字符串
     * @param source String 母字符串
     * @return String 替换后的字符串
     */
    public static String replace(String from, String to, String source) {
        if (source == null || from == null || to == null)
            return null;
        StringBuffer str = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(from)) != -1) {
            str.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
            index = source.indexOf(from);
        }
        str.append(source);
        return str.toString();
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.format(today);
            String timeDate = dateFormater2.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定字符串是null则返回""
     *
     * @param str
     * @return
     */
    public static String isEmptyString(String str) {
        if (str == null || str.equals("null")) {
            return " ";
        }
        return str;

    }

    public static boolean apkValidate(String url) {
        String regEx = "^.*\\.apk$";// 这里是正则表达式
        return validateString(url, regEx);
    }

    /**
     * 字符验证
     *
     * @param str   需要验证的字符
     * @param regEx 正则表达式
     * @return 满足条件返回true
     */
    private static boolean validateString(String str, String regEx) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 把距离转友好的显示
     *
     * @param distance 单位为m的距离
     * @return
     */
    public static String friendlyDistance(double distance) {
        if (distance <= 0) {
            return "10米内";
        }
        // 距离显示方式：100——900米内、1公里内、2公里内、20——100公里内（10公里一个间隔）、100——1000公里内（100公里一个间隔）、1000公里外（此数据为运营确认数据，请尽量实现）
        if (distance > 100) {
            if (distance < 900) {
                return ((int) (Math.floor(distance / 100) * 100)) + 100 + "米内";
            } else if (distance < 20 * 1000) {
                return ((int) (Math.floor(distance / 1000))) + 1 + "公里内";
            } else if (distance < 100 * 1000) {// 10公里一个间隔
                return ((int) (Math.floor(distance / (1000 * 10)))) + 1
                        + "0公里内";
            } else if (distance < 1000 * 1000) {// 10公里一个间隔
                return ((int) (Math.floor(distance / (1000 * 100)))) + 1
                        + "00公里内";
            } else {
                return "1000公里外";
            }
        } else {
            return 100 + "米内";
        }
    }

    public static boolean checkPhoneNumber(String phoneNum) {
        if (isEmpty(phoneNum)) {
            return false;
        }
        return phoneNum.matches("^(13|15|18)\\d{9}$");
    }

    /*
     * 判断字符串是否在规定的长度内
     */
    public static boolean checkStringLength(String str, int minLength,
                                            int maxLength) {
        if (isEmpty(str)) {
            return false;
        }
        return !(str.length() < minLength || str.length() > maxLength);
    }

    /*
     * 判断是否为一个手机号码
     */
    public static boolean isMobileNO(String mobiles) {
//		int len = PHONENUMBER_PREFIX.length;
        if (mobiles != null) {
//			for (int i = 0; i < len; i++) {
//				Pattern p = Pattern.compile(PHONENUMBER_PREFIX[i] + "\\d{8}");
            Pattern p = Pattern.compile("1" + "\\d{10}");
//            Pattern p = Pattern.compile("[1][358]\\d{9}");
            if (p.matcher(mobiles).matches()) {
                return true;
//				}
            }
        }
        return false;
    }

    /**
     * 判断是否为一个电话号码
     */
    public static boolean isTellNumber(String tellnumber) {
        tellnumber = tellnumber.replaceAll("-", "");
        String phone = "0\\d{2,3}\\d{7,8}";
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(tellnumber);
        return m.matches();
    }

    public static boolean notEmpty(String str) {
        return null != str;
    }

    public static String doEmpty(String title) {
        if (null == title) {
            return "null";
        }
        return title;
    }

    public static String getDistance(float km) {
        km = (int) km;
        if (km <= 50) {
            return "50米以内";
        } else if (km < 100) {
            return 100 + "米以内";
        } else if (100 <= km && km < 1000) {
            return ((int) km / 100) * 100 + "米以内";
        } else {
            return (int) km / 1000 + "公里以内";
        }
    }

    /**
     * 将 \n 替换成<br/>
     *
     * @param content
     * @return
     */
    public static String replaceAllSpace(String content) {
        content = content.replaceAll("\\n", "<br/>");
        return content;
    }

    /**
     * 去除字符串中的空格/换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    //验证是否身份证码号
    public static boolean isIdCare(String idCare) {
        String strPattern = "\\d{15}|\\d{18}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(idCare);
        return m.matches();
    }

    /**
     * 检测是否是emoji表情
     * @param codePoint
     * @return
     */
    public static   boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF))|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

}