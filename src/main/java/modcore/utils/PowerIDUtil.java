package modcore.utils;

public class PowerIDUtil {
    // 定义一个静态的int变量extraPowerID
    private static int extraPowerID = 0;

    // 私有的构造函数，防止实例化这个工具类
    private PowerIDUtil() {
    }

    // 静态的get方法，返回当前的extraPowerID值，并且将其加一
    public static int getExtraPowerIDAndAdd() {
        return ++extraPowerID;
    }
    public static int getSourceExtraPowerID() {
        return extraPowerID;
    }
    public static void printExtraPowerID() {
        System.out.println("-----------------------目前的 extraPowerID: " + extraPowerID);
    }
    // 静态的set方法，将extraPowerID重置为零
    public static void resetExtraPowerID() {
        extraPowerID = 0;
    }
}