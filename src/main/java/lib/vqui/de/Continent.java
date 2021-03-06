package lib.vqui.de;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "hp.continent")
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
}
