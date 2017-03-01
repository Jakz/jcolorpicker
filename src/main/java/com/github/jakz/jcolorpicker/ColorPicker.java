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
  private final Buttons buttons;
  
  private ColorSetter setter;
  private ColorGetter getter;
  
  private ColorHSB color;
  
  private Cursor pickCursor;
  private boolean isAntialiasingEnabled;
  private boolean autoSelectColorValue;
  
  public ColorPicker()
  {
    colorCanvas = new ColorCanvas(this);
    hueCanvas = new HueCanvas(this);
    colorCell = new ColorCell(this);
    colorValues = new ColorValues(this);
    buttons = new Buttons(this);
    
    pickCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    isAntialiasingEnabled = true;
    
    setPreferredSize(new Dimension(500,400));
    
    GridBagConstraints c = new GridBagConstraints();
    
    setLayout(new GridBagLayout());
    
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(5,5,5,10);
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 4;
    c.gridheight = 4;
    c.weightx = 0.8;
    c.weighty = 1.0;
    
    add(colorCanvas, c);
    
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(5,0,5,5);
    c.gridx = 4;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 4;
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
    c.weighty = 0.6;
    
    add(colorValues, c);
    
    c.gridx = 5;
    c.anchor = GridBagConstraints.SOUTH;
    c.gridy = 3;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 0.1;
    c.weighty = 0.2;
    
    add(buttons, c);

  }
  
  void pickHue(float hue)
  {
    colorCanvas.huePicked(hue);
  }
  
  void pickColor(ColorHSB color)
  {
    this.color = color;
    colorCell.setColor(color);
    colorValues.setColor(color);
  }
  
  void cancel()
  {
    if (setter == null)
      throw new IllegalStateException("ColorPicker setter must be non-null");
    setter.set(null);
  }
  
  void ok()
  {
    if (setter == null)
      throw new IllegalStateException("ColorPicker setter must be non-null");
    setter.set(color.toRGB());
  }
  
  // TODO: doc
  public void setColorSetter(ColorSetter setter)
  {
    this.setter = setter;
  }
  
  // TODO: doc
  public void setColorGetter(ColorGetter getter)
  {
    this.getter = getter;
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
  
  /**
   * Set if antialiasing should be enabled for the color picker.
   * Default value is <code>true</code>
   * @param enabled true if antialiasing should be enabled
   */
  public void setAntialiasingEnabled(boolean enabled)
  {
    if (isAntialiasingEnabled != enabled)
    {
      isAntialiasingEnabled = enabled;
      repaint();
    }
  }
  
  /**
   * Returns <code>true</code> if antialiasing is enabled for the color picker.
   * @return antialiasing enabled value
   */
  public boolean isAntialiasingEnabled()
  {
    return isAntialiasingEnabled;
  }
  
  // TODO: doc
  public void setAutoSelectColorValue(boolean enabled)
  {
    autoSelectColorValue = enabled;
  }
  
  public boolean isAutoSelectColorValueEnabled()
  {
    return autoSelectColorValue;
  }
  
  // TODO doc
  public void setColor(Color color)
  {
    ColorHSB hsbColor = new ColorHSB(color);
    colorCanvas.setColor(color);
    hueCanvas.setHue(hsbColor.h);
    pickColor(hsbColor);
  }
  
  public Color getColor()
  {
    return color.toRGB();
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
      colorValues.setValueVisible(Value.RED, visible);
      colorValues.setValueVisible(Value.GREEN, visible);
      colorValues.setValueVisible(Value.BLUE, visible);
    }
    else if (value == Value.HSB_CHANNELS)
    {
      colorValues.setValueVisible(Value.HUE, visible);
      colorValues.setValueVisible(Value.SATURATION, visible);
      colorValues.setValueVisible(Value.BRIGHTNESS, visible);
    }
    else
      colorValues.setValueVisible(value, visible);
  }
}
