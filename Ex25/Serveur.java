public class Serveur implements Runnable {
	public void run() {
		try	{
			while (true) {
				attendreRequete();
				traiterRequete();
			}
		}
		catch (InterruptedException e) {
			System.out.println("Serveur interrompu");
		}
	}

	public void soumettre(Requete r) {
		
	}

	private void attendreRequete() {

	}

	private void traiterRequete() {
		
	}
}