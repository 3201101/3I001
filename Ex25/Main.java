import java.util.ArrayList;

public class Main {
	public static final int NBCLIENTS = 10;

	public static void main(String[] args) {
		Serveur s = new Serveur();
		Thread t = new Thread(s);

		ArrayList<Thread> c = new ArrayList();

		for(int i = 0; i < NBCLIENTS; i++)
			c.add(new Thread(new Client(s)));

		// Démarrage des clients
		for(int i = 0; i < NBCLIENTS; i++)
			c.get(i).start();

		// Attente de la fin des clients
		try {
			for(int i = 0; i < NBCLIENTS; i++)
				c.get(i).join();
		}
		catch (InterruptedException e) {

		}

		t.stop();
	}
}