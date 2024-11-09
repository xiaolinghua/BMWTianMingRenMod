package modcore.utils;
public class GunShiUtil
{
    // 私有静态字段，用于存储GunShiDamage
    private static int gunShiDamage = 10;
    private static int gunShiMax=10;
    private static int exhaustGunShiDianInOneTurn;
    // 获取当前的GunShiDamage
    public static int getGunShiDamage() {
        return gunShiDamage;
    }
    public static int getGunShiMax()
    {
        return gunShiMax;
    }
    public static int getExhaustGunShiDianInOneTurn()
    {
        return exhaustGunShiDianInOneTurn;
    }
    // 设置GunShiDamage，但不允许设置为负值
    public static void setGunShiDamage(int value) {
        if (value >= 0)
        {
            gunShiDamage = value;
        }
    }
    public static void setExhaustGunShiDianInOneTurn(int value) {
        if (value >= 0)
        {
            exhaustGunShiDianInOneTurn += value;
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
    public static void resetExhaustGunShiDianInOneTurn()
    {
        exhaustGunShiDianInOneTurn=0;
    }
}