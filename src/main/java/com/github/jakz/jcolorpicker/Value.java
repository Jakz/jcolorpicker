package com.github.jakz.jcolorpicker;

public enum Value
{
  RED(Type.RGB),
  GREEN(Type.RGB),
  BLUE(Type.RGB),
  HUE(Type.HSB),
  SATURATION(Type.HSB),
  BRIGHTNESS(Type.HSB),
  RGB_CHANNELS(Type.RGB),
  HSB_CHANNELS(Type.HSB),
  HEX(Type.HEX)
  ;
  
  private static enum Type
  {
    RGB,
    HSB,
    HEX
  };
  
  private final Type type;
  
  Value(Type type)
  {
    this.type = type;
  }
  
  boolean isRGB() { return type == Type.RGB; }
  boolean isHSB() { return type == Type.HSB; }
  boolean isHex() { return type == Type.HEX; }
}
