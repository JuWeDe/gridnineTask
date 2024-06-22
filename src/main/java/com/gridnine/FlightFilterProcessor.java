package com.gridnine;

import com.gridnine.Filters.FlightFilter;
import com.gridnine.Models.Flight;

import java.util.List;

public class FlightFilterProcessor {
    public List<Flight> process(List<Flight> flights, List<FlightFilter> filters) {
        for (FlightFilter filter : filters) {
            flights = filter.filter(flights);
        }
        return flights;
    }
}