package com.example.registerchangenew;

public class FunnyUpercase {
    public static String toFunnyUpercase(String textInit, boolean isSwitchOn) {
        StringBuilder str2 = new StringBuilder();
        StringBuilder text = new StringBuilder(textInit.toLowerCase());
        boolean NotUpperCase = false;
        isSwitchOn = false; //!!!!  не работат, понять почему  !!!!
        int i = isSwitchOn ? 0 : 1;
        if (isSwitchOn)
            str2.append(text.charAt(0));
        for (; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (((Character.isLetter(ch) || (ch == ' ')) && !NotUpperCase) || (ch == ' ')) {
                NotUpperCase = true;
                str2.append(Character.toUpperCase(ch));
            } else {
                NotUpperCase = false;
                str2.append(ch);
            }
        }
        return str2.toString();
    }
}
