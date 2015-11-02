public class Test {
    public static void main(String[] args){
	Salle petriere = new Salle(10, 10);
	Thread[] g = new Thread[7];
	g[0] = new Thread( new Groupe(2, petriere));
	g[1] = new Thread(new Groupe(10, petriere));
	g[2] = new Thread(new Groupe(5, petriere));
	g[3] = new Thread(new Groupe(20, petriere));
	g[4] = new Thread(new Groupe(7, petriere));
	g[5] = new Thread(new Groupe(8, petriere));
	g[6] = new Thread(new Groupe(100, petriere));
	for(int i = 0; i < 7; i++)
	    g[i].start();
    }
}
