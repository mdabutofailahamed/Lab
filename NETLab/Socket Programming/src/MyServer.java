import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    public MyServer(){
        try{
         System.out.println("Server started");
         ss = new ServerSocket(10);
         s = ss.accept();
        System.out.println("Client Connected");
        dis =new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        serverChat();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void serverChat() throws IOException {
        String str,s1 = null;
        do{
          str = dis.readUTF();
          System.out.println("Client Message : " +str);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s1 = br.readLine();
            dos.writeUTF(s1);
            dos.flush();
        }
        while((!s1.equals("Bye")));
    }

    public static void main(String args[]){
         new MyServer();
    }
}
