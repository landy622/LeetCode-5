package com.interview.mustdo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

// Zillow

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if ((m + n) != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; ++i) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int j = 1; j < dp[0].length; ++j) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        PrettyPrint.print(dp);
        return dp[m][n];
    }

    @Test
    public void test0() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        assertTrue(isInterleave(s1, s2, s3));
    }
}
