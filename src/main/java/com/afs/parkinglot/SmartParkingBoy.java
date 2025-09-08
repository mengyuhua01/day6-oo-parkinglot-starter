package com.afs.parkinglot;

public class SmartParkingBoy extends StandardParkingBoy{
    @Override
    public Ticket park(Car car) {
        parkingLots.sort((o1, o2) -> {
            return o2.getAvailableSpaces() - o1.getAvailableSpaces();
        });
        return super.park(car);
    }
}
