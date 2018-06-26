package consumerservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import consumerservice.model.City;



@Service
public interface CityService {
	
	public List<City> getShortestDistance(String cityName) throws IOException;

}
