package com.rat.snowkids.entity.model;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 电源实体
 */
public class Power implements Serializable {
    private boolean isCharging;// 是否充电
    private boolean isPowerFull;// 是否满电
    private boolean isUsbPower;// 是否usb充电
    private boolean isACPower;// 是否交流电充电
    private int level;// 当前剩余电量
    private float batteryPct;// 电量百分比
    private String timeLeft;// 电池剩余时间

    public boolean isCharging() {
        return isCharging;
    }

    public void setIsCharging(boolean isCharging) {
        this.isCharging = isCharging;
    }

    public boolean isPowerFull() {
        return isPowerFull;
    }

    public void setIsPowerFull(boolean isPowerFull) {
        this.isPowerFull = isPowerFull;
    }

    public boolean isUsbPower() {
        return isUsbPower;
    }

    public void setIsUsbPower(boolean isUsbPower) {
        this.isUsbPower = isUsbPower;
    }

    public boolean isACPower() {
        return isACPower;
    }

    public void setIsACPower(boolean isACPower) {
        this.isACPower = isACPower;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBatteryPct() {
        return (int) (batteryPct * 100);
    }

    public void setBatteryPct(float batteryPct) {
        this.batteryPct = batteryPct;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public String toString() {
        return "Power{" +
                "isCharging=" + isCharging +
                ", isPowerFull=" + isPowerFull +
                ", isUsbPower=" + isUsbPower +
                ", isACPower=" + isACPower +
                ", level=" + level +
                ", batteryPct=" + batteryPct +
                ", timeLeft='" + timeLeft + '\'' +
                '}';
    }
}