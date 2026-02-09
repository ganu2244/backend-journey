package com.ganesh.day2;

import java.util.*;

public class DrillMain {
    static void main() {
        System.out.println(CollectionsDrill.charFrequency("banana"));
        System.out.println(CollectionsDrill.wordFrequency("Hello hello world world world"));

        List<Integer> nums = Arrays.asList(1,2,2,3,3,3,4,5,5,6,7,7,7,8);
        System.out.println(CollectionsDrill.removeDuplicatesKeepOrder(nums));
        System.out.println(CollectionsDrill.firstNonRepeatingChar("aabbccdxe"));
        System.out.println(CollectionsDrill.groupEvenOdd(nums));
        System.out.println(CollectionsDrill.topKFrequent(nums, 3));

        List<Integer> a = Arrays.asList(1,2,3,4,5);
        List<Integer> b = Arrays.asList(4,5,6,7);
        System.out.println(CollectionsDrill.intersectionUnique(a,b));
        System.out.println(CollectionsDrill.unionUnique(a,b));
        System.out.println(CollectionsDrill.differenceUnique(a,b));

        Map<String,Integer> m = new HashMap<>();
        m.put("a", 5); m.put("b", 1); m.put("c", 9);
        System.out.println(CollectionsDrill.sortMapByValueDesc(m));

        System.out.println(CollectionsDrill.areAnagrams("listen", "silent"));
        System.out.println(CollectionsDrill.longestConsecutive(Arrays.asList(100,4,200,1,3,2)));
        System.out.println(CollectionsDrill.findDuplicates(nums));
        System.out.println(CollectionsDrill.groupByFirstLetter(Arrays.asList("Apple","Ant","Ball","Cat","car")));

        System.out.println(Arrays.toString(CollectionsDrill.twoSum(new int[]{2,7,11,15}, 9)));
    }
}
