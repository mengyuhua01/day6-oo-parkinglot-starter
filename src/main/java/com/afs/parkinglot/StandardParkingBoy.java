package com.afs.parkinglot;

import com.afs.exception.InvalidParkingTicketException;
import com.afs.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;


public class StandardParkingBoy {
    protected final List<ParkingLot> parkingLots;
    public StandardParkingBoy(){
        this.parkingLots = new ArrayList<>();
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
        if (ticket.isUsed()){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (InvalidParkingTicketException e) {
                continue;
            }
        }
        throw new InvalidParkingTicketException("Unrecognized parking ticket.");
    }

}
