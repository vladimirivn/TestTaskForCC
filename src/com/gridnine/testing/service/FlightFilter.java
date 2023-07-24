package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> filterDepartureCurrentDateTime(List<Flight> flights);
    List<Flight> filterSegmentsArrivalDateTimeBeforeDepartureDateTime(List<Flight> flights);
    List<Flight> filterTotalGroundTime(List<Flight> flights, int totalGroundTime);
}
