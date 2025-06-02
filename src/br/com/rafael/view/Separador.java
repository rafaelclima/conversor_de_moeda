package br.com.rafael.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Separador extends JPanel {

  public Separador() {
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.LIGHT_GRAY);
    int y = getHeight() / 2;
    int margem = 8;
    g.drawLine(margem, y, getWidth() - margem, y);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(60, 20);
  }

}
