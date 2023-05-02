package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import view.MainFrame;

public class LoginController {

	private Login login;
	
	
	public LoginController(final Login login) {
		this.login=login;
		login.getOkButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int numOfPlayer=login.getSlider().getValue();
				// System.out.println(numOfPlayer);
				MainController frame = new MainController(numOfPlayer);
				login.dispose();
			}
		});
	}
	
}
