import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Response {
	public static void displayResponse(String res, JFrame c) {
		System.out.println("Recieved : " + res);
		String params[] = res.split("\n");
		String id = params[0];
		String exception = exception(params[1]);
		String message = "DONE";
		String type = "";
		int mt = JOptionPane.INFORMATION_MESSAGE;
		if (exception.equals("")) {
			type = "Success";
			if (params.length > 2) {
				message = "";
				for (int i = 3; i < params.length; i++) {
					message = message + params[i] + "\n";
				}
			}
		} else {
			mt = JOptionPane.ERROR_MESSAGE;
			type = "EXCEPTION!";
			message = "";
		}
		// JOptionPane.showMessageDialog(c, message, id + " Response :"+
		// exception , mt);
		new ViewResponse(c, " " + type + " for Request ID : " + id, exception + message);
	}

	public static String exception(String num) {
		String e = "";
		int en = Integer.parseInt(num);
		switch (en) {
		case 1:
			e = "create_exception";
			break;
		case 2:
			e = "remove_exception";
			break;
		case 3:
			e = "room_booked";
			break;
		case 4:
			e = "schedule_conflict";
			break;
		case 5:
			e = "malformed_hours";
			break;
		case 6:
			e = "malformed_state";
			break;
		case 7:
			e = "malformed_zip";
			break;
		case 8:
			e = "malformed_units";
			break;
		case 9:
			e = "malformed_location";
			break;
		case 10:
			e = "malformed_id";
			break;
		case 11:
			e = "person_exists";
			break;
		case 12:
			e = "has_courses";
			break;
		case 13:
			e = "student_not_enrolled";
			break;
		case 14:
			e = "student_already_enrolled";
			break;
		case 15:
			e = "too_many_units";
			break;
		case 16:
			e = "course_exists";
			break;
		case 17:
			e = "has_students";
			break;
		case 18:
			e = "no_such_office_hours";
			break;
		case 19:
			e = "no_such_person";
			break;
		case 20:
			e = "malformed_message";
			break;
		case 21:
			e = "no_such_course";
			break;
		default:
			e = "";
		}
		return e;
	}
}

class ViewResponse extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok;
	JTextArea ta;
	JScrollPane res;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	public ViewResponse(JFrame parent, String title, String message) {
		super(parent, title, true);
		setSize(460, 340);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		ta = new JTextArea(message, 10,30);

		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		res = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		res.setVerticalScrollBar(new JScrollBar());
		res.setHorizontalScrollBar(new JScrollBar());
		res.setAutoscrolls(true);
//		res.scrollRectToVisible(new Rectangle(15, 15));

		container.setLayout(new FlowLayout());
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel("Message ", Label.LEFT));
		panel1.add(res);
		panel2.add(ok);

		container.add(panel1);
		container.add(panel2);

		ok.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			dispose();
		}
	}
}