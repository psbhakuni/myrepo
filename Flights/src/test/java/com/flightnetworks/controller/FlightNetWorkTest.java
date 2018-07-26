package com.flightnetworks.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.flightnetworks.Flight;
import com.flightnetworks.service.FlightService;
@RunWith(MockitoJUnitRunner.class)
public class FlightNetWorkTest {
	@InjectMocks private FlightService service;
    
    @Test
    public void flightCountTest() {
    	List<Flight> flights = service.getFlightListings("YYZ", "YYC");
        assertEquals(flights.size() ,2);
    }
    
  @Test
    public void flightCountTest1() {
    	List<Flight> flights = service.getFlightListings("MIA", "ORD");
        assertEquals(flights.size() ,4);
    }
    
    @Test
    public void flightCountTest2() {
    	List<Flight> flights = service.getFlightListings("LHR", "BOS");
        assertEquals(flights.size() ,3);
    }
    
    @Test
    public void flightCountTest4() {
    	List<Flight> flights = service.getFlightListings("YYZ", "ASDF");
        assertEquals(flights.size() ,0);
    }
    
    @Test
    public void flightCountTest5() {
    	List<Flight> flights = service.getFlightListings("YYZ", "YYZ");
        assertEquals(flights.size() ,0);
    }
}