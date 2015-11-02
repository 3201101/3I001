public class Test {
	public static void main(String[] args) {
		EnsembleDonnees d = new EnsembleDonnees();
		Producteur p1 = new Producteur(d);
		Producteur p2 = new Producteur(d);
		Lecteur l1 = new Lecteur(d);
		Lecteur l2 = new Lecteur(d);
		Effaceur e1 = new Effaceur(d);
		Effaceur e2 = new Effaceur(d);

		p1.run();
		p2.run();
		l1.run();
		l2.run();
		e1.run();
		e2.run();
	}
}
