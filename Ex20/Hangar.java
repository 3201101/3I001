public class Hangar {
	private Loco loco;
	private int id;

	public Hangar(int n) {
		id = n;
		loco = NULL;
	}

	public int getPos() {
		return id;
	}

	public boolean isEmpty() {
		return (loco == NULL);
	}

	public void entrer(int id) {

	}
/*
	public void sortir() {
		
	}
*/
}