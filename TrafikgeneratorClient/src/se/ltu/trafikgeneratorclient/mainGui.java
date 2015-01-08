package se.ltu.trafikgeneratorclient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainGui extends JFrame {

  private JPanel     contentPane;
  public  JButton    startDownButton;
  public  JButton    startUpButton;
  public  JButton    exitButton;
  private JTextField ipAddress;
  private JTextField udpPort;
  private JTextField loadSize;  
  private JTextField nrThreads;
  private JTextField duration;
  private JTextField timeout;
  private JTextField retransmit;
  private JTextField random;
  private JTextField nPacket;

  private String	ipAddr     = "192.168.1.1";
  private String	udpPrt     = "5683";
  private String	loadsize   = "512";
  private String	nrThread   = "5";
  private String	Duration   = "10000";
  private String	timeOut    = "2000";
  private String	reTransmit = "4";  
  private String	ranDom     = "1.5";
  private String	nPkt       = "1000";

	/**
	 * Launch the application.
	 */
  public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	  public void run() {
		try {
		  mainGui frame = new mainGui();
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
  public mainGui() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	GridBagLayout gbl_contentPane = new GridBagLayout();
	gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
	gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	contentPane.setLayout(gbl_contentPane);
	
	startDownButton = new JButton("start download");
	GridBagConstraints gbc_btnStartDownload = new GridBagConstraints();
	gbc_btnStartDownload.insets = new Insets(0, 0, 5, 5);
	gbc_btnStartDownload.gridx = 0;
	gbc_btnStartDownload.gridy = 0;
	contentPane.add(startDownButton, gbc_btnStartDownload);
	startDownButton.addActionListener(new buttonListener());

	startUpButton = new JButton("start upload");
	GridBagConstraints gbc_btnStartUpload = new GridBagConstraints();
	gbc_btnStartUpload.insets = new Insets(0, 0, 5, 5);
	gbc_btnStartUpload.gridx = 1;
	gbc_btnStartUpload.gridy = 0;
	contentPane.add(startUpButton, gbc_btnStartUpload);
	startUpButton.addActionListener(new buttonListener());

	exitButton = new JButton("exit");
	GridBagConstraints gbc_btnExit = new GridBagConstraints();
	gbc_btnExit.insets = new Insets(0, 0, 5, 0);
	gbc_btnExit.gridx = 2;
	gbc_btnExit.gridy = 0;
	contentPane.add(exitButton, gbc_btnExit);
	exitButton.addActionListener(new buttonListener());

	JLabel lblIpAddress = new JLabel("IP address");
	GridBagConstraints gbc_lblIpAddress = new GridBagConstraints();
	gbc_lblIpAddress.anchor = GridBagConstraints.WEST;
	gbc_lblIpAddress.insets = new Insets(0, 0, 5, 5);
	gbc_lblIpAddress.gridx = 0;
	gbc_lblIpAddress.gridy = 1;
	contentPane.add(lblIpAddress, gbc_lblIpAddress);
	
	ipAddress = new JTextField(ipAddr);
	GridBagConstraints gbc_ipAddress = new GridBagConstraints();
	gbc_ipAddress.insets = new Insets(0, 0, 5, 5);
	gbc_ipAddress.fill = GridBagConstraints.HORIZONTAL;
	gbc_ipAddress.gridx = 1;
	gbc_ipAddress.gridy = 1;
	contentPane.add(ipAddress, gbc_ipAddress);
	ipAddress.setColumns(10);
	
	JLabel lblUdpPort = new JLabel("UDP port");
	GridBagConstraints gbc_lblUdpPort = new GridBagConstraints();
	gbc_lblUdpPort.anchor = GridBagConstraints.WEST;
	gbc_lblUdpPort.insets = new Insets(0, 0, 5, 5);
	gbc_lblUdpPort.gridx = 0;
	gbc_lblUdpPort.gridy = 2;
	contentPane.add(lblUdpPort, gbc_lblUdpPort);
	
	udpPort = new JTextField(udpPrt);
	GridBagConstraints gbc_udpPort = new GridBagConstraints();
	gbc_udpPort.insets = new Insets(0, 0, 5, 5);
	gbc_udpPort.fill = GridBagConstraints.HORIZONTAL;
	gbc_udpPort.gridx = 1;
	gbc_udpPort.gridy = 2;
	contentPane.add(udpPort, gbc_udpPort);
	udpPort.setColumns(10);
	
	JLabel lblLoadSizebyte = new JLabel("load size (byte)");
	GridBagConstraints gbc_lblLoadSizebyte = new GridBagConstraints();
	gbc_lblLoadSizebyte.anchor = GridBagConstraints.WEST;
	gbc_lblLoadSizebyte.insets = new Insets(0, 0, 5, 5);
	gbc_lblLoadSizebyte.gridx = 0;
	gbc_lblLoadSizebyte.gridy = 3;
	contentPane.add(lblLoadSizebyte, gbc_lblLoadSizebyte);
	
	loadSize = new JTextField(loadsize);
	GridBagConstraints gbc_loadSize = new GridBagConstraints();
	gbc_loadSize.insets = new Insets(0, 0, 5, 5);
	gbc_loadSize.fill = GridBagConstraints.HORIZONTAL;
	gbc_loadSize.gridx = 1;
	gbc_loadSize.gridy = 3;
	contentPane.add(loadSize, gbc_loadSize);
	loadSize.setColumns(10);
	
	JLabel lblThreads = new JLabel("# threads");
	GridBagConstraints gbc_lblThreads = new GridBagConstraints();
	gbc_lblThreads.anchor = GridBagConstraints.WEST;
	gbc_lblThreads.insets = new Insets(0, 0, 5, 5);
	gbc_lblThreads.gridx = 0;
	gbc_lblThreads.gridy = 4;
	contentPane.add(lblThreads, gbc_lblThreads);
	
	nrThreads = new JTextField(nrThread);
	GridBagConstraints gbc_nrThreads = new GridBagConstraints();
	gbc_nrThreads.insets = new Insets(0, 0, 5, 5);
	gbc_nrThreads.fill = GridBagConstraints.HORIZONTAL;
	gbc_nrThreads.gridx = 1;
	gbc_nrThreads.gridy = 4;
	contentPane.add(nrThreads, gbc_nrThreads);
	nrThreads.setColumns(10);
	
	JLabel lblDuration = new JLabel("duration (ms)");
	GridBagConstraints gbc_lblDuration = new GridBagConstraints();
	gbc_lblDuration.anchor = GridBagConstraints.WEST;
	gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
	gbc_lblDuration.gridx = 0;
	gbc_lblDuration.gridy = 5;
	contentPane.add(lblDuration, gbc_lblDuration);
	
	duration = new JTextField(Duration);
	GridBagConstraints gbc_duration = new GridBagConstraints();
	gbc_duration.insets = new Insets(0, 0, 5, 5);
	gbc_duration.fill = GridBagConstraints.HORIZONTAL;
	gbc_duration.gridx = 1;
	gbc_duration.gridy = 5;
	contentPane.add(duration, gbc_duration);
	duration.setColumns(10);
	
	JLabel lblTimeout = new JLabel(" timeout (ms)");
	GridBagConstraints gbc_lblTimeout = new GridBagConstraints();
	gbc_lblTimeout.anchor = GridBagConstraints.WEST;
	gbc_lblTimeout.insets = new Insets(0, 0, 5, 5);
	gbc_lblTimeout.gridx = 0;
	gbc_lblTimeout.gridy = 6;
	contentPane.add(lblTimeout, gbc_lblTimeout);
	
	timeout = new JTextField(timeOut);
	GridBagConstraints gbc_timeout = new GridBagConstraints();
	gbc_timeout.insets = new Insets(0, 0, 5, 5);
	gbc_timeout.fill = GridBagConstraints.HORIZONTAL;
	gbc_timeout.gridx = 1;
	gbc_timeout.gridy = 6;
	contentPane.add(timeout, gbc_timeout);
	timeout.setColumns(10);
	
	JLabel lblRetransmission = new JLabel("retransmission");
	GridBagConstraints gbc_lblRetransmission = new GridBagConstraints();
	gbc_lblRetransmission.anchor = GridBagConstraints.WEST;
	gbc_lblRetransmission.insets = new Insets(0, 0, 5, 5);
	gbc_lblRetransmission.gridx = 0;
	gbc_lblRetransmission.gridy = 7;
	contentPane.add(lblRetransmission, gbc_lblRetransmission);
	
	retransmit = new JTextField(reTransmit);
	GridBagConstraints gbc_retransmit = new GridBagConstraints();
	gbc_retransmit.insets = new Insets(0, 0, 5, 5);
	gbc_retransmit.fill = GridBagConstraints.HORIZONTAL;
	gbc_retransmit.gridx = 1;
	gbc_retransmit.gridy = 7;
	contentPane.add(retransmit, gbc_retransmit);
	retransmit.setColumns(10);
	
	JLabel lblRandom = new JLabel("random");
	GridBagConstraints gbc_lblRandom = new GridBagConstraints();
	gbc_lblRandom.anchor = GridBagConstraints.WEST;
	gbc_lblRandom.insets = new Insets(0, 0, 5, 5);
	gbc_lblRandom.gridx = 0;
	gbc_lblRandom.gridy = 8;
	contentPane.add(lblRandom, gbc_lblRandom);
	
	random = new JTextField(ranDom);
	GridBagConstraints gbc_random = new GridBagConstraints();
	gbc_random.insets = new Insets(0, 0, 5, 5);
	gbc_random.fill = GridBagConstraints.HORIZONTAL;
	gbc_random.gridx = 1;
	gbc_random.gridy = 8;
	contentPane.add(random, gbc_random);
	random.setColumns(10);
	
	JLabel lblNStart = new JLabel("n start");
	GridBagConstraints gbc_lblNStart = new GridBagConstraints();
	gbc_lblNStart.anchor = GridBagConstraints.WEST;
	gbc_lblNStart.insets = new Insets(0, 0, 0, 5);
	gbc_lblNStart.gridx = 0;
	gbc_lblNStart.gridy = 9;
	contentPane.add(lblNStart, gbc_lblNStart);
	
	nPacket = new JTextField(nPkt);
	GridBagConstraints gbc_nPacket = new GridBagConstraints();
	gbc_nPacket.insets = new Insets(0, 0, 0, 5);
	gbc_nPacket.fill = GridBagConstraints.HORIZONTAL;
	gbc_nPacket.gridx = 1;
	gbc_nPacket.gridy = 9;
	contentPane.add(nPacket, gbc_nPacket);
	nPacket.setColumns(10);
  }

  class buttonListener implements ActionListener {
	buttonListener() {}

	public void actionPerformed(ActionEvent ae) {
	  if (ae.getSource() == startDownButton) {
	    String ip     = ipAddress.getText();
	    String port   = udpPort.getText();
	    String thread = nrThreads.getText();
	    String tim    = duration.getText();
	    String timO   = timeout.getText();
	    String retr   = retransmit.getText();
	    String rnd    = random.getText();
	    String siz    = loadSize.getText();
	    String npkt   = nPacket.getText();
	System.out.printf("ipAddress:  %s\n", ip);
	System.out.printf("port:       %s\n", port);
	System.out.printf("thread:     %s\n", thread);
	System.out.printf("duration:   %s\n", tim);
	System.out.printf("timeout:    %s\n", timO);
	System.out.printf("retransmit: %s\n", retr);
	System.out.printf("random:     %s\n", rnd);
	System.out.printf("load size:  %s\n", siz);
	System.out.printf("num packet: %s\n", npkt);
		TrafficConfig config = new TrafficConfig();
		config.setConfig(ip, port, thread, tim, timO, retr, rnd, siz, npkt);
		ReceiveData.runThreads(config);
	  }
	  
	  if (ae.getSource() == startUpButton) {
	    String ip     = ipAddress.getText();
	    String port   = udpPort.getText();
	    String thread = nrThreads.getText();
	    String dur    = duration.getText();
	    String timO   = timeout.getText();
	    String retr   = retransmit.getText();
	    String rnd    = random.getText();
	    String siz    = loadSize.getText();
	    String npkt   = nPacket.getText();
	System.out.printf("ipAddress:  %s\n", ip);
	System.out.printf("port:       %s\n", port);
	System.out.printf("thread:     %s\n", thread);
	System.out.printf("duration:   %s\n", dur);
	System.out.printf("timeout:    %s\n", timO);
	System.out.printf("retransmit: %s\n", retr);
	System.out.printf("random:     %s\n", rnd);
	System.out.printf("load size:  %s\n", siz);
	System.out.printf("num packet: %s\n", npkt);
		TrafficConfig config = new TrafficConfig();
		config.setConfig(ip, port, thread, dur, timO, retr, rnd, siz, npkt);
		SendData.runThreads(config);
	  }

	  if (ae.getSource() == exitButton) {
	    System.exit(0);
	  }
    }
  }
}
