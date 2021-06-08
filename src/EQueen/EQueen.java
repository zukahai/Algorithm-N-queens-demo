package EQueen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class EQueen extends JFrame{
	Container cn;
	Timer timer;
	JButton bt[][] = new JButton[9][9];
	public EQueen() {
		super("Algorithms eight queens demo - HaiZuka");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(8, 8));
		
		for (int i = 1; i <= 8; i ++)
			for (int j = 1; j <= 8; j++) {
				bt[i][j] = new JButton();
//				bt[i][j].setFont(new Font("Britannic Bold", 1, 25));
//				bt[i][j].setBackground(Color.black);
				bt[i][j].setForeground(Color.white);
				pn.add(bt[i][j]);
			}
		
		
		cn.add(pn);
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		return cn;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EQueen();
	}

}
