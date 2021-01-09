package tron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Motor {
	
	protected int x ;
	protected int y ;
	protected int velx = 0;
	protected int vely = 0;
	public int width = 20;
	public int height = 80;
	public int widthMid = 10;
	public int heigthMid = 40;
	protected ArrayList<Line> line = new ArrayList<Line>();
	protected Color c;
	
	public Motor(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		line.add(new Line(x, y, x, y));
		this.c = c;
	}
	
	public void draw(Graphics g2) {

		g2.fillOval(x, y, width, height);
		g2.setColor(c);
		for (int i = 0; i < line.size(); i++) {
			g2.drawLine(line.get(i).getX1(), line.get(i).getY1(), line.get(i).getX2(), line.get(i).getY2());
		}
	
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidthMid() {
		return widthMid;
	}

	public void setWidthMid(int widthMid) {
		this.widthMid = widthMid;
	}

	public int getHeigthMid() {
		return heigthMid;
	}

	public void setHeigthMid(int heigthMid) {
		this.heigthMid = heigthMid;
	}

}
