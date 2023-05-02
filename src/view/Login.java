package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import java.awt.Cursor;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("My Shelfie");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 33, 414, 47);
		contentPanel.add(panel);
		
		JLabel lbLogin = new JLabel("Benvenuti su My Shelfie.");
		lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lbLogin);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(39, 130, 321, 47);
		contentPanel.add(splitPane);
		
		JLabel lblNewLabel = new JLabel("<html>Indicare il numero"+"<br>"+"di giocatori:<html>");
		lblNewLabel.setBorder(null);
		lblNewLabel.setDisplayedMnemonicIndex(0);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		splitPane.setLeftComponent(lblNewLabel);
		
		final JSlider slider = new JSlider();
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMinimum(2);
		slider.setMaximum(4);
		slider.setValue(2);
		splitPane.setRightComponent(slider);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int numOfPlayer= slider.getValue();
						//System.out.println(numOfPlayer);
						MainFrame frame = new MainFrame(numOfPlayer);
						frame.setVisible(true);
						dispose();
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
