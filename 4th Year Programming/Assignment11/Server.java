//David Higgins 
//11428382

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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Server extends JFrame implements Runnable {

    Socket nSocket;
    private JTextField txtFile;

    public Server(Socket nSocket) {
        //init socket
        this.nSocket = nSocket;
    }

    public static void main(String[] args) throws IOException {

        System.out.print("Server running...");
        
        //creating new socket
        ServerSocket socket = new ServerSocket(9991);

        while (true) {
            Socket soc = socket.accept();

            System.out.print("Connection accepted\n");
            
            //start new server thread when connected
            new Thread(new Server(soc)).start();

        }
    }

    @Override
    public void run() {

        txtFile = new JTextField();
        
        while (true) {
            try {
                //creating input and output streams
                InputStream input = this.nSocket.getInputStream();
                BufferedReader inReader = new BufferedReader(new InputStreamReader(
                        this.nSocket.getInputStream()));
                BufferedWriter outReader = new BufferedWriter(new OutputStreamWriter(
                        this.nSocket.getOutputStream()));

                // read filename
                String fileName = inReader.readLine();
                
                if (!fileName.equals("")) {
                   //User has selected upload, as the filename is not equal to null
                    
                    outReader.write("READY\n");
                    outReader.flush();

                    // create new filename in directory
                    FileOutputStream wr = new FileOutputStream(new File(
                            "C://Users/David/Documents/tmp" + fileName));

                    byte[] buffer = new byte[this.nSocket.getReceiveBufferSize()];

                    int bytesReceived = 0;

                    System.out.println("Writing file......");
                    while ((bytesReceived = input.read(buffer)) > 0) {
                        //write the bytes received to the file directory
                        wr.write(buffer, 0, bytesReceived);
                        bytesReceived--;

                    }

                    wr.flush();

                    System.out.println("Complete");
                }else{
                    //if filename is empty, user has selected to download a file
                    System.out.println("download");
                    
                    outReader.write("READY\n");
                    outReader.flush();
                    
                    OutputStream output = this.nSocket.getOutputStream();
                    
                    //open gui to allow user to select file to download
                    JFileChooser nFile = new JFileChooser();
                    nFile.showOpenDialog(this);
                    String downloadFileName = nFile.getSelectedFile().getAbsolutePath();
                    System.out.println(downloadFileName);
                    txtFile.setName(downloadFileName);
                    
                    //sending file name to client
                    OutputStreamWriter outputStream = new OutputStreamWriter(this.nSocket.getOutputStream());
                    outputStream.write(nFile.getSelectedFile().getName() + "\n");
                    outputStream.flush();
                    
                    // creating stream to send file
                    FileInputStream file = new FileInputStream(downloadFileName);
                    byte[] buffer = new byte[(int) file.getChannel().size()];
                    int bytesRead = (int) file.getChannel().size();

                    System.out.println("Writing File...");
                    while ((bytesRead = file.read(buffer)) > 0) {
                        // writing bytes to output stream
                        output.write(buffer, 0, bytesRead);
                        // bytesRead--;
                    }
                    
                    System.out.println("Complete.");
                    
                    output.close();
                    file.close();
                    this.nSocket.close();
                    // display message that file has been complete
                    JOptionPane.showMessageDialog(this, "Download complete");
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;

        }
    }
}
