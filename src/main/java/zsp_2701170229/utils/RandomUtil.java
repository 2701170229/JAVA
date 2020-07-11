package zsp_2701170229.utils;

import java.util.Random;

/**
 * 随机数工具类
 */
public final class RandomUtil {

    private static final Random RANDOM = new Random();

    /**
     * 获取随机码
     * 100000 ～ 999999
     * @return int 1234
     */
    public static String getRandomStr(int count){
        return String.valueOf(getIntRandom(count));
    }

    /**
     * 获取随机码 -- 推荐使用
     * @param count 随机码位数
     * @return int 谁随机数
     */
    public static int getIntRandom(int count){
        int main = 1;
        for (int i = 1; i<count; i++){
            main *= 10;
        }
        return (int)((Math.random()*9+1)*main);
    }

    /**
     * 生成固定长度随机码
     * @param n    长度
     */
    public static long getLongRandom(long n) {
        long min = 1,max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        return (((long) (new Random().nextDouble() * (max - min)))) + min;
    }

    /**
     * 随机生成短信验证码
     * @param count 验证码位数
     * @return
     */
    public static String randomSmsCode(int count) {
        String str = "0,1,2,3,4,5,6,7,8,9";
        String str2[] = str.split(",");
        Random rand = new Random();
        int index = 0;
        String randStr = "";
        for (int i = 0; i < count; ++i) {
            index = rand.nextInt(str2.length - 1);
            randStr += str2[index];
        }
        return randStr;
    }


}
