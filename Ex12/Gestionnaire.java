public class Gestionnaire implements Runnable {

	Thread g;
	Thread d;

	public run () {
		g = new Thread(new MoteurVitre(GAUCHE));
		d = new Thread(new MoteurVitre(DROITE));

                g.start();
		d.start();

		g.order(DESCENDRE);
		d.order(DESCENDRE);
	}
}
