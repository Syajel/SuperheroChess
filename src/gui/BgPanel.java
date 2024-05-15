package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.xml.transform.Source;

public class BgPanel extends JPanel {
	Image bg;
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }
	
	public void setbg(String s) {
		bg = new ImageIcon(s).getImage();
	}

}
