package consumerservice.service.distancecalculator;

import lombok.Data;

@Data
public class LongitudeLatitudeCalculator {

	private static final double EARTH_EQUATORIAL_RADIUS = 6378.1370D;
	private static final double CONVERTION_FACTOR_DEGREE_TO_RADIAN = Math.PI / 180D;
	private static final double CONVERTION_FACTOR_KM_TO_MILES = 0.631271;

	private double latitude;
	private double longitude;
	private String name;

	public LongitudeLatitudeCalculator(String name, double latitude, double longitude) {
		this.name = name;
		this.longitude = longitude * CONVERTION_FACTOR_DEGREE_TO_RADIAN;
		this.latitude = latitude * CONVERTION_FACTOR_DEGREE_TO_RADIAN;
	}
	
	public double measureDistance(LongitudeLatitudeCalculator longitudeLatitudeCalculator){
		
		double deltaLongitude = longitudeLatitudeCalculator.getLongitude()- this.getLongitude(); 
		double deltaLatitude = longitudeLatitudeCalculator.getLatitude()- this.getLatitude();
		double distance = Math.pow(Math.sin(deltaLatitude/2D), 2D)+
				Math.cos(this.getLatitude())*Math.cos(longitudeLatitudeCalculator.getLatitude())*Math.pow(Math.sin(deltaLongitude/2D), 2D);
		return CONVERTION_FACTOR_KM_TO_MILES*EARTH_EQUATORIAL_RADIUS*2D* Math.atan2(Math.sqrt(distance), Math.sqrt(1D-distance));
		
	}
	
	public String toString(){return this.name;}

}
