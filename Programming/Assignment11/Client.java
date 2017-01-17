//David Higgins 
//11428382


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener {

    private JTextField txtFile;
    private JButton buttonUpload;
    private JButton buttonDownload;

    public static void main(String[] args) {
        // create new instance of a client
        Client clientForm = new Client();
        clientForm.Display();
    }

    public void Display() {
        // display the gui
        JFrame frame = new JFrame();
        frame.setTitle("Client");

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        
        txtFile = new JTextField(25);

        JLabel fileName = new JLabel("Choose an option :");

        buttonUpload = new JButton("Upload");
        buttonUpload.addActionListener(this);

        buttonDownload = new JButton("Download");
        buttonDownload.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(fileName);
        panel.add(buttonUpload);
        panel.add(buttonDownload);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //gets info on which button was pressed
        Object src = event.getSource();

        if (src == buttonUpload) {
           // open gui if upload selected 
            JFileChooser nFile = new JFileChooser();
            nFile.showOpenDialog(this);
            String fileName = nFile.getSelectedFile().getAbsolutePath();
            System.out.println(fileName);
            txtFile.setName(fileName);

            while (true) {

                try {
                    // create new socket
                    Socket soc = new Socket("localhost", 9991);
                    OutputStream output = soc.getOutputStream();

                    // send filename
                    OutputStreamWriter outputStream = new OutputStreamWriter(soc.getOutputStream());
                    outputStream.write(nFile.getSelectedFile().getName() + "\n");
                    outputStream.flush();

                    // Get response from server
                    BufferedReader inReader = new BufferedReader(new InputStreamReader(
                            soc.getInputStream()));
                    String serverStatus = inReader.readLine();
                    
                    // when server ready send file
                    if (serverStatus.equals("READY")) {
                        // server is setup and ready to receive file
                        System.out.println("Server ready.");

                        // creating stream tyo send file
                        FileInputStream file = new FileInputStream(fileName);
                        byte[] buffer = new byte[(int) file.getChannel().size()];
                        int bytesRead = (int) file.getChannel().size();

                        while ((bytesRead = file.read(buffer)) > 0) {
                            // writing bytes to output stream
                            output.write(buffer, 0, bytesRead);
                            // bytesRead--;
                        }

                        System.out.println("file upload complete.");

                        output.close();
                        file.close();
                        soc.close();
                        // display message that file transfer has been complete
                        JOptionPane.showMessageDialog(this, "Transfer complete");
                        break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }else if(src == buttonDownload){
            //create new socket
            Socket soc;
            try {
                soc = new Socket("localhost", 9991);
                OutputStream output = soc.getOutputStream();
                
                //sending request to server
                OutputStreamWriter outputStream = new OutputStreamWriter(soc.getOutputStream());
                //sending an empty stream to signal that user wants to download a file
                outputStream.write("\n");
                outputStream.flush();
                
                //get response from server
                BufferedReader inReader = new BufferedReader(new InputStreamReader(
                        soc.getInputStream()));

                String serverStatus = inReader.readLine();
                
                if (serverStatus.equals("READY")) {
       
                    //open input stream
                    InputStream input = soc.getInputStream();
                    BufferedReader downInReader = new BufferedReader(new InputStreamReader(
                            soc.getInputStream()));
                    

                    // read filename
                    String fileName = downInReader.readLine();
                    
                    // create new filename in directory
                    FileOutputStream wr = new FileOutputStream(new File(
                            "C://Users/David/Documents/tmp" + fileName));
                    System.out.println("File directory created.");
                    
                    byte[] buffer = new byte[soc.getReceiveBufferSize()];

                    int bytesReceived = 0;

                    System.out.println("Writing file......");
                    while ((bytesReceived = input.read(buffer)) > 0) {
                        //write bytes to to file output stream 
                        wr.write(buffer, 0, bytesReceived);
                        bytesReceived--;

                    }

                    wr.flush();

                    System.out.println("Complete");
                }
                            
                
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
}
