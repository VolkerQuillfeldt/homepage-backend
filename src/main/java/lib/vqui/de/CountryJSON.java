package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;

public class CountryJSON implements Comparable{

	private final String country;
	private final List<TravelJSON> travels;

	public CountryJSON(String countryName, ArrayList<TravelJSON> exportListTravel) {
		this.country = countryName;
		this.travels = exportListTravel;
	}

	public String getCountry() {
		return country;
	}

	public List<TravelJSON> getTravels() {
		return travels;
	}

	public int compareTo(Object o) {
		if (o.getClass().getName().equals(this.getClass().getName())) {
			CountryJSON compare = (CountryJSON) o;
			return getCountry().compareTo(compare.getCountry());
		}
		return 0;
	}

}
