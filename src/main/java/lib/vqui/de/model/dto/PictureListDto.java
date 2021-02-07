package lib.vqui.de.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PictureListDto {

  @ToString.Exclude
  List<String> myPictures;
  int pictureStartIndex;
  int pictureEndIndex;
  int maxPage;

  public PictureListDto(List<String> myPictures,
      int pictureStartIndex,
      int pictureEndIndex,
      int maxPage) {
    this.myPictures = myPictures;
    this.pictureStartIndex = pictureStartIndex;
    this.pictureEndIndex = pictureEndIndex;
    this.maxPage = maxPage;
  }
}
