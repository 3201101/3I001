public class Hangar {
	private Loco loco;
	private int id;

	public Hangar(int n) {
		id = n;
		loco = null;
	}

	public int getPos() {
		return id;
	}

	public boolean isEmpty() {
		return (loco == null);
	}

	public void entrer(Loco l) {
		System.out.println("entrée dans le hangar " + id);
		loco = l;
	}

	public Loco sortir() {
		System.out.println("sortie du hangar " + id);		
		Loco l = loco;
		loco = null;
		return l;
	}

}