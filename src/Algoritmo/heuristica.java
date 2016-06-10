package Algoritmo;

public class heuristica {
	/*
	 * 	CLASSE CRIADA PARA DEFINIR O TAMANHO DA HEURISTICA DO MAPA, A FUNÇÃO QUE ESCOLHIDA PARA CALCULO DA HEURISTICA É O MODULO DE ORIGEM X 
	 * 	MENOS O DESTINO DE X, MAIS O MODULO DE ORIGEM DE Y MENOS O DESTINO DE Y
	 * 
	 * */	
	public double h;
	public int b;
	public int a;

	public int calcHeuristica(int oriX,int oriY,int desX,int desY){
		b = Math.abs(oriX-desX);// |ORIX - DESX|

		a = Math.abs(oriY-desY);//	|ORIY - DESY|

		h = a + b; // HEURISTICA = |ORIX - DESX| + |ORIY - DESY|

		return (int) h;}

}
