
public class RequestID {
	static int reqID = 0;
	
	static int getNextID(){
		reqID++;
		return reqID;
	}
}
