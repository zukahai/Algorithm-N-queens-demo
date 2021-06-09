package EQueen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Stack;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	TextArea tf;
	JButton bt[][] = new JButton[200][200];
	int Q[][] = new int[200][200];
	int cl[][] = new int[200][200];
	int N = 6;
	int delay = 0;
	int k = 1, K = 100;
	
	public int HaiZuka(int delay) {
		return 150 / delay;
	}
	
	public EQueen(int N, int delay) {
		super("Algorithms eight queens demo - HaiZuka");
		this.N = N;
		this.delay = delay;
		K = k = HaiZuka((int)Math.sqrt(delay));
		cn = init();
		timer.start();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		Vector v = new Vector<>();
		v.add(1);
		v.add(0);
		st.push(v);
		for (int i = 0; i < 3 * N; i++)
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
		
		tf = new TextArea();
		tf.setBackground(Color.black);
		tf.setForeground(Color.white);
		
		cn.add(tf, "East");
		cn.add(pn);
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		setIcon();
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (k == K) {
					Vector v = new Vector();
					if (!st.empty())
						v = st.peek();
					int I = (int)v.get(0);
					int J = (int)v.get(1) + 1;
					if (J > 1) {
						bt[I][J - 1].setBackground(Color.white);
						cl[I][J - 1] = 0;
						seticonSquar(I, J - 1);
						J--;
						if (Q[I][J] == 1) {
							c[J] = c1[I - J + N] = c2[I + J - 2] = true;
							Q[I][J] = 0;
							cl[I][J] = 0;
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
							Q[I][J] = cl[I][J] = 1;
							seticonSquar(I, J);
							c[J] = c1[I - J + N] = c2[I + J - 2] = false;
							Vector vv = new Vector();
							vv.add(I + 1);
							vv.add(0);
							st.pop();
							st.push(v);
							if (I == N) {
								Stack<Vector<Integer>> ST = st;
								tf.setText(tf.getText() + (count++) + ": " + getChess(ST) + "\n");
								k = 0;
//								timer.stop();
							} else {
								st.push(vv);
							}
						} else {
							cl[I][J] = 2;
							seticonSquar(I, J);
						}
					}
				}
				else k++;
			}
		});
		
		return cn;
	}
	
	public void setIcon() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {
				seticonSquar(i, j);
			}
	}
	
	public void seticonSquar(int i, int j) {
		String name = "";
		name = name + cl[i][j] +(i + j) % 2;
		bt[i][j].setIcon(getIcon(name));
	}
	
	private Icon getIcon(String name) {
		int size = 700 / N;
		int width = size, height = size;
		Image image = new ImageIcon(getClass().getResource("/Icons/" + name + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
	
	public String getChess(Stack<Vector<Integer>> stt) {
		Vector<Vector<Integer>> Vec = new Vector();
		String s = "";
		while (!stt.empty()) {
			Vector v = stt.pop();
			Vec.add(v);
			int I = (int) v.get(0);
			int J = (int) v.get(1);
			s = " ("  + I + ", " + J + ") " + s;
		}
		for (int i = Vec.size() - 1; i >= 0; i--)
			stt.add(Vec.elementAt(i));
		return s;
	}
	
	public boolean check(int i, int j) {
		if (!c[j] || !c1[i - j + N] || !c2[i + j - 2])
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EQueen(4, 1);
	}
}
