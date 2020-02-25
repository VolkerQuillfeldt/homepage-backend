package lib.vqui.de;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
@Configuration

public class ExportNavigation {

	private ArrayList<Continent> continentes;
	

	public List<Continent> getContinents() {
		return continentes;
	}

	public ExportNavigation(ServiceReader serviceReader) {
		this.continentes = (ArrayList<Continent>) getContinentes(serviceReader);
	}

	public List<Continent> getContinentes(ServiceReader serviceReader) {
		ArrayList<Continent> exportListContinent = new ArrayList<>();
		ArrayList<String> continentList = (ArrayList<String>) serviceReader.getNavigationContinents();
		if (continentList != null &&  ! continentList.isEmpty()) {
			for (Iterator<String> itContinent = continentList.iterator(); itContinent.hasNext();) {
				String continentName = itContinent.next();
				ArrayList<String> countryList = (ArrayList<String>) serviceReader.getNavigationCountries(continentName);
				if (countryList != null && ! countryList.isEmpty()) {
					ArrayList<Country> exportListCountry = new ArrayList<>();
					for (Iterator<String> itCountry = countryList.iterator(); itCountry.hasNext();) {
						String countryName = itCountry.next();
						ArrayList<HashMap> travelList = (ArrayList<HashMap>) serviceReader.getNavigationTravels(continentName+"#"+countryName);
						if (travelList != null && ! travelList.isEmpty()) {
							ArrayList<Travel> exportListTravel = new ArrayList<>();
							for (Iterator itTravel = travelList.iterator(); itTravel.hasNext();) {
								HashMap<String,String> travelMap = (HashMap) itTravel.next();
								String travelName = travelMap.get("name");
								String travelPath = travelMap.get("path");
								Travel thisTravel = new Travel(travelName, travelPath);
								exportListTravel.add(thisTravel);
							}

							exportListTravel = (ArrayList<Travel>) exportListTravel.stream()
									.sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());

							Country thisCounty = new Country(countryName, exportListTravel);
							exportListCountry.add(thisCounty);
						}
					}

					exportListCountry = (ArrayList<Country>) exportListCountry.stream()
							.sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());

					Continent thisContinent = new Continent(continentName, exportListCountry);
					exportListContinent.add(thisContinent);

				}

			}

			exportListContinent = (ArrayList<Continent>) exportListContinent.stream()
					.sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());

		}

		return exportListContinent;
	}
}
