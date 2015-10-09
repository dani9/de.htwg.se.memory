package de.htwg.se.memory.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

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
		
		String selectedIcon = "PIC"+(1+ Integer.parseInt(source.getField().getFieldId() )  );
		
		
		if (source.getField().isGuessed()){
			source.setIcon(null);
			((JPanel)source.getParent()).setBorder(null);
				
		}else{

			source.setIcon(icons.getIcon(selectedIcon,size, size));
		}
		
	}

}
