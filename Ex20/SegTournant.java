public class SegTournant implements Runnable {
	private final Lock l = new Lock();
	private Condition isOK = l.newCondition();
	private Condition isAvaiable = l.newCondition();
	private Condition isCalled = l.newCondition();
	private Condition isEmpty = l.newCondition();
	private int currentPos;
	private int calledPos;

	public void run() {
		try {
			while(true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}
		catch (InterruptedException e) {
			return;
		}
	}

	/**
	 * Appelle le segment tournant à la position n
	 * @param n identifiant du hangar (0 pour le segAccueil)
	 */
	public void appeler(int n = 0) {
		while(calledPos != -1)	// ******** TODO
			isCalled.await();
		isCalled.signalAll();
		l.lock();
		calledPos = n;
		l.unlock();
	}

	/**
	 * Bloque l'appelant tant que le segment tournant n'est pas en position
	 */
	public void attendrePositionOK() {
		l.lock();
		while(currentPos != calledPos)
			isOK.await();
		l.unlock();
	}

	/**
	 * Déplacer le train vers le hangard en position n
	 * @param n Position visée
	 */
	public void entrer(int n) {
		while(calledPos == currentPos)
			isCalled.await();
		l.lock();
		calledPos = n;
		l.unlock();
		isAvaiable.signalAll();
	}

	public void sortir(int n) {
		l.lock();
		calledPos = -1;
		// TODO print that usless n
		l.unlock();
		isEmpty.signalAll();
	}

	/**
	 * Bloque SegTournant tant qu'il n'est pas appelé par une Loco
	 */
	private void attendreAppel() {
		l.lock();
		while(calledPos != -1)	// ******** TODO
			isCalled.await();
		l.unlock();
	}

	/**
	 * Simule le déplacement en attendant une durée en fonction de la distance à parcourir
	 */
	private void seDeplacer() {
		sleep(abs(currentPos - calledPos) * 250)
		currentPos = calledPos;
		isOK.signalAll();
		l.unlock();
	}

	private void attendreEntree() {
		l.lock();
		while(currentPos != calledPos)
			isAvaiable.await();
		l.unlock();
	}

	private void attendreVide() {
		l.lock();
		while(calledPos == -1)
			isEmpty.await();
		l.unlock();
	}
}
