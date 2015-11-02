public class Requete {
	private Client c;
	private static int i = 0;
	private int id;
	private int t;

	public Requete(Client client, int type = 0) {
		id = i;
		i++;
		t = type;
		c = client;
	}
}