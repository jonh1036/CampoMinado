import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Janela extends JFrame implements ActionListener {
	private JButton[][] campo = new JButton[9][9];
	private int minas[][] = new int[9][9];
	private int adjacentes[][] = new int[9][9];
	Font f = new Font("Arial", Font.PLAIN, 8);
	int countToWin = 72;

	public Janela() {
		setSize(385, 405);
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		initComponents();
		setVisible(true);
	}

	int i, j, linha, coluna;;

	private void initComponents() {
		int x = 5, y = 5;
				
		for(i = 0; i < 9; i++) {//Iniciando os botões e as minas
			for(j = 0; j < 9; j++) {
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

		do {
			x = (int) (Math.random() * 9);
			y = (int) (Math.random() * 9);
			if (minas[y][x] == 0) {
				minas[y][x] = 1;
				setAdjacentes(x, y);
				countAdjcentes(x, y);
				count++;
			}
		} while (count < 9);
	}

	public static void main(String[] args) {
		new Janela();
	}

	private void setAdjacentes(int x, int y) {// Configurando onde tem áreas adjacentes
		if ((x - 1 >= 0) && minas[y][x - 1] == 0) {
			minas[y][x - 1] = -1;
		}
		if ((x + 1 < 9) && minas[y][x + 1] == 0) {
			minas[y][x + 1] = -1;
		}
		if ((y + 1 < 9) && minas[y + 1][x] == 0) {
			minas[y + 1][x] = -1;
		}
		if ((y - 1 >= 0) && minas[y - 1][x] == 0) {
			minas[y - 1][x] = -1;
		}
		if ((y - 1 >= 0 && x - 1 >= 0) && minas[y - 1][x - 1] == 0) {
			minas[y - 1][x - 1] = -1;
		}
		if ((y - 1 >= 0 && x + 1 < 9) && minas[y - 1][x + 1] == 0) {
			minas[y - 1][x + 1] = -1;
		}
		if ((y + 1 < 9 && x + 1 < 9) && minas[y + 1][x + 1] == 0) {
			minas[y + 1][x + 1] = -1;
		}
		if ((y + 1 < 9 && x - 1 >= 0) && minas[y + 1][x - 1] == 0) {
			minas[y + 1][x - 1] = -1;
		}
	}

	private void countAdjcentes(int x, int y) {// Configurando a quantidade de bombas matriz de adjacentes:
		if ((x - 1 >= 0) && minas[y][x - 1] == -1) {
			adjacentes[y][x - 1] += 1;
		}
		if ((x + 1 < 9) && minas[y][x + 1] == -1) {
			adjacentes[y][x + 1] += 1;
		}
		if ((y + 1 < 9) && minas[y + 1][x] == -1) {
			adjacentes[y + 1][x] += 1;
		}
		if ((y - 1 >= 0) && minas[y - 1][x] == -1) {
			adjacentes[y - 1][x] += 1;
		}
		if ((y - 1 >= 0 && x - 1 >= 0) && minas[y - 1][x - 1] == -1) {
			adjacentes[y - 1][x - 1] += 1;
		}
		if ((y - 1 >= 0 && x + 1 < 9) && minas[y - 1][x + 1] == -1) {
			adjacentes[y - 1][x + 1] += 1;
		}
		if ((y + 1 < 9 && x + 1 < 9) && minas[y + 1][x + 1] == -1) {
			adjacentes[y + 1][x + 1] += 1;
		}
		if ((y + 1 < 9 && x - 1 >= 0) && minas[y + 1][x - 1] == -1) {
			adjacentes[y + 1][x - 1] += 1;
		}
	}

	private void showAdjacentes(int i, int j) {// Fazer o "splash" no tabuleiro
		if ((j - 1 >= 0) && minas[i][j - 1] == 0) {
			campo[i][j - 1].setEnabled(false);
			countToWin -= 1;
		}
		if ((j + 1 < 9) && minas[i][j + 1] == 0) {
			campo[i][j + 1].setEnabled(false);
			countToWin -= 1;
		}
		if ((i + 1 < 9) && minas[i + 1][j] == 0) {
			campo[i + 1][j].setEnabled(false);
			countToWin -= 1;
		}
		if ((i - 1 >= 0) && minas[i - 1][j] == 0) {
			campo[i - 1][j].setEnabled(false);
			countToWin -= 1;
		}
		if ((i - 1 >= 0 && j - 1 >= 0) && minas[i - 1][j - 1] == 0) {
			campo[i - 1][j - 1].setEnabled(false);
			countToWin -= 1;
		}
		if ((i - 1 >= 0 && j + 1 < 9) && minas[i - 1][j + 1] == 0) {
			campo[i - 1][j + 1].setEnabled(false);
			countToWin -= 1;
		}
		if ((i + 1 < 9 && j + 1 < 9) && minas[i + 1][j + 1] == 0) {
			campo[i + 1][j + 1].setEnabled(false);
			countToWin -= 1;
		}
		if ((i + 1 < 9 && j - 1 >= 0) && minas[i + 1][j - 1] == 0) {
			campo[i + 1][j - 1].setEnabled(false);
			countToWin -= 1;
		}

		if ((i > 0 && i < 8) && (j > 0 && j < 8)) {
			if (minas[i][j - 1] == 0)
				showAdjacentes(i, j - 1);
			if (minas[i][j + 1] == 0)
				showAdjacentes(i, j + 1);
			if (minas[i + 1][j] == 0)
				showAdjacentes(i + 1, j);
			if (minas[i - 1][j] == 0)
				showAdjacentes(i - 1, j);
			if (minas[i - 1][j - 1] == 0)
				showAdjacentes(i - 1, j - 1);
			if (minas[i - 1][j + 1] == 0)
				showAdjacentes(i - 1, j + 1);
			if (minas[i + 1][j + 1] == 0)
				showAdjacentes(i + 1, j + 1);
			if (minas[i + 1][j - 1] == 0)
				showAdjacentes(i + 1, j - 1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Icon icone = new ImageIcon(getClass().getResource("/images/bomb.png"));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arg0.getSource() == campo[i][j]) {
					if (minas[i][j] == 0) {// Sem minas
						showAdjacentes(i, j);
					} else if (minas[i][j] == -1) {// Sem minas e área adjacente
						campo[i][j].setText("" + adjacentes[i][j]);
						countToWin -= 1;
					} else if (minas[i][j] == 1) {// Com minas
						campo[i][j].setIcon(icone);
						JOptionPane.showMessageDialog(null, "Você perdeu!", "Game over", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
					campo[i][j].setEnabled(false);
				}
			}
		}
		if (countToWin == 0) {
			JOptionPane.showMessageDialog(null, "Você ganhou!", "Vitória", JOptionPane.OK_OPTION);
			System.exit(0);
		}
	}
}