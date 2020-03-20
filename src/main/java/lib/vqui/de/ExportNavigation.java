package lib.vqui.de;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
@Configuration

public class ExportNavigation {

	private ArrayList<ContinentJSON> continentes;
	

	public List<ContinentJSON> getContinents() {
		return continentes;
	}

	public ExportNavigation(NavigationReader serviceReader) {
		this.continentes = (ArrayList<ContinentJSON>) serviceReader.getContinentes();
	}
	
}
