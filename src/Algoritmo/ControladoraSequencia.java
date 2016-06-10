package Algoritmo;

public class ControladoraSequencia {
	/*
	 *	CLASSE COM AS SEQUENCIAS E ORDEM DO MAPA E DAS DUNGEONS QUE SERAM INSTANCIADAS
	 * 
	 * */
	ExecutorMapa exm = new ExecutorMapa();
	ExecutorDungeon exd = new ExecutorDungeon();

	public void sequencia(){

		long tempo1 = System.nanoTime();

		System.out.println("\n\n[MAPA PARA DUNGEON 1]");
		exm.executeMap(27,24,32,5);//PASSANDO AS COORDENADAS INICIAIS DO AGENTE MAIS COORDENADAS DA PRIMEIRA DUNGEON
		System.out.println();

		System.out.println("\n\n[DUNGEON 1]");
		exd.executeMap("1");

		System.out.println("\n\n[MAPA PARA DUNGEON 2]");
		exm.executeMap(32,5,17,39);//PASSANDO AS COORDENADAS DA PRIMEIRA DUNGEON MAIS AS COORDENADAS DA SEGUNDA

		System.out.println("\n\n[DUNGEON 2]");
		exd.executeMap("2");

		System.out.println("\n\n[MAPA PARA DUNGEON 3]");
		exm.executeMap(17,39,1,24);//PASSANDO AS COORDENADAS DA SEGUNDA DUNGEON MAIS AS COORDENADAS DA TERCEIRA

		System.out.println("\n\n[DUNGEON 3]");
		exd.executeMap("3");

		System.out.println("\n\n[MAPA PARA DUNGEON ESPADA]");
		exm.executeMap(1,24,5,6);//PASSANDO AS COORDENADAS DA TERCEIRA DUNGEON ATE A ESPADA

		long tempo2 = System.nanoTime();

		//IMPRIMINDO O CUSTO TOTAL DE TODA MOVIMENTAÇÃO TANTO NO MAPA PRINCIPAL QUANTO NAS 3 DUNGEONS
		System.out.println("[CUSTO TOTAL DA MOVIMENTAÇÃO: "+(exm.getCustoTotal()+exd.getCusto())+"]");

		System.out.println("Tempo de pesquisa: " + (tempo2 - tempo1) + "ns");
	}
}
