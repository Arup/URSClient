import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentMenu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton view, update, create, remove, exit;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	public StudentMenu(JFrame parent, String title) {
		super(parent, title, true);
		setSize(320, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new GridLayout(3, 3));
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(300, 300);

		create = new JButton("Create Student");
		remove = new JButton("Remove Student");
		update = new JButton("Update Student");
		view = new JButton("View Student Details");
		exit = new JButton("Exit Student Menu");

		panel1.add(create);
		panel1.add(remove);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel2.add(update);
		panel2.add(view);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel3.add(exit);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		container.add(panel1);
		container.add(panel2);
		container.add(panel3);

		create.addActionListener(this);
		update.addActionListener(this);
		remove.addActionListener(this);
		view.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == create) {
			CreateStudent cs = new CreateStudent((JFrame) this.getParent(),
					"Create Student");
		}
		if (ae.getSource() == view) {
			String option[] = { "Courses", "First_Name", "Last_Name",
					"Address", "City", "State", "Zip", "Enrolled_Units" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to View", "Student",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				String id = JOptionPane.showInputDialog(this,
						"Enter Student ID");
				if (id != null) {
					String req = "Get_" + r + "\n" + RequestID.getNextID()
							+ "\n1\n" + id;
					String res = JMSSender.send(req);
//					JOptionPane.showMessageDialog(this, res, "Result", 1);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == update) {
			String option[] = { "First_Name", "Last_Name", "Address", "City",
					"State", "Zip", "ID" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to Update", "Student",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				String id = JOptionPane.showInputDialog(this,
						"Enter Student ID");
				if (id != null) {
					String newValue = JOptionPane.showInputDialog(this,
							"Enter " + r);
					String req = "Set_" + r + "\n" + RequestID.getNextID()
							+ "\n2\n" + id + "\n" + newValue;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == remove) {
			String req;
			String id = JOptionPane.showInputDialog(this, "Enter Student ID");
			if (id != null) {
				int fe = JOptionPane.showConfirmDialog(this, "Force UnEnroll?",
						"Input", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (fe == JOptionPane.YES_OPTION) {
					req = "Remove_Student\n" + RequestID.getNextID() + "\n2\n"
							+ id + "\n" + "1" + "\n";
				} else {
					req = "Remove_Student\n" + RequestID.getNextID() + "\n2\n"
							+ id + "\n" + "0" + "\n";
				}
				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
			}
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}