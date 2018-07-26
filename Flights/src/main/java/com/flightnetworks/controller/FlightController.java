package com.flightnetworks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightnetworks.Flight;
import com.flightnetworks.service.FlightService;

@RestController
public class FlightController {

    @Autowired FlightService service;
    
    @RequestMapping("/searchFlights/{origin}/{destination}")
    public List<Flight> searchFlights(@PathVariable String origin, @PathVariable String destination) {
        return service.getFlightListings(origin, destination);
    }
}