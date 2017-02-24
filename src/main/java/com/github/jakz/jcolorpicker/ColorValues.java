package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ColorValues extends JPanel
{
  private final ColorPicker chooser;
  
  private final static int RED = 0;
  private final static int GREEN = 1;
  private final static int BLUE = 2;
  private final static int HUE = 3;
  private final static int SATURATION = 4;
  private final static int BRIGHTNESS = 5;
  
  private static final String[] captions = new String[] {
    "R", "G", "B", "H", "S", "B"
  };
  
  private JLabel[] labels;
  private JTextField[] fields;
  
  ColorValues(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    labels = new JLabel[captions.length];
    fields = new JTextField[captions.length];
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    for (int i = 0; i < labels.length; ++i)
    {
      labels[i] = new JLabel(captions[i]);
      fields[i] = new JTextField(3);
      
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.add(labels[i]);
      panel.add(fields[i]);
      
      add(panel);
    }
  }
  
  void setColor(Color color)
  {
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();
    
    fields[RED].setText(Integer.toString(r));
    fields[GREEN].setText(Integer.toString(g));
    fields[BLUE].setText(Integer.toString(b));
    
    float[] hsb = Color.RGBtoHSB(r, g, b, null);
    
    
    fields[HUE].setText(Float.toString(hsb[0]));
    fields[SATURATION].setText(Float.toString(hsb[1]));
    fields[BRIGHTNESS].setText(Float.toString(hsb[2]));
  }
}
