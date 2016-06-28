package de.htwg.se.memory.controller;

import static org.junit.Assert.*;

import org.apache.log4j.pattern.FileDatePatternConverter;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.model.player.Player;
import de.htwg.se.memory.model.playingfield.Field;
import de.htwg.se.memory.model.playingfield.PlayingField;

public class ControllerTest {

	Controller controller;

	@Before
	public void setUp() {
		controller = Controller.getInstance();
		controller.startGame(4, "player1Name", "player2Name");
	}

	@Test
	public void nextPlayerTest() {
		assertEquals(controller.getActivePlayerName(), "player1Name");
		controller.nextPlayer();
		assertEquals(controller.getActivePlayerName(), "player2Name");
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
		Player[] player = controller.getPlayers();
		assertEquals(player[0].getPoints(), 0);
		assertEquals(player[1].getPoints(), 0);

	}

	@Test
	public void getTurnTest() {
		assertEquals(controller.getTurn(), 0);
	}
	
	@Test
	public void getFieldTest(){
		Field field = controller.getField(0, 0);
		assertFalse(field.isGuessed());
	}
	
	@Test
	public void getPlayingFieldTest(){
		PlayingField playingfield = controller.getPlayingField();
		assertEquals(playingfield.getColumn(), 4);
	}
	
	@Test
	public void setChoiceTest(){
		Field field = controller.getField(0, 0);
		assertFalse(field.isVisible());
		
		controller.setChoice(0, 0);
		
		assertTrue(field.isVisible());
		
		controller.setChoice(0, 1);
		assertTrue(field.isVisible());
		
		field = controller.getField(0, 1);
		assertTrue(field.isVisible());
		
	}

}
