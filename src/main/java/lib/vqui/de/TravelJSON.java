package lib.vqui.de;

public class TravelJSON implements Comparable<TravelJSON> {

	private final String travel;
	private final String path;

	public TravelJSON(String travelName, String travelPath) {
		this.travel = travelName;
		travelPath = travelPath.replaceAll(":\\\\", Constants.DISC_SEP);
		travelPath = travelPath.replaceAll("\\\\", Constants.FILE_SEP);
		travelPath = travelPath.replace("#", Constants.HASH_SEP);
		travelPath = travelPath.replace(" ", Constants.EMPTY_SEP);
		this.path = travelPath;
	}

	public String getTravel() {
		return travel;
	}

	public String getPath() {
		return path;
	}
	
	
	public int compareTo(TravelJSON obj) {
		return getTravel().compareTo(obj.getTravel());
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TravelJSON other = (TravelJSON) o;

		return getTravel().equals(other.getTravel());
	}

}
