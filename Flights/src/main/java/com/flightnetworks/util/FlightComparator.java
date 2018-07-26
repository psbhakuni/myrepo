package com.flightnetworks.util;

import java.util.Comparator;

import com.flightnetworks.Flight;

public class FlightComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight arg0, Flight arg1) {
		int priceDiff = (int)((Double.valueOf(arg0.getPrice()) - Double.valueOf(arg1.getPrice()))*1000) ;
		
		if(Double.valueOf((double)0).equals(priceDiff)){
			String departureTime1 = arg0.getDepartureTime();  
			String departureTime2 = arg1.getDepartureTime();
			return DateTimeUtility.getDateTime(departureTime1).compareTo(DateTimeUtility.getDateTime(departureTime2));
		} else {
			return priceDiff;
		}
	}
	

}
