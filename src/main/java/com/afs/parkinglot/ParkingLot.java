package com.afs.parkinglot;

import com.afs.exception.InvalidParkingTicketException;
import com.afs.exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private HashMap<Ticket,Car> parkingMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if(isFull()){
            throw new ParkingLotFullException("No available position.");
        }
        Ticket ticket = new Ticket();
        parkingMap.put(ticket,car);
        return ticket;
    }

    private boolean isFull(){
        return parkingMap.size() >= capacity;
    }

    public Car fetch(Ticket ticket) {
        if(ticket.isUsed() || !parkingMap.containsKey(ticket)){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
        ticket.setIsUsed(true);
        return parkingMap.get(ticket);
    }
}
