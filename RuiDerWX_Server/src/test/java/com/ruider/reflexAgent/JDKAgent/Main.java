package com.ruider.reflexAgent.JDKAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        ElectricCar car = new ElectricCar();
        InvocationHandler carHandle = new CarInvocationHandle(car);
        ClassLoader carClassLoader = ElectricCar.class.getClassLoader();
        Class[] interfaces = ElectricCar.class.getInterfaces();
        Object o = Proxy.newProxyInstance(carClassLoader,interfaces,carHandle);
        Vehicle vehicle = (Vehicle)o;
        vehicle.drive();
        Rechargable rechargable = (Rechargable)o;
        rechargable.reCharge();
    }
}
