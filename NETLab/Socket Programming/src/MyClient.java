import java.io.*;
import java.net.Socket;

public class MyClient {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;

public MyClient(){
   try{
      s = new Socket("Localhost", 10);
      System.out.println(s);
      din = new DataInputStream(s.getInputStream());
      dout = new DataOutputStream(s.getOutputStream());
      clientChat();
   }catch(Exception e){
       throw new RuntimeException(e);
   }
}

    private void clientChat() throws IOException {
        String s1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            s1 = br.readLine();
            dout.writeUTF(s1);
            dout.flush();
            System.out.println("Server Message : " +din.readUTF());
        } while(!s1.equals("stop"));
    }

    public static void main(String args[]){
    new MyClient();
}
}