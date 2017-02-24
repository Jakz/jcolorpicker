package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

class HueCanvas extends JPanel implements ComponentListener
{
  private final ColorPicker chooser;
  
  private Insets insets;
  private BufferedImage image;
  private int[] pixels;
  
  HueCanvas(ColorPicker chooser)
  {
    this.chooser = chooser;
    this.addComponentListener(this);
  }
  
  @Override
  public void paintComponent(Graphics gfx)
  {
    super.paintComponent(gfx);
    
    Graphics2D g = (Graphics2D)gfx;
    g.drawImage(image, insets.left, insets.top, null);
  }
  
  private void cacheCanvas()
  {
    insets = this.getInsets();
        
    final int width = getWidth() - (insets.right + insets.left);
    final int height = getHeight() - (insets.top + insets.bottom);    
    
    /* let's use directly the underlying raster to optimize performance */
    if (image == null || image.getWidth() != width || image.getHeight() != height)
    {
      if (width > 0 && height > 0)
      {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = image.getRaster();
        DataBufferInt buffer = (DataBufferInt)raster.getDataBuffer();
        pixels = buffer.getData();
      }
      else
      {
        image = null;
        pixels = null;
      }
    }
    
    final float fheight = (float)height;
    
    /* for each pixel compute the resulting color according to HSB values */
    for (int y = 0; y < height; ++y)
    {      
      final float hue = 1.0f - (y / fheight);
      for (int x = 0; x < width; ++x)
      {
        final int color = Color.HSBtoRGB(hue, 1.0f, 1.0f);
        pixels[x + y*width] = color;
      }
    }
  }
  

  public void componentResized(ComponentEvent e)
  {
    cacheCanvas();
  }

  public void componentMoved(ComponentEvent e) { }
  public void componentShown(ComponentEvent e) { }
  public void componentHidden(ComponentEvent e) { }
}
