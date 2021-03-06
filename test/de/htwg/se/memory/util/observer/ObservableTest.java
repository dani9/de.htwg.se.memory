package de.htwg.se.memory.util.observer;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.memory.util.observer.IObserver;
import de.htwg.se.memory.util.observer.IObserver.Topic;
import de.htwg.se.memory.util.observer.impl.Observable;

public class ObservableTest {

	
	
	
		private boolean ping=false;
		private IObserver testObserver;
		private IObservable testObservable;
		
		class TestObserver implements IObserver {

			@Override
			public void update(Topic topic) {
				ping=true;
				
			}
			
		}

		@Before
		public void setUp() throws Exception {
			testObserver = new TestObserver();
			testObservable = new Observable();
			testObservable.addObserver(testObserver);
		}

		@Test
		public void testNotify() {
			assertFalse(ping);
			testObservable.notifyObservers(Topic.GAME_FINISHED);
			assertTrue(ping);
		}
		
		@Test
		public void testRemove() {
			assertFalse(ping);
			testObservable.removeObserver(testObserver);
			testObservable.notifyObservers(Topic.GAME_FINISHED);
			assertFalse(ping);
		}
		
		@Test
		public void testUpdate() {
			assertFalse(ping);
			testObserver.update(Topic.NEW_GAME_STARTED);
			assertTrue(ping);
		}
		
		@Test
		public void testRemoveAll() {
			assertFalse(ping);
			testObservable.removeAllObservers();
			testObservable.notifyObservers(Topic.GAME_FINISHED);
			assertFalse(ping);
		}
	
	
	
	
}
