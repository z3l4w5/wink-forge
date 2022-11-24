package cn.foofun.forge;

import java.util.Random;

/**
 * 生成中国三个运营商移动电话号码
 */
public class MobileSource implements Source<String> {

    public static final String[] CHINA_MOBILE = {
            "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159",
            "182", "183", "184", "187", "188", "178", "147", "172", "198"
    };
    //中国联通
    public static final String[] CHINA_UNICOM = {
            "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "166"
    };
    //中国电信
    public static final String[] CHINA_TELECOME = {
            "133", "149", "153", "173", "177", "180", "181", "189", "199"
    };

    public static final String[][] groups = {
            CHINA_MOBILE, CHINA_UNICOM, CHINA_TELECOME
    };

    private int group = -1;

    public MobileSource() {
    }

    public MobileSource(int group) {
        this.group = group;
    }

    @Override
    public String next() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String mobile01; //手机号前三位
        int temp;
        if (group >= 0 && group < 3) {
            mobile01 = groups[group][random.nextInt(CHINA_MOBILE.length)];
        } else {
            int groupIndex = random.nextInt(3);
            String[] mobilePrefix = groups[groupIndex];
            int randomIndex = random.nextInt(mobilePrefix.length);
            mobile01 = mobilePrefix[randomIndex];
        }

        sb.append(mobile01);
        //生成手机号后8位
        for (int i = 0; i < 8; i++) {
            temp = random.nextInt(10);
            sb.append(temp);
        }
        return sb.toString();
    }
}
