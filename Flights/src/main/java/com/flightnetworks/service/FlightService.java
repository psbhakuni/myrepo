package com.flightnetworks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.flightnetworks.Flight;
import com.flightnetworks.util.FileUtility;
import com.flightnetworks.util.FlightComparator;

/**
 * Service layer to serve the results of business logic performed.
 * @author Prakash
 *
 */
@Service
public class FlightService {

   	public List<Flight> getFlightListings(String origin, String destination) {
		Set<Flight> flightListings = new TreeSet<Flight>(new FlightComparator());
		
		List<String> allFlightListings = FileUtility.getContents();
		
		for(String flightListing : allFlightListings) {
			String[] entry = flightListing.split("[|,$]");
			if(origin.equals(entry[0]) && destination.equals(entry[2])){
				Flight flight = new Flight();
				flight.setOrigin(entry[0]);
				flight.setDepartureTime(entry[1]);
				flight.setDestination(entry[2]);
				flight.setDestinationTime(entry[3]);
				flight.setPrice(entry[5]);
				
				flightListings.add(flight);
			}
		}
		return new ArrayList<Flight>(flightListings);
	}
}