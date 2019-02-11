import javax.swing.*;

public class Menu {
	private JMenuBar ToolBar;

	public Menu() {
		ToolBar = new JMenuBar();
		init();

	}
	private void init() {
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		JButton button = new JButton("Select Save Location");
		frame.getContentPane().add(button); 
		frame.setVisible(true);
		frame.setJMenuBar(ToolBar);

	}
}
