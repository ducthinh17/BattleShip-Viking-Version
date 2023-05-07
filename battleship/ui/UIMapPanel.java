package battleship.ui;

import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIMapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static int X = 570;
	static int Y = 630;
	int numC = 10;
	int sizeC = 48;
	int oroff = 1;
	int veroff = 1;
	int c1Off = 0;
	int c2Off = 0;
	JButton[][] buttons;
	JLabel[] COr;
	JLabel[] CVer;
	protected JLabel label;
	UIJPanelBG sea;
	Cursor cursorHand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	Cursor cursorDefault = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	public UIMapPanel(String labell) {

		this.setSize(X, Y);
		this.setLayout(null);
		this.setOpaque(false);
		label = new JLabel();
		label.setIcon(new ImageIcon(getClass().getResource(("/res/images/" + labell + ".png"))));
		this.add(label);
		label.setBounds(50, 0, 550, 60);
		sea = new UIJPanelBG(
				Toolkit.getDefaultToolkit().createImage(FrameManageship.class.getResource("/res/images/sea.png")));
		sea.setBounds(34, 45, 550, 550);
		buttons = new JButton[numC][numC];
		ImageIcon gray = new ImageIcon(getClass().getResource("/res/images/grayButtonOpaque.png"));
		for (int i = 0; i < numC; i++) {
			for (int j = 0; j < numC; j++) {
				buttons[i][j] = new JButton(gray);
				buttons[i][j].setSize(sizeC, sizeC);
				sea.add(buttons[i][j]);
				buttons[i][j].setCursor(cursorHand);
				buttons[i][j].setBorder(null);
				buttons[i][j].setOpaque(false);
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].setContentAreaFilled(false);
				buttons[i][j].setBounds(oroff, veroff, sizeC, sizeC);
				if (labell.equals("player")) {
					buttons[i][j].setCursor(cursorDefault);
					buttons[i][j].setDisabledIcon(gray);
					buttons[i][j].setEnabled(false);
				} else {
					buttons[i][j].setCursor(cursorHand);
				}
				oroff += sizeC + 2;
			}
			veroff += sizeC + 2;
			oroff = 1;
		}
		oroff = 40;
		veroff = 0;
		JPanel grid = new JPanel(null);
		grid.setOpaque(false);
		grid.add(sea);
		COr = new JLabel[10];
		CVer = new JLabel[10];
		for (int i = 0; i < 10; i++) {
			COr[i] = new JLabel();
			CVer[i] = new JLabel();
			grid.add(COr[i]);
			grid.add(CVer[i]);
			CVer[i].setIcon(new ImageIcon(getClass().getResource((("/res/images/coord/" + (i + 1) + ".png")))));
			CVer[i].setBounds(veroff, oroff, sizeC, sizeC);
			COr[i].setIcon(new ImageIcon(getClass().getResource((("/res/images/coord/" + (i + 11) + ".png")))));
			COr[i].setBounds(oroff, veroff, sizeC, sizeC);
			oroff += 50;
		}

		this.add(grid);
		grid.setBounds(0, 45, 550, 660);

	}

	void drawShip(int[] data) {
		int x = data[0];
		int y = data[1];
		int size = data[2];
		int dir = data[3];
		ImageIcon shipDim1orizz = new ImageIcon(
				getClass().getResource("/res/images/shipDim1orizz.png"));
		ImageIcon shipDim1vert = new ImageIcon(getClass().getResource("/res/images/shipDim1vert.png"));
		if (size == 1) {
			buttons[x][y].setEnabled(false);
			if (dir == 0)
				buttons[x][y].setDisabledIcon(shipDim1orizz);
			else if (dir == 1)
				buttons[x][y].setDisabledIcon(shipDim1vert);
		} else {
			ImageIcon shipHeadLeft = new ImageIcon(
					getClass().getResource("/res/images/shipHeadLeft.png"));
			ImageIcon shipHeadTop = new ImageIcon(
					getClass().getResource("/res/images/shipHeadTop.png"));
			ImageIcon shipBodyLeft = new ImageIcon(
					getClass().getResource("/res/images/shipBodyLeft.png"));
			ImageIcon shipBodyTop = new ImageIcon(
					getClass().getResource("/res/images/shipBodyTop.png"));
			ImageIcon shipFootLeft = new ImageIcon(
					getClass().getResource("/res/images/shipFootLeft.png"));
			ImageIcon shipFootTop = new ImageIcon(
					getClass().getResource("/res/images/shipFootTop.png"));
			if (dir == 0) {// direction horizontal
				// Ship Head
				buttons[x][y].setDisabledIcon(shipHeadLeft);
				buttons[x][y].setEnabled(false);
				// Ship Body
				for (int i = 1; i < size - 1; i++) {
					buttons[x][y + i].setDisabledIcon(shipBodyLeft);
					buttons[x][y + i].setEnabled(false);
				}
				// Ship Foot
				buttons[x][y + size - 1].setDisabledIcon(shipFootLeft);
				buttons[x][y + size - 1].setEnabled(false);
			} else { // direction vertical
				// Ship Head
				buttons[x][y].setDisabledIcon(shipHeadTop);
				buttons[x][y].setEnabled(false);
				// Ship Body
				for (int i = 1; i < size - 1; i++) {
					buttons[x + i][y].setDisabledIcon(shipBodyTop);
					buttons[x + i][y].setEnabled(false);
				}
				// Ship Foot
				buttons[x + size - 1][y].setDisabledIcon(shipFootTop);
				buttons[x + size - 1][y].setEnabled(false);
			}
		}
	}

}
