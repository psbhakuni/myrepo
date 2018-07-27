package com.flightnetworks.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightnetworks.Flight;

/**
 * This is a sample client provided to test the application.
 * @author Prakash
 *
 */
public class FlightNetworkClient  {

	private static final String HTTP_LOCALHOST_8080_SEARCH_FLIGHTS = "http://localhost:8080/searchFlights/{origin}/{destination}";
	
	public static void main(String args[]){
		 getFlights("YYZ", "YYC");
		
    }
	
	private static void getFlights(String origin, String destination) {
		List<Flight> flights= fetchFlightDetails(origin, destination);
		if(CollectionUtils.isNotEmpty(flights)) {
			for(Flight flight : flights) {
				System.out.println(flight);
			}
		} else {
			System.out.println("No flights found for "+origin + " --> "+ destination);
		}
	}

	private static List<Flight> fetchFlightDetails(String origin, String destination)	{
		List<Flight> flightsList= new ArrayList<Flight>();
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("origin", origin);
	    params.put("destination", destination);
	     
	    RestTemplate restTemplate = new RestTemplate();
	    List flights = restTemplate.getForObject(HTTP_LOCALHOST_8080_SEARCH_FLIGHTS, List.class, params);
	    
	    ObjectMapper mapper = new ObjectMapper();
		 
		for(int i =0; i< flights.size(); i++) {
			Flight flight = mapper.convertValue(flights.get(i), Flight.class);
			flightsList.add(flight);
		}
	    return flightsList;
	}
}