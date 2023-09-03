package lib.vqui.de.services;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lib.vqui.de.model.dto.ContinentDto;
import lib.vqui.de.model.entity.Continent;
import lib.vqui.de.repositories.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NavigationReader {

  protected List<ContinentDto> continentes;

  @Autowired
  ContinentRepository continentRepository;

  public List<ContinentDto> getContinentes() {
    return continentRepository.findAll().stream().filter(continent -> !continent.getCountries()
        .isEmpty()).sorted(Comparator.comparing(Continent::getName)).map(ContinentDto::new)
        .collect(Collectors.toCollection(LinkedList<ContinentDto>::new));
  }
}
