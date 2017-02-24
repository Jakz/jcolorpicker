package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class ColorCell extends JPanel
{
  private final ColorPicker chooser;
  
  private Color color;
  
  ColorCell(ColorPicker chooser)
  {
    this.chooser = chooser;
    color = Color.WHITE;
    
    setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    Insets insets = this.getInsets();
    
    final int width = getWidth() - (insets.right + insets.left);
    final int height = getHeight() - (insets.top + insets.bottom);  
    
    g.setColor(color);
    g.fillRect(insets.left, insets.top, width, height);
  }
  
  void setColor(Color color)
  {
    this.color = color;
    repaint();
  }
}
