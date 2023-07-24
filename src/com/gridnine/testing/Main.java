package com.gridnine.testing;

import com.gridnine.testing.factory.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.impl.FlightFilterImpl;

import java.util.List;

public class Main {
    public static final int GROUND_TIME = 2; // лимит времени проведенного на земле
    public static void main(String[] args) {

        System.out.println("----------- список всех вылетов --------------");
        FlightBuilder.createFlights().forEach(System.out::println);

        List<Flight> flights = new FlightBuilder().createFlights();

        FlightFilter flightFilter = new FlightFilterImpl();

        System.out.println("----------- вылеты до текущего момента времени --------------");
        System.out.println(flightFilter.filterDepartureCurrentDateTime(flights));
        System.out.println("----------- сегменты с датой прилёта раньше даты вылета --------------");
        System.out.println(flightFilter.filterSegmentsArrivalDateTimeBeforeDepartureDateTime(flights));
        System.out.println("----------- перелеты с общим временем, проведённым на земле более заданного лимита --------------");
        System.out.println(flightFilter.filterTotalGroundTime(flights,GROUND_TIME));

    }
}