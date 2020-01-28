package com.zero.system.util;

import java.util.Random;

public class InviteCodeUtil {

    private static final char[] r = new char[]{'Q', 'W', 'E', '8', 'A', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P', '5', 'I', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'V', 'Y', 'l', 'T', 'N', '6', 'B', 'G', 'H'};
    private static final char b = 'O';
    private static final int binLen;
    private static final int s = 6;

    public InviteCodeUtil() {
    }

    public static String getInviteCodebyId(long id) {
        char[] buf = new char[32];

        int charPos;
        for(charPos = 32; id / (long)binLen > 0L; id /= (long)binLen) {
            int ind = (int)(id % (long)binLen);
            --charPos;
            buf[charPos] = r[ind];
        }

        --charPos;
        buf[charPos] = r[(int)(id % (long)binLen)];
        String str = new String(buf, charPos, 32 - charPos);
        if (str.length() < 6) {
            StringBuilder sb = new StringBuilder();
            sb.append('O');
            Random rnd = new Random();

            for(int i = 1; i < 6 - str.length(); ++i) {
                sb.append(r[rnd.nextInt(binLen)]);
            }

            str = str + sb.toString();
        }

        return str;
    }

    public static long getIdByInviteCodde(String code) {
        char[] chs = code.toCharArray();
        long res = 0L;

        for(int i = 0; i < chs.length; ++i) {
            int ind = 0;

            for(int j = 0; j < binLen; ++j) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }

            if (chs[i] == 'O') {
                break;
            }

            if (i > 0) {
                res = res * (long)binLen + (long)ind;
            } else {
                res = (long)ind;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String inviteCodebyId = getInviteCodebyId(18600263119L);
        System.out.println("inviteCodebyId = " + inviteCodebyId);
    }

    static {
        binLen = r.length;
    }

}
