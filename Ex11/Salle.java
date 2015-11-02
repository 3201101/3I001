public class Salle{
    private boolean[][] placesLibres;
    public final int colonnes;
    public final int lignes;

    public Salle(int i, int j){
	lignes = i;
	colonnes = j;
	placesLibres = new boolean[i][j];
	for(int k = 0; k < i; k++){
	    for(int l = 0; l < j; l++){
		placesLibres[k][l] = false;
	    }
	}
    }

    public synchronized boolean capaciteOK(int n){
	int cpt = 0;
	for(int k = 0; k < lignes; k++){
	    for(int l = 0; l < colonnes; l++){
		if (placesLibres[k][l] = false)
		    cpt++;
	    }
	}
	return cpt < n;
    }

    public synchronized boolean nContiguesAuRangI(int n, int i){
	int j = 0;
	int cpt = 0;
	while(j < colonnes){
	    cpt = 0;
	    while(placesLibres[i][j] != false && j < colonnes){
		j++;
	    }
	    while(placesLibres[i][j] == false && j < colonnes){
		if((cpt == n) && (j+cpt < colonnes))
		    return true;
		cpt++;
		j++;
	    }
	}
	return false;
    }

    public synchronized int cbContiguesAuRangI(int n, int i){
	int j = 0;
	int cpt = 0;
	while(j < colonnes){
	    cpt = 0;
	    while(placesLibres[i][j] != false && j < colonnes){
		j++;
	    }
	    while(placesLibres[i][j] == false && j < colonnes){
		if(cpt == n)
		    return j;
		cpt++;
		j++;
	    }
	}
	return -1;
    }

    public synchronized boolean reserverContigues(int id, int n){
	for(int i = 0; i < lignes; i++){
	    if (this.nContiguesAuRangI(i, n)){
		int tmp = this.cbContiguesAuRangI(i,n);
		for(int j = tmp; j < n + tmp; j++)
		    placesLibres[i][j] = true;
		return true;
	    }
	}
	    return false;
	}
    public synchronized boolean reserver(int id, int n){
	if(this.reserverContigues(id,n))
	    return true;
	else if(this.capaciteOK(n)){
	    int cpt = 0;
	    for(int i = 0; i < lignes; i++)
		for(int j = 0; j < colonnes; j++){
		    if (cpt >= n)
			return true;
		    if(! placesLibres[i][j]){
			placesLibres[i][j] = true;
			cpt++;
		    }
		}
	}
	return false;
    }
}
