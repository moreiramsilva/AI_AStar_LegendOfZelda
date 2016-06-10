package Algoritmo;
import java.util.ArrayList;
/*
 *	CLASSE PARA CHAMAR O TXT QUE CONTEM AS INFORMAÇÕES DE PESO SOBRE O MAPA PRINCIPAL, 
 *	A CLASSE TAMBÉM PASSA AS INFORMAÇÕES PARA QUE SEJA REALIZADA A BUSCA A*
 *	E IMPRIME A DEMONSTRAÇÃO DO MAPA COM O CAMINHO.
 * 
 * */
import java.util.Collection;

public class ExecutorMapa {
	public ArrayList<ArrayList<Limites>> LMP;
	public static int oriX;//VARIAVEL PARA A COORDENADA DE ORIGEM X
	public static int oriY;//VARIAVEL PARA A COORDENADA DE ORIGEM Y
	public static int desX;//VARIAVEL PARA A COORDENADA DE DESTINO X
	public static int desY;//VARIAVEL PARA A COORDENADA DE DESTINO Y
	public int peso;//VARIAVEL PARA ARMAZENAR O PESO
	public int custoTotal;//VARIAVEL PARA RECEBER O SOMATORIO DOS PESOS
	public ArrayList<Limites> coord2;


	public void executeMap(int ox,int oy,int dx,int dy){
		oriX=ox;//PASSO OS VALORES DE X,Y TANTO DA ORIGEM QUANTO DO DESTINO 
		oriY=oy;//
		desX=dx;//
		desY=dy;//
		peso = 0;//INICIALIZA O PESO 0 A CHAMA INSTANCIA

		try{//CARREGANDO AS INFORMAÇÕES DO TXT REFERENTE AO MAPA PRINCIPAL
			Leitor reader = new Leitor();
			LMP = reader.lerArquivo2("mapa_main.txt");

		}catch(Exception e){
			e.printStackTrace();
		}
		//INICIALIZA UMA MATRIZ 42x42 DE COORDENADAS
		Coordenada[][] mapa = new Coordenada[42][42];
		heuristica h1 = new heuristica();//INSTANCIA A CLASSE RESPONSAVEL PELA HEURISTICA

		for (int i = 0; i < mapa.length; i++) {//CONFIGURA OS PESOS E HEURISTICAS DE CADA COORDENADAS
			for (int j = 0; j < mapa[i].length; j++) {
				int peso = LMP.get(i).get(j).peso;
				int heuristica = h1.calcHeuristica(i, j, desX, desY);
				mapa[i][j] = new Coordenada(i, j,peso,heuristica);
			}
		}

		Coordenada origem = mapa[oriX][oriY];//CONFIGURA PARAMETROS DE ORIGEM NA MATRIZ DE COORDENADAS
		Coordenada destino = mapa[desX][desY];//CONFIGURA PARAMETROS DE DESTINO NA MATRIZ DE COORDENADAS

		buscaA a = new buscaA(mapa, origem, destino);

		a.inicio();//INICIALIZANDO A BUSCA NA MATRIZ

		//IMPRENSÃO O MAPA PRINCIPAL
		System.out.println("[[ ] = GRAMA, [:] = AREIA, [Y] = FLORESTA, [#] = MONTANHA, [§] = AGUA, [@] = ORIGEM, [&] = DESTINO, [*] = CAMINHO]");
		for (int i = 0; i < mapa.length; i++) {
			System.out.println("");

			for (int j = 0; j < mapa.length; j++) {//MARCA O CAMINHO

				boolean jaImp = false;
				if(LMP.get(i).get(j).peso==10 || LMP.get(i).get(j).peso==20 ||LMP.get(i).get(j).peso==100 || LMP.get(i).get(j).peso==150 ||LMP.get(i).get(j).peso==180){
					for(int k = 1; k < a.getListaCaminho().size()-1; k++){
						if(a.getListaCaminho().get(k).getX()==i && a.getListaCaminho().get(k).getY()==j){
							System.out.print(" * ");
							jaImp = true;
							break;}}}

				if(LMP.get(i).get(j).peso==10 && !jaImp){//VERIFICA O PESO PARA IMPRENSÃO DA GRAMA

					if(i==oriX && j==oriY){
						System.out.print("[@]");
					}else if(i==desX && j==desY){
						System.out.print("[&]");
					}else
						System.out.print("   ");

				}else if(LMP.get(i).get(j).peso==20 && !jaImp){//VERIFICA O PESO PARA IMPRENSÃO DA AREIA

					if(i==oriX && j==oriY){
						System.out.print("[@]");
					}else if(i==desX && j==desY){
						System.out.print("[&]");
					}else 
						System.out.print(":::");

				}else if(LMP.get(i).get(j).peso==100 && !jaImp){//VERIFICA O PESO PARA IMPRENSÃO DA FLORESTA

					if(i==oriX && j==oriY){
						System.out.print("[@]");
					}else if(i==desX && j==desY){
						System.out.print("[&]");
					}else
						System.out.print("YYY");

				}else if(LMP.get(i).get(j).peso==150 && !jaImp){//VERIFICA O PESO PARA IMPRENSÃO DAS MONTANHAS

					if(i==oriX && j==oriY){
						System.out.print("[@]");
					}else if(i==desX && j==desY){
						System.out.print("[&]");
					}else
						System.out.print("###");

				}else if(LMP.get(i).get(j).peso==180 && !jaImp){//VERIFICA O PESO PARA IMPRENSÃO DA AGUA

					if(i==oriX && j==oriY){
						System.out.print("[@]");
					}else if(i==desX && j==desY){
						System.out.print("[&]");
					}else
						System.out.print("§§§");

				}}}
		System.out.println("\n\n[SEQUENCIA DO CAMINHO:]");//CAMINHO PERCORRIDO 
		for (int i = a.getListaCaminho().size()-1; i >= 0 ; i--) {
			System.out.print("["+a.getListaCaminho().get(i).getX()
					+ "," + a.getListaCaminho().get(i).getY()+"] ");
		}
		System.out.println("\n\n[PESO DOS CAMINHO:]");//PESO DO CAMINHO PERCORRIDO
		for (int i = a.getListaCaminho().size()-1; i >= 0 ; i--) {
			System.out.print(peso+",");
			peso = peso + LMP.get(a.getListaCaminho().get(i).getX()).get(a.getListaCaminho().get(i).getY()).peso;
		}

		System.out.println("\n\n[CUSTO: "+peso+"]");//CUSTO DA MOVIMENTAÇÃO NO MAPA
		custoTotal+=peso;

		coord2 = new ArrayList<Limites>();

		for(int i = a.getListaCaminho().size()-1; i >= 0 ; i--){

			int b = a.getListaCaminho().get(i).getX();

			int c = a.getListaCaminho().get(i).getY();

			Limites d = new Limites(b,c);

			coord2.add(d);
		}
	}
	public int getCustoTotal(){
		return custoTotal;
	}
	public void setCustoTotal(int custoTotal){
		this.custoTotal = custoTotal;
	}
}
