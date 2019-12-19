package com.acupt.surl.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujie
 */
public class SurlUtil {

    private static final char[] DIGITS = "MqNaB3zVxC6sXw9ZeAdS1cDv4FfGr7HtJg2KbL5nPhOy8IuUjY0mTkRiEoWlQp".toCharArray();

    private static final int DIGITS_LEN = DIGITS.length;

    private static Map<Character, Integer> DIGITS_IDX_MAP = new HashMap<>();

    static {
        for (int i = 0; i < DIGITS.length; i++) {
            DIGITS_IDX_MAP.put(DIGITS[i], i);
        }
    }

    public static String seq2id(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % DIGITS_LEN);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / DIGITS_LEN;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.reverse().toString();
    }

    public static long id2seq(String id) {
        long sum = 0L;
        int len = id.length();
        for (int i = 0; i < len; i++) {
            int idx = DIGITS_IDX_MAP.getOrDefault(id.charAt(len - i - 1), -1);
            sum += idx * Math.pow((double) DIGITS_LEN, (double) i);
        }
        return sum;
    }
}
