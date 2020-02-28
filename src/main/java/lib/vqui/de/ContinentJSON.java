package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;

public class ContinentJSON implements Comparable {

	private final String continent;
	private final List<CountryJSON> countries;

	public ContinentJSON(String continent, ArrayList<CountryJSON> exportListCountry) {
		this.continent = continent;
		this.countries = exportListCountry;
	}

	public List<CountryJSON> getCountries() {
		return countries;
	}

	public String getContinent() {
		return continent;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass().getName().equals(this.getClass().getName())) {
			ContinentJSON compare = (ContinentJSON) o;
			return getContinent().compareTo(compare.getContinent());
		}
		return 0;
	}
}
