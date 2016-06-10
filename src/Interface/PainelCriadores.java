package Interface;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelCriadores extends JPanel{
	
	private JPanel Icone;
	private JPanel integrantes;
	private JPanel auxiliar;
	
	private JLabel Jader;
	private JLabel Matheus;
	private JLabel icone;
	
		public PainelCriadores (){
	
			Icon cinema = new ImageIcon( "zelda.jpg" );
			icone = new JLabel("");
			icone.setIcon( cinema );
			Jader = new JLabel("Jáder Felipe Silva Duarte               Matricula: 10.1.8147");
			Matheus = new JLabel("Matheus Moreira                              Matricula: 12.2.8427");
			
			Icone = new JPanel();
			integrantes = new JPanel(new  GridLayout(2, 1, 5, 10));
			auxiliar = new JPanel();
			
			Icone.add(icone);
			integrantes.add(Matheus);
			integrantes.add(Jader);
			auxiliar.add(integrantes);
			
			setLayout(new BorderLayout(5,5));
			add(Icone, BorderLayout.CENTER);;
			add(auxiliar, BorderLayout.SOUTH);
		}

}
