package com.afs.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    @Override
    public Ticket park(Car car) {
        parkingLots.sort(Comparator.comparingInt(ParkingLot::getAvailableSpaces).reversed());
        return super.park(car);
    }
}
