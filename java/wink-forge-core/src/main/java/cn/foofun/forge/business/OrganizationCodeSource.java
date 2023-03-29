package cn.foofun.forge.business;

import cn.foofun.forge.Source;

import java.util.Random;

public class OrganizationCodeSource implements Source<String> {

    @Override
    public String next() {
        return getOrganizationCode();
    }

    public static String getOrganizationCode() {
        int[] in = {3, 7, 9, 10, 5, 8, 4, 2};
        String data = "";
        String yz = "";
        int a = 0;
        //随机生成英文字母和数字
        for (int i = 0; i < in.length; i++) {
            String word = getCharAndNumr(1, 0).toUpperCase();
            if (word.matches("[A-Z]")) {
                a += in[i] * getAsc(word);
            } else {
                a += in[i] * Integer.parseInt(word);
            }
            data += word;
        }
        //确定序列
        int c9 = 11 - a % 11;
        //判断c9大小，安装 X 0 或者C9
        if (c9 == 10) {
            yz = "X";
        } else if (c9 == 11) {
            yz = "0";
        } else {
            yz = c9 + "";
        }
        data += "-" + yz;
        return data.toUpperCase();
    }

    public static String getCharAndNumr(int length, int status) {
        Random random = new Random();
        StringBuffer valSb = new StringBuffer();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxy";
        if (status == 1) charStr = "0123456789";
        if (status == 2) charStr = "0123456789";
        if (status == 3) charStr = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            if (status == 1 && index == 0) {
                index = 3;
            }
            valSb.append(charStr.charAt(index));
        }
        return valSb.toString();
    }

    public static int getAsc(String st) {
        byte[] gc = st.getBytes();
        int ascNum = (int) gc[0] - 55;
        return ascNum;
    }
}
