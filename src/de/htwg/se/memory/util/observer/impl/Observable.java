package de.htwg.se.memory.util.observer.impl;



import java.util.HashSet;
import java.util.Set;

import de.htwg.se.memory.util.observer.IObservable;
import de.htwg.se.memory.util.observer.IObserver;
import de.htwg.se.memory.util.observer.IObserver.Topic;


public class Observable implements IObservable {

	Set<IObserver> observers = new HashSet<IObserver>();
	
	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	
	@Override
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
	
	@Override
	public void removeAllObservers(){
		this.observers.clear();
	}
	
	@Override
	public void notifyObservers(Topic topic){
		for (IObserver observer : observers) {
			observer.update(topic);	
		}
	}

	

}
