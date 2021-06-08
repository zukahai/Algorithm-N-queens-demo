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
	boolean c1[] = new boolean[2000];
	boolean c2[] = new boolean[2000];
	boolean c[] = new boolean[2000];
	Timer timer;
	int count = 1;
	JButton bt[][] = new JButton[100][100];
	int Q[][] = new int[100][100];
	int N = 4;
	int delay = 1000;
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
			c1[i] = c2[i] = c[i] = true;
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(N, N));
		
		for (int i = 1; i <= N; i ++)
			for (int j = 1; j <= N; j++) {
				bt[i][j] = new JButton();
//				bt[i][j].setFont(new Font("Britannic Bold", 1, 25));
				bt[i][j].setBackground(Color.white);
				Q[i][j] = 0;
				pn.add(bt[i][j]);
			}
		
		
		cn.add(pn);
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector v = st.peek();
				int I = (int)v.get(0);
				int J = (int)v.get(1) + 1;
				if (J > 1) {
					bt[I][J - 1].setBackground(Color.white);
					J--;
					if (Q[I][J] == 1) {
						c[J] = c1[I - J + N] = c2[I + J - 2] = true;
						Q[I][J] = 0;
					}
					J++;
				}
				if (J == N + 1 ) {
					st.pop();
					if (st.empty())
						timer.stop();
				} else {
					v.set(1, J);
					bt[I][J].setBackground(Color.green);
					if (check(I, J)) {
						Q[I][J] = 1;
//						System.out.println((I - J + 7) + " : " + c1[I - J + 7]);
						c[J] = c1[I - J + N] = c2[I + J - 2] = false;
//						System.out.println((I - J + 7) + " : " + c1[I - J + 7]);
						Vector vv = new Vector();
						vv.add(I + 1);
						vv.add(0);
						st.pop();
						st.push(v);
						if (I == N) {
							System.out.println((count++) +": " + st);
//							timer.stop();
						} else {
							st.push(vv);
						}
					} else {
						bt[I][J].setBackground(Color.yellow);
					}
				}
			}
		});
		
		return cn;
	}
	
	public boolean check(int i, int j) {
		if (!c[j] || !c1[i - j + N] || !c2[i + j - 2])
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
