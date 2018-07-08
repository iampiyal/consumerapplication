package consumerservice.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import consumerservice.model.City;
import consumerservice.model.LatitudeLongitudeDetails;
import consumerservice.service.CityServices;
import consumerservice.service.distancecalculator.LongitudeLatitudeCalculator;
import consumerservice.service.distancecalculator.NearestNeighbour;
import consumerservice.service.distancecalculator.RouterDetails;

@Service
public class CityServicesImpl implements CityServices {

	private static final String CITY_FILEPATH = "E:/Exercise_CS/city.txt"; // Currently using local path 
	private static final String LATITUDE_LONGITUDE_FILEPATH = "E:/Exercise_CS/latitudelongitude.txt"; // Currently using local path 
	private final Logger logger = LoggerFactory.getLogger(CityServicesImpl.class);

	@Override
	public RouterDetails getShortestDistance(String cityName) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		List<City> cityDetails = new ArrayList<City>();
		List<String> destinyCityList = new ArrayList<String>();
		Lists.newArrayList(Files.readAllLines(Paths.get(CITY_FILEPATH))).parallelStream().forEach(line -> {
			try {
				cityDetails.add(mapper.readValue(line, City.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		cityDetails.stream().filter(city -> city.getCityName().equals(cityName)).collect(Collectors.toList()).stream()
				.forEach(city -> destinyCityList.add(city.getDestiny()));

		return calculateShortestDistance(cityName, destinyCityList);
	}

	/**
	 * To Implement the TSP algorithm by converting all the cities to graph
	 * nodes
	 * 
	 * @param list
	 * @throws IOException
	 */
	private RouterDetails calculateShortestDistance(String city, List<String> destinationCityList) throws IOException {
		return printShortestPath(city, new NearestNeighbour().findShortestRoute(jsonToObjectMapperforDestinations(destinationCityList)));
	}

	/**
	 * To print the result to the console
	 * @param city
	 * @param route
	 */
	
	private RouterDetails printShortestPath(String city, RouterDetails route) {
		
		logger.info("Shortest Route found so far from  the source city of   "+city+"   is -- >  " + route);
		logger.info("Total Distance travelled is   " + route.calculateTotalDistance());
		return route;
	}

	private ArrayList<LongitudeLatitudeCalculator> jsonToObjectMapperforDestinations(List<String> destinationCityList) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		ArrayList<LongitudeLatitudeCalculator> latitudeLongitueDetails = new ArrayList<LongitudeLatitudeCalculator>();
		destinationCityList.forEach(city -> {
			try {
				readAllLinesFromFile().forEach(line -> {
					try {
						LatitudeLongitudeDetails mappedObjectFromJson = mapper.readValue(line,
								LatitudeLongitudeDetails.class);
						if (mappedObjectFromJson.getName().equals(city))
						latitudeLongitueDetails.add(new LongitudeLatitudeCalculator(mappedObjectFromJson.getName(),
								mappedObjectFromJson.getLatitude(), mappedObjectFromJson.getLongitude()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return latitudeLongitueDetails;
	}

	/**
	 * This method reads the file from the source 
	 * @return
	 * @throws IOException
	 */
	private Stream<String> readAllLinesFromFile() throws IOException {
		return Lists.newArrayList(Files.readAllLines(Paths.get(LATITUDE_LONGITUDE_FILEPATH))).parallelStream();
	}

}
