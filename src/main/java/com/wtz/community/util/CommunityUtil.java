package com.wtz.community.util;

import com.wtz.community.entity.DiscussPost;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Description：
 * Date:2021/11/15 20:36
 **/
public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID() {
        return  UUID.randomUUID().toString().replaceAll("-","");
    }
    //MD5加密：特点-只能加密不能解密；相同字符串对应相同密码
    //所以还要添加随机字符串加在密码上：hello + 3e65f ==> abc8f76767f 使得安全性提高
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {//判断是否为空
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
