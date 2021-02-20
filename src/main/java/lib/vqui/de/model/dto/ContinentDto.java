package lib.vqui.de.model.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lib.vqui.de.model.entity.Continent;
import lib.vqui.de.model.entity.Country;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContinentDto {

  private final String continent;
  private final List<CountryDto> countries;

  public ContinentDto(Continent continent) {
    this.continent = continent.getName();
    countries = continent.getCountries().stream().filter(country -> !country.getTravels().isEmpty())
        .sorted(Comparator.comparing(Country::getName)).map(CountryDto::new).collect(
            Collectors.toList());
  }


}
