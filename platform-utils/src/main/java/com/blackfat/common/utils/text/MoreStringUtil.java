package com.blackfat.common.utils.text;

import com.google.common.base.Utf8;
import org.apache.commons.lang3.StringUtils;
import com.blackfat.common.utils.base.annotation.Nullable;
import com.blackfat.common.utils.collection.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 尽量使用Common Lang StringUtils, 基本覆盖了所有类库的StringUtils
 *
 * @author blackfat
 * @create 2017-03-09 下午1:23
 */
public class MoreStringUtil {

    /**
     * 高性能的Split，针对char的分隔符号，比JDK String自带的高效.
     *
     * from Commons Lange 3.5 StringUtils, 做如下优化:
     *
     * 1. 最后不做数组转换，直接返回List.
     *
     * 2. 可设定List初始大小.
     *
     * 3. preserveAllTokens 取默认值false
     *
     * @return 如果为null返回null, 如果为""返回空数组
     */
    public static List<String> split(@Nullable final String str, final char separatorChar, int expectParts){
        if(str == null){
           return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ListUtil.emptyList();
        }
        final List<String> list = new ArrayList<String>(expectParts);
        int i = 0;
        int start = 0;
        boolean match = false;
        while(i < len){
            if(str.charAt(i) == separatorChar){
                  if(match){
                       list.add(str.substring(start ,i));
                       match = false;
                  }
                  start = ++i;
                  continue;
            }
            match = true;
            i++;
        }
        if(match){
                list.add(str.substring(start, i));
        }
        return list;
    }


    /**
     * String 有replace(char,char)，但缺少单独replace first/last的
     */
    public static String replaceFirst(@Nullable String s, char sub, char with) {
        if (s == null) {
            return null;
        }
        int index = s.indexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }

    /**
     * String 有replace(char,char)替换全部char，但缺少单独replace first/last
     */
    public static String replaceLast(@Nullable String s, char sub, char with) {
        if (s == null) {
            return null;
        }

        int index = s.lastIndexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }


    /**
     * 判断字符串是否以字母开头
     *
     * 如果字符串为Null或空，返回false
     */
    public static boolean startWith(@Nullable CharSequence s, char c) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.charAt(0) == c;
    }

    /**
     * 判断字符串是否以字母结尾
     *
     * 如果字符串为Null或空，返回false
     */
    public static boolean endWith(@Nullable CharSequence s, char c) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.charAt(s.length() - 1) == c;
    }


    /**
     * 计算字符串被UTF8编码后的字节数 via guava
     *
     * @see Utf8#encodedLength(CharSequence)
     */
    public static int utf8EncodedLength(@Nullable CharSequence sequence) {
        if (StringUtils.isEmpty(sequence)) {
            return 0;
        }
        return Utf8.encodedLength(sequence);
    }


    /**
     * 如果结尾字符为c, 去除掉该字符.
     */
    public static String removeEnd(final String s, final char c) {
        if (endWith(s, c)) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }






}
