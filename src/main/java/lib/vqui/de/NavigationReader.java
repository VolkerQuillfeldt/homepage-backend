package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.hibernate.query.Query;
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
	String hibernateConfig;

	public List<ContinentJSON> getContinentes() {
		return continentes;
	}

	protected void setup() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(hibernateConfig) // configures
																													// settings
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

		Session session = null;
		try {

			session = sessionFactory.openSession();

			String hql = "SELECT c.continent.name, c.name, c from Country c inner join c.continent order by c.continent.name, c.name";
			Query query = session.createQuery(hql);
			List<Object[]> listResult = query.list();

			for (Object[] aRow : listResult) {
				String thisContinentName = (String) aRow[0];
				String thisCountryName = (String) aRow[1];
				Country thisCountry = (Country) aRow[2];

				if (!thisCountry.getTravels().isEmpty()) {

					loadContinentes(thisContinentName, thisCountryName, thisCountry);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	protected ContinentJSON curContinentJSON = null;
	protected CountryJSON curCountry = null;
	protected ArrayList<CountryJSON> curCountries = null;
	protected ArrayList<TravelJSON> curTravels = null;

	private void loadContinentes(String thisContinentName, String thisCountryName, Country thisCountry) {

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

	@PostConstruct
	public void init() {

		this.setup();
		this.readNavigation();
		this.exit();

	}
}
