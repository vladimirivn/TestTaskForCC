package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {
    @Override
    public List<Flight> filterDepartureCurrentDateTime(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments().stream()
                        .anyMatch(s -> s.getDepartureDate().isBefore(LocalDateTime.now()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterSegmentsArrivalDateTimeBeforeDepartureDateTime(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments().stream()
                        .anyMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterTotalGroundTime(List<Flight> flights, int totalGroundTime) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        Duration duration = Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                        totalTime += duration.toHours();
                    }
                    return totalTime > totalGroundTime;
                })
                .collect(Collectors.toList());
    }
}
