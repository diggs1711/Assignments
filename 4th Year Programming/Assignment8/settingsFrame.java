import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class settingsFrame extends JFrame{
 
        private JFrame f = new JFrame("Second");
        private JLabel heatIntensity;
        private JLabel butState;
        
        public settingsFrame(int heatIntLevel, boolean buttonState, boolean opbuttonState) {
        
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            Container container = getContentPane();
            container.setLayout( new FlowLayout() );
            
            heatIntensity = new JLabel("\nHeat Intensity : " + (heatIntLevel+1));
            container.add(heatIntensity);
            
            
            
            if(buttonState){//if button state is true, the device is on
            butState = new JLabel("\nThe device is on ");
            container.add(butState);
            }else{// if button state is false, the device is off
                butState = new JLabel("\nThe device is off ");
                container.add(butState);
            }
            
            if(opbuttonState){//if button state is true, the device is timed mode 
                butState = new JLabel("\nDevice is in timed mode ");
                container.add(butState);
                }else{// if button state is false, the device is in manual mode
                    butState = new JLabel("\nDevice is in manual mode  ");
                    container.add(butState);
                }
                
            
            setSize(200,200);
            setVisible(true);
        }
    }
;

