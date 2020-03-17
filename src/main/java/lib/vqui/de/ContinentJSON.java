package lib.vqui.de;

import java.util.List;

public class ContinentJSON implements Comparable<ContinentJSON> {

	private final String continent;
	private final List<CountryJSON> countries;

	public ContinentJSON(String continent, List<CountryJSON> exportListCountry) {
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
	public int compareTo(ContinentJSON compare) {
		return getContinent().compareTo(compare.getContinent());
	}

}
