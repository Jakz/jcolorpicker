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
  
  private ColorHSB color;
  private Color rgbColor;
  
  ColorCell(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    rgbColor = Color.WHITE;
    color = new ColorHSB(rgbColor);
    
    setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    Insets insets = this.getInsets();
    
    final int width = getWidth() - (insets.right + insets.left);
    final int height = getHeight() - (insets.top + insets.bottom);  
    
    g.setColor(rgbColor);
    g.fillRect(insets.left, insets.top, width, height);
  }
  
  void setColor(ColorHSB color)
  {
    this.color = color;
    this.rgbColor = color.toRGB();
    repaint();
  }
}
