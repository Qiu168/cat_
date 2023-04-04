package com.HuangTaiQi.www.utils.regex;

import java.util.regex.Pattern;

/**
 * @author 14629
 */
public class CommonRegex {
    /**
     * 长度在6~18之间，只能包含字符、数字和下划线。
     */
    public static String usernameRegex= "^\\w{6,18}$";
    /**
     * 只有中文
     */
    public static String nameRegex= "[\\u4e00-\\u9fa5]+";
    /**
     * 长度为6 只能包含字母、数字。
     */
    public static String electromobileNumberRegex="^[A-Za-z0-9]{6}$";

    public static boolean check(String regex,String str){
        return Pattern.matches(regex,str);
    }
}
