package lib.vqui.de.model.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "continent", schema = "hp")
public class Continent {

	@Id
	@Column(name = "continent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column(name = "continent_name")
	String name;

	@OneToMany(mappedBy = "continent")
	private Set<Country> countries;

	public Set<Country> getCountries() {
		return countries;
	}

}
