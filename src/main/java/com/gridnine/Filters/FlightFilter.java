package com.gridnine.Filters;


import com.gridnine.Models.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}
