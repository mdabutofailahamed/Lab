import java.net.*;
import java.io.*;
import java.time.LocalTime;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  

public class Server{
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server is running...");
            Socket s = ss.accept();
            System.out.println("Client connected.");
            while(true){
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String message_from_client = dis.readUTF();
                System.out.println("Client Message: "+message_from_client);
                
                if(message_from_client.equals("hi")){
                    System.out.println("Server Message: Hello");
                }
                else if(message_from_client.equals("date")){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
                    LocalDateTime now = LocalDateTime.now();  
                    System.out.println("Server Date:"+dtf.format(now));
                }
                else if(message_from_client.equals("time")){
                    System.out.println("Server Time: " + LocalTime.now());
                }

                else if (message_from_client.equals("ip")) {
                    // Get the machine's IP address
                    String ipAddress = InetAddress.getLocalHost().getHostAddress();
                    System.out.println("Server IP: " + ipAddress);
                }

                else if(message_from_client.equals("exit")){
                    dis.close();
                    s.close();
                    ss.close();
                    System.out.println("Server shut down.");
                    break;
                }

                else {
                    System.out.println("Server : Invalid Message from Client");
                }


            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}