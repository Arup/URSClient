import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CreateInstructor extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	Container container = getContentPane();
	JButton ok, exit;
	JTextField id, fn, ln, add, city, state, zip, department;
	// JLabel lid, lfn, lln, ladd, lcity, lstate, lzip;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	public CreateInstructor(JFrame parent, String title) {
		super(parent, title, true);
		setSize(450, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// container.setLayout(new GridLayout(3, 3));
		setFont(new Font("Arial", Font.BOLD, 25));
		setLocationRelativeTo(parent);
		setLocation(200, 100);

		ok = new JButton("OK");
		exit = new JButton("Exit Instructor Menu");
		id = new JTextField();
		fn = new JTextField();
		ln = new JTextField();
		add = new JTextField();
		city = new JTextField();
		state = new JTextField();
		zip = new JTextField();
		department = new JTextField();

		panel1.setLayout(new GridLayout(9, 2));
		panel1.add(new JLabel("Instructor ID", Label.LEFT));
		panel1.add(id);
		panel1.add(new JLabel("First Name", Label.LEFT));
		panel1.add(fn);
		panel1.add(new JLabel("Last Name", Label.LEFT));
		panel1.add(ln);
		panel1.add(new JLabel("Address", Label.LEFT));
		panel1.add(add);
		panel1.add(new JLabel("City", Label.LEFT));
		panel1.add(city);
		panel1.add(new JLabel("State", Label.LEFT));
		panel1.add(state);
		panel1.add(new JLabel("Zip Code", Label.LEFT));
		panel1.add(zip);
		panel1.add(new JLabel("Department", Label.LEFT));
		panel1.add(department);
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
			String req = "Create_Instructor\n" + RequestID.getNextID()
					+ "\n8\n" + id.getText() + "\n" + fn.getText() + "\n"
					+ ln.getText() + "\n" + add.getText() + "\n"
					+ city.getText() + "\n" + state.getText() + "\n"
					+ zip.getText() + "\n" + department.getText();
			String res = JMSSender.send(req);
			Response.displayResponse(res, (JFrame) this.getParent());
			dispose();
		}
		if (ae.getSource() == exit) {
			dispose();
		}
	}
}