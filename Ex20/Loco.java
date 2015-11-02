public class Loco implements Runnable {
	private int id;
	private SegTournant sTournant;
	private SegAccueil sAccueil;
	private PoolHangars pHangars;

	public Loco() {

	}

	public void run() {
		try {
			sAccueil.reserver();
			sTournant.appeler(0);
			sTournant.attendrePositionOK();
			sTournant.entrer(id);
			sAccueil.liberer();
			sTournant.attendrePositionOK();
			pHangars.getHangar( sTournant.getPosition() ).entrer(id);
			sTournant.sortir(id);
		}
		catch(InterruptedException e) {

		}
	}
}