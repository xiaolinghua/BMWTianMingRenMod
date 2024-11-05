package modcore.utils;
public class GunShiDamageCalculate {
    // 私有静态字段，用于存储GunShiDamage
    private static int gunShiDamage = 10;
    private static int gunShiMax=10;

    // 获取当前的GunShiDamage
    public static int getGunShiDamage() {
        return gunShiDamage;
    }
    public static int getGunShiMax()
    {
        return gunShiMax;
    }
    // 设置GunShiDamage，但不允许设置为负值
    public static void setGunShiDamage(int value) {
        if (value >= 0)
        {
            gunShiDamage = value;
        }
    }

    public static void setGunShiMax(int value)
    {
        if (value >= 0)
        {
            gunShiMax= value;
        }
    }
    // 翻倍GunShiDamage
    public static void doubleGunShiDamage() {
        gunShiDamage *= 2;
    }
    // 增加GunShiDamage
    public static void increaseGunShiDamage(int amount) {
        if (amount > 0) {
            gunShiDamage += amount;
        }
    }
    // 重置GunShiDamage为10点
    public static void resetGunShiDamage() {
        gunShiDamage = 10;
    }
    public static void resetGunShiMax()
    {
        gunShiMax=10;
    }
}