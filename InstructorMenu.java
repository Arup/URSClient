import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InstructorMenu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton view, update, create, remove, add, exitInsMenu;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	public InstructorMenu(JFrame parent, String title) {
		super(parent, title, true);
		setSize(500, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		container.setLayout(new GridLayout(3, 3));
		// panel.setLayout(new FlowLayout());
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocation(300, 300);

		create = new JButton("Create Instructor");
		remove = new JButton("Remove Office Hours");
		update = new JButton("Update Instructor");
		view = new JButton("View Instructor");
		add = new JButton("Add Office Hours");
		exitInsMenu = new JButton("Exit Instructor Menu");

		panel1.add(create);
		panel1.add(remove);
		panel1.add(add);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel2.add(update);
		panel2.add(view);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel3.add(exitInsMenu);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		container.add(panel1);
		container.add(panel2);
		container.add(panel3);

		add.addActionListener(this);
		create.addActionListener(this);
		update.addActionListener(this);
		remove.addActionListener(this);
		view.addActionListener(this);
		exitInsMenu.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == create) {
			CreateInstructor cs = new CreateInstructor((JFrame) this
					.getParent(), "Create Instructor");
		}
		if (ae.getSource() == view) {
			String option[] = { "Department", "First_Name", "Last_Name",
					"Address", "City", "State", "Zip", "Office_Hours",
					"Courses" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to View", "Instructor",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				String id = JOptionPane.showInputDialog(this,
						"Enter Instructor ID");
				if (id != null) {
					String req = "Get_" + r + "\n" + RequestID.getNextID()
							+ "\n1\n" + id;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == update) {
			String option[] = { "Courses", "Department", "First_Name",
					"Last_Name", "Address", "City", "State", "Zip", "ID" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to Update", "Instructor",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				String id = JOptionPane.showInputDialog(this,
						"Enter Instructor ID");
				if (id != null) {
					String newValue = JOptionPane.showInputDialog(this,
							"Enter " + r);
					String req = "Set_" + r + "\n" + RequestID.getNextID()
							+ "\n2\n" + newValue + "\n" + id;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == add) {
			String req;
			String id = JOptionPane
					.showInputDialog(this, "Enter Instructor ID");
			if (id != null) {
				String location = JOptionPane.showInputDialog(this,
						"Enter Location");
				if (location != null) {
					req = "Add_Office_Hours\n" + RequestID.getNextID()
							+ "\n2\n" + id + "\n" + location;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == remove) {
			String req;
			String id = JOptionPane
					.showInputDialog(this, "Enter Instructor ID");
			if (id != null) {
				String location = JOptionPane.showInputDialog(this,
						"Enter Location");
				if (location != null) {
					req = "Remove_Office_Hours\n" + RequestID.getNextID()
							+ "\n2\n" + id + "\n" + location;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == exitInsMenu) {
			dispose();
		}
	}
}