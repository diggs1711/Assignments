import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class heatControl extends JFrame{
    //button to turn device on or off
    private JButton onOffButton;
    boolean buttonState = false;
    
    //button to put device in timed or manual mode
    boolean OpbuttonState = false;
    private JButton opStateButton;
  
    //change heat intensity
    private JComboBox heatIntensity;
    private JLabel label;
    
    int heatIntLevel;
    
    private JButton dispStatus;
    
    
    //import button images
    Icon onButt = new ImageIcon("src/onButton.gif");
    Icon offButton = new ImageIcon("src/offButton.jpg");
    
    //different intensity levels
    private String levels[]={"1","2","3","4","5"};
    
    
    
    public heatControl(){
        super("Test");
        //setup window
        Container container = getContentPane();
        container.setLayout (new FlowLayout() );
        
        //adding on/off button to window
        onOffButton = new JButton();
        onOffButton.setIcon(offButton);
        container.add(onOffButton);
        
        //adding operation of device button
        opStateButton = new JButton();
        opStateButton.setText("Manual");
        container.add(opStateButton);
        
        
        OpButtonHandler opHandler = new OpButtonHandler();
        //adding a listener to check if user has changed the mode of operation
        opStateButton.addActionListener(opHandler);

        
        
        
        heatIntensity = new JComboBox(levels);
        
        //adding comboBox for heat intensity
        heatIntensity.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent event){
                if(event.getStateChange() == event.SELECTED){
                    //getting the intensity level selected by the user
                    heatIntLevel=heatIntensity.getSelectedIndex();
                }
            }
            
        });
        
        //adding combox to container
        container.add(heatIntensity);
        
        ButtonHandler onOffbutHandler = new ButtonHandler();
        //adding listener to on/off
        onOffButton.addActionListener(onOffbutHandler);
        
        //adding button which allows to check the current settings 
        dispStatus = new JButton("Check Settings");
        container.add(dispStatus);
        
        SettingsButtonHandler dispStatusHandler = new SettingsButtonHandler();
        //adding button that allows user to check the current status 
        dispStatus.addActionListener(dispStatusHandler);
        
        setSize(400,500);
        setVisible(true);
        
        
        
        
    }
    
    public static void main(String[] args){
        
        heatControl app = new heatControl();
        
        app.setDefaultCloseOperation(
                JFrame.
                EXIT_ON_CLOSE
                );
        
    }
    
    
    private class ButtonHandler implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            if(buttonState == false){//if button is pressed and button is off
                //change button to on and set the image to on image
                buttonState=true;
                onOffButton.setIcon(onButt);
            }
            else{
                //if button is on and button is pressed,set buttonstate to off and image to off
                buttonState=false;
                onOffButton.setIcon(offButton);
            }
            
        }

    }
    
 private class OpButtonHandler implements ActionListener{
        
    

        public void actionPerformed(ActionEvent event){
            if(OpbuttonState == false){
                OpbuttonState=true;
                opStateButton.setText("Timed");
            }
            else{
                OpbuttonState=false;
                opStateButton.setText("Manual");
            }
            
        }

    }
 
 private class SettingsButtonHandler implements ActionListener{
     
     public void actionPerformed(ActionEvent event){
         new settingsFrame( heatIntLevel,buttonState,OpbuttonState);
         
     }

 }
    
    
}

