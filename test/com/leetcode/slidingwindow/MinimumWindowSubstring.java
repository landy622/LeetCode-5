package com.leetcode.slidingwindow;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] times = new int[128];
        for (char c : t.toCharArray()) {
            ++times[c];
        }

        int begin = 0, diff = t.length();
        int minLength = Integer.MAX_VALUE;
        String res = "";
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // this character exists in t
            if(times[c] > 0) --diff;
            --times[c];

            while (diff == 0) {
                if (end - begin < minLength) {
                    minLength = end - begin;
                    res = s.substring(begin, end + 1);
                }
                char kickout = s.charAt(begin++);
                if (++times[kickout] == 1) {
                    ++diff;
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        assertEquals("BANC", minWindow(S, T));
    }
}
