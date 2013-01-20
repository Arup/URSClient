import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class URSMainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container container = getContentPane();
	JButton student, instructor, course, admission, exit, find;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();

	public void closewin() {
		try {
			dispose();
			System.exit(0);
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public URSMainMenu(String title) {
		super(title);
		setSize(370, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new GridLayout(4, 2));
//		panel.setLayout(new FlowLayout());
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocation(450, 150);
		
		course = new JButton("Course Operations");
		admission = new JButton("Admission Operations");
		instructor = new JButton("Instructor Operations");
		student = new JButton("Student Operations");
		find = new JButton("Find Operations");
		exit = new JButton("Exit Main Menu");

		panel1.add(student);
		panel1.add(instructor);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		panel2.add(find);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		panel3.add(course);
		panel3.add(admission);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		panel4.add(exit);
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);

		course.addActionListener(this);
		student.addActionListener(this);
		instructor.addActionListener(this);
		admission.addActionListener(this);
		find.addActionListener(this);
		exit.addActionListener(this);
		
		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == course) {
			CourseMenu cm = new CourseMenu(this, "Course Menu");
		}
		if(ae.getSource() == find){
			SearchMenu sm = new SearchMenu(this,"Find");
		}
		if(ae.getSource() == instructor){
			InstructorMenu scm = new InstructorMenu(this,"Find");
		}
		if(ae.getSource() == admission){
			AdmissionMenu am = new AdmissionMenu(this, "Admission Menu");
		}
		if(ae.getSource() == student){
			StudentMenu sm = new StudentMenu(this,"Student Menu");
		}
		if (ae.getSource() == exit) {
			closewin();
		}
	}
}