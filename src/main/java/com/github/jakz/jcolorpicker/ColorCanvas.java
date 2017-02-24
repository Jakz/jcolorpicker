package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

class ColorCanvas extends JPanel implements ComponentListener, MouseListener,
  MouseMotionListener
{
  private final ColorPicker chooser;
  
  private Insets insets;
  private BufferedImage image;
  private int[] pixels;  
  private int width, height;
  
  private float hue = 0.2f;
  
  ColorCanvas(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    addComponentListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
  }
  
  @Override
  public void paintComponent(Graphics gfx)
  {
    super.paintComponent(gfx);
    
    Graphics2D g = (Graphics2D)gfx;
    
    if (image != null)
    g.drawImage(image, insets.left, insets.top, null);
  }
  
  private void cacheCanvas()
  {
    insets = this.getInsets();
        
    width = getWidth() - (insets.right + insets.left);
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
    
    final float fwidth = (float)width;
    final float fheight = (float)height;
    
    /* for each pixel compute the resulting color according to HSB values */
    for (int x = 0; x < width; ++x)
    {
      final float saturation = x / (fwidth-1); 
      
      for (int y = 0; y < height; ++y)
      {
        final float brightness = 1.0f - (y / (fheight-1));
        final int color = Color.HSBtoRGB(hue, saturation, brightness);
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
  
  public void colorPicked(int x, int y)
  {
    Color color = null;
    
    if (x >= 0 && y >= 0 && x < width && y < height)
      color = new Color(pixels[x + y*width]);
    else if (x >= width && y >= height)
      color = Color.BLACK;
    else if (x < 0 && y < 0)
      color = Color.WHITE;
    
    if (color != null)
      chooser.pickColor(color);
  }

  public void mouseClicked(MouseEvent e)
  {
    colorPicked(e.getX(), e.getY());
  }

  public void mousePressed(MouseEvent e)
  {
    
  }

  public void mouseReleased(MouseEvent e)
  {
    
  }

  public void mouseEntered(MouseEvent e)
  {
    setCursor(chooser.getPickCursor());
  }

  public void mouseExited(MouseEvent e)
  {
    setCursor(Cursor.getDefaultCursor());
  }

  public void mouseDragged(MouseEvent e)
  {
    colorPicked(e.getX(), e.getY());
  }

  public void mouseMoved(MouseEvent e)
  {
    
  }
}
