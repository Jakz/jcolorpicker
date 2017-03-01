package com.github.jakz.jcolorpicker;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

class Buttons extends JPanel implements ActionListener
{
  private final ColorPicker chooser;
  
  private final JButton ok;
  private final JButton cancel;
  
  Buttons(ColorPicker chooser)
  {
    this.chooser = chooser;
    
    ok = new JButton("Ok");
    cancel = new JButton("Cancel");
    
    ok.addActionListener(this);
    cancel.addActionListener(this);

    setLayout(new GridLayout(2,1));
    add(ok);
    add(cancel);
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == ok)
      chooser.ok();
    else if (e.getSource() == cancel)
      chooser.cancel();
  }
}
