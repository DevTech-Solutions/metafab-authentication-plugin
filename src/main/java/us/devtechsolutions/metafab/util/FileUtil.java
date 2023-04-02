package us.devtechsolutions.metafab.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/**
 * @author LBuke (Teddeh)
 */
public final class FileUtil {

	public static String exportResource(String filename, File outputDir, ClassLoader classLoader) {
		//noinspection ResultOfMethodCallIgnored
		outputDir.mkdirs();

		File output = new File(outputDir, filename);
		if (output.exists())
			return output.getAbsolutePath();

		try {
			final URL url = classLoader.getResource(filename);
			if (Objects.isNull(url)) {
				throw new NullPointerException("url is null");
			}

			final URLConnection urlConnection = url.openConnection();
			urlConnection.setUseCaches(false);

			try (final InputStream inputStream = urlConnection.getInputStream()) {
				if(Objects.isNull(inputStream)) {
					throw new RuntimeException("Cannot get resource %s from Jar file.".formatted(filename));
				}

				try (OutputStream outputStream = new FileOutputStream(output)) {
					int readBytes;
					byte[] buffer = new byte[4096];
					while ((readBytes = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, readBytes);
					}
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return output.getAbsolutePath();
	}
}
