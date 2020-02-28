package lib.vqui.de;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("NavigationReader")
public class NavigationReader {
	
	protected SessionFactory sessionFactory;
	protected ArrayList<ContinentJSON> continentes = new ArrayList<>();
	
	@Value("${hibernate.config}")
	String hibernate_config;

	public ArrayList<ContinentJSON> getContinentes() {
		return continentes;
	}

	protected void setup() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(hibernate_config) // configures settings
																									// from
																									// hibernate.cfg.xml
				.build();
	

		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {

			ex.printStackTrace();

			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void readNavigation() {

		ContinentJSON curContinentJSON = null;
		CountryJSON curCountry = null;
		ArrayList<CountryJSON> curCountries = null;
		ArrayList<TravelJSON> curTravels = null;
		try {

			Session session = sessionFactory.openSession();

			String hql = "SELECT c.continent.name, c.name, c from Country c inner join c.continent order by c.continent.name, c.name";
			Query query = session.createQuery(hql);
			List<Object[]> listResult = query.list();

			for (Object[] aRow : listResult) {
				String thisContinentName = (String) aRow[0];
				String thisCountryName = (String) aRow[1];
				Country thisCountry = (Country) aRow[2];
				if (!thisCountry.getTravels().isEmpty()) {

					if (curContinentJSON == null || !curContinentJSON.getContinent().equals(thisContinentName)) {
						curCountries = new ArrayList<>();
						curContinentJSON = new ContinentJSON(thisContinentName, curCountries);
						continentes.add(curContinentJSON);
					}
					if (curCountry == null || !curCountry.getCountry().equals(thisCountryName)) {
						curTravels = new ArrayList<>();
						curCountry = new CountryJSON(thisCountryName, curTravels);
						curCountries.add(curCountry);
					}

					Stream<Travel> thisTravels = thisCountry.getTravels().stream();
					List<Object> sortedTravel = thisTravels.sorted().collect(Collectors.toList());
					for (Object travel : sortedTravel) {
						String travelName = ((Travel) travel).getName();
						String travelPath = ((Travel) travel).getDirectory();
						TravelJSON curTravel = new TravelJSON(travelName, travelPath);
						curTravels.add(curTravel);
					}
				}
			}

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@PostConstruct
	public void init() {

		this.setup();
		this.readNavigation();
		this.exit();

	}
}
