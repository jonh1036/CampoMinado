import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Janela extends JFrame implements ActionListener{
	private JButton[][] campo = new JButton[9][9];
	private int minas[][] = new int[9][9];
	private int adjacentes[][] = new int[9][9];
	Font f = new Font("Arial",  Font.PLAIN, 10);
	
	public Janela() {
		setSize(385,405);
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		initComponents();
		setVisible(true);
	}
	
	private void initComponents() {
		int x = 5, y = 5;
		for(int i = 0; i < 9; i++) {//Iniciando os botões e as minas
			for(int j = 0; j < 9; j++) {
				campo[i][j] = new JButton();
				campo[i][j].addActionListener(this);
				campo[i][j].setFont(f);
				minas[i][j] = 0;
				adjacentes[i][j] = 0;
				campo[i][j].setBounds(x, y, 40, 40);
				x += 41;
				add(campo[i][j]);
			}
			y += 41;
			x = 5;
		}
		setMines();//Colocando as minas, quando clicar, comparar se tem minas e executar as ações
	}
	
	private void setMines() {
		int x, y, count = 0;
		
		do{
			x = (int) (Math.random()*9);
			y = (int) (Math.random()*9);
			if(minas[y][x] == 0) {
				minas[y][x] = 1;
				setAdjacentes(x, y);
				countAdjcentes(x, y);
				count++;
			}
		}while(count < 9);
	}
	
	public static void main(String[] args) {
		new Janela();
	}
	
	private void setAdjacentes(int x, int y) {
		if((x-1 >= 0) && minas[y][x-1] == 0) {minas[y][x-1] = -1;	}
		if((x+1 < 9) && minas[y][x+1] == 0) {minas[y][x+1] = -1;	}
		if((y+1 < 9) && minas[y+1][x] == 0) {minas[y+1][x] = -1;	}
		if((y-1 >= 0) && minas[y-1][x] == 0) {minas[y-1][x] = -1;}
		if((y-1 >= 0 && x-1 >=0) && minas[y-1][x-1] == 0) {minas[y-1][x-1] = -1;}
		if((y-1 >= 0 && x+1 < 9) && minas[y-1][x+1] == 0) {minas[y-1][x+1] = -1;}
		if((y+1 < 9 && x+1 < 9) && minas[y+1][x+1] == 0) {minas[y+1][x+1] = -1;}
		if((y+1 < 9 && x-1 >= 0) && minas[y+1][x-1] == 0) {minas[y+1][x-1] = -1;}
	}
	
	private void countAdjcentes(int x, int y) {
		if((x-1 >= 0) && minas[y][x-1] == 1) {adjacentes[y][x-1] += 1;}
		if((x+1 < 9) && minas[y][x+1] == 1) {adjacentes[y][x+1] += 1;}
		if((y+1 < 9) && minas[y+1][x] == 1) {adjacentes[y+1][x] += 1;}
		if((y-1 >= 0) && minas[y-1][x] == 1) {adjacentes[y-1][x] += 1;}
		if((y-1 >= 0 && x-1 >=0) && minas[y-1][x-1] == 1) {adjacentes[y-1][x-1] += 1;}
		if((y-1 >= 0 && x+1 < 9) && minas[y-1][x+1] == 1) {adjacentes[y-1][x+1] += 1;}
		if((y+1 < 9 && x+1 < 9) && minas[y+1][x+1] == 1) {adjacentes[y+1][x+1] += 1;}
		if((y+1 < 9 && x-1 >= 0) && minas[y+1][x-1] == 1) {adjacentes[y+1][x-1] += 1;}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if ( arg0.getSource() == campo[i][j] ) {
					campo[i][j].setText("" + adjacentes[i][j]);
					campo[i][j].setEnabled(false);
				}
			}
		}	            
	}
}
