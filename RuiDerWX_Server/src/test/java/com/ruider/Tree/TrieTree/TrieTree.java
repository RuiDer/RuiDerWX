package com.ruider.Tree.TrieTree;
import java.util.HashMap;

/**
 * java实现前缀树
 */
public class TrieTree {

    private class Node {
        public HashMap<Character , Node> childs;      //子结点
        public boolean isLeaf;     //当前结点是否是完整字符串（是否是叶结点）

        public Node () {
            this.isLeaf = false;
            this.childs = new HashMap<>();
        }

    }

    //根结点
    private  Node root;
    public TrieTree () {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public  void insert (String str) {
        insert(root , str);
    }
    //添加结点的过程既是添加结点的作用也是匹配前缀的过程
    private  void insert (Node root , String str) {
        if (str == null || str.length() == 0) return;
        char[] chars = str.toCharArray();
        Node cur = root;
        for (int i = 0 , length = chars.length ; i < length ; i++) {
            //不包含当前字符加入
            if (! cur.childs.containsKey(chars[i])) {
                cur.childs.put(chars[i] , new Node());
            }
            cur = cur.childs.get(chars[i]);
        }
        if (!cur.isLeaf)
            cur.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public  boolean search (String str) {
        return search(root , str);
    }
    //遍历查找是否包含字符串
    private  boolean search (Node root , String str) {
        if (str == null || str.length() == 0) return false;
        char[] chars = str.toCharArray();
        Node cur = root;
        for (int i = 0 , length = chars.length ; i < length ; i++) {
            if (!cur.childs.containsKey(chars[i])) {
                return false;
            }
            cur = cur.childs.get(chars[i]);
        }
        return cur.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String str) {
        if (str == null || str.length() == 0) return false;
        char[] chars = str.toCharArray();
        Node cur = root;
        for (int i = 0 , length = chars.length ; i < length ; i++) {
            if (!cur.childs.containsKey(chars[i]))
                return false;
            cur = cur.childs.get(chars[i]);
        }
        return true;
    }
}

