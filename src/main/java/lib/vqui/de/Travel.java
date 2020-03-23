package lib.vqui.de;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hp.travel")
public class Travel implements Comparable<Travel> {

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

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	@ManyToMany(mappedBy = "linkedTravels")
	Set<Country> countries;

	public Set<Country> getCountries() {
		return countries;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	public boolean equals(Travel o) {
		return this.getName().equals(o.getName());
	}

	public int compareTo(Travel o) {
		return name.compareToIgnoreCase(o.getName());
	}
}
