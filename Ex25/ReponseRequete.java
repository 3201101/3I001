public class ReponseRequete {
	private Client c;
	private static int i = 0;
	private int id;
	private int n;
	private int r;

	public ReponseRequete(Client cl, int req, int val) {
		id = i;
		i++;
		c = cl;
		r = req;
		n = val;
		System.out.println("Réponse requête " + id + " de valeur " + val);
	}

	public String toString() {
		return "[Client " + c + "][Request #" + id + "] " + n;
	}
	
	public int getId() {
		return id;
	}
}