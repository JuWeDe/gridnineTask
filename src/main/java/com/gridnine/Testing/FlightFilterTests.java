package com.gridnine.Testing;


import com.gridnine.Filters.ArrivalBeforeDepartureFilter;
import com.gridnine.Filters.DepartureBeforeNowFilter;
import com.gridnine.Filters.FlightFilter;
import com.gridnine.Filters.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.Models.Flight;
import com.gridnine.Models.FlightBuilder;
import com.gridnine.Models.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTests {
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void testDepartureBeforeNowFilter() {
        FlightFilter filter = new DepartureBeforeNowFilter();
        List<Flight> result = filter.filter(flights);
        assertTrue(result.stream().noneMatch(flight ->
                flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())));
    }

    @Test
    void testArrivalBeforeDepartureFilter() {
        FlightFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> result = filter.filter(flights);
        assertTrue(result.stream().flatMap(flight -> flight.getSegments().stream())
                .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
    }

    @Test
    void testGroundTimeExceedsTwoHoursFilter() {
        FlightFilter filter = new GroundTimeExceedsTwoHoursFilter();
        List<Flight> result = filter.filter(flights);
        assertTrue(result.stream().allMatch(flight -> {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                Segment currentSegment = segments.get(i);
                Segment nextSegment = segments.get(i + 1);
                Duration groundTime = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate());
                if (groundTime.toHours() > 2) {
                    return false;
                }
            }
            return true;
        }));
    }
}
