package com.afs.parkinglot;

import com.afs.exception.InvalidParkingTicketException;
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
    @Test
    public void should_throw_InvalidParkingTicketException_when_fetch_given_a_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //when
        parkingLot.fetch(ticket);
        //then
        InvalidParkingTicketException exception = assertThrows(InvalidParkingTicketException.class, () -> parkingLot.fetch(ticket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_car_when_fetch_given_a_valid_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //when
        Car fetchedCar = parkingLot.fetch(ticket);
        //then
        assertEquals(car, fetchedCar);
    }
    @Test
    public void should_throw_InvalidParkingTicketException_when_fetch_given_a_unCorrespond_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        parkingLot.park(car);
        Ticket fakeTicket = new Ticket();
        //when
        InvalidParkingTicketException exception = assertThrows(InvalidParkingTicketException.class, () -> parkingLot.fetch(fakeTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_twice_given_two_parked_cars_and_two_tickets() {
        // given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        // when
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);
        // then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }
    @Test
    public void should_return_ticket_when_standard_parking_boy_park_given_a_car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(3);
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        //when
        Ticket ticket = smartParkingBoy.park(car);
        //then
        assertNotNull(ticket);
    }
    @Test
    public void should_throw_exception_when_standard_parking_boy_park_given_all_parking_lots_are_full() {
        //given
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Car car = new Car();
        //when
        ParkingLotFullException parkingLotFullException = assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(car));
        assertEquals("All parkingLots are full.",parkingLotFullException.getMessage());
    }

    @Test
    public void should_throw_exception_when_smart_parking_boy_park_given_all_parking_lots_are_full() {
        //given
        StandardParkingBoy parkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Car car = new Car();
        //when
        ParkingLotFullException parkingLotFullException = assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(car));
        assertEquals("All parkingLots are full.",parkingLotFullException.getMessage());
    }
    @Test
    public void should_return_car_when_standard_parking_boy_fetch_given_a_valid_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        //when
        Car fetchedCar = standardParkingBoy.fetch(ticket);
        //then
        assertEquals(car, fetchedCar);
    }
    @Test
    public void should_throw_InvalidParkingTicketException_when_standard_parking_boy_fetch_given_a_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        //when
        standardParkingBoy.fetch(ticket);
        //then
        InvalidParkingTicketException exception = assertThrows(InvalidParkingTicketException.class, () -> standardParkingBoy.fetch(ticket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_throw_InvalidParkingTicketException_when_standard_parking_boy_fetch_given_a_unCorrespond_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        standardParkingBoy.park(car);
        Ticket fakeTicket = new Ticket();
        //when
        InvalidParkingTicketException exception = assertThrows(InvalidParkingTicketException.class, () -> standardParkingBoy.fetch(fakeTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    void should_park_in_parking_lot_with_most_available_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(6); // 容量10
        ParkingLot parkingLot2 = new ParkingLot(5);  // 容量5
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        smartParkingBoy.park(new Car());
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        Car car1 = parkingLot1.fetch(ticket);
        // Then
        assertEquals(car, car1);
    }
    @Test
    void should_park_in_parking_lot_with_highest_available_position_rate() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10); // 容量10
        ParkingLot parkingLot2 = new ParkingLot(5);  // 容量5
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        Car carToPark = new Car();
        // When
        Ticket ticket = superParkingBoy.park(carToPark);
        Car fetchCar = parkingLot2.fetch(ticket);
        // Then
        assertEquals(carToPark, fetchCar);
    }
}
