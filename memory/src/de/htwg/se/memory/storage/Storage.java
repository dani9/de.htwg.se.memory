package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

	private String workingdir;
	

	public Storage(String workingdir) throws Exception {
		File dir = new File(workingdir);
		if (!dir.isDirectory()) {
			System.out.println("no dir!");
			throw new Exception();
		}
		this.workingdir = workingdir;
		
	}

	public boolean save(User u) {

		try {

			FileOutputStream out = new FileOutputStream(workingdir + u.getNickname() + ".sav");
			ObjectOutputStream put = new ObjectOutputStream(out);
			put.writeObject(u);
			put.close();

		} catch (Exception ex) {
//			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public User load(User u) {
		try {

			FileInputStream in = new FileInputStream(workingdir + u.getNickname() + ".sav");
			ObjectInputStream user_object = new ObjectInputStream(in);

			Object obj = user_object.readObject();

			user_object.close();

			return (User) obj;

		} catch (Exception ex) {

//			ex.printStackTrace();

			return null;

		}
	}

}
