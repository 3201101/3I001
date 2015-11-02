public class MoteurVitre implements Runnable {

	public Cote c;
	boolean demande = false;

	public MoteurVitre(Cote c) {
		this.c = c;
	}

	public run() {
		while(true) {
			attendreDemande();
			executerDemande();
		}
	]

	synchronized  attendreDemande() {
		while(!demande)
			wait;
	}

	public executeDemande(Direction d)
	{

	}

}
