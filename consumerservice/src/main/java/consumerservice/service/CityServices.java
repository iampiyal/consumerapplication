package consumerservice.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import consumerservice.service.distancecalculator.RouterDetails;

@Service
public interface CityServices {
	
	public RouterDetails getShortestDistance(String cityName) throws IOException;

}
