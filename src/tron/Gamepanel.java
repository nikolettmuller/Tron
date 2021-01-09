package tron;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {

	protected int width = 1800;
	protected int height = 1000;
	protected Timer timer;
	protected Motor mt;
	protected Motor mt1;
	
	protected String name1;
	protected String name2;
	
	protected JTextField tx1;
	protected JTextField tx2;

	protected Color c;
	protected Color c1;
	public Window w;
	
	protected Database db;

	public Gamepanel() {
		chooseLineColor1();
		chooseLineColor2();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		init();
		start();
		name1 = tx1.getText();
		name2 = tx2.getText();
		db = new Database();
		checkUser(name1);
		checkUser(name2);
	}

	public void init() {
		mt = new Motor(10, 10, c);
		mt1 = new Motor(10, height - 200, c1);

	}
	
	public void checkUser(String name) {
		if(db.saveUser(name) == false) {
			db.insertPlayer(name);
		}
	}

	@Override
	public void paintComponent(Graphics g2) {

		super.paintComponent(g2);
		g2.setColor(Color.BLUE);
		mt.draw(g2);
		g2.setColor(Color.RED);
		mt1.draw(g2);
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			mt.setHeight(20);
			mt.setWidth(80);
			mt.velx = -1;
			mt.vely = 0;
			mt.line.add(new Line(mt.x, mt.y, mt.x, mt.y));
		}

		if (c == KeyEvent.VK_UP) {
			mt.setHeight(80);
			mt.setWidth(20);
			mt.velx = 0;
			mt.vely = -1;
			mt.line.add(new Line(mt.x, mt.y, mt.x, mt.y));
		}

		if (c == KeyEvent.VK_RIGHT) {
			mt.setHeight(20);
			mt.setWidth(80);
			mt.velx = 1;
			mt.vely = 0;
			mt.line.add(new Line(mt.x, mt.y, mt.x, mt.y));

		}

		if (c == KeyEvent.VK_DOWN) {
			mt.setHeight(80);
			mt.setWidth(20);
			mt.velx = 0;
			mt.vely = 1;
			mt.line.add(new Line(mt.x, mt.y, mt.x, mt.y));
		}

		if (c == KeyEvent.VK_A) {
			mt1.setHeight(20);
			mt1.setWidth(80);
			mt1.velx = -1;
			mt1.vely = 0;
			mt1.line.add(new Line(mt1.x, mt1.y, mt1.x, mt1.y));
		}

		if (c == KeyEvent.VK_W) {
			mt1.setHeight(80);
			mt1.setWidth(20);
			mt1.velx = 0;
			mt1.vely = -1;
			mt1.line.add(new Line(mt1.x, mt1.y, mt1.x, mt1.y));
		}

		if (c == KeyEvent.VK_D) {
			mt1.setHeight(20);
			mt1.setWidth(80);
			mt1.velx = 1;
			mt1.vely = 0;
			mt1.line.add(new Line(mt1.x, mt1.y, mt1.x, mt1.y));

		}

		if (c == KeyEvent.VK_S) {
			mt1.setHeight(80);
			mt1.setWidth(20);
			mt1.velx = 0;
			mt1.vely = 1;
			mt1.line.add(new Line(mt1.x, mt1.y, mt1.x, mt1.y));
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		boolean l = checkCollision(mt1, mt, name2);
		if(l == true) {
			overConfirmation();
		}
		
		boolean f = checkCollision(mt, mt1, name1);
		if(f == true) {
			overConfirmation();
		}

		if (mt.x < 0 ) {
			db.updateScore(name2);
			overConfirmation();	
		}
		if (mt1.x < 0) {
			db.updateScore(name1);
			overConfirmation();	
		}

		if (mt.x > width - 30) {
			db.updateScore(name2);
			overConfirmation();
			
		}
		if (mt1.x > width - 30) {
			db.updateScore(name1);
			overConfirmation();
		}

		if (mt.y < 0) {
			db.updateScore(name2);
			overConfirmation();
			
		}
		if (mt1.y < 0) {
			db.updateScore(name1);
			overConfirmation();
		}
		
		if (mt.y > height - 120) {
			db.updateScore(name2);
			overConfirmation();

		}
		if (mt1.y > height - 120) {
			db.updateScore(name1);
			overConfirmation();

		}

		mt.x = mt.x + mt.velx;
		mt.y = mt.y + mt.vely;
		mt.line.get(mt.line.size() - 1).setX2(mt.x);
		mt.line.get(mt.line.size() - 1).setY2(mt.y);

		mt1.x = mt1.x + mt1.velx;
		mt1.y = mt1.y + mt1.vely;
		mt1.line.get(mt1.line.size() - 1).setX2(mt1.x);
		mt1.line.get(mt1.line.size() - 1).setY2(mt1.y);
		repaint();
	}

	public boolean checkCollision(Motor m1, Motor m2, String name) {
		for (int i = 0; i < m2.line.size(); i++) {
			if(m2.line.get(i).isInTheLine(m1.x, m1.y)) {
				db.updateScore(name);
				return true;
			}
		}
		return false;
	}

	public void start() {
		timer = new Timer(5, this);
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void restart() {
		timer.restart();
		mt = new Motor(10, 10, c);
		mt1 = new Motor(10, height - 120, c1);
	}
	
	
	public void chooseLineColor1() {

		Object[] options = { "Green", "Blue", "Red", "Yellow", "Cyan", "Magenta" };
		JPanel panel = new JPanel();
		panel.add(new JLabel("Name: "));
		tx1 = new JTextField(20);
		panel.add(tx1);

		int result = JOptionPane.showOptionDialog(null, panel, "Choose the color of your line ",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (result == 0) {
			c = Color.GREEN;

		}
		if (result == 1) {
			c = Color.BLUE;
		}
		if (result == 2) {
			c = Color.RED;
		}
		if (result == 3) {
			c = Color.YELLOW;
		}
		if (result == 4) {
			c = Color.CYAN;
		}
		if (result == 5) {
			c = Color.MAGENTA;
		}
	}

	
	
	public void chooseLineColor2() {

		Object[] options = { "Green", "Blue", "Red", "Yellow", "Cyan", "Magenta" };
		JPanel panel = new JPanel();
		panel.add(new JLabel("Name: "));
		tx2 = new JTextField(20);
		panel.add(tx2);

		int result = JOptionPane.showOptionDialog(null, panel, "Choose the color of your line ",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (result == 0) {
			c1 = Color.GREEN;

		}
		if (result == 1) {
			c1 = Color.BLUE;
		}
		if (result == 2) {
			c1 = Color.RED;
		}
		if (result == 3) {
			c1 = Color.YELLOW;
		}
		if (result == 4) {
			c1 = Color.CYAN;
		}
		if (result == 5) {
			c1 = Color.MAGENTA;
		}
	}

	public void overConfirmation() {
		int n = JOptionPane.showConfirmDialog(null, "Do you want to play an other game?", "Game over ",
				JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			stop();
			chooseLineColor1();
			chooseLineColor2();
			init();
			restart();

		} else {
			System.exit(0);
		}
	}

}
