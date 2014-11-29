package de.htwg.se.memory.util;

import java.awt.Image;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;



public class IconContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1427131171238925111L;
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String WORKINGDIR = System.getProperty("user.dir") + SEPARATOR;
	public static final String PICTUREPATH = WORKINGDIR + "picture" + SEPARATOR;

	private static IconContainer instance = null;
	private List<ImageIcon> images;

	private final int iconCount = 74;
	public final static int LOGO = 0;
	public final static int DONE = 1;
	public final static int BLOCK = 2;
	public final static int CANCEL = 3;
	public final static int STOP = 4;
	public final static int START = 5;
	public final static int ADD = 6;
	public final static int REMOVE = 7;
	public final static int SEARCH = 8;
	public final static int STATS = 9;
	public final static int WARNING = 10;
	public final static int INFO = 11;
	public final static int GLOBE = 12;
	public final static int REFRESH = 13;
	public final static int CLOCK = 14;
	public final static int HOME = 15;
	public final static int CALENDAR = 16;
	public final static int ADDRESS = 17;
	public final static int MAIL = 18;
	public final static int LABLE = 19;
	public final static int BOOK = 20;
	public final static int UP = 21;
	public final static int RIGHT = 22;
	public final static int LEFT = 23;
	public final static int DOWN = 24;
	public final static int EXIT = 25;
	public final static int OPEN = 26;
	public final static int TRASH = 27;
	public final static int TRASH_FULL = 28;
	public final static int TRASH_EMPTY = 29;
	public final static int WALLET = 30;
	public final static int HEART = 31;
	public final static int FLAG = 32;
	public final static int CONFIG = 33;
	public final static int LINK = 34;
	public final static int LINK_BROKEN = 35;
	public final static int CALC = 36;
	public final static int MONITOR = 37;
	public final static int DIAGRAM = 38;
	public final static int EDIT = 39;
	public final static int FOLDER = 40;
	public final static int FOLDER_NEW = 41;
	public final static int FOLDER_CLOSED = 42;
	public final static int FOLDER_OPEN = 43;
	public final static int FOLDER_DELETE = 44;
	public final static int FILE = 45;
	public final static int FILE_NEW = 46;
	public final static int FILE_REMOVE = 47;
	public final static int FILE_RUN = 48;
	public final static int FILE_EMPTY = 49;
	public final static int FILE_FULL = 50;
	public final static int KEY = 51;	
	public final static int LOCKED = 52;
	public final static int QUESTION = 53;
	public final static int HELP = 54;
	public final static int PICTURES = 55;
	public final static int LIST = 56;
	public final static int PLUGIN = 57;
	public final static int PRINT = 58;
	public final static int BROWSER = 59;
	public final static int UNLOCK = 60;
	public final static int USER = 61;
	public final static int SAVE = 62;
	public final static int LOAD = 63;
	public final static int SHARE = 64;
	public final static int CLEAR = 65;
	public final static int FILE_RF = 66;
	public final static int FILE_SF = 67;
	public final static int FILE_TCF = 68;
	public final static int PROG_ICON = 69;
	public final static int LINE = 70;
	public final static int PIN = 71;
	public final static int ZOOM_IN = 72;
	public final static int ZOOM_OUT = 73;
	

	private IconContainer() {

		images = new LinkedList<ImageIcon>();
		for (int i = 0; i < iconCount; i++) {
			ImageIcon img = new ImageIcon(PICTUREPATH + i + ".png");
			images.add(img);
		}

	}

	public static IconContainer getInstance() {
		if (instance == null) {
			
			Storage storage = null;
			try {
				storage = new Storage();
			} catch (Exception e) {
				// TODO
				e.printStackTrace();
			}
			instance = (IconContainer) storage.loadSerializable(PICTUREPATH +"icons.if");
			if (instance == null) {
				instance = new IconContainer();
				storage.saveSerializable(instance, PICTUREPATH + "icons.if");
			}
		}
		return instance;
	}

	public Icon getIcon(int selectedIcon) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		return scale(20, 20, icon);
	}
	
	public Image getImage(int selectedIcon) {
		return images.get(selectedIcon).getImage();
	}

	public Icon getIcon(int selectedIcon, boolean gray) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		if (gray) {
			return grayIcon(scale(20, 20, icon));
		}
		return scale(20, 20, icon);
	}

	public Icon getIcon(int selectedIcon, int width, int height) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		return scale(width, height, icon);
	}

	public Icon getIcon(int selectedIcon, int width, int height, boolean gray) {
		Icon icon = new ImageIcon(images.get(selectedIcon).getImage());
		if (gray) {
			return grayIcon(scale(width, height, icon));
		}
		return scale(width, height, icon);
	}

	private Icon scale(int width, int height, Icon icon) {
		Image img = ((ImageIcon) icon).getImage();
		return new ImageIcon(img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
	}

	private Icon grayIcon(Icon icon) {
		Image img = ((ImageIcon) icon).getImage();
		img = GrayFilter.createDisabledImage(img);
		return new ImageIcon(img);
	}

}
