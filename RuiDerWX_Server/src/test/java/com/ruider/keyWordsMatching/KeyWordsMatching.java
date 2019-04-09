package com.ruider.keyWordsMatching;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nlpcn.commons.lang.index.MemoryIndex;
import org.nlpcn.commons.lang.pinyin.Pinyin;

import java.util.ArrayList;
import java.util.List;


public class KeyWordsMatching {
    @Test
    public void test() {
        List<String> itemList = new ArrayList<String>();
        itemList.add("中国");
        itemList.add("美国");
        itemList.add("dog");
        itemList.add("China");
        itemList.add("America");
        itemList.add("我是中国人，我爱中国");
        itemList.add("我是一个程序猿");

        System.out.println(itemSearch("国", itemList));//[中国, 美国]
        System.out.println(itemSearch("g",itemList));//[中国, 美国,dog, 我是一个程序猿, 我是中国人，我爱中国]
        System.out.println(itemSearch("in",itemList));//[china]
        System.out.println(itemSearch("A",itemList));//[America]
        System.out.println(itemSearch("我",itemList));//[我是一个程序猿, 我是中国人，我爱中国]
        System.out.println(itemSearch("wo",itemList));//[我是一个程序猿, 我是中国人，我爱中国]
    }

    private List<String> itemSearch(String key, List<String> itemList) {
        MemoryIndex<String> memoryIndex = new MemoryIndex<String>();
        if (StringUtils.isBlank(key) || itemList == null || itemList.size() == 0) {
            return null;
        }
        for (String item : itemList) {
            String fullChar = StringUtils.join(Pinyin.pinyin(item), "");
            String firstChar = StringUtils.join(Pinyin.firstChar(item), "");
            memoryIndex.addItem(item, item, fullChar, firstChar);
        }
        return memoryIndex.suggest(key);
    }
}