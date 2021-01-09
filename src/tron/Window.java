package tron;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame {

	public int width = 1800;
	public int height = 1000;
	protected JMenu menu;
	protected JMenuBar mb;
	protected JMenuItem m1, m2;
	public Database db = new Database();
	public JTextField tx;
	public String name = "";

	public Window() {
		setTitle("Tron");
		setSize(width, height);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		createMenu();
		setJMenuBar(mb);
		addPanel();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				showExitConfirmation();
			}
		});
	}

	public void createMenu() {
		menu = new JMenu("Menu");
		mb = new JMenuBar();
		m1 = new JMenuItem("New player");
		m2 = new JMenuItem("Top 10");

		menu.add(m1);
		menu.add(m2);

		mb.add(menu);
		
		m1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.add(new JLabel("New player: "));
				tx = new JTextField(20);
				panel.add(tx);
				
				
				if(JOptionPane.showConfirmDialog(null, panel, "Want to save this player? :", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					name = tx.getText();
					db.insertPlayer(name);
				}else {
					System.out.println("no");
				}
			}
		});
		
		m2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, db.getTop10().toString());
			}
		});
	}
	
	
	public void addPanel() {
		getContentPane().invalidate();
		getContentPane().removeAll();
		add(new Gamepanel()).requestFocusInWindow();
		getContentPane().revalidate();
		getContentPane().repaint();
		getContentPane();
	}

	public void showExitConfirmation() {
		int show = JOptionPane.showConfirmDialog(this, "Are you leaving the game?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (show == JOptionPane.YES_OPTION) {
			doExit();
		}
	}

	public void doExit() {
		this.dispose();
	}

}
