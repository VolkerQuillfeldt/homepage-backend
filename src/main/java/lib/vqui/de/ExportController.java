package lib.vqui.de;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("lib.vqui.de")
public class ExportController {
	
	@Autowired
	private ServiceReader serviceReader;

	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3080"})
	@GetMapping("/getNavigation")
	public ExportNavigation getNavigation() {
		return new ExportNavigation(serviceReader );
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3080"})
	@GetMapping("/getPictures")
	public ExportPictures getPictures(@RequestParam String path, @RequestParam Integer page, @RequestParam Integer count) {
			return new ExportPictures(path,page, count);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.178.*:3080"})
	@GetMapping("/getStartPage")
	public ExportPictures getPictures() {
		String path = "/app/highlights";
		Integer page = Integer.valueOf(1);
		Integer count = Integer.valueOf(10);
		return new ExportPictures(path,page, count);
	}

}
