package com.xiaoma.class02_hashMap;

import java.util.HashMap;

/**
 * 设计RandomPool结构
 * 【题目】
 * 设计一种结构，在该结构中有如下三个功能:
 * insert(key):将某个key加入到该结构，做到不重复加入
 * delete(key):将原本在结构中的某个key移除
 * getRandom(): 等概率随机返回结构中的任何一个key。
 * 【要求】
 * Insert、delete和getRandom方法的时间复杂度都是O(1)
 * @author xiaoma
 * @date 2022/12/5 10:39
 */
public class Code01_RandomPool {
    public static class Pool<K> {
        // key --> index 的哈希表
        private final HashMap<K , Integer> keyIndexMap;
        // index --> key 的哈希表
        private final HashMap<Integer , K> indexKeyMap;
        private Integer size;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        /**
         * 插入函数
         * @param key 要插入的key值
         * @return 是否插入成功
         */
        public boolean insert(K key) {
            if (!keyIndexMap.containsKey(key)) {
                size++;
                keyIndexMap.put(key , size);
                indexKeyMap.put(size , key);
                return true;
            }
            return false;
        }

        /**
         * 获取随机值
         * @return 获取的随机key
         */
        public K getRandom() {
            if (size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * size) + 1; // 1 ~ size
            return indexKeyMap.get(randomIndex);
        }

        /**
         * 根据 key 做删除操作
         * @param key 要删除的key
         * @return 是否删除成功
         */
        public boolean delete(K key) {
            if (keyIndexMap.containsKey(key)) {
                Integer keyIndex = keyIndexMap.get(key);
                K lastKey = indexKeyMap.get(size);
                indexKeyMap.put(keyIndex , lastKey);
                keyIndexMap.put(lastKey , keyIndex);
                // 移除要删除的元素
                indexKeyMap.remove(size);
                keyIndexMap.remove(key);
                size--;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("xiaoma");
        pool.insert("xiaomi");
        pool.insert("huawei");
        System.out.println("获取的随机值:" + pool.getRandom());
//        System.out.println("是否插入成功:" + pool.insert("xiaoma"));
//        System.out.println("是否删除成功:" + pool.delete("sss"));
        System.out.println("是否删除成功:" + pool.delete("xiaoma"));
    }
}
