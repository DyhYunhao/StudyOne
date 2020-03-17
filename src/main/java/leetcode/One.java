package leetcode;

import org.junit.Test;

import java.util.HashMap;

public class One {

    /**
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
     * 你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     */
    @Test
    public String test1(String S){
        if (S.length() <= 1)
            return S;
        StringBuilder sb = new StringBuilder();
        int one = 0, two = 1, count = 0;
        while (one < S.length() - 1) {
            while (two < S.length() && S.charAt(one) == S.charAt(two)) {
                two ++;
                count ++;
            }
            sb.append(S.charAt(one));
            sb.append(count);
            one = two;
            count = 0;
        }
        return sb.length() < S.length() ? sb.toString() : S;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     */
    @Test
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++){
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i ++){
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            }
        }
        return res;
    }

    /**
     * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
     *
     * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
     *
     * 注意：每次拼写时，chars 中的每个字母都只能用一次。
     *
     * 返回词汇表 words 中你掌握的所有单词的 长度之和。
     *
     */
    @Test
    public int countCharacters(String[] words, String chars) {
        int res = 0;
        int[] c = new int[26];
        //统计chars中的各个字母的个数
        for (int i = 0; i < chars.length(); i ++){
            c[chars.charAt(i) - 'a'] ++;
        }

        for (String s: words) {
            int[] cs = new int[26];
            for (int i = 0; i < s.length(); i ++) {
                cs[s.charAt(i) - 'a'] ++;
            }
            boolean flag = true;
            for (int i = 0; i < 26; i ++) {
                if (c[i] - cs[i] < 0){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res += s.length();
            }
        }
        return res;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        //类似指针，用于指示结果的位置
        ListNode t = res;
        int temp = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sum = v1 + v2 + temp;
            temp = sum / 10;
            t.next = new ListNode(sum % 10);
            t = t.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (temp > 0)
            t.next = new ListNode(temp);
        return res.next;
    }

    /**
     *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        return res;
    }
}
