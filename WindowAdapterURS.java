import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class WindowAdapterURS extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
		we.getWindow().dispose();
	}
}
