package lib.vqui.de.controller;

import java.util.List;
import lib.vqui.de.model.dto.ContinentDto;
import lib.vqui.de.model.dto.PictureListDto;
import lib.vqui.de.services.ExportPictures;
import lib.vqui.de.services.NavigationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("lib.vqui.de")
public class ExportController {

	@Value("${dataPath}")
	String dataPath;

	@Autowired
	private NavigationReader serviceReader;

	@Autowired
	private ExportPictures exportPictures;

	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3000"})
	@GetMapping("/getNavigation")
	public ResponseEntity<List<ContinentDto>> getNavigation() {
		return ResponseEntity.ok(serviceReader.getContinentes());
	}

	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3000"})
	@GetMapping("/getRollingPictures")
	public ResponseEntity<PictureListDto> getRollingPictures(@RequestParam String path,
			@RequestParam Integer position) {
		path = dataPath + "webpicture" + path;
		return ResponseEntity.ok(exportPictures.getRollingPictures(position, path));
	}

	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3080"})
	@GetMapping("/getStartPage")
	public ResponseEntity<PictureListDto> getPictures() {
		String path = dataPath + "highlights";
		int page = 1;
		int count = 999;
		return ResponseEntity.ok(exportPictures.getPictures(path, page, count));
	}

}
