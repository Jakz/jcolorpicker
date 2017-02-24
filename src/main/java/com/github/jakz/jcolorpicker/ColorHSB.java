package com.github.jakz.jcolorpicker;

import java.awt.Color;

public class ColorHSB
{
  float h;
  float s;
  float b;
  
  public ColorHSB(float h, float s, float b)
  {
    this.h = h;
    this.s = s;
    this.b = b;
  }
  
  ColorHSB(int rgb)
  {
    Color c = new Color(rgb);
    float hsb[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
    this.h = hsb[0];
    this.s = hsb[1];
    this.b = hsb[2];
  }
  
  ColorHSB(Color rgb)
  {
    float hsb[] = Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
    this.h = hsb[0];
    this.s = hsb[1];
    this.b = hsb[2];
  }
  
  public float hue() { return h; }
  public float saturation() { return s; }
  public float brightness() { return b; }
  
  int toIntRGB() { return Color.HSBtoRGB(h, s, b); }
  Color toRGB() { return new Color(Color.HSBtoRGB(h, s, b)); }
  
  @Override
  public boolean equals(Object object)
  {
    if (object instanceof ColorHSB)
    {
      ColorHSB color = (ColorHSB)object;
      return color.h == h && color.s == s && color.b == b;
    }
    else
      return false;
  }
}
