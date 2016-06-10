package Algoritmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 *	CLASSE RESPOSAVEL POR ABRIR OS ARQUIVOS, ELA POSSUI DOIS METODOS PARA QUE MAPAS COM DIFERENTES CARACTERISTICAS SEJA ABERTO.
 *	
 *	-O PRIMEIRO METODO E PARA ABRIR O MAPA DA DUNGEON QUE É DEFINIDO COM APENAS DOIS PESOS 10 E 999, CASO O PESO SEJA 999 A COORDENADA
 *	X,Y É ADICIONADA A UM ARRAYLIST PARA FUTURAMENTE DEMONSTRAR AO EXECUTOR_DUNGEON QUE AQUELA COORDENADA ESTA BLOQUEADA.
 *	
 *	-O SEGUNDO METODO ADICIONA EM UM ARRAYLIST DE ARRAYLIST TODOS OS PESOS ENCONTRADOS NO MAPA, ESTE ARRAYLIST DE ARRAYLIST PASSA PARA O
 *	EXECUTOR_MAPA O PESO DE CADA POSIÇÃO DA MATRIZ.
 *
 * */

public class Leitor {

	private File arquivo;//ARQUIVO A SER ABERTO
	public Coordenada XY;
	public ArrayList<Limites> CoordenadasMapaDg;
	public ArrayList<ArrayList<Limites>> CoordenadasMapa;
	public int tamX;
	public int tamY;
	public int[][] L;
	public int numVertices;
	public int numArestas;

	public Leitor(){
	}


	public ArrayList<Limites> lerArquivo(String nome) throws IOException{//METODO QUE ABRE O TXT RESPONSAVEL PELA DUNGEON
		this.arquivo = new File(nome);

		FileReader reader = new FileReader(arquivo);
		BufferedReader bufferReader = new BufferedReader(reader);

		XY = new Coordenada(0, 0);
		String[] line;

		line = bufferReader.readLine().split(" ");//PRIMEIRA LINHAS DO TXT DEFINE O TAMANHO DA MATRIZ
		tamX = (Integer.parseInt(line[0]));//PRIMEIRO CAMPO ABERTO DEFINE O TAM DE X
		tamY = (Integer.parseInt(line[1]));//SEGUNDO CAMPO DA LINHA DEFINE O TAM DE Y

		CoordenadasMapaDg = new ArrayList<Limites>();

		for(int i=0;i<tamX;i++){

			String [] data = bufferReader.readLine().split(" ");

			for(int j=0;j<tamY;j++){

				int coordenada = Integer.parseInt(data[j]);

				if(coordenada>=999){//CONDICIONAL QUE AVALIA SE O PESO É REFERENTE A UMA AREA BLOQUEADA OU NÃO
					Limites a = new Limites(i,j);
					CoordenadasMapaDg.add(a);

				}
			}
		}
		bufferReader.close();
		reader.close();
		return CoordenadasMapaDg;
	}

	public ArrayList<ArrayList<Limites>> lerArquivo2(String nome) throws IOException{//METODO QUE ABRE O TXT RESPONSAVEL PELO MAPA PRINCIPAL
		this.arquivo = new File(nome);

		FileReader reader = new FileReader(arquivo);
		BufferedReader bufferReader = new BufferedReader(reader);

		XY = new Coordenada(0, 0);
		String[] line;

		line = bufferReader.readLine().split(" ");//PRIMEIRA LINHAS DO TXT DEFINE O TAMANHO DA MATRIZ
		tamX = (Integer.parseInt(line[0]));//PRIMEIRO CAMPO ABERTO DEFINE O TAM DE X
		tamY = (Integer.parseInt(line[1]));//SEGUNDO CAMPO DA LINHA DEFINE O TAM DE Y

		CoordenadasMapa = new ArrayList<ArrayList<Limites>>();

		for(int i=0;i<tamX;i++){//ARMAZENA O PESO DE CADA POSIÇÃO EM UM ARRAYLIST DE ARRAYLIST

			ArrayList<Limites>lista = new ArrayList<Limites>();
			CoordenadasMapa.add(lista);
			String [] data = bufferReader.readLine().split(" ");

			for(int j=0;j<tamY;j++){

				int coordenada = Integer.parseInt(data[j]);

				Limites lmp = new Limites(coordenada);						 
				CoordenadasMapa.get(i).add(lmp);
			}
		}
		bufferReader.close();
		reader.close();
		return CoordenadasMapa;
	}

	public int [][] lerArquivo3(String nome) throws IOException{
		this.arquivo = new File(nome);

		FileReader reader = new FileReader(arquivo);
		BufferedReader bufferReader = new BufferedReader(reader);

		String[] line;

		line = bufferReader.readLine().split(" ");
		numVertices = (Integer.parseInt(line[0]));
		numArestas = (Integer.parseInt(line[1]));

		L = new int[42][42];

		for(int i=0;i<numVertices;i++){

			String [] data = bufferReader.readLine().split(" ");

			for(int j=0;j<numArestas;j++){

				int valor = Integer.parseInt(data[j]);

				L[i][j] = valor;
			}
		}
		bufferReader.close();
		reader.close();
		return L;
	}

}
