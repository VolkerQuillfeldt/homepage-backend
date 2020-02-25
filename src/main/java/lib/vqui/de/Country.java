package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;

public class Country implements Comparable{

	private final String country;
	private final List<Travel> travels;

	public Country(String countryName, ArrayList<Travel> exportListTravel) {
		this.country = countryName;
		this.travels = exportListTravel;
	}

	public String getCountry() {
		return country;
	}

	public List<Travel> getTravels() {
		return travels;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass().getName().equals(this.getClass().getName())) {
			Country compare = (Country) o;
			return getCountry().compareTo(compare.getCountry());
		}
		return 0;
	}

}
