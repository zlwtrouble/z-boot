package com.spring.boot.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 标准的字符串处理工具
 * @author ruimin.jrm
 *
 */
public class StringUtil {

    //    private static final Pattern MOBILE_PATTERN   = Pattern.compile("^[1][0-9]{10}$");
    private static final Pattern TRUCK_NO_PATTERN = Pattern
                                                      .compile("^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$");

    //    public static boolean isMobile(String value) {
    //        if (!StringUtil.isBlank(value)) {
    //            Matcher m = MOBILE_PATTERN.matcher(value);
    //            return m.matches();
    //        }
    //        return false;
    //    }

    /**
     * 汉字转化为UNICODE格式
     * 
     * @param str
     * @param seperator
     * @return
     */
    public static String chinaToUnicode(String str, String seperator, String symbol) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {//汉字范围 \u4e00-\u9fa5 (中文)  
                if (i != 0 && !StringUtil.isBlank(seperator)) {
                    result.append(seperator);
                }
                result.append(symbol);
                result.append(Integer.toHexString(chr1).toUpperCase());
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * 字符串是否包含表情字符
     * 
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        if (isBlank(source)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isNormalCharacter(codePoint)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否是英文加空格
     * 
     * @param str
     * @return
     */
    public static boolean isEnglish(String str) {
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && c != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否一般文字，包含asc码0-127的所有字符，加上中文
     * 
     * @param codePoint
     * @return
     */
    public static boolean isNormalCharacter(char codePoint) {
        return (codePoint < 0x80) || ((codePoint >= 0x4E00) && (codePoint <= 0x9FA5))
               || ((codePoint >= 0x9FA6) && (codePoint <= 0x9FCB))
               || ((codePoint >= 0x3400) && (codePoint <= 0x4DB5))
               || ((codePoint >= 0x20000) && (codePoint <= 0x2A6D6))
               || ((codePoint >= 0x2A700) && (codePoint <= 0x2B734))
               || ((codePoint >= 0x2B740) && (codePoint <= 0x2B81D));
    }

    /**
     * 字符串是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    /**
     * 一个字符串是否是空的
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 一个字符串是否是非空的
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String firstCharUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 把字符串转化成类名的形式如： hello_world==>helloWorld
     * 
     * @param str
     * @return
     */
    public static String convertToClassString(String str) {
        str = str.replace("_", "-");
        String[] array = str.split("-");
        StringBuilder sb = new StringBuilder();
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    sb.append(array[i]);
                } else {
                    sb.append(firstCharUpperCase(array[i]));
                }
            }
            return sb.toString();
        }
        return sb.toString();
    }

    /**
     * 把字符串转化成一个数组
     * 
     * @param str
     * @return
     */
    public static String[] stringToArray(String str) {
        if (isBlank(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, "^");
        String[] array = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            array[i] = st.nextToken();
        }
        return array;
    }

    /**
     * 把字符串数组转化成一个字符串
     * 
     * @param array
     * @return
     */
    public static String arrayToString(String[] array) {
        return arrayToString(array, "^");
    }

    public static String arrayToString(String[] array, String seprator) {
        if (array == null || array.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(seprator);
            sb.append(s);
        }
        return sb.substring(1);
    }

    /**
     * 把map转化成形如aaa|bbb^ccc|ddd^eee|fff
     * 的string
     * @param map
     * @return
     */
    public static String mapToString(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("^");
            sb.append(entry.getKey());
            sb.append("|");
            sb.append(entry.getValue());
        }
        return sb.substring(1);
    }

    /**
     * 把形如 aaa|bbb^ccc|ddd^eee|fff
     * 的string转化成map
     * 
     * @param str
     * @return
     */
    public static Map<String, String> stringToMap(String str) {
        if (isBlank(str)) {
            return null;
        }
        Map<String, String> result = new HashMap<String, String>();
        String[] entryStrs = str.split("^");
        for (String s : entryStrs) {
            int sepPos = s.indexOf("|");
            result.put(s.substring(0, sepPos), s.substring(sepPos + 1));
        }
        return result;
    }

    /**
     * 左填充
     * 
     * @param str
     * @param length
     * @param pad
     * @return
     */
    public static String fillLeft(String str, int length, char pad) {
        int leftNum = length - str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftNum; i++) {
            sb.append(pad);
        }
        return sb.toString() + str;
    }

    /**
     * 转化成大写的形式
     * 
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**  
     * 十六进制的字符串转成byte数组  
     * @param hexString the hex string  
     * @return byte[]  
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    /**
     * 
     * byte数组转化成十六进制的字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 把字符串进行url编码
     * 
     * @param s
     * @return
     * @throws IOException
     */
    public static String urlEncode2Utf8(String s) throws IOException {
        return URLEncoder.encode(s, "utf-8");
    }

    /**
     * 填充占位符，占位符以 ${xx} 形式
     * 
     * @param content
     * @param replacement
     * @return
     */
    public static String fillReplacement(String content, Map<String, String> replacement) {
        StringBuilder result = new StringBuilder();
        int pos = 0;
        while (true) {
            int firstPos = content.indexOf("${", pos);
            if (firstPos != -1) {
                int secondPos = content.indexOf("}", firstPos);
                if (secondPos != -1) {
                    result.append(content.substring(pos, firstPos));
                    String key = content.substring(firstPos + 2, secondPos);
                    String value = replacement.get(key);
                    if (!StringUtil.isBlank(value)) {
                        result.append(value);
                    } else {
                        result.append(key);
                    }
                    pos = secondPos + 1;
                } else {
                    result.append(content.substring(pos));
                    break;
                }
            } else {
                result.append(content.substring(pos));
                break;
            }
        }
        return result.toString();
    }

    /**
     * 填充占位符，占位符以 ${xx} 形式
     * 
     * @param content
     * @param replacement
     * @return
     */
    public static String fillReplacement(String content, Properties replacement) {
        StringBuilder result = new StringBuilder();
        int pos = 0;
        while (true) {
            int firstPos = content.indexOf("${", pos);
            if (firstPos != -1) {
                int secondPos = content.indexOf("}", firstPos);
                if (secondPos != -1) {
                    result.append(content.substring(pos, firstPos));
                    String key = content.substring(firstPos + 2, secondPos);
                    String value = replacement.getProperty(key);
                    if (!StringUtil.isBlank(value)) {
                        result.append(value);
                    } else {
                        result.append(key);
                    }
                    pos = secondPos + 1;
                } else {
                    result.append(content.substring(pos));
                    break;
                }
            } else {
                result.append(content.substring(pos));
                break;
            }
        }
        return result.toString();
    }

    /**
     * 字符串替换，把一个字符串的第idx位开始的replaceLength个字符，替换成replacement
     * 
     * @param str
     * @param replacement
     * @param idx
     * @param replaceLength
     * @return
     */
    public static String replaceStr(String str, String replacement, int idx, int replaceLength) {
        String head = str.substring(0, idx);
        String tail = str.substring(idx + replaceLength);
        return head + replacement + tail;
    }

    /**
     * 删除左右的'\n','\t','\r',' '
     * @param str
     * @return
     */
    public static String trim(String str) {
        return trimRight(trimLeft(str));
    }

    /**
     * 删除左边的'\n','\t','\r',' '
     * @param str
     * @return
     */
    public static String trimLeft(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            int len = str.length();
            char tmp;
            int idx = 0;
            for (; idx < len; idx++) {
                tmp = str.charAt(idx);
                if (tmp != '\t' && tmp != '\n' && tmp != '\r' && tmp != ' ') {
                    break;
                }
            }
            return str.substring(idx);
        }
    }

    /**
     * 删除右边的'\n','\t','\r',' '
     * @param str
     * @return
     */
    public static String trimRight(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            int len = str.length();
            int idx = len;
            char tmp;
            for (; idx > 0; idx--) {
                tmp = str.charAt(idx - 1);
                if (tmp != '\t' && tmp != '\n' && tmp != '\r' && tmp != ' ') {
                    break;
                }
            }
            return str.substring(0, idx);
        }
    }

    /**
     * 根据指定字符串分隔
     * @param str
     * @param split
     * @return
     */
    public static String[] split(String str, String split) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        List<String> list = new ArrayList<String>();
        char cs[] = str.toCharArray();
        int count = cs.length;
        if (split == null) {
            String[] result = new String[count];
            for (int i = 0; i < count; i++) {
                result[i] = String.valueOf(cs[i]);
            }
            return result;
        } else {
            int slen = split.length();
            int index = 0;
            int ls = count - slen + 1;
            for (int i = 0; i < ls; i++) {
                if (str.substring(i, i + slen).equals(split)) {
                    list.add(str.substring(index, i));
                    index = i + slen;
                }
            }
            if (index < count) {
                list.add(str.substring(index));
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 对前端输入的字符转译
     * 
     * @param str
     * @return
     */
    public static String escapeHtml4(String str) {
        return StringEscapeUtils.escapeHtml4(str);
    }

    /**
     * 对List字符串去重复值并删除null值
     * 不改变list中原先顺序
     * 
     * @param list
     * @return
     */
    public static List<String> removeDuplicateWithOrder(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        LinkedHashSet<String> set = new LinkedHashSet<String>(list);
        set.remove(null);
        return new ArrayList<String>(set);
    }

    /**
     * 判断是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isDigits(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把一个数字字符串转成Number
     * 比如：字符串为9.223372036854776E16，转成long或者double会报错，可以先转成Number再转成long或者double
     * 
     * @param numberStr 数字型字符串
     * @return
     * @throws ParseException 
     */
    public static Number getNumberFromStr(String numberStr) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        Number nu = numberFormat.parse(numberStr);
        return nu;
    }

    /**
     * 校验是否为车牌号
     * 
     * @param value
     * @return
     */
    public static boolean isTruckNo(String value) {
        if (!StringUtil.isBlank(value)) {
            Matcher m = TRUCK_NO_PATTERN.matcher(value);
            return m.matches();
        }
        return false;
    }

    /**
     * 校验是否为数字（不带符号）
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        String reg = "\\d+(\\.\\d+)?";
        return value.matches(reg);
    }

    /**
     * 校验是否为数字（不带符号）
     * @param value
     * @return
     */
    public static boolean isNotNumber(String value) {
        return !isNumber(value);
    }

    public static void main(String[] args) {

        System.out.println(StringUtil.phoneHide("1391111827729"));
    }

    /**
     * 手机号隐藏中间4位（不带符号）
     * @param phone :
     */
    public static String phoneHide(String phone) {
        if(StringUtil.isBlank(phone)){
            return phone;
        }
        if(phone.length()>10){
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        }else{
            return phone;
        }
    }

}
