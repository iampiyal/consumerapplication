package consumerservice.service.distancecalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class RouterDetails {
	
	List<LongitudeLatitudeCalculator> cities = new ArrayList<LongitudeLatitudeCalculator>();
	public RouterDetails(List<LongitudeLatitudeCalculator> cities){this.cities.addAll(cities);}
	
	public int calculateTotalDistance(){
		int citiesSize = this.getCities().size();
		
		return (int)(this.getCities().stream().mapToDouble(x -> {
			int cityIndex = this.getCities().indexOf(x);
			double returnValue = 0;
			if(cityIndex < citiesSize -1) 
				returnValue = x.measureDistance(this.getCities().get(cityIndex+1));
			return returnValue;
		}).sum() + this.getCities().get(citiesSize - 1).measureDistance(this.getCities().get(0)));
	}
	
	public String toString(){return Arrays.toString(cities.toArray());}

}
