package com.github.jakz.jcolorpicker;

import java.awt.Color;

/**
 * Functional interface used to provide a color to the <code>ColorChooser</code>.
 * This interface is used so that the color chooser can ask for a specific
 * color when opening.
 * 
 * @author Jack
 */

@FunctionalInterface
public interface ColorGetter
{
  Color get();
}
