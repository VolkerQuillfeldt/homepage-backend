package lib.vqui.de;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ExportPictures {

	private ArrayList<String> pictures = new ArrayList<>();
	private int pictureCount = 0;
	private int page = 1;
	private int pictureStartIndex = 0;
	private int pictureEndIndex = 0;
	private int maxPage = 0;
	private int count = 0;

	private FileFilter filter = new ImageFileFilter();

	class ImageFileFilter implements FileFilter {
		private final String[] okFileExtensions = new String[] { "jpg", "jpeg", "png", "gif" };

		public boolean accept(File file) {
			for (String extension : okFileExtensions) {
				if (file.getName().toLowerCase().endsWith(extension)) {
					return true;
				}
			}
			return false;
		}
	}

	public ExportPictures(String path, Integer page, Integer count) {
		this.page = page;
		this.count = count;
		this.pictures = getPictures(path, page, count);
	}

	public int getPictureStartIndex() {
		return pictureStartIndex;
	}

	public int getPictureEndIndex() {
		return pictureEndIndex;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getPictureCount() {
		return pictureCount;
	}

	public int getPage() {
		return page;
	}

	public int getCount() {
		return count;
	}

	public List<String> getPictures() {
		return pictures;
	}

	private static String encodeFileToBase64Binary(File file) throws Exception {
		String imageEnc = "";
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		try {
			byte[] bytes = new byte[(int) file.length()];
			if (fileInputStreamReader.read(bytes) > 0) {
				imageEnc = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
			}
			return imageEnc;
		} finally {
			fileInputStreamReader.close();
		}

	}

	private ArrayList<String> getPictures(String path, int page, int count) {

		int startIndex = (page - 1) * count + 1;
		int endIndex = page * count;

		path = path.replace(Constants.DISC_SEP, ":\\\\");
		path = path.replace(Constants.FILE_SEP, "\\\\");
		path = path.replace(Constants.HASH_SEP, "#");
		path = path.replace(Constants.EMPTY_SEP, " ");

		File thisDir = new File(path);
		if (thisDir.isDirectory()) {
			readDirectory(thisDir, startIndex, endIndex);
		}

		if (pictureCount > 0) {
			maxPage = pictureCount / count;
			if (pictureCount % count != 0) {
				maxPage++;
			}
		}
		return pictures;
	}

	private void readDirectory(File thisDir, int startIndex, int endIndex) {
		File[] imageFiles = thisDir.listFiles(filter);
		if (imageFiles.length > 0) {
			for (int i = 0; i < imageFiles.length; i++) {
				readFile(imageFiles[i], startIndex, endIndex);
			}
		}

		File[] directories = thisDir.listFiles(File::isDirectory);
		if (directories.length > 0) {
			for (int i = 0; i < directories.length; i++) {
				File childDir = directories[i];
				readDirectory(childDir, startIndex, endIndex);
			}
		}
	}

	private void readFile(File image, int startIndex, int endIndex) {
		try {
			pictureCount++;
			if (pictureCount >= startIndex && pictureCount <= endIndex) {
				if (pictureStartIndex == 0)
					pictureStartIndex = pictureCount;
				pictures.add(encodeFileToBase64Binary(image));
				pictureEndIndex = pictureCount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
