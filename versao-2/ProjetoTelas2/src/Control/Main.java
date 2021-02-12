package Control;

import javax.swing.SwingUtilities;

import View.LoginUsuario;
//import View.MainFrame;

public class Main {

	public static void main(String[] args) {
		// new MainFrame();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("antes mainframe projet 2");
				//new MainFrame();
				try {
					LoginUsuario frame = new LoginUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				new LoginUsuario();
			}
		});
	}

	public void teste() {
		System.out.println(" sdasfasdf asdf adsf adsfsdasfasdf asdf adsf adsf");
	}
}
