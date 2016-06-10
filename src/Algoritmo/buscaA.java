package Algoritmo;
import java.util.ArrayList;
/*
 * 	CLASSE BUSCA É RESPONSAVEL EM AVERIGUAR TODOS AS CASAS ADJACENTES A POSIÇÃO ATUAL EM BUSCA DE UM CUSTO MENOR CONSIDERANDO CUSTO A SOMA
 * 	DO PESO MAIS A HEURISTICA.
 * 
 * */
public class buscaA {

	private Coordenada[][] mapa;//MATRIZ DO MAPA
	private Coordenada origem;//COORDENADA ORIGEM
	private Coordenada destino;//COORDENADA DESTINO
	private ArrayList<Coordenada> Analisando;//COORDENADAS EM ANALISE
	private ArrayList<Coordenada> JaAnalisados;//COORDENADAS JÁ ANALISADAS
	private ArrayList<Coordenada> listaCaminho;//CAMINHOS
	private ArrayList<Coordenada> AreaInacessivel;//AREAS ESCURAS DA DUNGEON

	public buscaA(Coordenada grade[][], Coordenada origem, Coordenada destino) {
		this.mapa = grade;
		this.origem = origem;
		this.destino = destino;
		Analisando = new ArrayList<Coordenada>();
		JaAnalisados = new ArrayList<Coordenada>();
		listaCaminho = new ArrayList<Coordenada>();
		AreaInacessivel = new ArrayList<Coordenada>();
	}

	public boolean inicio() {//CASO CAMINHO SEJA ENCONTRADO É RETORNADO UM VALOR VERDADEIRO CASO NÃO SEJA POSSIVEL RETORNA-SE FALSO
		if (getMapa() == null) {//EM CASO DE LISTA VAZIA
			return false;
		}
		if (getOrigem() == getDestino()) {//ORIGEM == DESTINO
			return true;
		}

		Analisando.add(getOrigem());//LISTA DE COORDENADAS QUE ESTAO SENDO OLHADAS
		if (busca()) {
			return caminho();
		}
		return false;
	}

	private boolean busca() {
		Coordenada percurso = Analisando.get(0);
		for (int i = 1; i < Analisando.size(); i++) {
			if (percurso.getSomaPesoHeuri() > Analisando.get(i).getSomaPesoHeuri()) {
				percurso = Analisando.get(i);
			}
		}

		JaAnalisados.add(percurso);//SALVA A COORDENADA QUE JA FOI CONFERIDA
		Analisando.remove(percurso);//RETIRA A COORDENADA DA LISTA DE COORDENADAS EM ANALISE

		if (percurso == destino) {//CASO O PERCURSO SEJA IDENTICO AO DESTINO O CAMINHO FOI ENCONTRADO
			return true;
		}

		//ANALISE EM CASAS ADJACENTES
		int x;
		int y;

		x = percurso.getX();
		y = percurso.getY();

		int direita = x + 1;
		int esquerda = x - 1;
		int acima = y - 1;
		int abaixo = y + 1;

		if (direita < mapa[0].length) {
			Coordenada adjacenteDireta = mapa[direita][y];
			if (!JaAnalisados.contains(adjacenteDireta) && !AreaInacessivel.contains(adjacenteDireta)) {//SE COORDENADA NAO É ESTA NA AREA ESCURA DO MAPA OU SE JA NÃO FOI CONFERIDA
				int Peso = percurso.getPeso()+mapa[direita][y].getPeso();
				int Heuristica = percurso.getHeuristica();

				if (!Analisando.contains(adjacenteDireta)) { //SE COORDENADA NÃO ANALISADA, ANTECESSORA ASSUMIRA O VALOR DELA
					adjacenteDireta.setAntecessor(percurso);
					Analisando.add(adjacenteDireta);
					adjacenteDireta.setPeso(Peso);
					adjacenteDireta.setHeuristica(Heuristica);
				} else {
					if (adjacenteDireta.getHeuristica() > Heuristica) {
						adjacenteDireta.setAntecessor(percurso);
						adjacenteDireta.setPeso(Peso);
						adjacenteDireta.setHeuristica(Heuristica);
					}
				}
			}
		}

		if (esquerda >= 0) {
			Coordenada adjacenteEsquerda = getMapa()[esquerda][y];
			if (!JaAnalisados.contains(adjacenteEsquerda) && !AreaInacessivel.contains(adjacenteEsquerda)) {//SE COORDENADA NAO É ESTA NA AREA ESCURA DO MAPA OU SE JA NÃO FOI CONFERIDA
				int Peso = percurso.getPeso()+getMapa()[esquerda][y].getPeso();
				int Heuristica = percurso.getHeuristica();

				if (!Analisando.contains(adjacenteEsquerda)) {  //SE COORDENADA NÃO ANALISADA, ANTECESSORA ASSUMIRA O VALOR DELA
					adjacenteEsquerda.setAntecessor(percurso); 
					Analisando.add(adjacenteEsquerda);  
					adjacenteEsquerda.setPeso(Peso);
					adjacenteEsquerda.setHeuristica(Heuristica);
				} else {                                         
					if (adjacenteEsquerda.getHeuristica() > Heuristica) { 
						adjacenteEsquerda.setAntecessor(percurso); 
						adjacenteEsquerda.setPeso(Peso); 
						adjacenteEsquerda.setHeuristica(Heuristica);
					}
				}
			}
		}

		if (acima >= 0) {
			Coordenada adjacenteAcima = getMapa()[x][acima];
			if (!JaAnalisados.contains(adjacenteAcima) && !AreaInacessivel.contains(adjacenteAcima)) { //SE COORDENADA NAO É ESTA NA AREA ESCURA DO MAPA OU SE JA NÃO FOI CONFERIDA
				int Peso = percurso.getPeso()+getMapa()[x][acima].getPeso();
				int Heuristica = percurso.getHeuristica();

				if (!Analisando.contains(adjacenteAcima)) {  //SE COORDENADA NÃO ANALISADA, ANTECESSORA ASSUMIRA O VALOR DELA
					adjacenteAcima.setAntecessor(percurso); 
					Analisando.add(adjacenteAcima);  
					adjacenteAcima.setPeso(Peso);
					adjacenteAcima.setHeuristica(Heuristica);
				} else {                                         
					if (adjacenteAcima.getHeuristica() > Heuristica) { 
						adjacenteAcima.setAntecessor(percurso); 
						adjacenteAcima.setPeso(Peso); 
						adjacenteAcima.setHeuristica(Heuristica);
					}
				}
			}
		}

		if (abaixo < mapa.length) {
			Coordenada adjacenteAbaixo = mapa[x][abaixo];
			if (!JaAnalisados.contains(adjacenteAbaixo) && !AreaInacessivel.contains(adjacenteAbaixo)) {//SE COORDENADA NAO É ESTA NA AREA ESCURA DO MAPA OU SE JA NÃO FOI CONFERIDA 
				int Peso = percurso.getPeso()+mapa[x][abaixo].getPeso();
				int Heuristica = percurso.getHeuristica();

				if (!Analisando.contains(adjacenteAbaixo)) {  //SE COORDENADA NÃO ANALISADA, ANTECESSORA ASSUMIRA O VALOR DELA
					adjacenteAbaixo.setAntecessor(percurso); 
					Analisando.add(adjacenteAbaixo);  
					adjacenteAbaixo.setPeso(Peso);
					adjacenteAbaixo.setHeuristica(Heuristica);
				} else {                                         
					if (adjacenteAbaixo.getHeuristica() > Heuristica) { 
						adjacenteAbaixo.setAntecessor(percurso); 
						adjacenteAbaixo.setPeso(Peso); 
						adjacenteAbaixo.setHeuristica(Heuristica);
					}
				}
			}
		}

		if (Analisando.isEmpty()) {
			return false;
		}

		return busca();
	}

	private boolean caminho() {
		Coordenada percurso = getDestino();

		if (percurso == null) {
			return false;
		}

		while(percurso != null) {
			listaCaminho.add(percurso);//O PERCURSO SALVA O ESTADO ATUAL E ASSUME VALOR DO ANTERIOR DO SEU ANTECESSOR NO TRAGETO.
			percurso = percurso.getAntecessor();
		}
		return true;

	}

	public Coordenada[][] getMapa() {
		return mapa;
	}

	public void setMapa(Coordenada[][] mapa) {
		this.mapa = mapa;
	}

	public Coordenada getOrigem() {
		return origem;
	}

	public void setOrigem(Coordenada origem) {
		this.origem = origem;
	}

	public Coordenada getDestino() {
		return destino;
	}

	public void setDestino(Coordenada destino) {
		this.destino = destino;
	}

	public ArrayList<Coordenada> getListaCaminho() {
		return listaCaminho;
	}

	public ArrayList<Coordenada> getAreaInacessivel() {
		return AreaInacessivel;
	}

	public void addAreaEscuraMapa(Coordenada bloqueio) {
		AreaInacessivel.add(bloqueio);

	}
}
