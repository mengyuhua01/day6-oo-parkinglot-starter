package com.afs.parkinglot;

import java.util.TreeSet;

public class SuperParkingBoy extends StandardParkingBoy{
    @Override
    public Ticket park(Car car) {
        parkingLots.sort((o1, o2) -> {
            double rate1 = (double)o1.getAvailableSpaces() / o1.getCapacity();
            double rate2 = (double)o2.getAvailableSpaces() / o2.getCapacity();
            return Double.compare(rate2, rate1);
        });
        return super.park(car);
    }
}
