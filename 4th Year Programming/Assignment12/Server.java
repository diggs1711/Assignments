//David Higgins
//11428382

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame implements Runnable {

    private DatagramPacket sendPacket, receivePacket;
    private DatagramSocket socket;

    Socket nSocket;
    private JTextField txtFile;
    private JTextArea displayArea;
    private StringBuilder sb;
    private byte[] outBuf;

    public Server() {

        super("Server");
        // init socket

        displayArea = new JTextArea();
        getContentPane().add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400, 300);
        setVisible(true);

    }

    public void waitForPackets() {
        while (true) {
            try {
                byte data[] = new byte[256];
                receivePacket = new DatagramPacket(data, data.length);

                socket.receive(receivePacket);

                displayPacket();

                sendPacketToClient();
            } catch (IOException ex) {
                displayArea.append(ex.toString() + "\n");
                ex.printStackTrace();
            }
        }
    }

    public static void echo(String msg) {
        //System.out.println(msg);
    }

    private void displayPacket() {

        displayArea.append("\nPacket Received: " + "\nFrom Host: " + receivePacket.getAddress() +
                "\nHost port: " + receivePacket.getPort() +
                "\nLength: " + receivePacket.getLength() +
                "\nContaining : \n\t" +
                new String(receivePacket.getData(), 0, receivePacket.getLength()));
    }

    private void sendPacketToClient() throws IOException {

        displayArea.append("\nEcho data to client...");

        sendPacket = new DatagramPacket(receivePacket.getData(),
                receivePacket.getLength(), receivePacket.getAddress(),
                receivePacket.getPort());

        socket.send(sendPacket);

        displayArea.append("Packet sent\n");
        displayArea.setCaretPosition(displayArea.getText().length());
    }

    @Override
    public void run() {

        txtFile = new JTextField();

        try {
            socket = new DatagramSocket(10000);

                byte[] buffer = new byte[65535];
                receivePacket = new DatagramPacket(buffer, buffer.length);

                while (true) {

                    socket.receive(receivePacket);
                    byte[] data = receivePacket.getData();
                    //String s = new String(data, 0, receivePacket.getLength());
                    
                    String s = "Ready";

                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length,
                            receivePacket.getAddress(), receivePacket.getPort());
                    socket.send(dp);
                    
                    //open gui to allow user to select file to download
                    JFileChooser nFile = new JFileChooser();
                    nFile.showOpenDialog(this);
                    File ff = new File(nFile.getSelectedFile().getAbsolutePath());
                    FileReader fr = new FileReader(ff);
                    BufferedReader brf = new BufferedReader(fr);
                    
                    sb = new StringBuilder();
                    
                    while((s= brf.readLine()) != null){
                        
                        sb.append(s);
                        
                    }
                    
                    if(brf.readLine() == null){
                        JOptionPane.showMessageDialog(this, "File read successful.");
                    }
                    
                    outBuf = new byte[100000];
                    outBuf = (sb.toString()).getBytes();
                    
                    sendPacket = new DatagramPacket(outBuf ,0, outBuf.length,
                            receivePacket.getAddress() ,receivePacket.getPort() );
                    
                    socket.send(sendPacket);
                    
                    

                }

               
    }catch(Exception e){
        e.printStackTrace();}
 }
    
    public static void main(String[] args ){
        Server s = new Server();
        new Thread(s).start();
    }
}
