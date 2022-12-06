package com.xiaoma.class02_hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 实现并查集的数据结构
 * @author xiaoma
 * @date 2022/12/6 17:16
 */
public class Code07_AndLookUp {
    // value 对应的元素 Element
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        // value --> element 对应的Map
        private final HashMap<V , Element<V>> elementMap;
        // element --> fatherElement 当前元素指向的父元素
        private final HashMap<Element<V> , Element<V>> fatherMap;
        // element --> int 当前代表元素所在集合的元素个数
        private final HashMap<Element<V> , Integer> sizeMap;

        /**
         * 进行初始化元素的操作
         * @param list 传入的list集合
         */
        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : list) {
                Element<V> element = new Element<>(v);
                elementMap.put(v ,element);
                fatherMap.put(element , element);
                sizeMap.put(element , 1);
            }
        }

        /**
         * 寻找当前元素的代表元素
         * @param element 传入的元素
         * @return 代表元素
         */
        public Element<V> findHead(Element<V> element) {
            // 同时进行扁平化的处理
            Stack<Element<V>> stack = new Stack<>();
            while (fatherMap.get(element) != element) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            while (!stack.isEmpty()) {
                // 将栈中元素的父元素指向代表元素
                fatherMap.put(stack.pop() , element);
            }
            return element;
        }

        /**
         * 判断两个元素是否在同一个集合中
         * @param a 元素1
         * @param b 元素2
         * @return true:在同一个集合中;false:不在同一个集合中
         */
        public boolean isSameSet(V a , V b) {
            if (a == b) {
                return true;
            }
            // 先判断有没有初始化
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }else {
                System.out.println("传入的两个参数没有初始化!");
                return false;
            }
        }


        /**
         * 将两个元素所在的集合合并在一起
         * @param a 元素1
         * @param b 元素2
         */
        public void union(V a , V b) {
            if(isSameSet(a , b)) {
                System.out.println("两个元素在同一个集合中!");
                return;
            }
            // 寻找元素1和元素2对应的代表元素
            Element<V> aHead = findHead(elementMap.get(a));
            Element<V> bHead = findHead(elementMap.get(b));
            // 将小集合挂载到大集合上
            Element<V> big = sizeMap.get(aHead) > sizeMap.get(bHead) ? aHead : bHead;
            Element<V> small = big == aHead ? bHead : aHead;
            fatherMap.put(small , big);
            // 设置新挂载的元素所在的集合的个数
            sizeMap.put(big , sizeMap.get(big) + sizeMap.get(small));
            // 同时移除旧的元素
            sizeMap.remove(small);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        UnionFindSet<String> findSet = new UnionFindSet<String>(list);
//        System.out.println(findSet.isSameSet("A", "A"));
        findSet.union("A" , "B");
        System.out.println(findSet.isSameSet("B", "C"));
        findSet.union("B" , "C");
        System.out.println(findSet.isSameSet("B", "C"));
    }

}
