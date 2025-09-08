package com.afs.parkinglot;

import com.afs.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.park(car);
        //then
        assertNotNull(ticket);
    }
    @Test
    public void should_throw_ParkingLotFullException_when_park_given_parkingLot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLotFullException parkingLotFullException = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()));
        assertEquals("No available position.",parkingLotFullException.getMessage());
    }

}
