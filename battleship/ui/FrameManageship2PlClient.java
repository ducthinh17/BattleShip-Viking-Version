package battleship.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;

import battleship.Map;

public class FrameManageship2PlClient extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 2923975805665801740L;
	private static final int NUM_NAVI = 10;
	LinkedList<int[]> myShips;// contiene le navi inserite,serve per
	LinkedList<int[]> advShips; // costruire la frameBattle
	boolean done = false;
	int naviInserite = 0;
	int[] counterShip = { 1, 2, 3, 4 };
	Map map;
	UIManagePanel choosePan;
	UIMapPanel mapPanel;

	public FrameManageship2PlClient() {
		super("Naval Battle - Pirate Edition");
		map = new Map();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(900, 672);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/images/icon.png")));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
		UIJPanelBG container = new UIJPanelBG(
				Toolkit.getDefaultToolkit().createImage(getClass().getResource("/res/images/wood.jpg")));
		mapPanel = new UIMapPanel("manage");
		container.add(mapPanel);
		choosePan = new UIManagePanel();
		container.add(choosePan);
		mapPanel.setBounds(25, 25, 600, 620);
		choosePan.setBounds(580, 25, 280, 800);
		// Pannello interno contenente le navi da positionre.
		this.add(container);
		for (int i = 0; i < mapPanel.buttons.length; i++) {
			for (int j = 0; j < mapPanel.buttons[i].length; j++) {
				mapPanel.buttons[i][j].addActionListener(this);
				mapPanel.buttons[i][j].setActionCommand("" + i + " " + j);
			}
		}
		choosePan.random.addActionListener(this);
		choosePan.reset.addActionListener(this);
		choosePan.plays.addActionListener(this);
		myShips = new LinkedList<int[]>();
		advShips = new LinkedList<int[]>();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		String text = source.getText();
		// RESET
		if (text.equals("reset")) {
			reset();
		}
		// RANDOM
		else if (text.equals("random")) {
			random();
		}
		// GIOCA
		else if (text.equals("plays")) {
			plays();

		} else {
			if (done) {
				return;
			}
			StringTokenizer st = new StringTokenizer(source.getActionCommand(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int ship = -1;
			int size = 0;
			int dir;
			for (int i = 0; i < choosePan.ship.length; i++) {
				if (choosePan.ship[i].isSelected())
					ship = i;
			}
			switch (ship) {
				case 0:
					size = 4;
					break;
				case 1:
					size = 3;
					break;
				case 2:
					size = 2;
					break;
				case 3:
					size = 1;
					break;
			}
			if (choosePan.direction[0].isSelected())// 0=horizontal 1=vertical
				dir = 0;
			else
				dir = 1;
			boolean inserted = map.enterShip(x, y, size, dir);
			if (inserted) {
				// incrementa il numero di navi inserite
				naviInserite++;
				// decrementa il contatore della ship inserita
				counterShip[ship]--;
				choosePan.counterLabel[ship].setText("" + counterShip[ship]);
				// disabilita la ship se sono state inserite tutte e seleziona
				// automaticamente un'altra ship da inserire
				if (choosePan.counterLabel[ship].getText().equals("0")) {
					choosePan.ship[ship].setEnabled(false);
					for (int i = 0; i < choosePan.ship.length; i++) {
						if (choosePan.ship[i].isEnabled() && !choosePan.ship[i].isSelected()) {
							choosePan.ship[i].setSelected(true);
							break;
						}
					}
				}
				// verifica se abbiamo inserted tutte le navi (10)
				if (naviInserite == NUM_NAVI) {
					done = true;
					choosePan.direction[0].setEnabled(false);
					choosePan.direction[1].setEnabled(false);
					choosePan.plays.setEnabled(true);
				}
				int[] data = { x, y, size, dir };
				myShips.add(data);
				mapPanel.drawShip(data);
			}
		}
		this.requestFocusInWindow();
	}

	private void random() {
		if (naviInserite == NUM_NAVI) {
			reset();
		}
		Random r = new Random();
		int[] data = new int[4];
		for (int i = 0; i < counterShip.length; i++) {
			for (int j = 0; j < counterShip[i]; j++) {
				data = map.enterShipRandom(r, counterShip.length - i);
				myShips.add(data);
				mapPanel.drawShip(data);
			}
		}
		naviInserite = NUM_NAVI;
		done = true;
		choosePan.plays.setEnabled(true);
		for (int i = 0; i < choosePan.ship.length; i++) {
			choosePan.ship[i].setEnabled(false);
		}
		choosePan.direction[0].setEnabled(false);
		choosePan.direction[1].setEnabled(false);
		for (int i = 0; i < counterShip.length; i++) {
			counterShip[i] = 0;
			choosePan.counterLabel[i].setText("0");
		}
		choosePan.ship[0].setSelected(true);

	}

	private void reset() {
		map = new Map();
		myShips = new LinkedList<int[]>();
		for (int i = 0; i < Map.SIZE_MAP; i++) {
			for (int j = 0; j < Map.SIZE_MAP; j++) {
				mapPanel.buttons[i][j].setEnabled(true);
			}
		}
		done = false;
		choosePan.plays.setEnabled(false);
		for (int i = 0; i < choosePan.ship.length; i++) {
			choosePan.ship[i].setEnabled(true);
		}
		choosePan.direction[0].setEnabled(true);
		choosePan.direction[1].setEnabled(true);
		for (int i = 0; i < counterShip.length; i++) {
			counterShip[i] = i + 1;
			choosePan.counterLabel[i].setText("" + (i + 1));
		}
		choosePan.ship[0].setSelected(true);
		naviInserite = 0;
	}

	private void plays() {
		new SendShipsAdv().start();
		FrameBattle2Pl battle = new FrameBattle2Pl(myShips, advShips, map);
		battle.frame.setVisible(true);
		this.setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		char s = Character.toLowerCase(arg0.getKeyChar());
		int tasto = arg0.getKeyCode();
		if (s == 'g') {

			random();
			plays();
		} else {
			if (s == 'r') {
				random();
			} else {
				if (tasto == KeyEvent.VK_DELETE || tasto == KeyEvent.VK_BACK_SPACE) {
					reset();
				} else {
					if (tasto == KeyEvent.VK_ESCAPE) {
						System.exit(0);
					}
				}
				if (tasto == KeyEvent.VK_ENTER) {
					if (done) {
						plays();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	class SendShipsAdv extends Thread {
		public void run() {
			try {
				Socket s = new Socket("localhost", 8081);
				ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
				output.writeObject(myShips);
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
