package Algoritmo;
import java.util.ArrayList;
/*
 *	CLASSE COM AS SEQUENCIAS E ORDEM DO MAPA E DAS DUNGEONS QUE SERAM INSTANCIADAS NA
 *	INTERFACE GRAFICA.
 * 
 * */

public class ControlaInterface {

	ExecutorMapa exm = new ExecutorMapa();
	ExecutorDungeon exd = new ExecutorDungeon();
	public ArrayList<Limites> valores;

	public void sequencia(){//MAPA 1

		exm.executeMap(27,24,32,5);

		valores = (ArrayList<Limites>) exm.coord2.clone();
	}

	public void sequencia2(){//DUNGEON 1

		valores.clear();

		exd.executeMap("1");

		valores = (ArrayList<Limites>) exd.coord.clone();
		for(int i=0;i<valores.size();i++)
			System.out.println(valores.get(i).getLimiteX() +"," +valores.get(i).getLimiteY());
	}

	public void sequencia3(){//MAPA 2

		valores.clear();

		exm.executeMap(32,5,17,39);

		valores = (ArrayList<Limites>) exm.coord2.clone();
	}

	public void sequencia4(){// DUNGEON 2

		valores.clear();

		exd.executeMap("2");

		valores = (ArrayList<Limites>) exd.coord.clone();
	}

	public void sequencia5(){//MAPA 3

		valores.clear();

		exm.executeMap(17,39,1,24);

		valores = (ArrayList<Limites>) exm.coord2.clone();
	}

	public void sequencia6(){// DUNGEON 3

		valores.clear();

		exd.executeMap("3");

		valores = (ArrayList<Limites>) exd.coord.clone();
	}

	public void sequencia7(){//MAPA 3

		valores.clear();

		exm.executeMap(1,24,5,6);

		valores = (ArrayList<Limites>) exm.coord2.clone();
	}
}
