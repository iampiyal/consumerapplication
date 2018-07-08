package consumerservice.service.distancecalculator;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearestNeighbour {
	
	private final Logger logger = LoggerFactory.getLogger(NearestNeighbour.class);
	
	public RouterDetails findShortestRoute(ArrayList<LongitudeLatitudeCalculator> cities){
		
		ArrayList<LongitudeLatitudeCalculator> shortestRouteCities = new ArrayList<LongitudeLatitudeCalculator>(cities.size());
		logger.info("Initial Route   --->  "+ Arrays.toString(cities.toArray()));
		logger.info("Total Distance  --->  "+ new RouterDetails(cities).calculateTotalDistance());
		
		LongitudeLatitudeCalculator longitudeLatitudeCalculator = cities.get((int)(cities.size()*Math.random()));
		updateRoute(shortestRouteCities, cities, longitudeLatitudeCalculator);
		while(cities.size()>=1){
			longitudeLatitudeCalculator = getNextCity(cities, longitudeLatitudeCalculator);
			updateRoute(shortestRouteCities, cities, longitudeLatitudeCalculator);
		}
		return new RouterDetails(shortestRouteCities);
	}
	
	private void updateRoute(ArrayList<LongitudeLatitudeCalculator> shortestRouteCities,ArrayList<LongitudeLatitudeCalculator> cities, LongitudeLatitudeCalculator longitudeLatitudeCalculator){
		shortestRouteCities.add(longitudeLatitudeCalculator);
		cities.remove(longitudeLatitudeCalculator);
		logger.info("Cities in Shortest Route  -->  "+ Arrays.toString(shortestRouteCities.toArray()));
		logger.info("Cities Remaining   -->  "+Arrays.toString(cities.toArray()) + "\n");
	}
	
	private LongitudeLatitudeCalculator getNextCity(ArrayList<LongitudeLatitudeCalculator> cities, LongitudeLatitudeCalculator city ){
		return cities.stream().min((city1,city2) -> {
			int flag = 0;
			if (city1.measureDistance(city)< city2.measureDistance(city)) flag = -1;
			else if (city1.measureDistance(city)> city2.measureDistance(city)) flag = 1;
			return flag;
		}).get();
	}
	

}
