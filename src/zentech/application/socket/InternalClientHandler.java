package zentech.application.socket;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class InternalClientHandler implements Runnable{
	
    Socket socket;
    ObjectInputStream reader;
    ObjectOutputStream writer;
    int position=0;
    public InternalClientHandler(Socket socket){
        try
        {
            this.socket=socket;
            this.reader=new ObjectInputStream(socket.getInputStream());
            this.writer=new ObjectOutputStream(socket.getOutputStream());
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while(true){	
                InternalChatUser user=(InternalChatUser)reader.readObject();
                for(InternalClientHandler s:InternalChatServer.clientlist)
                {	
                    System.out.println(user.getFromUserId()+":"+user.getMessage());
                    s.writer.writeObject(user);
                    s.writer.reset();
                }
            }
        } catch(EOFException exp) {
            InternalChatServer.clientlist.remove(this.position);
            int i=0;
            for(InternalClientHandler s:InternalChatServer.clientlist)
            {
                s.position=i;
                i++;
            }
        } catch(SocketException exp) {
            //if user is disconnected then close input stream and output stream
            InternalChatServer.clientlist.remove(this.position);
            int i=0;
            for(InternalClientHandler s:InternalChatServer.clientlist)
            {
                s.position=i;
                i++;
            }
        } catch(Exception exp) {
                exp.printStackTrace();
        }
    }
}


