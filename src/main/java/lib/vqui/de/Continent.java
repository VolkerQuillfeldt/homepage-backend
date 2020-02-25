package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;

public class Continent implements Comparable {

	private final String continent;
	private final List<Country> countries;

	public Continent(String continent, ArrayList<Country> exportListCountry) {
		this.continent = continent;
		this.countries = exportListCountry;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public String getContinent() {
		return continent;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass().getName().equals(this.getClass().getName())) {
			Continent compare = (Continent) o;
			return getContinent().compareTo(compare.getContinent());
		}
		return 0;
	}
}
