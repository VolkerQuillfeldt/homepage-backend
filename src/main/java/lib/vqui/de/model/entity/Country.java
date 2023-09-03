package lib.vqui.de.model.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "country", schema = "hp")
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

	@ManyToMany
	@JoinTable(name = "travel_connector", schema = "hp", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "travel_id"))
	private Set<Travel> linkedTravels;

	public Set<Travel> getTravels() {
		return linkedTravels;
	}
}
