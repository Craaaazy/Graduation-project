package cn.ning.ysestate.util;

import java.util.Random;

public class ValidateCodeUtil {

    public String getValidCode() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }

        return sb.toString().toLowerCase();
    }
}
