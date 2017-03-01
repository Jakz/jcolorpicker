package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

class ColorValues extends JPanel implements FocusListener, MouseListener,
  ActionListener
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
  
  private final Map<JTextField, Value> mapping;
  
  private JLabel[] labels;
  private JTextField[] fields;
  
  private Format[] formats = new Format[ids.length];
  
  private boolean visible[] = new boolean[ids.length];
  
  private ColorHSB color;
    
  ColorValues(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    mapping = new HashMap<>();
    labels = new JLabel[captions.length];
    fields = new JTextField[captions.length];
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    for (int i = 0; i < labels.length; ++i)
    {
      formats[i] = Format.DECIMAL;
      visible[i] = true;
      
      labels[i] = new JLabel(captions[i]);
      fields[i] = new JTextField(4);
      fields[i].addFocusListener(this);
      fields[i].addMouseListener(this);
      fields[i].addActionListener(this);
      
      mapping.put(fields[i], ids[i]);
    }
    
    rebuildFields();
  }
  
  public void rebuildFields()
  {
    removeAll();
    
    GroupLayout layout = new GroupLayout(this);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    setLayout(layout);
    
    GroupLayout.ParallelGroup labelGroup = layout.createParallelGroup();
    GroupLayout.ParallelGroup fieldsGroup = layout.createParallelGroup();

    for (int i = 0; i < labels.length; ++i)
    {
      if (visible[i])
      {
        labelGroup.addComponent(labels[i]);
        fieldsGroup.addComponent(fields[i]);
      }
    }
    
    GroupLayout.SequentialGroup horGroup = layout.createSequentialGroup();
    horGroup.addGroup(labelGroup).addGroup(fieldsGroup);
    layout.setHorizontalGroup(horGroup);
    
    GroupLayout.SequentialGroup verGroup = layout.createSequentialGroup();

    for (int i = 0; i < labels.length; ++i)
    {      
      if (visible[i])
      {
        GroupLayout.ParallelGroup group = layout.createParallelGroup(Alignment.BASELINE);
        group.addComponent(labels[i]);
        group.addComponent(fields[i]);
        verGroup.addGroup(group);
      }
    }
    
    layout.setVerticalGroup(verGroup);
    
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
  
  void setColor(ColorHSB color)
  {
    this.color = color;
    
    Color colorRgb = color.toRGB();
    
    int r = colorRgb.getRed();
    int g = colorRgb.getGreen();
    int b = colorRgb.getBlue();

    float[] values = { 
        r / 255.0f, g / 255.0f, b / 255.0f, 
        color.h, color.s, color.b 
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
  
  void setValueVisible(Value id, boolean visible)
  {
    this.visible[id.ordinal()] = visible;
    rebuildFields();
  }

  @Override
  public void focusGained(FocusEvent e)
  {
    if (!e.isTemporary())
    {   
      System.out.println("Focus Gained");
      if (chooser.isAutoSelectColorValueEnabled())
        ((JTextField)e.getSource()).selectAll();
    }
  }

  @Override
  public void focusLost(FocusEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    System.out.println("Mouse Clicked");
    if (chooser.isAutoSelectColorValueEnabled())
      ((JTextField)e.getSource()).selectAll(); 
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if (chooser.isAutoSelectColorValueEnabled())
      ((JTextField)e.getSource()).selectAll();   
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
  private void parseValue(Value id)
  {
    final int i = id.ordinal();
    float value = Float.NaN;
    
    /* then it's RGB or HSB value */
    if (!id.isHex())
    {

    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    JTextField field = (JTextField)e.getSource();
    parseValue(mapping.get(field)); 
  }
}
