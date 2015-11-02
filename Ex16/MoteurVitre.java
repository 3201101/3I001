public class MoteurVitre implements Runnable {

	private Cote c;
	private 
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

	synchronized attendreDemande() {
		while(!demande)
			wait;
	}

	public executerDemande(Operation o)
	{

	}

	public Position getPosition() {

	}

}
