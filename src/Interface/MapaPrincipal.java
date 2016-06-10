package Interface;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import Algoritmo.Leitor;
import Algoritmo.Limites;


public class MapaPrincipal extends JPanel{

	private boolean keepRunning;
	private boolean keepBreak;
	private static int SIZE = 42;

	private ColorTableModel tableModel= new ColorTableModel();

	private Thread colorShadeThread;

	private ArrayList<Limites> movimento;

	public MapaPrincipal(ArrayList<Limites> movimento) {
		this.movimento = movimento;
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(15);
		for(int i=0;i<SIZE;i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(15);

		table.setDefaultRenderer(Object.class, new ColorRenderer(0));
		add(table);

		this.keepRunning = true;
		this.keepBreak = false;
		this.colorShadeThread = new Thread(new RandomColorShadeRunnable());
		this.colorShadeThread.start();
	}

	private class RandomColorAction extends AbstractAction {
		public RandomColorAction() {
			super("Create Random Color");
		}

		public void actionPerformed(ActionEvent e) {
			//tela.this.tableModel.ColoreMapa(VARIABLE);
		}
	}


	private class ColorTableModel extends AbstractTableModel {
		private Color[][] colors = new Color[SIZE][SIZE];

		public ColorTableModel() {
			for (int i = 0; i < SIZE; i++) {
				for (int x = 0; x < SIZE; x++) {
					colors[i][x] = Color.white;
				}
			}
		}

		public int getRowCount() {
			return SIZE;
		}

		public int getColumnCount() {
			return SIZE;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return colors[rowIndex][columnIndex];
		}

		public void ColoreMapa(String arquivo) {

			Color color = Color.white;
			Leitor l = new Leitor();
			int[][] mapa = null;
			try {
				mapa = l.lerArquivo3(arquivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for(int linha=0; linha<SIZE;linha++)
				for(int coluna=0; coluna<SIZE;coluna++){
					if(mapa[linha][coluna] == 150){
						color = new Color(139, 69, 19); // marrom montanha
					}
					else if(mapa[linha][coluna] == 10){
						color = new Color(124, 252, 0); // verde grama
					}
					else if( mapa[linha][coluna] == 100 ){
						color = new Color(34, 139, 34); // verde floresta
					}
					else if(mapa[linha][coluna] == 180){
						color = new Color(30, 144, 255); //azul da agua
					}
					else if(mapa[linha][coluna] == 20){
						color = new Color(240, 230, 140); // marrom areia
					}


					colors[linha][coluna] = color;
					fireTableCellUpdated(linha, coluna);

				}
			color = new Color(0, 0, 0);
			colors[32][5] = color;
			fireTableCellUpdated(32, 5);
			colors[17][39] = color;
			fireTableCellUpdated(17, 39);
			colors[1][24] = color;
			fireTableCellUpdated(1, 24); // bloco de criação 

			color = new Color(255, 255, 255); // porta lost 
			colors[5][6] = color;
			fireTableCellUpdated(5, 6);

			color = new Color(128, 0, 0); // espada
			colors[1][2] = color;
			fireTableCellUpdated(1, 2);

			color = new Color(153, 50, 204);
			colors[27][24] = color;
			fireTableCellUpdated(27, 24);

		}

		public void movimento(Limites l){
			Color color;
			color = new Color(153, 50, 204);
			colors[l.getLimiteX()][l.getLimiteY()] = color;
			fireTableCellUpdated(l.getLimiteX(), l.getLimiteY());
		}
	}

	private class ColorRenderer implements TableCellRenderer {
		private JLabel label;

		public ColorRenderer(int op) {
			label = new JLabel();
			label.setOpaque(true);
			label.setPreferredSize(new Dimension(0, 0));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			label.setBackground((Color) value);
			return label;
		}
	}

	private class RandomColorShadeRunnable implements Runnable {
		public void run() {
			tableModel.ColoreMapa("mapa_main.txt");
			int i=0;
			while (keepRunning) {
				//colocar a logica de mudança no mapa
				while(keepBreak){

				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				Limites l = movimento.get(i);

				tableModel.movimento(l);
				i++;
				if(i==movimento.size()){
					keepBreak = true;
				}
			}
		}
	}
}
