package de.htwg.se.memory.model.ai;




import java.util.LinkedList;

import de.htwg.se.memory.controller.Controller;
import de.htwg.se.memory.model.playingField.Field;

class FieldWithCordinate{
	
	public int x;
	public int y;
	public Field field;
	
	public FieldWithCordinate(Field field, int x, int y) {
		
		this.field = field;
		this.x = x;
		this.y = y;
	}
	
}

public class Ai {

	private LinkedList<FieldWithCordinate> remeberedFields = null;
	private Controller controller;
	private int difficult;
	
	
	public Ai(Controller controller, int difficult) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		setDifficultLevel(difficult);
		remeberedFields = new LinkedList<FieldWithCordinate>();
	}
	
	
	public final void setDifficultLevel(int difficult){ 
		this.difficult = difficult;
	}
	
	public int getDifficultLevel(){
		
		return this.difficult;
	}
	
	public void remember(Field field ,int x, int y){

		
		for (FieldWithCordinate tmp : remeberedFields) {
			
			if(field.compareTo(tmp.field) == 0 && x == tmp.x && y == tmp.y){
				//wird gelöscht, da es anschließend wieder neu einegfügt wird
				remeberedFields.remove(tmp);
				
				}
		}	
		
		
		FieldWithCordinate tmp = new FieldWithCordinate(field, x, y);
		remeberedFields.add(tmp);

		int maxListSize = 4;
		if(difficult == 1){
			maxListSize = 4;
		}else if(difficult == 2){
			maxListSize = 6;
		}else{
			maxListSize = 8;
		}
		
		System.out.println(maxListSize);
		//Liste auf größe anpassen
		while (remeberedFields.size() > maxListSize) {
			remeberedFields.poll();	
		}
		
		
	}
	

	
	public int[] getChoice(){
		//erstes feld wird geraten
		int[] choices = new int[4];
		
		int randX = (int)(Math.floor(Math.random() * controller.getPlayFieldSize()));
		int randY =(int)(Math.floor(Math.random() * controller.getPlayFieldSize()));
		
		choices[0] = randX;
		choices[1] = randY;

		

		
		//zufallszahl 0 oder 1, wenn eins dann raten mit liste sonst zufall
		if((int)(Math.floor(Math.random() * 2)) == 1){
			//raten mit hilfe der Liste
			System.out.println("Ai prüft liste");
			
			
			//schauen ob zwei gleiche Felder in Liste vorhanden sind
			for (FieldWithCordinate field : remeberedFields) {
				for(FieldWithCordinate field2 : remeberedFields){
					if(field.field.compareTo(field2.field) == 0){
						
						choices[0] = field.x;
						choices[1] = field.y;
						choices[2] = field2.x;
						choices[3] = field2.y;
						System.out.println("Ai weiß es");
						return choices;
					}
				}
			}
			
			
			
			//keine zwei gleichen dann wähle zufällig eins aus und schaue dann ob es in der liste ist

			System.out.println("Ai ratet ein Feld und prüft liste nochmal");
			Field guessedField = controller.getField(randX, randY);
			
			
			
			for (FieldWithCordinate field : remeberedFields) {
				if(field.field.compareTo(guessedField) == 0 && field.x != randX && field.y != randY){
					choices[2] = field.x;
					choices[3] = field.y;
					System.out.println("Ai weiß es");
					return choices;
				}
			}
			System.out.println("Ai nicht fündig geworden");
		}
		//zufällig, wenn nicht in remeberedFields oder wenn nicht in Liste geprüft wurde
		
		//wenn zufällig das gleiche feld nochmal gewählt wird, so lange wiederholen bis anderes raus kommt
		
		//Das bool ist dafür da um zu prüfen ob mindesten 1 mal die schleife durchlaufen wurde
		boolean isLoopExecuted = false; 
		while(choices[2] == choices[0] && choices[1] == choices[3] && isLoopExecuted){
			choices[2] = (int)(Math.floor(Math.random() * controller.getPlayFieldSize()));
			choices[3] = (int)(Math.floor(Math.random() * controller.getPlayFieldSize()));
			isLoopExecuted = true;
		}
		
		
		return choices;
	}
	
	
}
