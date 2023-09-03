package lib.vqui.de.model.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lib.vqui.de.model.entity.Country;
import lib.vqui.de.model.entity.Travel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CountryDto {

  private final String country;
  private final List<TravelDto> travels;

  public CountryDto(Country country) {
    this.country = country.getName();
    travels = country.getTravels().stream()
        .sorted(Comparator.comparing(Travel::getYear).thenComparing(Travel::getMonth).reversed())
        .map(TravelDto::new).collect(Collectors.toList());
  }
}
