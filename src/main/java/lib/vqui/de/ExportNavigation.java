package lib.vqui.de;

import java.util.List;

@SuppressWarnings("unused")
public class ExportNavigation {

	private List<ContinentJSON> continentes;
	
	public List<ContinentJSON> getContinents() {
		return continentes;
	}

	public ExportNavigation(NavigationReader serviceReader) {
		this.continentes = serviceReader.getContinentes();
	}
	
}
