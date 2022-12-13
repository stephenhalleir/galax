package external_systems.mobile_network.utilities;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ExtractUi {

	// UI controls
	private JFrame frmMobileNetworkExtract;
	private JTextField textField;
	private Thread extractThread;
	private JLabel lblMsisdns;
	private JButton btnExtract;
	private JProgressBar progressBar;
	private JLabel lblProgress;
	private JLabel lblStatus;
	private JLabel statusLabel;
	

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtractUi window = new ExtractUi();
					window.frmMobileNetworkExtract.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExtractUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmMobileNetworkExtract = new JFrame();
		textField = new JTextField();
		lblMsisdns = new JLabel("MSISDNs");
		progressBar = new JProgressBar();
		statusLabel = new JLabel("");
		btnExtract = new JButton("Extract");
		
		frmMobileNetworkExtract.setTitle("Mobile Network Extract Tool v0.1");
		frmMobileNetworkExtract.setBounds(100, 100, 493, 363);
		frmMobileNetworkExtract.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMobileNetworkExtract.getContentPane().setLayout(null);

		textField.setBounds(80, 58, 340, 22);
		textField.setColumns(10);
		frmMobileNetworkExtract.getContentPane().add(textField);

		lblMsisdns.setBounds(12, 61, 56, 16);
		frmMobileNetworkExtract.getContentPane().add(lblMsisdns);

		btnExtract.setBounds(77, 136, 343, 25);
		frmMobileNetworkExtract.getContentPane().add(btnExtract);

		progressBar.setBounds(80, 93, 340, 30);
		progressBar.setStringPainted(true);
		frmMobileNetworkExtract.getContentPane().add(progressBar);
	
		statusLabel.setBounds(12, 227, 408, 55);
		frmMobileNetworkExtract.getContentPane().add(statusLabel);
		
		lblProgress = new JLabel("Progress");
		lblProgress.setBounds(12, 98, 56, 16);
		frmMobileNetworkExtract.getContentPane().add(lblProgress);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(12, 198, 56, 16);
		frmMobileNetworkExtract.getContentPane().add(lblStatus);

		// add a listener to the button
		btnExtract.addActionListener(new ActionListener() {

			// when the Extract button is clicked
			public void actionPerformed(ActionEvent arg0) {
				
				// create a new thread
				extractThread = new Thread(new Runnable() {

					// Thread.run()
					public void run() {

						// reset the progress bar
						progressBar.setValue(0);
						progressBar.setString("");

						// read in the input
						String userInputString = textField.getText();

						// split the string up by comma
						String[] msisdns = userInputString.split(",");
						String dir = "";

						// for each MSISDN in the list
						for (int i = 0; i < msisdns.length; i++) {

							// read the MSISDN value
							String msisdn = msisdns[i].trim();

							// update the status
							statusLabel.setText("Extracting network profiles for " + msisdn);

							// set up an extract object for the MSISDN
							NetworkProfileExtract netExt = new NetworkProfileExtract(msisdn);

							// extract and print the profile
							dir = netExt.printNetworkProfile();

							// calculate the % complete
							double progress = ((double) (i + 1) / msisdns.length) * 100;
							
							// update the progress bar
							progressBar.setValue((int) progress);
							progressBar.setString((int)progress + "%");
						}

						// update the status label with the file directory
						statusLabel.setText("File(s) generated in " + dir);
						
						// open the file directory for the user
						try {
							Desktop.getDesktop().open(new File(dir));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}); // end run()
				
				// start the thread
				extractThread.start();
			}
		}); // end addActionListener()
	}
}
