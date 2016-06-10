package Algoritmo;

public class Limites {
	/*
	 *	CLASSE QUE DEFINE O PESO DE CADA COORDENADA QUANDO PASSADA DO BUFFER DO ARQUIVO PARA O ARRAYLIST
	 *
	 * */

	public int limiteX;
	public int limiteY;
	public int peso;

	public Limites(int limiteX, int limiteY) {
		super();
		this.limiteX = limiteX;
		this.limiteY = limiteY;
	}

	public Limites(int peso) {
		this.peso = peso;
	}

	public Limites(Limites limites) {
		// TODO Auto-generated constructor stub
	}

	public int getLimiteX() {
		return limiteX;
	}
	public void setLimiteX(int limiteX) {
		this.limiteX = limiteX;
	}
	public int getLimiteY() {
		return limiteY;
	}
	public void setLimiteY(int limiteY) {
		this.limiteY = limiteY;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}
