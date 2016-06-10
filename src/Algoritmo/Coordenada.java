package Algoritmo;

public class Coordenada {

	private int peso;// PESO DE CADA 'CASA'
	private int heuristica; // HEURISTICA REFERENTE A DISTANCIA DA 'CASA' AO DESTINO 
	private int x = 0;
	private int y = 0;
	private Coordenada antecessor = null;//REFERENCIA DA 'CASA' ANTERIOR(ORIGEM) A QUE ESTA

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordenada(int x, int y,int peso,int heuristica) {
		this.x = x;
		this.y = y;
		this.peso = peso;
		this.heuristica = heuristica;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	public Coordenada getAntecessor() {
		return antecessor;
	}

	public void setAntecessor(Coordenada antecessor) {
		this.antecessor = antecessor;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public boolean equals(Object obj) {
		return (((Coordenada)obj).getX() == x && ((Coordenada)obj).getY()==y);
	}

	public int getSomaPesoHeuri() {
		return peso + heuristica;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
