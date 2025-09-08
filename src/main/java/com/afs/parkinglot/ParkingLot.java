package com.afs.parkinglot;

import com.afs.exception.InvalidParkingTicketException;
import com.afs.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final HashMap<Ticket,Car> parkingMap = new HashMap<>();

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpaces() {
        return capacity - parkingMap.size();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null.");
        }
        for (Map.Entry<Ticket, Car> entry : parkingMap.entrySet()) {
            if (car.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        if(isFull()){
            throw new ParkingLotFullException("No available position.");
        }
        Ticket ticket = new Ticket();
        parkingMap.put(ticket,car);
        return ticket;
    }

    public boolean isFull(){
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
