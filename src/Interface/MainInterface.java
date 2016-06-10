package Interface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Algoritmo.ControlaInterface;


public class MainInterface {

	static int controle = 0;
	static ControlaInterface cs = new ControlaInterface();

	static JPanel painelDungeon1 = null;
	static  JPanel painelDungeon2 = null;
	static  JPanel painelDungeon3 = null;
	static JPanel painelMapaPrincipal;

	public static void main(String[] a) {



		cs.sequencia();//INICIALIZANDO O CONTROLADOR_DE_SEQUENCIA

		final JFrame f = new JFrame("Reino de Hyrule");
		JButton botaoTrocaMapa;
		JLabel labelTM;

		JPanel painelAuxiliar;



		cs.sequencia();
		labelTM = new JLabel("Visualize os demais mapas:");
		painelAuxiliar = new JPanel();
		painelMapaPrincipal = new MapaPrincipal(cs.valores);

		botaoTrocaMapa = new JButton("Avançar");
		botaoTrocaMapa.addActionListener(
				new ActionListener( ) {
					public void actionPerformed( ActionEvent e ) {
						if(controle == 0){
							controle ++;
							cs.sequencia2();
							painelDungeon1 = new Dungeon1(cs.valores);

							f.setTitle("DUNGEON 1");
							f.remove(painelMapaPrincipal);
							f.add(painelDungeon1);
							f.validate();
							f.repaint();
						}
						else if(controle == 1){
							controle ++;
							f.setTitle("Reino de Hyrule");
							f.remove(painelDungeon1);
							cs.sequencia3();
							painelMapaPrincipal = new MapaPrincipal(cs.valores);
							f.add(painelMapaPrincipal);
							f.validate();
							f.repaint();
						}
						else if(controle == 2){
							controle ++;
							cs.sequencia4();
							painelDungeon2 = new Dungeon2(cs.valores);
							f.setTitle("DUNGEON 2");
							f.remove(painelMapaPrincipal);

							f.add(painelDungeon2);
							f.validate();
							f.repaint();							
						}
						else if(controle == 3){
							controle ++;
							cs.sequencia5();
							painelMapaPrincipal = new MapaPrincipal(cs.valores);
							f.setTitle("Reino de Hyrule");
							f.remove(painelDungeon2);
							f.add(painelMapaPrincipal);
							f.validate();
							f.repaint();
						}
						else if(controle == 4){
							controle ++;
							cs.sequencia6();
							painelDungeon3 = new Dungeon3(cs.valores);
							f.setTitle("DUNGEON 3");
							f.remove(painelMapaPrincipal);
							f.add(painelDungeon3);
							f.validate();
							f.repaint();							
						}
						else if(controle == 5){
							controle ++;
							cs.sequencia7();
							painelMapaPrincipal = new MapaPrincipal(cs.valores);
							f.setTitle("Reino de Hyrule");
							f.remove(painelDungeon3);
							f.add(painelMapaPrincipal);
							f.validate();
							f.repaint();
						}



					}	
				}
				);

		painelAuxiliar.add(labelTM);
		painelAuxiliar.add(botaoTrocaMapa);
		f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(painelMapaPrincipal, BorderLayout.CENTER);
		f.add(painelAuxiliar, BorderLayout.SOUTH);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);


	}

}
