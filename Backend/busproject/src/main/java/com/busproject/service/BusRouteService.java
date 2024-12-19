package com.busproject.service;

import java.time.LocalTime;
import java.util.List;

import com.busproject.entity.RoutePrice;
import com.busproject.entity.BusRoute;
import com.busproject.entity.RoutePrice;

public interface BusRouteService {
    
    int addBusRoute(BusRoute busRoute);

    BusRoute getBusRoute(int id);
    
    BusRoute findByOriginAndDestination(String origin, String destination);

    List<String> getAllOrigins();
    
    List<String> getAllDestinations();
    
    List<LocalTime> getAllHoraires();
    
    RoutePrice getRoutePrice(int PriceId);
    
    List<BusRoute> getBusRoutesByOrigin(String origin);

    List<BusRoute> getBusRoutes();

    boolean removebusRoute(int id);


}

