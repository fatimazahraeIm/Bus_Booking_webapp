package com.busproject.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busproject.entity.BusRoute;
import com.busproject.entity.RoutePrice;
import com.busproject.repo.BusRouteRepository;
import com.busproject.repo.RoutePriceRepo;
import com.busproject.repo.RouteHoraireRepo;

@Repository
public class BusRouteServiceImpl implements BusRouteService {

	 @Autowired
	    private BusRouteRepository busRouteRepo;
	    
	    @Autowired
	    private RouteHoraireRepo routeHoraireRepo;
	    
	    @Autowired
	    private RoutePriceRepo routePriceRepository;

	    @Override
	    public int addBusRoute(BusRoute busRoute) {
	        busRouteRepo.save(busRoute);
	        return busRoute.getId();
	    }

	    @Override
	    public BusRoute getBusRoute(int id) {
	        return busRouteRepo.findById(id).orElse(null);
	    }

	    @Override
	    public List<BusRoute> getBusRoutesByOrigin(String origin) {
	        return busRouteRepo.findByOrigin(origin);
	    }

	    @Override
	    public List<BusRoute> getBusRoutes() {
	        return busRouteRepo.findAll();
	    }

		@Override
		public List<String> getAllOrigins() {
			return busRouteRepo.findAllOrigin();
		}
		
		@Override
		public List<String> getAllDestinations() {
			return busRouteRepo.findAllDestination();
		}

		@Override
		public boolean removebusRoute(int id) {
			
			BusRoute busRoute = busRouteRepo.findById(id).orElse(null);
			
			if(busRoute != null) {
				busRouteRepo.delete(busRoute);
				return true;
			}
			
			return false;
		}

		@Override
		public BusRoute findByOriginAndDestination(String origin, String destination) {
			return busRouteRepo.findByOriginAndDestination(origin, destination);
		}

		@Override
		public RoutePrice getRoutePrice(int PriceId) {
			return routePriceRepository.findById(PriceId).orElse(null);
		}

		@Override
		public List<LocalTime> getAllHoraires() {
			return routeHoraireRepo.findAllHoraire();
		}

		
   
}
