package com.github.jakz.jcolorpicker;

import java.awt.Color;

/**
 * This interface provides the callback that will be invoked by the color
 * picker when <b>Ok</b> or <b>Cancel</b> button are pressed. If <b>Ok</b> is pressed then
 * the <code>Color</code> is passed as the argument, otherwise <code>null</code>.
 */
@FunctionalInterface
public interface ColorSetter
{
  /**
   * Callback method invoked when button in color picker is pressed.
   * @param color the chosen color from the picker or <code>null</code> if
   * cancel button has been chosen
   */
  void set(Color color);
}
