package consumerservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import consumerservice.model.City;

import com.fasterxml.jackson.core.JsonParser;


@Service
public class CityServicesImpl implements CityService {

	private static final String FILEPATH = "E:/Exercise_CS/city.txt"; // Currently using local path
	
	@Override
	public List<City> getShortestDistance(String cityName) throws IOException  {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		List<City> cityDetails = new ArrayList<City>();
			Lists.newArrayList(Files.readAllLines(Paths.get(FILEPATH))).parallelStream()
			.forEach(line -> {
				try {
					cityDetails.add(mapper.readValue(line, City.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return calculateShortestDistance(cityDetails.stream().filter(city -> city.getCityName().equals(cityName)).collect(Collectors.toList()));
	}
	
	/**
	 * To Implement the TSP algorithm by converting all the cities to graph nodes
	 * @param list
	 */
	private List<City> calculateShortestDistance(List<City> list){
				list.parallelStream()
				.collect(Collectors.groupingBy(City::getCityName))
				.forEach((processID, processDeatils) -> {
					processDeatils.stream().forEach(System.out::println);
				});
		return null;  // temporary
	}
}
