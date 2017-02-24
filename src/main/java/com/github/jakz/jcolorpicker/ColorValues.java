package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ColorValues extends JPanel
{
  private final ColorPicker chooser;
  
  private static final Value[] ids = new Value[] {
    Value.RED, Value.GREEN, Value.BLUE, 
    Value.HUE, Value.SATURATION, Value.BRIGHTNESS,
  };
  
  private static final int[] maxValues = new int[] {
    255, 255, 255, 360, 100, 100
  };
  
  private static final String[] captions = new String[] {
    "R", "G", "B", "H", "S", "B"
  };
  
  private JLabel[] labels;
  private JTextField[] fields;
  
  private Format[] formats = new Format[ids.length];
  
  private boolean visible[] = new boolean[ids.length];
  
  private Color color;
    
  ColorValues(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    labels = new JLabel[captions.length];
    fields = new JTextField[captions.length];
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    for (int i = 0; i < labels.length; ++i)
    {
      formats[i] = Format.DECIMAL;
      visible[i] = true;
      
      labels[i] = new JLabel(captions[i]);
      fields[i] = new JTextField(4);
    }
    
    rebuildFields();
  }
  
  public void rebuildFields()
  {
    removeAll();
    
    for (int i = 0; i < labels.length; ++i)
    {
      if (visible[i])
      {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(labels[i]);
        panel.add(fields[i]);  
        add(panel);
      }
    }
    
    revalidate();
  }
  
  private String getTextValue(float value, int maxValue, Format format)
  {
    switch (format)
    {
      case DECIMAL: return Integer.toString((int)Math.round(value*maxValue));
      case FLOAT: return String.format("%1.3f", value);
      case HEX: return String.format("%02X",(int)Math.round(value*maxValue));
      default: return "";
    }
  }
  
  void setColor(Color color)
  {
    this.color = color;
    
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();
    float[] hsb = Color.RGBtoHSB(r, g, b, null);

    float[] values = { 
        r / 255.0f, g / 255.0f, b / 255.0f, 
        hsb[0], hsb[1], hsb[2] 
    };
    
    for (int i = 0; i < ids.length; ++i)
    {
      String text = getTextValue(values[i], maxValues[i], formats[i]);
      fields[ids[i].ordinal()].setText(text);
    }
  }
  
  void setFormat(Value id, Format format)
  {
    // TODO: forbid hex for hsv
    
    formats[id.ordinal()] = format;
    if (color != null)
      setColor(color);
  }
  
  void setValuevisible(Value id, boolean visible)
  {
    this.visible[id.ordinal()] = visible;
    rebuildFields();
  }
}
