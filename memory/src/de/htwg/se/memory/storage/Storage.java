package de.htwg.se.memory.storage;

import de.htwg.se.memory.user.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

	public Storage() {
		// TODO Auto-generated constructor stub
	}
	public static boolean saveUser(User u, String filepath) {

		try {

			FileOutputStream fout = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(u);
			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public static User loadUser(String filepath) {
		try {

			FileInputStream in = new FileInputStream(filepath);

			ObjectInputStream user_object = new ObjectInputStream(in);

			Object obj = user_object.readObject();

			user_object.close();

			return (User) obj;

		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		}
	}

}
