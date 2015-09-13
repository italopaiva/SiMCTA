package view;

import java.awt.EventQueue;

@SuppressWarnings("serial")
public class SimCta extends View{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimCta frame = new SimCta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimCta() {
		super();
	}

}
