package de.htwg.se.memory.util;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1427131171238925111L;
	private static final String SEPARATOR = System
			.getProperty("file.separator");
	private static final String WORKINGDIR = System.getProperty("user.dir")
			+ SEPARATOR;
	public static final String PICTUREPATH = WORKINGDIR + "picture" + SEPARATOR;

	private static IconContainer instance = null;
	private Map<String, ImageIcon> images;

	private final int additionalIcons = 1;
	public final static int CARD_BACK = 0;

	private final int cardcolor = 4;
	public final static int SPADE = 100;
	public final static int CLUB = 200;
	public final static int HEART = 300;
	public final static int DIAMOND = 400;

	private final int cardvalue = 13;
	public final static int KING = 0;
	public final static int ACE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	public final static int FOUR = 4;
	public final static int FIVE = 5;
	public final static int SIX = 6;
	public final static int SEVEN = 7;
	public final static int EIGHT = 8;
	public final static int NINE = 9;
	public final static int TEN = 10;
	public final static int QUEEN = 11;
	public final static int JACK = 12;

	private IconContainer() {

		images = new HashMap<String, ImageIcon>();

		for (int i = 0; i < cardcolor; i++) {
			for (int j = 0; j < cardvalue; j++) {
				ImageIcon img = new ImageIcon(PICTUREPATH
						+ new Integer((i * 100) + j) + ".png");
				images.put("PIC" + new Integer((i * 100) + j), img);
			}
		}

		for (int i = 0; i < additionalIcons; i++) {
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
				// TODO
				e.printStackTrace();
			}
			instance = (IconContainer) storage.loadSerializable(PICTUREPATH
					+ "icons.if");
			if (instance == null) {
				instance = new IconContainer();
				storage.saveSerializable(instance, PICTUREPATH + "icons.if");
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
