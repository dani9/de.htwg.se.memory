package de.htwg.se.memory.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Storage {
	private final String workingdir = System.getProperty("user.dir") + System.getProperty("file.separator");
	private XStream xstream;

	public static final String SEPARATOR = System.getProperty("file.separator");
	public static final String WORKINGDIR = System.getProperty("user.dir") + SEPARATOR;

	public Storage() throws Exception {

		File dir = new File(workingdir);
		if (!dir.isDirectory()) {
			throw new IOException();
		}
		xstream = new XStream(new StaxDriver());
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
			// TODO
			if ("tmp\\".indexOf(filename) < 0) {
				filename.replace("tmp\\", WORKINGDIR + "tmp" + SEPARATOR);
			}
			FileOutputStream fos = new FileOutputStream(filename);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("object");
			zos.putNextEntry(ze);
			xstream.toXML(obj, zos);

			zos.close();
			fos.close();

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public boolean saveSerializable(Object obj, String filePath) {

		try {

			FileOutputStream fos = new FileOutputStream(filePath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("object");
			zos.putNextEntry(ze);
			ObjectOutputStream os = new ObjectOutputStream(zos);
			os.writeObject(obj);
			os.close();
			zos.close();
			fos.close();
			return true;
		} catch (Exception exc) {
			return false;
		}

	}

	
	@SuppressWarnings("resource")
	public  Object loadSerializable(String filePath) {
		try {
			ZipFile zip = new ZipFile(filePath);
			ZipEntry entry = zip.getEntry("object");
			BufferedInputStream istream = new BufferedInputStream(zip.getInputStream(entry));
			ObjectInputStream is = new ObjectInputStream(istream);
			return is.readObject();
		} catch (Exception exc) {
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

			// TODO
			if ("tmp\\".indexOf(filename) < 0) {
				filename.replace("tmp\\", WORKINGDIR + "tmp" + SEPARATOR);
			}
			ZipFile zip = new ZipFile(filename);
			ZipEntry entry = zip.getEntry("object");
			InputStream entryStream = zip.getInputStream(entry);
			Object out = xstream.fromXML(entryStream);

			zip.close();
			return out;

		} catch (Exception ex) {
			return null;

		}
	}
	
	
	public boolean mkdir(String dirPath){
		return (new File(dirPath)).mkdirs();
	}
	
	public boolean mktmpdir(String dirName){
		return (new File(WORKINGDIR+"tmp\\"+ dirName)).mkdirs();
	}

	/**
	 * Delete a file.
	 * 
	 * @param filename
	 *            to delete.
	 * @return true if possible
	 */
	public boolean delete(String filename) {
		// TODO
		if ("tmp\\".indexOf(filename) < 0) {
			filename.replace("tmp\\", WORKINGDIR + "tmp" + SEPARATOR);
		}

		File file = new File(filename);
		return file.delete();
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
			this.delete(fileNewName);
		}

		return file.renameTo(file2);
	}
}
