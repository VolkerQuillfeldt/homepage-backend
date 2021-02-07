package lib.vqui.de.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import lib.vqui.de.model.dto.PictureListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ExportPictures {

  private static String encodeFileToBase64Binary(File file) {
    try (
        FileInputStream fileInputStreamReader = new FileInputStream(file)) {
      byte[] bytes = new byte[(int) file.length()];
      if (fileInputStreamReader.read(bytes) > 0) {
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
      }
    } catch (IOException io) {
      //Log
    }
    return null;
  }

  public PictureListDto getPictures(String path, int page, int count) {

    log.debug("get {}", path);

    AtomicInteger startIndex = new AtomicInteger((page - 1) * count + 1);
    AtomicInteger endIndex = new AtomicInteger(page * count);
    AtomicInteger pictureCount = new AtomicInteger(0);
    List<String> pictures = new LinkedList<>();

    path = path.replace(Constants.DISC_SEP, ":\\\\");
    path = path.replace(Constants.FILE_SEP, "\\\\");
    path = path.replace(Constants.HASH_SEP, "#");
    path = path.replace(Constants.EMPTY_SEP, " ");

    File thisDir = new File(path);

    if (thisDir.isDirectory()) {
      readDirectory(thisDir, startIndex, endIndex, pictureCount, pictures, new AtomicInteger(0));
    }

    int maxPage = 1;
    if (pictureCount.get() > 0) {
      maxPage = pictureCount.get() / count;
      if (pictureCount.get() % count != 0) {
        maxPage++;
      }
    }

    log.debug("return {}", pictures.size());
    return new PictureListDto(pictures, startIndex.get(), endIndex.get(), maxPage);
  }


  private void readDirectory(File thisDir, AtomicInteger startIndex, AtomicInteger endIndex,
      AtomicInteger pictureCount, List<String> pictures, AtomicInteger currentIndex) {

    List<String> okFileExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

    Predicate<File> isFileNotValid = file -> !okFileExtensions
        .contains(StringUtils.getFilenameExtension(file.getName().toLowerCase()));
    File[] dir = thisDir.listFiles(File::isFile);
    if (dir != null) {
      ArrayList<File> files = new ArrayList<>(Arrays.asList(dir));
      files.removeIf(isFileNotValid);
      files.forEach(file -> {
        currentIndex.incrementAndGet();
        pictureCount.incrementAndGet();
        if (startIndex.get() <= currentIndex.get() && currentIndex.get() <= endIndex.get()) {
          pictures.add(encodeFileToBase64Binary(file));
        }
      });
    }

    dir = thisDir.listFiles(File::isDirectory);
    if (dir != null) {
      ArrayList<File> directories = new ArrayList<>(
          Arrays.asList(dir));
      directories.forEach(
          directory -> readDirectory(directory, startIndex, endIndex, pictureCount, pictures,
              currentIndex));
    }
  }
}
