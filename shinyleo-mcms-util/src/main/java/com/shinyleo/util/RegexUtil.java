package com.shinyleo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhugh on 17/7/20.
 */
public class RegexUtil {

    public RegexUtil() {
    }

    public static String parseFirst(String source, String regex, int find) {
        String content = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()) {
            content = matcher.group(find);
        }

        return content;
    }

    public static List<String> parseAll(String source, String regex, int find) {
        ArrayList content = new ArrayList();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);

        while(matcher.find()) {
            content.add(matcher.group(find));
        }

        return content;
    }

    public static String replaceAll(String source, String regex, String newContent) {
        Pattern pattern = Pattern.compile(regex);

        for(Matcher matcher = pattern.matcher(source); matcher.find(); source = matcher.replaceAll(Matcher.quoteReplacement(newContent.toString().replace("\\", "/")))) {
            ;
        }

        return source;
    }

    public static String replaceFirst(String source, String regex, String newContent) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()) {
            source = matcher.replaceFirst(Matcher.quoteReplacement(newContent));
        }

        return source;
    }

    public static int count(String source, String regex) {
        Pattern patternList = Pattern.compile(regex);
        Matcher matcherList = patternList.matcher(source);

        int i;
        for(i = 0; matcherList.find(); ++i) {
            ;
        }

        return i;
    }

    public static Map<String, String> doubleRegex(String globalRegex, String singleRegex, String content) {
        HashMap map = new HashMap();
        Pattern patternList = Pattern.compile(globalRegex);
        Matcher matcherList = patternList.matcher(content);

        while(matcherList.find()) {
            Pattern _patternList = Pattern.compile(singleRegex);
            Matcher _matcherList = _patternList.matcher(matcherList.group());

            while(_matcherList.find()) {
                if(!StringUtil.isBlank(_matcherList.group(1)) && !StringUtil.isBlank(_matcherList.group(2))) {
                    map.put(_matcherList.group(1), _matcherList.group(2));
                }
            }
        }

        return map;
    }

    public static List<Map<Integer, String>> doubleRegexToMap(String globalRegex, String singleRegex, String content, int find) {
        ArrayList listAll = new ArrayList();
        Pattern patternList = Pattern.compile(globalRegex);
        Matcher matcherList = patternList.matcher(content);

        while(matcherList.find()) {
            Pattern _patternList = Pattern.compile(singleRegex);
            Matcher _matcherList = _patternList.matcher(matcherList.group());
            HashMap map = new HashMap();

            while(_matcherList.find()) {
                for(int i = 0; i < find; ++i) {
                    if(!StringUtil.isBlank(_matcherList.group(i + 1))) {
                        map.put(Integer.valueOf(i), _matcherList.group(i + 1));
                    }
                }

                listAll.add(map);
            }
        }

        return listAll;
    }

    public static List<String> parseAllToList(String content, String regex, int find) {
        ArrayList list = new ArrayList();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            for(int i = 0; i < find; ++i) {
                list.add(matcher.group(i + 1));
            }
        }

        return list;
    }
}
