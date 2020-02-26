package lib.vqui.de;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ServiceReader")
public class ServiceReader {

	@Value("${dataPath}")
	String dataPath;

	private HashSet<String> continentSet = new HashSet<>();
	private HashMap<String, ArrayList> countryMap = new HashMap<>();
	private HashMap<String, ArrayList> travelMap = new HashMap<>();

	@PostConstruct
	public void init() {
		
		String basePath = dataPath+"webpicture";
		
		File basePathDir = new File(basePath);
		if (basePathDir.exists() && basePathDir.isDirectory()) {
			File[] directories = basePathDir.listFiles(File::isDirectory);
			if (directories.length > 0) {
				for (int i = 0; i < directories.length; i++) {
					String[] continentArray = directories[i].getName().split("#");
					for (int k = 0; k < continentArray.length; k++) {
						continentSet.add(continentArray[k]);
						ArrayList<String> countrySet = new ArrayList<>();
						if (countryMap.containsKey(continentArray[k])) {
							countrySet = countryMap.get(continentArray[k]);
						}
						File[] countries = directories[i].listFiles(File::isDirectory);
						if (countries.length > 0) {
							for (int l = 0; l < countries.length; l++) {
								String[] countryArray = countries[l].getName().split("#");
								for (int m = 0; m < countryArray.length; m++) {
									if (!countrySet.contains(countryArray[m])) {
										countrySet.add(countryArray[m]);
									}
									ArrayList<HashMap> travelSet = new ArrayList<>();
									if (travelMap.containsKey(continentArray[k] + "#" + countryArray[m])) {
										travelSet = travelMap.get(continentArray[k] + "#" + countryArray[m]);
									}
									File[] travels = countries[l].listFiles(File::isDirectory);
									if (travels.length > 0) {
										for (int n = 0; n < travels.length; n++) {
											String travelName = travels[n].getName();
											String travelPath = travels[n].getPath();
											HashMap<String, String> travelDetailMap = new HashMap<>();
											travelDetailMap.put("name", travelName);
											travelDetailMap.put("path", travelPath);
											travelSet.add(travelDetailMap);
										}
									}
									travelMap.put(continentArray[k] + "#" + countryArray[m], travelSet);
								}

							}
						}
						countryMap.put(continentArray[k], countrySet);
					}

				}
			}
		}
	}

	public List<String> getNavigationContinents() {
		return new ArrayList(continentSet);
	}

	public List<String> getNavigationCountries(String continent) {
		return countryMap.get(continent);
	}

	public List<HashMap> getNavigationTravels(String country) {
		return travelMap.get(country);
	}
}
