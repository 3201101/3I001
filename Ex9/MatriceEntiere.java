import java.io.*;

public class MatriceEntiere {
	
    private int[][] matrice;

    final public int colonnes;
    final public int lignes;
	
    public MatriceEntiere()
    {
	this(0, 0);
    }
	
    public MatriceEntiere(int l, int c)
    {
	this.matrice = new int[l][c];
	this.colonnes = c;
	this.lignes = l;
    }
	
    public int getElem(int i, int j)
    {
	return this.matrice[i][j];
    }
	
    public void setElem(int i, int j, int x)
    {
	this.matrice[i][j] = x;
    }

    static void initMatrixFromFile (MatriceEntiere m, String fic) throws FileNotCompliantException, IOException { // Le fichier doit exister
	try( BufferedReader in = new BufferedReader(new FileReader (new File(fic)))){
	    try{
		int l = Integer.parseInt(in.readLine());
		if (l != m.lignes){
		    throw new FileNotCompliantException("Nombres de lignes non correspondant.");
		}
	    
		int c = Integer.parseInt(in.readLine());
		if (c != m.colonnes){
		    throw new FileNotCompliantException("Nombres de colonnes non correspondant.");
		}

		for(int i = 0; i < l; i++){
		    String[] strtab = in.readLine().split(" ");
		    if (strtab.length == 0){
			throw new FileNotCompliantException("Nombres de lignes non correspondant.");
		    }
		    if (strtab.length != c){
			throw new FileNotCompliantException("Nombres de colonnes non correspondant.");
		    }
		    for(int j = 0; j < c; j++){
			try{
			    m.setElem(i, j, Integer.parseInt(strtab[j]));
			}
			catch(ArrayIndexOutOfBoundsException e){
			    throw new FileNotCompliantException("Nombres de colonnes non correspondant.");
			}
		    }
		}
	    }
	    catch(NumberFormatException e){
		throw new FileNotCompliantException("Fichier non convertissable en nombre.");
	    }
	}
    }

    public String toString(){
	String str = "";
	for (int i = 0; i < this.lignes; i++){
	    for(int j = 0; j < this.colonnes; j++){
		str = str.concat(Integer.toString(this.getElem(i, j))).concat(" ");
	    }
	    str = str.concat("\n");
	}
	return str;
    }

    public void print(){
	System.out.println(this.toString());
    }

    public static MatriceEntiere createFromFile(String fic) throws FileNotCompliantException, IOException{
	try( BufferedReader in = new BufferedReader(new FileReader (new File(fic)))){
	    int l = Integer.parseInt(in.readLine());
	    int c = Integer.parseInt(in.readLine());
	
	    MatriceEntiere m = new MatriceEntiere(l, c);
	    MatriceEntiere.initMatrixFromFile(m, fic);
	    return m;
	}
    }

    public void init0 (){
	for(int i = 0; i < this.lignes; i++){
	    for(int j = 0; i < this.colonnes; j++){
		this.setElem(i, j, 0);
	    }
	}
    }

    public MatriceEntiere transpose(){
	MatriceEntiere r = new MatriceEntiere(this.lignes, this.colonnes);
	for(int i = 0; i < this.lignes; i++){
	    for(int j = 0; i < this.colonnes; j++){
		r.setElem(j, i, this.getElem(i, j));
	    }
	}
	return r;
    }

    public MatriceEntiere sommeMatrice(MatriceEntiere m) throws TaillesNonConcordantesException{
	if (this.colonnes != m.colonnes || this.lignes != m.lignes)
	    throw new TaillesNonConcordantesException();
	MatriceEntiere r = new MatriceEntiere(this.lignes, this.colonnes);
	for(int i = 0; i < this.lignes; i++){
	    for(int j = 0; j < this.colonnes; j++){
		r.setElem(i, j, this.getElem(i, j) + m.getElem(i, j));
	    }
	}
	return r;
    }

    public MatriceEntiere produitParScalaire(int s){
	MatriceEntiere r = new MatriceEntiere(this.lignes, this.colonnes);
	for(int i = 0; i < this.lignes; i++){
	    for(int j = 0; i < this.colonnes; j++){
		r.setElem(j, i, this.getElem(i, j) * s);
	    }
	}
	return r;
    }

    public MatriceEntiere produitMatriciel(MatriceEntiere m) throws TaillesNonConcordantesException{
	if (this.colonnes != m.lignes)
	    throw new TaillesNonConcordantesException();
	MatriceEntiere r = new MatriceEntiere(this.lignes, m.colonnes);
	for(int i = 0; i < this.lignes; i++){
	    for(int j = 0; j < m.colonnes; j++){
		for(int k = 0; k < this.colonnes; k++){
		    r.setElem(i, j, r.getElem(i,j) + this.getElem(i, k) * m.getElem(k, j));
		}
	    }
	}
	return r;
    }

    public static int produitLigneColonne (MatriceEntiere m1, int i, int j, MatriceEntiere m2)throws TaillesNonConcordantesException{
	if (m1.colonnes != m2.lignes)
	    throw new TaillesNonConcordantesException();
	int acc = 0;
	for(int k = 0; k < m1.colonnes; k++){
	    acc =+ m1.getElem(i, k) * m2.getElem(k, j);
	}
	return acc;
    }

    public static void main(String[] args) throws FileNotCompliantException, IOException, TaillesNonConcordantesException, InterruptedException
    {
	MatriceEntiere m1 = MatriceEntiere.createFromFile("./donnees_produit1");
	m1.print();
	MatriceEntiere m2 = MatriceEntiere.createFromFile("./donnees_produit2");
	m2.print();
	// MatriceEntiere m3 = MatriceEntiere.createFromFile("./donnees_somme1");
	// m3.print();
	// MatriceEntiere m4 = MatriceEntiere.createFromFile("./donnees_somme2");
	// m4.print();
	// MatriceEntiere m5 = m3.sommeMatrice(m4);
	// m5.print();
	// MatriceEntiere m6 = m1.produitMatriciel(m2);
	// m6.print();
	Thread[] threadTab = new Thread[m1.lignes + m2.colonnes];
	MatriceEntiere m = new MatriceEntiere(m1.lignes, m2.colonnes);
	for(int i = 0; i < m.lignes; i++){
	    for(int j = 0; j < m.colonnes; j++){
		threadTab[i+j] = new Thread(new CalculElem(m, m1, m2, i, j));
		threadTab[i+j].start();
	    }
	}
	for(int i = 0; i < m1.lignes + m2.colonnes; i++)
	    threadTab[i].join();
	m.print();
    }
}

class FileNotCompliantException extends Exception {
    public FileNotCompliantException (){
	super();
    }
     public FileNotCompliantException (String str){
	super(str);
    }
}

class TaillesNonConcordantesException extends Exception {
    public TaillesNonConcordantesException(){
	super();
    }
     public TaillesNonConcordantesException(String str){
	super(str);
    }
}

class CalculElem implements Runnable{
    public final MatriceEntiere m;
    public final MatriceEntiere m1;
    public final MatriceEntiere m2;
    public final int i;
    public final int j;
    public CalculElem(MatriceEntiere m, MatriceEntiere m1, MatriceEntiere m2, int i, int j){
	this.m = m;
	this.m1 = m1;
	this.m2 = m2;
	this.i = i;
	this.j = j;
    }

    public void run(){
	int acc = 0;
	try{
	acc = MatriceEntiere.produitLigneColonne(this.m1, this.i, this.j, this.m2);
	}
	catch(TaillesNonConcordantesException e){
	}
	this.m.setElem(this.i, this.j, acc);
    }
}
