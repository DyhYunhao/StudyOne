package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class One {

    /**
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
     * 你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     */
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
     *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 "pwwkew"
     */
    public static int lengthOfLongestSubstring(String s) {
        int res = 1;
        int i = 0;
        int c = 0;
        ArrayList<String> t = new ArrayList<>();
        //空串情况
        if (s.isEmpty())
            return 0;
        while (i < s.length() && c < s.length()){
            if (!t.contains(String.valueOf(s.charAt(i)))) {
                t.add(String.valueOf(s.charAt(i)));
                res = Math.max(res, t.size());
                i ++;
            }else {
                res = Math.max(res, t.size());
                t = new ArrayList<>();
                c ++;
                i = c;
                continue;
            }

        }
        return res;
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //第K小的数
        int l = (nums1.length + nums2.length + 1) / 2;
        int r = (nums1.length + nums2.length + 2) / 2;
        return (getK(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, l)
                + getK(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, r)) * 0.5;
    }
    public int getK(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k) {
        int l1 = e1 - s1 + 1;
        int l2 = e2 - s2 + 1;
        if (l1 > l2)
            return getK(nums2, s2, e2, nums1, s1, e1, k);
        if (l1 == 0)
            return nums2[s2 + k - 1];
        if (k == 1)
            return Math.min(nums1[s1], nums2[s2]);
        int i = s1 + Math.min(l1, k / 2) - 1;
        int j = s2 + Math.min(l2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getK(nums1, s1, e1, nums2, j + 1, e2, k - (j - s2 + 1));
        }
        else {
            return getK(nums1, i + 1, e1, nums2, s2, e2, k - (i - s1 + 1));
        }
    }

    /**
     * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
     *
     * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
     *
     * 给出两个矩形，判断它们是否重叠并返回结果。
     *
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[0] >= rec2[2] || rec1[1] >= rec2[3] || rec1[2] <= rec2[0] || rec1[3] <= rec2[1]);
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     */
    public int longestPalindrome(String s) {
        int[] c = new int[122];
        for (char c1: s.toCharArray()) {
            c[c1] ++;
        }
        int res = 0;
        for (int v : c) {
            res += v / 2 * 2;
            if (v % 2 == 1 && res % 2 == 0) {
                res ++;
            }
        }
        return res;
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i ++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     *
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     *
     * 你允许：
     *
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * 裴蜀定理（或贝祖定理）得名于法国数学家艾蒂安·裴蜀，说明了对任何整数a、b和它们的最大公约数d，关于未知数x和y的线性不定方程（称为裴蜀等式）：
     * 若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，特别地，一定存在整数x,y，使ax+by=d成立。
     * 它的一个重要推论是：a,b互质的充要条件是存在整数x,y使ax+by=1.
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0)
            return true;
        if (x + y < z)
            return false;
        return z % gcd(x, y) == 0;
    }
    private int gcd(int x, int y) {
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }


    /**
     * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     *
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     *
     * 返回车能够在一次移动中捕获到的卒的数量。
     *
     */
    public int numRookCaptures(char[][] board) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 找到白车所在的位置
                if (board[i][j] == 'R') {
                    // 分别判断白车的上、下、左、右四个方向
                    int res = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i, y = j;
                        while (true) {
                            x += dx[k];
                            y += dy[k];
                            if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] == 'B') {
                                break;
                            }
                            if (board[x][y] == 'p') {
                                res++;
                                break;
                            }
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * s = "LEETCODEISHIRING", numRows = 3
     * "LCIRETOESIIGEDHN"
     */
    public static String convert(String s, int numRows) {
        StringBuilder res = new StringBuilder();
        String[] strings = new String[numRows];
        boolean down = true;
        int go = 0;
        for (int i = 0; i < numRows; i ++) {
            strings[i] = "";
        }
        if (s.length() == 1 || numRows == 1)
            return s;
        for (int i = 0; i < s.length(); i ++) {
            strings[go] += s.charAt(i);
            System.out.println(go);
            if (go == numRows - 1) {
                down = false;
            }else if (go == 0) {
                down = true;
            }
            if (down) {
                go += 1;
            }else {
                go -= 1;
            }
        }
        for (int i = 0; i < strings.length; i ++) {
            res.append(strings[i]);
            System.out.println(strings[i]);
        }
        return res.toString();
    }

    /**
     *给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
     public static int reverse(int x) {
         StringBuilder res = new StringBuilder();
//         if (x < 0) {
//             res.append("-");
//             x = Math.abs(x);
//             System.out.println(Math.abs(-2147483648));
//         }
         String s = String.valueOf(x);
         System.out.println(s);
         if (s.charAt(0) == '-') {
             res.append('-');
         }

         for (int i = s.length() - 1; i >= 0 && s.charAt(i) != '-'; i --) {
             res.append(s.charAt(i));
         }
         return Double.parseDouble(res.toString()) > Integer.MAX_VALUE ||
                 Double.parseDouble(res.toString()) < Integer.MIN_VALUE ?
                 0 : Integer.parseInt(res.toString());
     }

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
     * 求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
     * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     */
    public int lastRemaining(int n, int m) {
        int res = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            temp.add(i);
        }
        while (n > 1) {
            res = (res + m - 1) % n;
            temp.remove(res);
            n --;
        }
        return temp.get(0);
    }

    public static void main(String[] args) {
        System.out.println(reverse(-214748));
    }


}
