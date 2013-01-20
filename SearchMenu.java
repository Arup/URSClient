import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchMenu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton findAllPersons, findAllCourses, findPersonBy, findInstByDept,
			findCourseBy, exit;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	public void closewin() {
		try {
			dispose();
			System.exit(0);
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public SearchMenu(JFrame parent, String title) {
		super(parent, title, true);
		setSize(450, 250);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new GridLayout(3, 3));
		// panel.setLayout(new FlowLayout());
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocation(300, 300);

		findInstByDept = new JButton("Instructor By Department");
		findCourseBy = new JButton("Course By Search Criteria");
		findPersonBy = new JButton("Person By Search Criteria");
		findAllPersons = new JButton("All Persons");
		findAllCourses = new JButton("All Courses");
		exit = new JButton("Exit Search Menu");

		panel1.add(findPersonBy);
		panel1.add(findCourseBy);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel2.add(findAllPersons);
		panel2.add(findAllCourses);
		panel2.add(findInstByDept);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel3.add(exit);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		container.add(panel1);
		container.add(panel2);
		container.add(panel3);

		findInstByDept.addActionListener(this);
		findAllPersons.addActionListener(this);
		findAllCourses.addActionListener(this);
		findPersonBy.addActionListener(this);
		findCourseBy.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == findInstByDept) {
			String req;
			String id = JOptionPane
					.showInputDialog(this, "Enter Department Prefix/Pattern");
			if (id != null) {
				req = "Find_Instructors_By_Department\n"
						+ RequestID.getNextID() + "\n1\n" + id;
				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
			}
		}
		if (ae.getSource() == findPersonBy) {
			String option[] = { "Name", "City", "State", "Zip" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to View", "Find Person By",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				String sORi[] = { "Student", "Instructor", "Both" };
				String sori = (String) JOptionPane.showInputDialog(this,
						"Select Option", "Find Person Type",
						JOptionPane.QUESTION_MESSAGE, null, sORi, sORi[0]);
				if (sori != null) {
					int si = 1;
					if (sori.equals("Student")) {
						si = 0;
					} else if (sori.equals("Both")) {
						si = 2;
					}
					String pat = JOptionPane.showInputDialog(this,
							"Enter Pattern");
					if (pat != null) {
						String req = "Find_Persons_By_" + r + "\n"
								+ RequestID.getNextID() + "\n2\n" + pat + "\n"
								+ si;
						String res = JMSSender.send(req);
						Response.displayResponse(res, (JFrame) this.getParent());
					}
				}
			}
		}
		if (ae.getSource() == findCourseBy) {
			String opt[] = { "Course_Name", "Instructor", "Location" };
			String option = (String) JOptionPane.showInputDialog(this,
					"Select Option", "Find Course By",
					JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]);
			if (option != null) {
				String pat = JOptionPane.showInputDialog(this, "Enter "
						+ option);
				if (pat != null) {
					String req = "Find_Courses_By_" + option + "\n"
							+ RequestID.getNextID() + "\n1\n" + pat;
					String res = JMSSender.send(req);
					Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == findAllPersons) {
			String sORi[] = { "Student", "Instructor", "Both" };
			String sori = (String) JOptionPane.showInputDialog(this,
					"Select Option", "Find Person Type",
					JOptionPane.QUESTION_MESSAGE, null, sORi, sORi[0]);
			if (sori != null) {
				int si = 1;
				if (sori.equals("Student")) {
					si = 0;
				} else if (sori.equals("Both")) {
					si = 2;
				}
				String req = "Find_All_Persons" + "\n" + RequestID.getNextID()
						+ "\n1\n" + si;
				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
			}
		}
		if (ae.getSource() == findAllCourses) {
			String req = "Find_All_Courses" + "\n" + RequestID.getNextID()
					+ "\n0";
			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}