package lib.vqui.de.model.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "travel", schema = "hp")
public class Travel {

	@Id
	@Column(name = "travel_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column(name = "travel_name")
	String name;

	@Column(name = "travel_month")
	int month;

	@Column(name = "travel_year")
	int year;

	@Column(name = "directory")
	String directory;

	@ManyToMany(mappedBy = "linkedTravels")
	Set<Country> countries;

}
