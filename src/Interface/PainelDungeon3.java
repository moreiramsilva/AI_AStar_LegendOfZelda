package Interface;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import Algoritmo.Leitor;
import Algoritmo.Limites;


public class PainelDungeon3 extends JPanel {

	private boolean keepRunning;

	private static int SIZE = 28;

	private ColorTableModel tableModel= new ColorTableModel();

	private Thread colorShadeThread;

	private ArrayList<Limites> movimento;
	
	public PainelDungeon3() {
		// TODO Auto-generated constructor stub
	}

	public PainelDungeon3(ArrayList<Limites> movimento) {
		this.movimento = movimento;
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(15);
		for(int i=0;i<SIZE;i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(15);

		table.setDefaultRenderer(Object.class, new ColorRenderer(0));
		add(table);

		this.keepRunning = true;
		this.colorShadeThread = new Thread(new RandomColorShadeRunnable());
		this.colorShadeThread.start();
	}

	private class RandomColorAction extends AbstractAction {
		public RandomColorAction() {
			super("Create Random Color");
		}

		public void actionPerformed(ActionEvent e) {
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
				e.printStackTrace();
			}

			for(int linha=0; linha<SIZE;linha++)
				for(int coluna=0; coluna<SIZE;coluna++){
					if(mapa[linha][coluna] == 999){
						color = new Color(120, 134, 107); // verde militar
					}
					else if(mapa[linha][coluna] == 10){
						color = new Color(240, 230, 140); // marrom areia
					}


					colors[linha][coluna] = color;
					fireTableCellUpdated(linha, coluna);

				}

			color = new Color(255, 0, 0); // cor do pingente do poder "VERMELHO"
			colors[19][15] = color;
			fireTableCellUpdated(19, 15);

			color = new Color(0, 0, 0); // preto da porta dungeon
			colors[26][14] = color;
			fireTableCellUpdated(26, 14);

			color = new Color(153, 50, 204); // roxo  representa o agente
			colors[25][14] = color;
			fireTableCellUpdated(25, 14);

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
			tableModel.ColoreMapa("mapa_dg3.txt");
			int i=0;
			while (keepRunning) {
				//colocar a logica de mudança no mapa

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				Limites l = new Limites(movimento.get(i).getLimiteY(), movimento.get(i).getLimiteX());
				tableModel.movimento(l);
				i++;
				if(i==movimento.size()){
					keepRunning = false;
				}
			}
		}
	}
}
