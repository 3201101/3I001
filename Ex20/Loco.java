public class Loco implements Runnable {
	private static int i = 1;
	private int id;
	private SegTournant sTournant;
	private SegAccueil sAccueil;
	private PoolHangars pHangars;

	public Loco(SegTournant t, SegAccueil a, PoolHangars p) {
		id = i;
		i++;
		sTournant = t;
		sAccueil = a;
		pHangars = p;
	}

	public void run() {
		try {
			System.out.println("lancement loco " + id);
			sAccueil.reserver();
			sTournant.appeler(0);
			sTournant.attendrePositionOK();
			sTournant.entrer(id);
			sAccueil.liberer();
			sTournant.attendrePositionOK();
			pHangars.getHangar( sTournant.getPosition() ).entrer(this);
			sTournant.sortir(id);
		}
		catch(InterruptedException e) {

		}
	}
}