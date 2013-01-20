import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdmissionMenu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container container = getContentPane();
	JButton enroll, unenroll, bill, exit;
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

	public AdmissionMenu(JFrame parent,String title) {
		super(parent,title,true);
		setSize(300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		container.setLayout(new GridLayout(3, 3));
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(300, 300);
		
		bill = new JButton("Calculate Bill");
		unenroll = new JButton("UnEnroll Student");
		enroll = new JButton("Enroll Student");
		exit = new JButton("Exit Admission Menu");

		panel1.add(enroll);
		panel1.add(unenroll);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,40));
		panel2.add(bill);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		panel3.add(exit);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);

		bill.addActionListener(this);
		exit.addActionListener(this);
		enroll.addActionListener(this);
		unenroll.addActionListener(this);
		
		addWindowListener(new WindowAdapterURS());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == bill) {
			String req;
			String id = JOptionPane.showInputDialog(this, "Enter Student ID");
			if (id != null) {
					req = "Calculate_Bill\n" + RequestID.getNextID() + "\n1\n"
							+ id ;
				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
			}
		}
		if (ae.getSource() == enroll) {
			String req;
			String id = JOptionPane.showInputDialog(this, "Enter Student ID");
			if (id != null) {
				String courseName = JOptionPane.showInputDialog(this, "Enter Course Name");
				if (id != null) {
					String courseSection = JOptionPane.showInputDialog(this, "Enter Course Section");
					req = "Enroll_Student\n" + RequestID.getNextID() + "\n3\n"
							+ id + "\n" + courseName + "\n"+ courseSection + "\n";

				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == unenroll) {
			String req;
			String id = JOptionPane.showInputDialog(this, "Enter Student ID");
			if (id != null) {
				String courseName = JOptionPane.showInputDialog(this, "Enter Course Name");
				if (id != null) {
					String courseSection = JOptionPane.showInputDialog(this, "Enter Course Section");
					req = "Unenroll_Student\n" + RequestID.getNextID() + "\n3\n"
							+ id + "\n" + courseName + "\n"+ courseSection + "\n";

				String res = JMSSender.send(req);
				Response.displayResponse(res, (JFrame) this.getParent());
				}
			}
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}