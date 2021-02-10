package lib.vqui.de.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lib.vqui.de.model.dto.ContinentDto;
import lib.vqui.de.repositories.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NavigationReader {

  protected List<ContinentDto> continentes;

  @Autowired
  ContinentRepository continentRepository;

  public List<ContinentDto> getContinentes() {
    return continentRepository.findAll().stream().map(ContinentDto::new)
        .collect(Collectors.toCollection(ArrayList<ContinentDto>::new));
  }
}
