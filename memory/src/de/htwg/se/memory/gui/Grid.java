package de.htwg.se.memory.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.htwg.se.memory.util.IconContainer;

public class Grid extends JPanel {

	private static final long serialVersionUID = -234046136726127157L;

	private Map<String, Component> cards;
	private int width;
	private int height;
	private int size;

	// width height pictures
	public Grid(int width, int height, int size) {

		this.width = width;
		this.height = height;
		this.size = size;
		this.cards = new HashMap<String, Component>();

		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		this.setLayout(new GridLayout(width, height, 10, 10));
		this.setBackground(Color.white);
		this.setName("grid");
		this.setBorder(border);

		JPanel[] gridPanels = initEmptyPictures();
		for (int i = 0; i < gridPanels.length; i++) {
			this.add(gridPanels[i]);
		}

	}

	private JPanel[] initEmptyPictures() {

		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(), loweredbevel);

		JButton[] pictures = new JButton[width * height];
		JPanel[] panels = new JPanel[pictures.length];
		
		ActionListener listener = new GridActionListener(size);

		for (int i = 0; i < pictures.length; i++) {

			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
			panels[i].setBorder(border);
			panels[i].setLayout(new BorderLayout());
			panels[i].setName("CAD" + i);

			pictures[i] = new JButton();
			pictures[i].setName("BTN" + i);
			pictures[i].addActionListener(listener);
			pictures[i].setPreferredSize(new Dimension(this.size, this.size));
			pictures[i].setBorderPainted(false);
			pictures[i].setFocusPainted(false);
			pictures[i].setContentAreaFilled(false);
			
			
			//TODO
			IconContainer icons = IconContainer.getInstance();
			pictures[i].setIcon(icons.getIcon("PIC"+ (IconContainer.CARD_BACK),this.size,this.size));
			

			this.cards.put("BTN" + i, pictures[i]);
			this.cards.put("CAD" + i, panels[i]);

			panels[i].add(pictures[i], BorderLayout.CENTER);

		}
		return panels;
	}

	public void setIcon(String btnID, String picID) {

		if (btnID.contains("BTN") && picID.contains("PIC")) {

			IconContainer icons = IconContainer.getInstance();

			((JButton) this.cards.get(btnID)).setIcon(icons.getIcon(picID, this.size,
					this.size));
		}

	}

	/**
	 * Offers the ability to get components by their name
	 * 
	 * @param name
	 *            of the component
	 * @return the component or null if not exists
	 */
	public Component getComponent(String name) {
		if (cards.containsKey(name)) {
			return (Component) cards.get(name);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 *            of the component
	 * @return true if the componentn exists else false
	 */
	public boolean containsComponent(String name) {
		return cards.containsKey(name);
	}

	/**
	 * 
	 * @return the overall count of all components
	 */
	public int getComponentMapCount() {
		return cards.size();
	}

	/**
	 * 
	 * @return the entirety keySet
	 */
	public Set<String> getComponentMapKeySet() {
		return cards.keySet();
	}

}
