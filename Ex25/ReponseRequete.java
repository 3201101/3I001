public class ReponseRequete {
	private Client c;
	private static int i = 0;
	private int id;
	private int n;

	public ReponseRequete(Client c, int val = 0) {
		id = i;
		i++;
		n = (n) ? n : math.random();
	}

	public String toString() {
		return "[Client " + c + "][Request #" + id + "] " + n;
	}
}