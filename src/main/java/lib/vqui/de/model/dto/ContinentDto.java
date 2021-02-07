package lib.vqui.de.model.dto;

import java.util.List;
import java.util.stream.Collectors;
import lib.vqui.de.model.entity.Continent;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContinentDto {

  private final String continent;
  private final List<CountryDto> countries;

  public ContinentDto(Continent continent) {
    this.continent = continent.getName();
    countries = continent.getCountries().stream().map(CountryDto::new).collect(
        Collectors.toList());
  }


}
