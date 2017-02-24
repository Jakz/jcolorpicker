package com.github.jakz.jcolorpicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ColorPicker extends JPanel
{
  private final ColorCanvas colorCanvas;
  private final HueCanvas hueCanvas;
  private final ColorCell colorCell;
  private final ColorValues colorValues;
  
  private Cursor pickCursor;
  
  public ColorPicker()
  {
    colorCanvas = new ColorCanvas(this);
    hueCanvas = new HueCanvas(this);
    colorCell = new ColorCell(this);
    colorValues = new ColorValues(this);
    
    pickCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    
    setPreferredSize(new Dimension(500,400));
    
    GridBagConstraints c = new GridBagConstraints();
    
    setLayout(new GridBagLayout());
    
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(5,5,5,10);
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 3;
    c.gridheight = 3;
    c.weightx = 0.8;
    c.weighty = 1.0;
    
    add(colorCanvas, c);
    
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(5,0,5,5);
    c.gridx = 4;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 3;
    c.weightx = 0.1;
    c.weighty = 1.0;
    
    add(hueCanvas, c);
    
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(5,5,5,5);
    c.anchor = GridBagConstraints.NORTH;
    c.gridx = 5;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 0.1;
    c.weighty = 0.2;
    
    add(colorCell, c);
    
    c.fill = GridBagConstraints.NONE;
    c.gridx = 5;
    c.gridy = 1;
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weightx = 0.1;
    //c.weighty = 0.8;
    
    add(colorValues, c);

  }
  
  void pickHue(float hue)
  {
    colorCanvas.huePicked(hue);
  }
  
  void pickColor(Color color)
  {
    colorCell.setColor(color);
    colorValues.setColor(color);
  }
  
  /**
   * Sets the <code>Cursor</code> that is shown when hovering the color canvas
   * or the hue canvas.
   * @param cursor the cursor that is shown when picking a color 
   */
  public void setPickCursor(Cursor cursor)
  {
    this.pickCursor = cursor;
  }
  
  /**
   * Returns the <code>Cursor</code> currently set as the one used when hovering
   * the color canvas
   * @return the currently set cursor
   */
  public Cursor getPickCursor()
  {
    return pickCursor;
  }
  
  // TODO doc
  public void setValueFormat(Value value, Format format)
  {
    if (value == Value.RGB_CHANNELS)
    {
      colorValues.setFormat(Value.RED, format);
      colorValues.setFormat(Value.GREEN, format);
      colorValues.setFormat(Value.BLUE, format);
    }
    else if (value == Value.HSB_CHANNELS)
    {
      colorValues.setFormat(Value.HUE, format);
      colorValues.setFormat(Value.SATURATION, format);
      colorValues.setFormat(Value.BRIGHTNESS, format);
    }
    else
      colorValues.setFormat(value, format);
  }
  
  // TODO doc
  public void setValueVisible(Value value, boolean visible)
  {
    if (value == Value.RGB_CHANNELS)
    {
      colorValues.setValuevisible(Value.RED, visible);
      colorValues.setValuevisible(Value.GREEN, visible);
      colorValues.setValuevisible(Value.BLUE, visible);
    }
    else if (value == Value.HSB_CHANNELS)
    {
      colorValues.setValuevisible(Value.HUE, visible);
      colorValues.setValuevisible(Value.SATURATION, visible);
      colorValues.setValuevisible(Value.BRIGHTNESS, visible);
    }
    else
      colorValues.setValuevisible(value, visible);
  }
}
