package com.ganesh.day2;

import java.util.*;

public class CollectionsDrill {

    // 1) Frequency of characters in a string
    public static Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    // 2) Frequency of words in a sentence
    public static Map<String, Integer> wordFrequency(String sentence) {
        Map<String, Integer> freq = new HashMap<>();
        String[] parts = sentence.trim().toLowerCase().split("\\s+");
        for (String w : parts) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        return freq;
    }

    // 3) Remove duplicates from a list (keep order)
    public static List<Integer> removeDuplicatesKeepOrder(List<Integer> nums) {
        Set<Integer> seen = new LinkedHashSet<>(nums);
        return new ArrayList<>(seen);
    }

    // 4) Find first non-repeating character
    public static Character firstNonRepeatingChar(String s) {
        Map<Character, Integer> freq = charFrequency(s);
        for (char c : s.toCharArray()) {
            if (freq.get(c) == 1) return c;
        }
        return null;
    }

    // 5) Group numbers by even/odd
    public static Map<String, List<Integer>> groupEvenOdd(List<Integer> nums) {
        Map<String, List<Integer>> grouped = new HashMap<>();
        grouped.put("even", new ArrayList<>());
        grouped.put("odd", new ArrayList<>());
        for (int n : nums) {
            if (n % 2 == 0) grouped.get("even").add(n);
            else grouped.get("odd").add(n);
        }
        return grouped;
    }

    // 6) Top K frequent numbers
    public static List<Integer> topKFrequent(List<Integer> nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, entries.size()); i++) {
            result.add(entries.get(i).getKey());
        }
        return result;
    }

    // 7) Intersection of two lists (unique)
    public static Set<Integer> intersectionUnique(List<Integer> a, List<Integer> b) {
        Set<Integer> setA = new HashSet<>(a);
        Set<Integer> setB = new HashSet<>(b);
        setA.retainAll(setB);
        return setA;
    }

    // 8) Union of two lists (unique)
    public static Set<Integer> unionUnique(List<Integer> a, List<Integer> b) {
        Set<Integer> res = new HashSet<>(a);
        res.addAll(b);
        return res;
    }

    // 9) Difference a - b (unique)
    public static Set<Integer> differenceUnique(List<Integer> a, List<Integer> b) {
        Set<Integer> res = new HashSet<>(a);
        res.removeAll(new HashSet<>(b));
        return res;
    }

    // 10) Sort a map by value descending
    public static LinkedHashMap<String, Integer> sortMapByValueDesc(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        for (var e : entries) sorted.put(e.getKey(), e.getValue());
        return sorted;
    }

    // 11) Check if two strings are anagrams
    public static boolean areAnagrams(String a, String b) {
        if (a.length() != b.length()) return false;
        Map<Character, Integer> fa = charFrequency(a.toLowerCase());
        Map<Character, Integer> fb = charFrequency(b.toLowerCase());
        return fa.equals(fb);
    }

    // 12) Longest consecutive sequence (classic set problem)
    public static int longestConsecutive(List<Integer> nums) {
        Set<Integer> set = new HashSet<>(nums);
        int best = 0;

        for (int n : set) {
            if (!set.contains(n - 1)) { // start of a sequence
                int len = 1;
                int cur = n;
                while (set.contains(cur + 1)) {
                    cur++;
                    len++;
                }
                best = Math.max(best, len);
            }
        }
        return best;
    }

    // 13) Find duplicates in list
    public static Set<Integer> findDuplicates(List<Integer> nums) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> dup = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) dup.add(n);
        }
        return dup;
    }

    // 14) Group strings by first letter
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> map = new HashMap<>();
        for (String w : words) {
            if (w == null || w.isEmpty()) continue;
            char key = Character.toLowerCase(w.charAt(0));
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }
        return map;
    }

    // 15) Two sum using map (index-based)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (index.containsKey(need)) {
                return new int[]{ index.get(need), i };
            }
            index.put(nums[i], i);
        }
        return new int[]{ -1, -1 };
    }
}
