package com.gridnine.Filters;


import com.gridnine.Models.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}