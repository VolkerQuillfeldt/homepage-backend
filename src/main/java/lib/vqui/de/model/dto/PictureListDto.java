package lib.vqui.de.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PictureListDto {

  @ToString.Exclude
  List<String> myPictures;
  boolean isfinal;
}
