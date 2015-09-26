package view;

import java.awt.EventQueue;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class SimCta extends View{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 try{ 
					    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
					 } 
					 catch(Exception e){ 
					
					 }
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
