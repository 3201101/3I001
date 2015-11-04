public class Requete {
	private Client c;
	private static int i = 0;
	private int id;
	private int t;

	public Requete(Client client, int type) {
		id = i;
		i++;
		t = type;
		c = client;
		System.out.println("Création requête " + id + " de type " + t);
	}

	public Client getClient() {
		return c;
	}

	public int getId() {
		return id;
	}
}