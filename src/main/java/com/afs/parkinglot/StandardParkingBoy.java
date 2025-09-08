package com.afs.parkinglot;

import com.afs.exception.ParkingLotFullException;

import java.util.HashSet;
import java.util.Set;


public class StandardParkingBoy {
    protected final Set<ParkingLot> parkingLots;
    public StandardParkingBoy(){
        this.parkingLots = new HashSet<>();
    }
    public StandardParkingBoy(Set<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingLotFullException("All parkingLots are full.");
    }
    public Car fetch(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (Exception e) {
                //ignore
            }
        }
        throw new IllegalArgumentException("Car not found in any parking lot.");
    }

}
