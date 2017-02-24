package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

class HueCanvas extends JPanel implements ComponentListener, MouseListener,
  MouseMotionListener
{
  private final ColorPicker chooser;
  
  private Insets insets;
  private BufferedImage image;
  private int[] pixels;
  private int width, height;
  private final int margin = 2;
  
  float hue = 0.0f;
  
  HueCanvas(ColorPicker chooser)
  {
    this.chooser = chooser;
    addComponentListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
  }
  
  @Override
  public void paintComponent(Graphics gfx)
  {
    gfx.drawImage(image, insets.left + margin, insets.top, null);
    gfx.setColor(Color.BLACK);
    
    int y = (int)(hue*height);
    gfx.drawRect(insets.left, y/*-2-insets.top*/, getWidth()-1, 5);
  }
  
  private void cacheCanvas()
  {
    insets = this.getInsets();
        
    width = getWidth() - (insets.right + insets.left) - margin*2;
    height = getHeight() - (insets.top + insets.bottom);    
    
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
      final float hue = (y / fheight);
      for (int x = 0; x < width; ++x)
      {
        final int color = Color.HSBtoRGB(hue, 1.0f, 1.0f);
        pixels[x + y*width] = color;
      }
    }
  }
  
  void setHue(float hue)
  {
    this.hue = hue;
    repaint();
  }
  
  public void hueChanged(int x, int y)
  {
    float hue = y / (float) height;
    
    hue = Math.max(0.0f, Math.min(1.0f, hue));
    
    this.hue = hue;
    chooser.pickHue(hue);
    repaint();
  }

  public void componentResized(ComponentEvent e)
  {
    cacheCanvas();
  }

  public void componentMoved(ComponentEvent e) { }
  public void componentShown(ComponentEvent e) { }
  public void componentHidden(ComponentEvent e) { }

  public void mouseDragged(MouseEvent e)
  {
    hueChanged(e.getX(), e.getY()); 
  }

  public void mouseMoved(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  public void mouseClicked(MouseEvent e)
  {
    hueChanged(e.getX(), e.getY());   
  }

  public void mousePressed(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }
}
