package com.ruider.reflexAgent.JDKAgent;

/**
 * 可充电驾驶的汽车
 */
public class ElectricCar implements Vehicle, Rechargable {
    @Override
    public void drive() {
        System.out.println("汽车drive");
    }

    @Override
    public void reCharge() {
        System.out.println("汽车reCharge");
    }
}
