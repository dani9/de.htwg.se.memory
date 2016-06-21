package de.htwg.se.memory.model.storage;

import de.htwg.se.memory.model.player.Player;

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

	public boolean save(Player u) {

		try {

			FileOutputStream out = new FileOutputStream(workingdir
					+ u.getName());
			ObjectOutputStream put = new ObjectOutputStream(out);
			put.writeObject(u);
			put.close();

		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public Player load(Player u) {
		try {

			FileInputStream in = new FileInputStream(workingdir
					+ u.getName());
			ObjectInputStream userObject = new ObjectInputStream(in);

			Object obj = userObject.readObject();

			userObject.close();

			return (Player) obj;

		} catch (Exception ex) {

			return null;

		}
	}

}
