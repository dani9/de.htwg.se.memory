package de.htwg.se.memory.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.playingField.Field;
import de.htwg.se.memory.util.IconContainer;
import de.htwg.se.memory.util.observer.IObserver.Topic;

public class Grid extends JPanel implements ActionListener {

	private static final long serialVersionUID = -234046136726127157L;

	private Map<String, Component> cards;
	private int size;
	private Controller controller;
	private Topic topic;

	// width height pictures
	public Grid(Controller controller, Topic topic, int width, int height, int size) {
		this.topic = topic;
		this.controller = controller;
		this.size = size;
		this.cards = new HashMap<String, Component>();
		int playFieldSize = controller.getPlayFieldSize();
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		this.setLayout(new GridLayout(playFieldSize, playFieldSize, 10, 10));
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
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), loweredbevel);

		int playFieldSize = controller.getPlayFieldSize();
		JCard[] pictures = new JCard[playFieldSize * playFieldSize];
		JPanel[] panels = new JPanel[pictures.length];

		for (int i = 0; i < pictures.length; i++) {

			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
			panels[i].setBorder(border);
			panels[i].setLayout(new BorderLayout());
			panels[i].setName("CAD" + i);
			// TODO
			int row = (int) i / playFieldSize;
			int column = i % playFieldSize;
			Field field = controller.getField(row, column);
			pictures[i] = new JCard(field, row, column);

			pictures[i].setName("BTN" + i);
			pictures[i].addActionListener(this);
			pictures[i].setPreferredSize(new Dimension(this.size, this.size));
			pictures[i].setBorderPainted(false);
			pictures[i].setFocusPainted(false);
			pictures[i].setContentAreaFilled(false);

			// TODO
			IconContainer icons = IconContainer.getInstance();
			if (field.isGuessed()) {
				pictures[i].setIcon(null);
				panels[i].setBorder(null);

			} else if (field.isVisible()) {
				String selectedIcon = "PIC" + (1 + Integer.parseInt(field.getFieldId()));
				pictures[i].setIcon(icons.getIcon(selectedIcon, size, size));
			} else {
				pictures[i].setIcon(icons.getIcon("PIC" + (IconContainer.CARD_BACK), this.size, this.size));
			}

			this.cards.put("BTN" + i, pictures[i]);
			this.cards.put("CAD" + i, panels[i]);

			panels[i].add(pictures[i], BorderLayout.CENTER);

		}
		return panels;
	}

	public void setIcon(String btnID, String picID) {

		if (btnID.contains("BTN") && picID.contains("PIC")) {

			IconContainer icons = IconContainer.getInstance();

			((JButton) this.cards.get(btnID)).setIcon(icons.getIcon(picID, this.size, this.size));
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

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JCard source = (JCard) arg0.getSource();

		if (source.getField().isGuessed()) {

		} else if (source.getField().isVisible()) {
			switch (topic) {

			case WAIT_FOR_NEXT_PLAYER:
				controller.nextPlayer();
				controller.setChoice(source.getRow(), source.getColumn());
				break;
			default:
				break;

			}
		} else {

			switch (topic) {
			case WAIT_FOR_CHOICE:
				controller.setChoice(source.getRow(), source.getColumn());
				break;

			case WAIT_FOR_NEXT_PLAYER:
				controller.nextPlayer();
				controller.setChoice(source.getRow(), source.getColumn());
				break;
			default:
				break;

			}

		}

	}

}