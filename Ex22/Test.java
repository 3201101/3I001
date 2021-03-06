public class Test {
	public static void main(String[] args) {
		EnsembleDonnees d = new EnsembleDonnees();
		Thread p1 = new Thread(new Producteur(d));
		Thread p2 = new Thread(new Producteur(d));
		Thread l1 = new Thread(new Lecteur(d));
		Thread l2 = new Thread(new Lecteur(d));
		Thread e1 = new Thread(new Effaceur(d));
		Thread e2 = new Thread(new Effaceur(d));

		p1.start();
		p2.start();
		l1.start();
		l2.start();
		e1.start();
		e2.start();

		try { Thread.sleep(10000); } catch (Exception e) {}

		p1.stop();
		p2.stop();
		l1.stop();
		l2.stop();
		e1.stop();
		e2.stop();
	}
}
