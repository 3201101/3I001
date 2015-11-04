import java.util.concurrent.*;

public class SegTournant implements Runnable {
	private final Lock l = new ReentrantLock();
	private Condition isOK = l.newCondition();
	private Condition isFull = l.newCondition();
	private Condition isCalled = l.newCondition();
	private Condition isEmpty = l.newCondition();
	private int currentPos = 0;
	private int calledPos = -1;

	public void run() {
		try {
			System.out.println("lancement SegTournant");
			while(true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}
		catch (Exception e) {
			return;
		}
	}

	/**
	 * Appelle le segment tournant à la position n
	 * @param n identifiant du hangar (0 pour le segAccueil)
	 */
	public void appeler(int n) {
		System.out.println("Appel du SegTournant pour " + n);
		isCalled.signalAll();
		l.lock();
		calledPos = n;
		l.unlock();
		System.out.println("Fin de l'appel");
	}

	/**
	 * Bloque l'appelant tant que le segment tournant n'est pas en position
	 */
	public void attendrePositionOK() throws InterruptedException {
		System.out.println("Attente de la position");
		l.lock();
		while(currentPos != calledPos)
			isOK.await();
		l.unlock();
		System.out.println("fin attente");
	}

	/**
	 * Déplacer le train vers le hangard en position n
	 * @param n Position visée
	 */
	public void entrer(int n) throws InterruptedException {
		System.out.println("Entré du train dans le seg tournant pour la pos " + n);
		while(calledPos == currentPos)
			isCalled.await();
		l.lock();
		calledPos = n;
		l.unlock();
		isFull.signalAll();
		System.out.println("fin de l'entrée");
	}

	public void sortir(int n) {
		System.out.println("sortie du train en pos " + n);
		l.lock();
		calledPos = -1;
		// TODO print that usless n
		l.unlock();
		isEmpty.signalAll();
		System.out.println("fin de la sortie");
	}

	public int getPosition() {
		return currentPos;
	}

	/**
	 * Bloque SegTournant tant qu'il n'est pas appelé par une Loco
	 */
	private void attendreAppel() throws InterruptedException {
		System.out.println("attente d'un appel d'entré");
		l.lock();
		while(calledPos == -1)
			isCalled.await();
		l.unlock();
		System.out.println("fin de l'attente d'appel");
	}

	/**
	 * Simule le déplacement en attendant une durée en fonction de la distance à parcourir
	 */
	private void seDeplacer() throws InterruptedException {
		System.out.println("début déplacement vers " + calledPos);
		Thread.sleep(Math.abs(currentPos - calledPos) * 250);
		currentPos = calledPos;
		isOK.signalAll();
		l.unlock();
		System.out.println("fin déplacement");
	}

	private void attendreEntree() throws InterruptedException {
		System.out.println("début attente de l'entrée de la loco");
		l.lock();
		while(currentPos != calledPos)
			isFull.await();
		l.unlock();
		System.out.println("fin attente entrée");
	}

	private void attendreVide() throws InterruptedException {
		System.out.println("attente de la sortie de la loco");
		l.lock();
		while(calledPos == -1)
			isEmpty.await();
		l.unlock();
		System.out.println("fin attente sortie");
	}
}
