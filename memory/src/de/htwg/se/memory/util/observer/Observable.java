package de.htwg.se.memory.util.observer;



import java.util.HashSet;


public class Observable {

	HashSet<IObserver> observers = new HashSet<>();
	
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
	
	public void removeAllObservers(){
		this.observers.clear();
	}
	
	public void notifyObservers(){
		for (IObserver observer : observers) {
			observer.update();	
		}
	}

	

}
