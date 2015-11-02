class NegativeSoldException extends Exception { // L'exception
	public NegativeSoldException() {
		super();
	}

	public NegativeSoldException(String s) {
		super(s);
	}
}

public class Compte {
	private int numero = 0;
	private float s;
	private boolean isNegativeAllowed; // Autorisation de découverr

	public Compte(float s) throws NegativeSoldException {
		this.setSolde(s);
	}

	public void setSolde(float s) throws NegativeSoldException {
		if (s < 0 && isNegativeAllowed == false)
			throw new NegativeSoldException("Solde negatif interdit");
		else
			this.s = s;
	}

	public float getSolde() {
		return this.s;
	}

	public void addSolde(float s) throws NegativeSoldException {
		this.setSolde(this.s + s);
	}

	public boolean isNegativeAllowed() {
		return this.isNegativeAllowed;
	}

	public void setNegativeAllowed(boolean b) {
		this.isNegativeAllowed = b;
	}

	public static void main(String[] args) {
		try { // necessaire car la creation d'un compte peut kever une exceprion aui si non rattrapee crazhe le programme.
			Compte c = new Compte(1);
			System.out.println(c.getSolde());
			c.setNegativeAllowed(true);
			System.out.println(c.getSolde());
			c.addSolde(-2);
			System.out.println(c.getSolde());
			c.setNegativeAllowed(false);
			c.addSolde(-1); // Ici on leve une exception car set d'une somme negative a un compte sans decouvert.
			System.out.println(c.getSolde());
		}
		catch(NegativeSoldException e) {
			e.printStackTrace();
		}
	}
}
