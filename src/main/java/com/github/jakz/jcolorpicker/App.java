package com.github.jakz.jcolorpicker;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Hello world!
 *
 */
public class App 
{
  static class TestFrame extends JFrame
  {
    private final ColorPicker chooser;
    
    TestFrame()
    {
      chooser = new ColorPicker();
      chooser.setValueFormat(Value.RGB_CHANNELS, Format.DECIMAL);
      chooser.setColor(new Color(45,231,90));
      
      chooser.setColorSetter(c -> {
       this.setVisible(false);
       if (c != null)
         System.out.println(c);
      });
      
      add(chooser);
      
      setTitle("Color Chooser");
      
      
      pack();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  }
  
  public static void setNimbusLNF()
  {
    try {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            
            UIManager.setLookAndFeel(info.getClassName());
            break;
          }
      }
    } catch (Exception e) {
      e.printStackTrace();
        // If Nimbus is not available, you can set the GUI to another look and feel.
    }
  }
  
  public static void main( String[] args )
  {
    //setNimbusLNF();
    new TestFrame();
  }
}
