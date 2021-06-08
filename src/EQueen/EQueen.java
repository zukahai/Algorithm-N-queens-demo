package EQueen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Stack;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class EQueen extends JFrame{
	Container cn;
	Stack<Vector<Integer>> st = new Stack<>();
	boolean c1[] = new boolean[20];
	boolean c2[] = new boolean[20];
	boolean c[] = new boolean[20];
	boolean h[] = new boolean[20];
	Timer timer;
	JButton bt[][] = new JButton[9][9];
	public EQueen() {
		super("Algorithms eight queens demo - HaiZuka");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		Vector v = new Vector<>();
		v.add(1);
		v.add(0);
		st.push(v);
		for (int i = 0; i < 20; i++)
			c1[i] = c2[i] = h[i] = c[i] = true;
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
		timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector v = st.peek();
				int I = (int)v.get(0);
				int J = (int)v.get(1) + 1;
				if (J > 1)
					bt[I][J - 1].setBackground(Color.white);
				if (J == 9 ) {
					J--;
					h[I] = c[J] = c1[I - J + 7] = c2[I + J - 2] = true;
					st.pop();
					v = st.peek();
					I = (int)v.get(0);
					J = (int)v.get(1);
					h[I] = c[J] = c1[I - J + 7] = c2[I + J - 2] = true;
				} else {
					v.set(1, J);
					bt[I][J].setBackground(Color.green);
					if (check(I, J)) {
						h[I] = c[J] = c1[I - J + 7] = c2[I + J - 2] = false;
						Vector vv = new Vector();
						vv.add(I + 1);
						vv.add(0);
						st.pop();
						st.push(v);
						if (I == 8) {
							System.out.println(st);
						} else {
							st.push(vv);
						}
					} else {
						bt[I][J].setBackground(Color.white);
					}
				}
			}
		});
		
		return cn;
	}
	
	public boolean check(int i, int j) {
		if (!h[i] || !c[j] || !c1[i - j + 7] || !c2[i + j - 2])
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EQueen().timer.start();
//		Stack<Vector<Integer>> st = new Stack<>();
//		Vector <Integer> v = new Vector<>();
//		v.add(2);
//		v.add(3);
//		st.push(v);
//		System.out.println(st);
	}

}
