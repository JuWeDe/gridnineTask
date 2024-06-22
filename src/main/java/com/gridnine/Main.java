package com.gridnine;



import com.gridnine.Filters.ArrivalBeforeDepartureFilter;
import com.gridnine.Filters.DepartureBeforeNowFilter;
import com.gridnine.Filters.FlightFilter;
import com.gridnine.Filters.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.Models.Flight;
import com.gridnine.Models.FlightBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Factory class to get sample list of flights.
 */



public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterProcessor processor = new FlightFilterProcessor();

        List<FlightFilter> filters = Arrays.asList(
                new DepartureBeforeNowFilter(),
                new ArrivalBeforeDepartureFilter(),
                new GroundTimeExceedsTwoHoursFilter()
        );

        for (FlightFilter filter : filters) {
            List<Flight> filteredFlights = processor.process(flights, Arrays.asList(filter));
            System.out.println("Filtered flights: " + filter.getClass().getSimpleName());
            filteredFlights.forEach(System.out::println);
            System.out.println();
        }
    }
}


