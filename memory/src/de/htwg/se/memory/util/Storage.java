package de.htwg.se.memory.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.CloseShieldOutputStream;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Storage {

	private XStream xstream;
	private ZipOutputStream zos;
	private FileOutputStream fos;
	private static final Logger LOG = Logger.getLogger(Storage.class);
	private boolean multiZip;

	public static final String SEPARATOR = System.getProperty("file.separator");

	public static final String DIR_USER = System.getProperty("user.dir");
	public static final String DIR_WORKSPACE = System.getProperty("user.dir") + SEPARATOR;
	public static final String DIR_TMP = DIR_WORKSPACE + "tmp" + SEPARATOR;
	public static final String DIR_PICTURE = DIR_WORKSPACE + "picture" + SEPARATOR;
	public static final String FILE_EXTENSION_ICONCONTAINER = ".if";

	private static final String LOG_OPEN_STREAM = "open new FileOutputStream ";
	private static final String LOG_CLOSE_STREAM = "close FileOutputStream ";

	public Storage() {

		xstream = new XStream(new DomDriver("UTF-8"));
		multiZip = false;
	}

	/**
	 * Save an object as xml.
	 * 
	 * @param obj
	 *            to save
	 * @param filename
	 * @return true if no exception
	 */
	public boolean save(Object obj, String filename) {

		try {
			FileOutputStream foslocal;
			ZipOutputStream zoslocal;
			if (!multiZip) {

				foslocal = new FileOutputStream(filename);
				zoslocal = new ZipOutputStream(foslocal);
				LOG.debug(LOG_OPEN_STREAM + filename);

			} else {

				foslocal = this.fos;
				zoslocal = this.zos;
			}

			ZipEntry ze = new ZipEntry("object");
			zoslocal.putNextEntry(ze);
			xstream.toXML(obj, zoslocal);

			if (!multiZip) {

				zoslocal.close();
				foslocal.close();
				LOG.debug(LOG_CLOSE_STREAM + filename);

			}
			LOG.debug("saved " + filename);
		} catch (Exception ex) {
			LOG.warn("cant save " + filename, ex);
			return false;
		}
		return true;
	}

	/**
	 * Save an object as ObjectStream.
	 * 
	 * @param obj
	 *            to save
	 * @param filename
	 * @return true if no exception
	 */
	public boolean saveSerializable(Object obj, String filePath, String entry) {

		try {
			FileOutputStream foslocal;
			ZipOutputStream zoslocal;
			ObjectOutputStream os;

			if (!multiZip) {

				foslocal = new FileOutputStream(filePath);
				zoslocal = new ZipOutputStream(foslocal);
				LOG.debug(LOG_OPEN_STREAM + filePath);

			} else {

				foslocal = this.fos;
				zoslocal = this.zos;

			}

			ZipEntry ze = new ZipEntry(entry);
			zoslocal.putNextEntry(ze);

			CloseShieldOutputStream shield = new CloseShieldOutputStream(zoslocal);
			os = new ObjectOutputStream(shield);

			os.writeObject(obj);
			os.close();

			if (!multiZip) {

				zoslocal.close();
				foslocal.close();
				LOG.debug(LOG_CLOSE_STREAM + filePath);
			}
			LOG.debug("saved " + filePath);
			return true;
		} catch (Exception exc) {
			LOG.warn("cant save " + filePath, exc);
			return false;
		}

	}

	/**
	 * Create a new zipfile. Leave the streams open. finishZip() is required
	 * urgently!
	 * 
	 * @param filePath
	 *            to the new zipfile
	 * @return true if no Exception
	 */
	public boolean newZip(String filePath) {
		try {
			if (!multiZip) {

				this.fos = new FileOutputStream(filePath);
				this.zos = new ZipOutputStream(fos);
				ZipEntry ze = new ZipEntry("dump");
				zos.putNextEntry(ze);
				multiZip = true;
				LOG.debug(LOG_OPEN_STREAM + filePath);

			}
			return true;
		} catch (Exception e) {
			LOG.warn("cant create a new zipfile ", e);
			return false;
		}
	}

	public boolean finishZip() {
		try {

			if (multiZip) {

				zos.close();
				fos.close();
				LOG.debug("close zipfile");
			}
			multiZip = false;
			return true;
		} catch (Exception e) {
			LOG.warn("cant close zipfile", e);
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
	 * Load an object from an xml file.
	 * 
	 * @param filename
	 *            to load
	 * @return if no exception the loaded object
	 */
	public Object load(String filename) {
		try {

			ZipFile zip = new ZipFile(filename);
			ZipEntry entry = zip.getEntry("object");
			InputStream entryStream = zip.getInputStream(entry);
			Object out = xstream.fromXML(entryStream);

			zip.close();
			LOG.debug("loaded " + out.getClass());
			return out;

		} catch (Exception ex) {
			LOG.trace("cant load " + filename, ex);
			return null;

		}
	}

	public boolean mkdir(String dirPath) {
		boolean mkdir = (new File(dirPath)).mkdirs();
		LOG.debug("mdir " + dirPath + " " + mkdir);
		return mkdir;
	}

	/**
	 * Delete a directory recursively
	 * 
	 * @param directory
	 * @return true if possible
	 */

	public boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						LOG.debug("delete " + files[i].delete());
						files[i].delete();
					}
				}
			}
		}
		boolean deleteDir = directory.delete();
		LOG.debug("deleteDir " + directory + " " + deleteDir);
		return deleteDir;
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

	/**
	 * Rename a file. Delete if file already exists.
	 * 
	 * @param fileOldName
	 *            file to rename
	 * @param fileNewName
	 *            new name of the file.
	 * @return true if possible.
	 */
	public boolean rename(String fileOldName, String fileNewName) {

		File file = new File(fileOldName);
		File file2 = new File(fileNewName);
		if (file2.exists()) {
			LOG.debug("file exists " + fileNewName);
			this.delete(fileNewName);
		}
		boolean renamed = file.renameTo(file2);
		LOG.debug("file renamed from " + fileOldName + " to " + fileNewName);

		return renamed;
	}

	public boolean copy(String src, String dest) {

		try {
			FileUtils.copyFile(new File(src), new File(dest));
			return true;
		} catch (IOException e) {
			LOG.warn("cant copy file " + src + " to " + dest, e);
			return false;

		}
	}

	public String readFile(String path) {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOG.warn("cant read file " + path, e);
			return "";
		}

	}

	public void writeFile(String path, String content) {
		try {
			FileUtils.writeStringToFile(new File(path), content, StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOG.warn("cant write into file " + path, e);
		}
	}

}
