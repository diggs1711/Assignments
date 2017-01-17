//David Higgins
//11428382

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Client {

    private static String serverStatus;
    private static byte[] data;
    private JTextField txtFile;
    private JButton buttonUpload;
    private JButton buttonDownload;
    private DatagramPacket sendPacket, receivePacket;

    public static void main(String[] args) throws IOException {
        
        DatagramSocket socket = new DatagramSocket();
        
        InetAddress address = InetAddress.getLocalHost();

        //sending packet
        String msg = "";
        data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, address, 10000);
        socket.send(packet);
                 
        byte[] buf = new byte[65535];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(received);
        
        serverStatus = received;
        
        if(serverStatus.equals("Ready")){
            System.out.println("Server Ready");
            
            JFileChooser nFile = new JFileChooser();
            nFile.showOpenDialog(null);
            File ff = new File(nFile.getSelectedFile().getAbsolutePath());
            FileReader fr = new FileReader(ff);
            BufferedReader brf = new BufferedReader(fr);
        }

        socket.close();

    }
}
