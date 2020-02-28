package lib.vqui.de;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hp.country")
public class Country {

	@Id
	@Column(name = "country_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column(name = "country_name")
	String name;

	@ManyToOne
	@JoinColumn(name = "continent_id")
	private Continent continent;

	public Continent getContinent() {
		return continent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "hp.travel_connector", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "travel_id"))
	private Set<Travel> linkedTravels;
	public Set<Travel> getTravels() {
		return linkedTravels;
	}
}
