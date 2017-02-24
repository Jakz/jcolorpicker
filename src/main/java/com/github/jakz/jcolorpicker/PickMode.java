package com.github.jakz.jcolorpicker;

public enum PickMode
{
  /**
   * Pick a color without any previous available color.
   * This mode doesn't require a <code>ColorGetter</code>. The picker just
   * prompts the user for a color to choose, without using a previously
   * available color. 
   */
  JUST_PICK,
  
  /**
   * Pick a color while providing a previous color to update.
   * This mode requires a <code>ColorSetter</code>. The picker starts from the
   * color provided by the setter and it's meant to be used when you want to
   * let the user change a color into something else, the previous color is
   * indeed shown into the picker.
   */
  PICK_ANOTHER
}
