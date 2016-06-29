package de.htwg.se.memory.util.observer;



import java.util.HashSet;
import java.util.Set;

import de.htwg.se.memory.util.observer.IObserver.Topic;


public class Observable {

	Set<IObserver> observers = new HashSet<IObserver>();
	
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
	
	public void removeAllObservers(){
		this.observers.clear();
	}
	
	public void notifyObservers(Topic topic){
		for (IObserver observer : observers) {
			observer.update(topic);	
		}
	}

	

}
