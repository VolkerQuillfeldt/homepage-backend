package lib.vqui.de.model.dto;

import static lib.vqui.de.services.Constants.DISC_SEP;
import static lib.vqui.de.services.Constants.EMPTY_SEP;
import static lib.vqui.de.services.Constants.FILE_SEP;
import static lib.vqui.de.services.Constants.HASH_SEP;

import lib.vqui.de.model.entity.Travel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TravelDto {

	private final String travel;
	private final String path;

	public TravelDto(Travel travel) {
		this.travel = travel.getName();
		String travelPath = travel.getDirectory().replace(":\\", DISC_SEP);
		travelPath = travelPath.replace("\\", FILE_SEP);
		travelPath = travelPath.replace("#", HASH_SEP);
		travelPath = travelPath.replace(" ", EMPTY_SEP);
		path = travelPath;
	}

}
