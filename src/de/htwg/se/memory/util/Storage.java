package de.htwg.se.memory.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.output.CloseShieldOutputStream;
import org.apache.log4j.Logger;

public class Storage {

	private static final Logger LOG = Logger.getLogger(Storage.class);

	public static final String SEPARATOR = System.getProperty("file.separator");

	public static final String DIR_USER = System.getProperty("user.dir");
	public static final String DIR_WORKSPACE = System.getProperty("user.dir") + SEPARATOR;
	public static final String DIR_TMP = DIR_WORKSPACE + "tmp" + SEPARATOR;
	public static final String DIR_PICTURE = DIR_WORKSPACE + "picture" + SEPARATOR;
	public static final String FILE_EXTENSION_ICONCONTAINER = ".if";

	private static final String LOG_OPEN_STREAM = "open new FileOutputStream ";

	public boolean saveSerializable(Object obj, String filePath, String entry) {

		try {
			FileOutputStream foslocal;
			ZipOutputStream zoslocal;
			ObjectOutputStream os;

			foslocal = new FileOutputStream(filePath);
			zoslocal = new ZipOutputStream(foslocal);
			LOG.debug(LOG_OPEN_STREAM + filePath);

			ZipEntry ze = new ZipEntry(entry);
			zoslocal.putNextEntry(ze);

			CloseShieldOutputStream shield = new CloseShieldOutputStream(zoslocal);
			os = new ObjectOutputStream(shield);

			os.writeObject(obj);
			os.close();

			zoslocal.close();
			foslocal.close();

			LOG.debug("saved " + filePath);
			return true;
		} catch (Exception exc) {
			LOG.warn("cant save " + filePath, exc);
			return false;
		}

	}

	/**
	 * Load an object form an object file
	 * 
	 * @param filePath
	 *            to load
	 * @param entry
	 *            entry in the archive
	 * @return if no exception the loaded object
	 */
	public Object loadSerializable(String filePath, String entry) {
		try {

			ZipFile zip = new ZipFile(filePath);
			ZipEntry ge = zip.getEntry(entry);
			InputStream zis = zip.getInputStream(ge);
			BufferedInputStream istream = new BufferedInputStream(zis);
			ObjectInputStream is = new ObjectInputStream(istream);
			Object object = is.readObject();

			is.close();
			istream.close();
			zip.close();

			LOG.debug("loaded " + object.getClass());
			return object;
		} catch (Exception exc) {
			LOG.trace("cant load " + filePath + " from " + entry, exc);
			return null;
		}
	}
	
	/**
	 * Delete a file.
	 * 
	 * @param filename
	 *            to delete.
	 * @return true if possible
	 */
	public boolean delete(String filename) {
		File file = new File(filename);
		boolean deleteFile = file.delete();
		LOG.debug("deleteFile " + filename + " " + deleteFile);

		return deleteFile;
	}

}
