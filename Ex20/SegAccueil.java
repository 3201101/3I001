import java.util.concurrent.*;

public class SegAccueil {
	private final Lock l = new ReentrantLock();
	private Condition isAvaiable = l.newCondition();

	public void reserver() {
		l.lock();
		System.out.println("réservation du segment d'acceuil");
	}
	
	public void liberer() {
		l.unlock();
		System.out.println("libération du segment d'acceuil");
	}
}