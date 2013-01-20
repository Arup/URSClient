import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CourseMenu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton view, update, create, remove ,exit;
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

	public CourseMenu(JFrame parent, String title) {
		super(parent, title, true);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new GridLayout(3, 3));
		// panel.setLayout(new FlowLayout());
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocation(300, 300);

		create = new JButton("Create Course");
		remove = new JButton("Remove Course");
		update = new JButton("Update Course Details");
		view = new JButton("View Course Details");
		exit = new JButton("Exit Course Menu");

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
		remove.addActionListener(this);
		update.addActionListener(this);
		view.addActionListener(this);
		exit.addActionListener(this);

		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == create) {
			CreateCourse c = new CreateCourse((JFrame) this.getParent(),
					"Create Course");
		}
		if (ae.getSource() == remove) {
			RemoveCourse r = new RemoveCourse((JFrame) this.getParent(),
					"remove Course");
		}
		if (ae.getSource() == update) {
			String option[] = { "Course_Name", "Course_Section",
					"Course_Units", "Course_Instructor", "Location" };
			String r = (String) JOptionPane.showInputDialog(this,
					"Select Option to Update", "Course",
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			if (r != null) {
				UpdateCourse u = new UpdateCourse(((JFrame) this.getParent()),
						"Update Course", r);
			}
		}
		if (ae.getSource() == view) {
			String option1[] = { "Course_Units", "Course_Instructor",
					"Location", "Students" };
			String r1 = (String) JOptionPane.showInputDialog(this,
					"Select Option to View", "Course",
					JOptionPane.QUESTION_MESSAGE, null, option1, option1[1]);
			if (r1 != null) {
				ViewCourse u = new ViewCourse(((JFrame) this.getParent()),
						"View Course", r1);
			}
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}