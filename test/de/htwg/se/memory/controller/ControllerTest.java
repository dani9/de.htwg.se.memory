package de.htwg.se.memory.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.controller.impl.Controller;
import de.htwg.se.memory.model.player.IPlayer;
import de.htwg.se.memory.model.playingfield.IField;
import de.htwg.se.memory.model.playingfield.IPlayingField;
import de.htwg.se.memory.model.playingfield.impl.PlayingField;
import de.htwg.se.memory.util.observer.IObserver;

public class ControllerTest implements IObserver {

	Controller controller;

	@Before
	public void setUp() {
		controller = Controller.getInstance();
		controller.startGame(4, "player1Name", "player2Name");
		controller.addObserver(this);
	}

	@Test
	public void nextPlayerTest() {
		assertEquals(controller.getActivePlayerName(), "player1Name");
		controller.nextPlayer();
		assertEquals(controller.getActivePlayerName(), "player2Name");
		assertEquals(controller.getInactivePlayerName(), "player1Name");
	}

	@Test
	public void getPlayFieldSizeTest() {
		assertEquals(controller.getPlayFieldSize(), 4);
	}

	@Test
	public void getPlayerNameTest() {
		assertEquals(controller.getPlayerName(1), "player1Name");
		assertEquals(controller.getPlayerName(2), "player2Name");
	}

	@Test
	public void getActivePlayerPointsTest() {
		assertEquals(controller.getActivePlayerPoints(), 0);
	}

	@Test
	public void getPlayerPointsTest() {
		assertEquals(controller.getPlayerPoints(1), 0);
		assertEquals(controller.getPlayerPoints(2), 0);
	}

	@Test
	public void getPlayersTest() {
		IPlayer[] player = controller.getPlayers();
		assertEquals(player[0].getPoints(), 0);
		assertEquals(player[1].getPoints(), 0);

	}

	@Test
	public void getTurnTest() {
		assertEquals(controller.getTurn(), 0);
	}
	
	@Test
	public void getFieldTest(){
		IField field = controller.getField(0, 0);
		assertFalse(field.isGuessed());
	}
	
	@Test
	public void getPlayingFieldTest(){
		IPlayingField playingfield = controller.getPlayingField();
		assertEquals(playingfield.getColumn(), 4);
	}
	
	@Test
	public void setChoiceTest(){
		controller.setPlayingfield(new PlayingField(4));
		IField field = controller.getField(0, 0);
		
		assertFalse(field.isVisible());
		
		controller.setChoice(0, 0);
		
		assertTrue(field.isVisible());
		
		
		/*player one right choice */
		controller.setChoice(0, 1);
		assertTrue(field.isVisible());
		assertTrue(field.isGuessed());
		field = controller.getField(0, 1);
		assertTrue(field.isVisible());
		assertTrue(field.isGuessed());
		
		/*player one false choice */
		controller.setChoice(0, 2);
		field = controller.getField(0, 2);
		assertTrue(field.isVisible());
		controller.setChoice(1, 0);
		field = controller.getField(0, 2);
		
		/* next player call already visible field*/
		controller.setChoice(0, 0);
		field = controller.getField(0, 0);
		assertTrue(field.isGuessed());
		
		
		/*everything to finish game */
		controller.setChoice(0, 2);
		field = controller.getField(0, 2);
		assertTrue(field.isVisible());
		controller.setChoice(0, 3);
		field = controller.getField(0, 3);
		assertTrue(field.isVisible());
		assertTrue(field.isGuessed());
		
		
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				controller.setChoice(i, j);
				field = controller.getField(i, j);
				assertTrue(field.isVisible());
				
				++j;
				
				controller.setChoice(i, j);
				field = controller.getField(i, j);
				assertTrue(field.isVisible());
				assertTrue(field.isGuessed());
				
				
			}
		}
		
		

		
		
		
		
	}

	@Override
	public void update(Topic topic) {
		if(topic == Topic.WAIT_FOR_NEXT_PLAYER){
			controller.nextPlayer();
		}
		
	}

}
