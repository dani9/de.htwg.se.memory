package de.htwg.se.memory.util.observer;

import de.htwg.se.memory.util.observer.IObserver.Topic;

public interface IObservable {

	void addObserver(IObserver observer);

	void removeObserver(IObserver observer);

	void removeAllObservers();

	void notifyObservers(Topic topic);

}