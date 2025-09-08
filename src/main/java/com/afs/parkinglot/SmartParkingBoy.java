package com.afs.parkinglot;

import java.util.Comparator;

public class SmartParkingBoy extends StandardParkingBoy{
    @Override
    public Ticket park(Car car) {
        parkingLots.sort(Comparator.comparingInt(ParkingLot::getAvailableSpaces).reversed());
        return super.park(car);
    }
}
