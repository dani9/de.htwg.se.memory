package de.htwg.se.memory.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.htwg.se.memory.util.IconContainer;

public class GridActionListener implements ActionListener {

	int size;

	public GridActionListener(int size) {
		super();
		this.size = size;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		IconContainer icons = IconContainer.getInstance();
		JCard source = (JCard) arg0.getSource();
		System.out.println("CardPressed ID: " + source.getName() );
		
		String selectedIcon = "PIC"+(1+ Integer.parseInt(source.getField().getFieldId() )  );
		
		
		System.out.println("FIELD ID AND PIC: "+selectedIcon);
		System.out.println("ID");
		
		source.setIcon(icons.getIcon(selectedIcon,size, size));
	}

}
