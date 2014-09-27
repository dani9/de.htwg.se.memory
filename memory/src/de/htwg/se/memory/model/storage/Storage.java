package de.htwg.se.memory.model.storage;

import de.htwg.se.memory.model.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

	private String workingdir;

	public Storage(String workingdir) throws Exception {

		File dir = new File(workingdir);
		if (!dir.isDirectory()) {

			throw new IOException();
		}
		this.workingdir = workingdir;

	}

	public boolean save(User u) {

		try {

			FileOutputStream out = new FileOutputStream(workingdir
					+ u.getNickname());
			ObjectOutputStream put = new ObjectOutputStream(out);
			put.writeObject(u);
			put.close();

		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public User load(User u) {
		try {

			FileInputStream in = new FileInputStream(workingdir
					+ u.getNickname());
			ObjectInputStream userObject = new ObjectInputStream(in);

			Object obj = userObject.readObject();

			userObject.close();

			return (User) obj;

		} catch (Exception ex) {

			return null;

		}
	}

}
