import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//Server stub super class which directly communicate with clients through socket, parsing all the incoming commands
abstract class SocketSession implements Runnable{
	private Socket socket;
	
	//setSocket() method to set the Socket variable
	public final void setSocket(Socket sk){
		socket = sk;
	}
	
	//template method
	public final void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			String request, response;
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				request = new String(buffer, 0, len);
				System.out.println(">" + request);
				response = handleRequest(request);
				System.out.println("<" + response);
				out.write((response).getBytes());
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//abstract method to be implemented by concrete classes
	abstract protected String handleRequest(String request);
}