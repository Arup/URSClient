import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CreateCourse extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok, exit;
	JTextField id, name, sec, units, building, room, dow, start, end;
	JPanel panel1 = new JPanel();

	public CreateCourse(JFrame parent, String title) {
		super(parent, title, true);
		setSize(450, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// container.setLayout(new GridLayout(3, 3));
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		exit = new JButton("Exit");
		id = new JTextField();
		name = new JTextField();
		sec = new JTextField();
		units = new JTextField();
		building = new JTextField();
		room = new JTextField();
		dow = new JTextField();
		start = new JTextField();
		end = new JTextField();

		panel1.setLayout(new GridLayout(10, 2));
		panel1.add(new JLabel("Instructor ID", Label.LEFT));
		panel1.add(id);
		panel1.add(new JLabel("Name", Label.LEFT));
		panel1.add(name);
		panel1.add(new JLabel("Section", Label.LEFT));
		panel1.add(sec);
		panel1.add(new JLabel("Units", Label.LEFT));
		panel1.add(units);
		panel1.add(new JLabel("Building", Label.LEFT));
		panel1.add(building);
		panel1.add(new JLabel("Room", Label.LEFT));
		panel1.add(room);
		panel1.add(new JLabel("Days Of Week", Label.LEFT));
		panel1.add(dow);
		panel1.add(new JLabel("Start Hours", Label.LEFT));
		panel1.add(start);
		panel1.add(new JLabel("End Hours", Label.LEFT));
		panel1.add(end);

		panel1.add(ok);
		panel1.add(exit);

		container.add(panel1);

		ok.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			String loc = building.getText() + " " + room.getText() + " "
					+ dow.getText() + " " + start.getText() + "-"
					+ end.getText();
			String req = "Create_Course\n" + RequestID.getNextID() + "\n5\n"
					+ name.getText() + "\n" + sec.getText() + "\n"
					+ id.getText() + "\n" + loc + "\n" + units.getText();
			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
			dispose();
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}

class RemoveCourse extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok, exit;
	JTextField name, sec;
	JPanel panel1 = new JPanel();

	public RemoveCourse(JFrame parent, String title) {
		super(parent, title, true);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// container.setLayout(new GridLayout(3, 3));
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		exit = new JButton("Exit");
		name = new JTextField();
		sec = new JTextField();

		panel1.setLayout(new GridLayout(3, 2));
		panel1.add(new JLabel("Name", Label.LEFT));
		panel1.add(name);
		panel1.add(new JLabel("Section", Label.LEFT));
		panel1.add(sec);

		panel1.add(ok);
		panel1.add(exit);

		container.add(panel1);

		ok.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			String req = "";
			int fe = JOptionPane.showConfirmDialog(this, "Force UnEnroll?",
					"Input", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (fe == JOptionPane.YES_OPTION) {
				req = "Remove_Course\n" + RequestID.getNextID() + "\n3\n"
						+ name.getText() + "\n" + sec.getText() + "\n" + 1;
			} else {
				req = "Remove_Course\n" + RequestID.getNextID() + "\n3\n"
						+ name.getText() + "\n" + sec.getText() + "\n" + 0;
			}
			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
			dispose();
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}

class UpdateCourse extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok, exit;
	JTextField name, sec, op, building, room, dow, start, end;
	JPanel panel1 = new JPanel();
	String s = "";

	public UpdateCourse(JFrame parent, String title, String option) {
		super(parent, title, true);
		this.s = option;
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		exit = new JButton("Exit");
		name = new JTextField();
		sec = new JTextField();
		op = new JTextField();
		building = new JTextField();
		room = new JTextField();
		dow = new JTextField();
		start = new JTextField();
		end = new JTextField();

		panel1.setLayout(new GridLayout(8, 2));
		panel1.add(new JLabel("Name", Label.LEFT));
		panel1.add(name);
		panel1.add(new JLabel("Section", Label.LEFT));
		panel1.add(sec);
		if (option.equalsIgnoreCase("Location")) {
			panel1.add(new JLabel("Building", Label.LEFT));
			panel1.add(building);
			panel1.add(new JLabel("Room", Label.LEFT));
			panel1.add(room);
			panel1.add(new JLabel("Days Of Week", Label.LEFT));
			panel1.add(dow);
			panel1.add(new JLabel("Start Hours", Label.LEFT));
			panel1.add(start);
			panel1.add(new JLabel("End Hours", Label.LEFT));
			panel1.add(end);
		} else {
			panel1.add(new JLabel(option, Label.LEFT));
			panel1.add(op);
		}
		panel1.add(ok);
		panel1.add(exit);

		container.add(panel1);

		ok.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			String req = "Set_" + s + "\n" + RequestID.getNextID() + "\n3\n";
			if (s.equalsIgnoreCase("Location")) {
				String loc = building.getText() + " " + room.getText() + " "
						+ dow.getText() + " " + start.getText() + "-"
						+ end.getText();
				req = req + loc + "\n" + name.getText() + "\n" + sec.getText();
			} else if (s.equalsIgnoreCase("Course_Instructor")) {
				req = req + op.getText() + "\n" + name.getText() + "\n"
						+ sec.getText();
			} else {
				req = req + name.getText() + "\n" + sec.getText() + "\n"
						+ op.getText();
			}
			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
			dispose();
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}

class ViewCourse extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok, exit;
	JTextField name, sec;
	JPanel panel1 = new JPanel();
	String s = "";

	public ViewCourse(JFrame parent, String title, String option) {
		super(parent, title, true);
		this.s = option;
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		exit = new JButton("Exit");
		name = new JTextField();
		sec = new JTextField();

		panel1.setLayout(new GridLayout(3, 2));
		panel1.add(new JLabel("Name", Label.LEFT));
		panel1.add(name);
		panel1.add(new JLabel("Section", Label.LEFT));
		panel1.add(sec);

		panel1.add(ok);
		panel1.add(exit);

		container.add(panel1);

		ok.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			String req = "Get_" + s + "\n" + RequestID.getNextID() + "\n2\n"
					+ name.getText() + "\n" + sec.getText();

			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
			dispose();
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}