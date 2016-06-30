package de.htwg.se.memory.util;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class IconContainer implements Serializable {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger("de.htwg.se.memory.util");
	private static final long serialVersionUID = 1427131171238925111L;
	private static final String SEPARATOR = System
			.getProperty("file.separator");
	private static final String WORKINGDIR = System.getProperty("user.dir")
			+ SEPARATOR;
	public static final String PICTUREPATH = WORKINGDIR + "picture" + SEPARATOR;

	private static IconContainer instance = null;
	private Map<String, ImageIcon> images;

	private static final int ADDITIONALICONS = 77;
	public static final int CARD_BACK = 0;
	public static final int DONE = 1;
	public static final int BLOCK = 2;
	public static final int CANCEL = 3;
	public static final int STOP = 4;
	public static final int START = 5;
	public static final int ADD = 6;
	public static final int REMOVE = 7;
	public static final int SEARCH = 8;
	public static final int STATS = 9;
	public static final int WARNING = 10;
	public static final int INFO = 11;
	public static final int GLOBE = 12;
	public static final int REFRESH = 13;
	public static final int CLOCK = 14;
	public static final int HOME = 15;
	public static final int CALENDAR = 16;
	public static final int ADDRESS = 17;
	public static final int MAIL = 18;
	public static final int LABLE = 19;
	public static final int BOOK = 20;
	public static final int UP = 21;
	public static final int RIGHT = 22;
	public static final int LEFT = 23;
	public static final int DOWN = 24;
	public static final int EXIT = 25;
	public static final int OPEN = 26;
	public static final int TRASH = 27;
	public static final int TRASH_FULL = 28;
	public static final int TRASH_EMPTY = 29;
	public static final int WALLET = 30;
	public static final int HEARTI = 31;
	public static final int FLAG = 32;
	public static final int CONFIG = 33;
	public static final int LINK = 34;
	public static final int LINK_BROKEN = 35;
	public static final int CALC = 36;
	public static final int MONITOR = 37;
	public static final int DIAGRAM = 38;
	public static final int EDIT = 39;
	public static final int FOLDER = 40;
	public static final int FOLDER_NEW = 41;
	public static final int FOLDER_CLOSED = 42;
	public static final int FOLDER_OPEN = 43;
	public static final int FOLDER_DELETE = 44;
	public static final int FILE = 45;
	public static final int FILE_NEW = 46;
	public static final int FILE_REMOVE = 47;
	public static final int FILE_RUN = 48;
	public static final int FILE_EMPTY = 49;
	public static final int FILE_FULL = 50;
	public static final int KEY = 51;
	public static final int LOCKED = 52;
	public static final int QUESTION = 53;
	public static final int HELP = 54;
	public static final int PICTURES = 55;
	public static final int LIST = 56;
	public static final int PLUGIN = 57;
	public static final int PRINT = 58;
	public static final int BROWSER = 59;
	public static final int UNLOCK = 60;
	public static final int USER = 61;
	public static final int SAVE = 62;
	public static final int LOAD = 63;
	public static final int SHARE = 64;
	public static final int CLEAR = 65;
	public static final int FILE_RF = 66;
	public static final int FILE_SF = 67;
	public static final int FILE_TCF = 68;
	public static final int PROG_ICON = 69;
	public static final int LINE = 70;
	public static final int PIN = 71;
	public static final int ZOOM_IN = 72;
	public static final int ZOOM_OUT = 73;
	public static final int WINDOW = 74;
	public static final int PLAY_PAUSE = 75;
	public static final int FILE_JAVA = 76;

	private static final int CARDCOLOR = 4;
	public static final  int SPADE = 100;
	public static final int CLUB = 200;
	public static final int HEART = 300;
	public static final int DIAMOND = 400;

	private static final int CARDVALUE = 13;
	public static final int KING = 0;
	public static final int ACE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int QUEEN = 11;
	public static final int JACK = 12;

	private IconContainer() {

		images = new HashMap<String, ImageIcon>();

		for (int i = 1; i < CARDCOLOR+1; i++) {
			for (int j = 0; j < CARDVALUE; j++) {
				ImageIcon img = new ImageIcon(PICTUREPATH
						+ new Integer((i * 100) + j) + ".png");
				images.put("PIC" + new Integer((i * 100) + j), img);
			}
		}

		for (int i = 0; i < ADDITIONALICONS; i++) {
			ImageIcon img = new ImageIcon(PICTUREPATH + i + ".png");
			images.put("PIC" + new Integer(i), img);
		}
	}

	public static IconContainer getInstance() {
		if (instance == null) {

			Storage storage = null;
			try {
				storage = new Storage();
			} catch (Exception e) {
				LOGGER.error(e);
			}
			instance = (IconContainer) storage.loadSerializable(PICTUREPATH
					+ "icons.if","pic");
			if (instance == null) {
				instance = new IconContainer();
				LOGGER.debug(instance.images.keySet());
				storage.saveSerializable(instance, PICTUREPATH + "icons.if","pic");
			}
		}
		return instance;
	}

	public Icon getIcon(String selectedIcon) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		return scale(20, 20, icon);
	}

	public Image getImage(String selectedIcon) {
		return images.get(selectedIcon).getImage();
	}

	public Icon getIcon(String selectedIcon, boolean gray) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		if (gray) {
			return grayIcon(scale(20, 20, icon));
		}
		return scale(20, 20, icon);
	}

	public Icon getIcon(String selectedIcon, int width, int height) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		return scale(width, height, icon);
	}

	public Icon getIcon(String selectedIcon, int width, int height, boolean gray) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		if (gray) {
			return grayIcon(scale(width, height, icon));
		}
		return scale(width, height, icon);
	}

	private Icon scale(int width, int height, Icon icon) {
		Image img = ((ImageIcon) icon).getImage();
		return new ImageIcon(img.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH));
	}

	private Icon grayIcon(Icon icon) {
		Image img = ((ImageIcon) icon).getImage();
		img = GrayFilter.createDisabledImage(img);
		return new ImageIcon(img);
	}

}
