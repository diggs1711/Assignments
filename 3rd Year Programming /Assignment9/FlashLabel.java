import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/* david higgins 
 * 11428382
 */

public class FlashLabel extends Applet implements Runnable {
    
    

    
    public  Thread HelloThread = null;
    public boolean flash;
    public String Message = "";
    public Font font = new Font("Times Roman", Font.BOLD, 50);;
    public int x;
    public int y;

    public void init() {
        flash = true;

        x = size().width;
        y = size().height / 2;
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                flash=!flash;
            }
        });
            
        

    }

    public void start() {
        if (HelloThread == null) {
            HelloThread = new Thread(this);
            HelloThread.start();
        }
    }

    @Override
    public void run() {
        while(true){
            
        if (flash) {
            Message = "Hello World";
            repaint();

            try {
                
                Thread.sleep(100);
                Message="";
                repaint();
                
                Thread.sleep(100);
                Message = "Hello World";
                repaint();
            } catch (InterruptedException e) {

            }
        
        }

        else if(flash == false){
            Message = "Hello World";
            repaint();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

           }
        }
    }

    public void paint(Graphics g) {
        g.setFont(font);
        g.drawString(Message, x, y);
    }


}
