package lib.vqui.de;

import java.util.List;

public class CountryJSON implements Comparable<CountryJSON>{

	private final String country;
	private final List<TravelJSON> travels;

	public CountryJSON(String countryName, List<TravelJSON> exportListTravel) {
		this.country = countryName;
		this.travels = exportListTravel;
	}

	public String getCountry() {
		return country;
	}

	public List<TravelJSON> getTravels() {
		return travels;
	}

	@Override
	public int compareTo(CountryJSON compare) {
		return getCountry().compareTo(compare.getCountry());
	}
	
}
