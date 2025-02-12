package modcore.utils;
public class GunShiUtil
{
    // 私有静态字段，用于存储GunShiDamage
    private static int ErDouZhongGunDamage = 11;
    private static int SanDouZhongGunDamage = 15;
    private static int SiDouZhongGunDamage = 20;
    private static int gunShiMax=9;
    private static int PowerAmount=1;
    private static int exhaustGunShiDianInOneTurn;
    // 获取当前的GunShiDamage
    public static int getErDouZhongGunDamage() {
        return ErDouZhongGunDamage;
    }
    public static int getSanDouZhongGunDamage() {
        return SanDouZhongGunDamage;
    }
    public static int getSiDouZhongGunDamage() {
        return SiDouZhongGunDamage;
    }
    public static int getGunShiMax()
    {
        return gunShiMax;
    }
    public static int getPowerAmount()
    {
        return PowerAmount;
    }
    public static int getExhaustGunShiDianInOneTurn()
    {
        return exhaustGunShiDianInOneTurn;
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
    public static void doubleGunShiDamage()
    {
        ErDouZhongGunDamage *= 2;
        SanDouZhongGunDamage *= 2;
        SiDouZhongGunDamage *= 2;
    }
    // 增加GunShiDamage
    public static void increaseGunShiDamage(int amount)
    {
        if (amount > 0)
        {
            ErDouZhongGunDamage += amount;
            SanDouZhongGunDamage += amount;
            SiDouZhongGunDamage += amount;
        }
    }
    // 重置GunShiDamage为10点
    public static void resetGunShiDamage()
    {
        ErDouZhongGunDamage = 11;
        SanDouZhongGunDamage = 15;
        SiDouZhongGunDamage = 20;
        PowerAmount=1;
    }
    public static void addPowerAmount(int amount)
    {
        if (amount > 0)
        {
            PowerAmount += amount;
        }
    }
    public static void resetGunShiMax()
    {
        gunShiMax=9;
    }
    public static void resetExhaustGunShiDianInOneTurn()
    {
        exhaustGunShiDianInOneTurn=0;
    }
}