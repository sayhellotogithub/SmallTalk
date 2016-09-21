package com.iblogstreet.smalltalk.utils;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.utils
 *  @文件名:   StringUtils
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 19:24
 *  @描述：    TODO
 */
public class StringUtils {
    private static final String TAG = "StringUtils";
    private static String userNameReg="^[a-zA-Z]\\w{2,19}$";
    private static String pwdReg="^\\d{3,19}$";

    /**
     * 检查用户名是否规范
     * @param username
     * @return
     */
    public static boolean checkUserName(String username){
        return username.matches(userNameReg);
    }

    /**
     * 检查密码是否符合规范
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd){
        return pwd.matches(pwdReg);
    }
}
