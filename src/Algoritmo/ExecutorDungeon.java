package Algoritmo;
import java.util.ArrayList;
/*
 *	CLASSE PARA CHAMAR O TXT QUE CONTEM AS INFORMAÇÕES DE CADA MAPA, 
 *	A CLASSE TAMBÉM PASSA AS INFORMAÇÕES PARA QUE SEJA REALIZADA A BUSCA A*
 *	E IMPRIME A DEMONSTRAÇÃO DO MAPA COM O CAMINHO.
 * 
 * */
import java.util.Collection;

public class ExecutorDungeon {

	public static ArrayList<Limites> AreaInacessivel;//ARRAYLIST DA REGIÃO ESCURA DO MAPA
	public static int oriX;//VARIAVEL PARA A COORDENADA DE ORIGEM X
	public static int oriY;//VARIAVEL PARA A COORDENADA DE ORIGEM Y
	public static int desX;//VARIAVEL PARA A COORDENADA DE DESTINO X
	public static int desY;//VARIAVEL PARA A COORDENADA DE DESTINO Y
	public int peso;//VARIAVEL PARA ARMAZENAR O PESO
	public int custo;//VARIAVEL PARA RECEBER O SOMATORIO DOS PESOS
	public ArrayList<Limites> coord;

	public void executeMap(String args){

		peso = 0;
		try{
			if(args == "1"){//CARREGANDO AS INFORMAÇÕES DO TXT REFERENTE AO MAPA 1
				Leitor reader = new Leitor();
				AreaInacessivel = reader.lerArquivo("mapa_dg1.txt");
				oriX=14;//SEQUENCIA DE COORDENADA QUE DEFINEM ENTRADA NA DUNGEON 1
				oriY=25;
				desX=12;//SEQUENCIA DE COORDENADA QUE DEFINEM DESTINO NA DUNGEON 1
				desY=3;

			}
			else 
				if(args == "2"){//CARREGANDO AS INFORMAÇÕES DO TXT REFERENTE AO MAPA 2
					Leitor reader = new Leitor();
					AreaInacessivel = reader.lerArquivo("mapa_dg2.txt");
					oriX=13;//SEQUENCIA DE COORDENADA QUE DEFINEM ENTRADA NA DUNGEON 2
					oriY=25;
					desX=13;//SEQUENCIA DE COORDENADA QUE DEFINEM DESTINO NA DUNGEON 2
					desY=3;
				}
				else 
					if(args == "3"){//CARREGANDO AS INFORMAÇÕES DO TXT REFERENTE AO MAPA 3
						Leitor reader = new Leitor();
						AreaInacessivel = reader.lerArquivo("mapa_dg3.txt");
						oriX=14;//SEQUENCIA DE COORDENADA QUE DEFINEM ENTRADA NA DUNGEON 3
						oriY=25;
						desX=15;//SEQUENCIA DE COORDENADA QUE DEFINEM DESTINO NA DUNGEON 3
						desY=18;
					}

		}catch(Exception e){
			e.printStackTrace();
		}

		Coordenada[][] mapa = new Coordenada[28][28];//INICIALIZA UMA MATRIZ 28x28 DE COORDENADAS
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[i].length; j++) {
				mapa[i][j] = new Coordenada(i, j);
			}
		}

		Coordenada origem = mapa[oriX][oriY];//CONFIGURA PARAMETROS DE ORIGEM NA MATRIZ DE COORDENADAS
		Coordenada destino = mapa[desX][desY];//CONFIGURA PARAMETROS DE DESTINO NA MATRIZ DE COORDENADAS

		buscaA a = new buscaA(mapa, origem, destino);

		for(int j=0;j<AreaInacessivel.size();j++){//CONFIGURA AS COORDENADAS DAS AREAS ESCURAS DO MAPA AS AREAS INACESSIVEIS
			a.addAreaEscuraMapa(mapa[AreaInacessivel.get(j).getLimiteY()][AreaInacessivel.get(j).getLimiteX()]);
		}

		a.inicio();//INICIALIZANDO A BUSCA NA MATRIZ

		//IMPRENSÃO DUNGEONS
		for (int i = 0; i < mapa.length; i++) {
			System.out.println("");

			for (int j = 0; j < mapa[i].length; j++) {

				if (origem.getX() == j && origem.getY() == i) {//LOCAL DE ORIGEM
					System.out.print(" @ ");

				} else if (destino.getX() == j && destino.getY() == i) {//LOCAL DE DESTINO
					System.out.print(" & ");

				} else {
					boolean caminho = false;
					for (int k = 0; k < a.getListaCaminho().size(); k++) {
						if (a.getListaCaminho().get(k).getX() == j && a.getListaCaminho().get(k).getY() == i) {
							System.out.print(" * ");//MARCA O CAMINHO
							caminho = true;
							break;
						}
					}
					boolean isBloqueio = false;
					for (int k = 0; k < a.getAreaInacessivel().size(); k++) {
						if (a.getAreaInacessivel().get(k).getX() == j && a.getAreaInacessivel().get(k).getY() == i) {
							System.out.print("|||");//REGIÃO ESCURA DO MAPA
							isBloqueio = true;
							break;
						}
					}
					if (!caminho && !isBloqueio) {
						System.out.print("   ");//CAMINHOS ABERTO NO MAPA
					}
				}

			}
		}
		System.out.println("\n\n[SEQUENCIA DO CAMINHO:]");//CAMINHO PERCORRIDO 
		for (int i = a.getListaCaminho().size()-1; i >= 0 ; i--) {
			System.out.print("["+a.getListaCaminho().get(i).getX()
					+ "," + a.getListaCaminho().get(i).getY()+"] ");
		}
		System.out.println("\n\n[PESO DOS CAMINHO:]");//PESO DO CAMINHO PERCORRIDO
		for (int i = a.getListaCaminho().size()-1; i >= 0 ; i--) {
			peso+=10;
			System.out.print(peso+",");
		}
		System.out.println("\n\n[CUSTO IDA: "+peso+"]\t[CUSTO IDA E VOLTA: "+2*peso+"]");//CUSTO DA MOVIMENTAÇÃO DENTRO DA DUNGEON
		custo +=peso;

		coord = new ArrayList<Limites>();

		for(int i = a.getListaCaminho().size()-1; i >= 0 ; i--){

			int b = a.getListaCaminho().get(i).getX();

			int c = a.getListaCaminho().get(i).getY();

			Limites d = new Limites(b,c);

			coord.add(d);
		}
	}

	public int getCusto(){
		return custo;
	}
	public void setCusto(int custo){
		this.custo = custo;
	}
}
