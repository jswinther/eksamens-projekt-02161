package dtu.project.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class Planner extends JFrame implements ActionListener {
	public JTextField textFieldEmail, textFieldEmailReg, textFieldPasswordReg, textFieldRePassReg, textFieldPhone, textFieldReceiver, textFieldCC, textFieldTopic;
	public static JButton buttonLogin;
	public static JButton buttonGoToRegister;
	public JTextArea textAreaMail;
	public JButton buttonGoToLogin;
	public JButton buttonRegister;
	public JButton buttonSendMail;
	public JButton buttonNewMail;
	public JButton buttonRefresh, buttonDeleMail;
	public JLabel labelEmail, labelPassword;
	public static JLabel labelStatus;
	public JLabel labelEmailReg;
	public JLabel labelPasswordReg;
	public JLabel labelRePassReg;
	
	public JPanel paneUserPass, paneLogin, paneGoToRegister, paneRegisterData, paneRegisterButton, paneNewMail, paneMailLayout, paneTo, paneCc, paneSubject;
	public JPasswordField passwordField;
	
	
	
	private String userName =null, passWord = null;
	private static Planner window;
	private DefaultListModel<String> listMail;
	Dimension textFieldTo = new Dimension(650,30);
	Dimension textFieldsize = new Dimension(300,30);
	Dimension buttonsize = new Dimension(150,70);
	Dimension labelsize = new Dimension(300,50);
	Dimension labelMail = new Dimension(50,50);
	Dimension textFieldMail = new Dimension(650,600);
	

	
	public Planner() 
	{
		drawLayoutLogin();	
	}
	private void drawPlaner() 
	{
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout(1,5));
		window.setSize(800, 800);
		window.setTitle("Planner");
		getContentPane().validate();
	}

	
	public static void main(String[] args) 
	{
		File f = new File("C:\\Plannerdata");
		try{
		    if(f.mkdir()) { 
		        System.out.println("Directory Created");
		    } else {
		        System.out.println("Directory is not created");
		    }
		} catch(Exception e){
		    e.printStackTrace();
		} 
		window = new Planner();

		window.setTitle("Login");
		window.setSize(640,280);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
		

	}
	public void drawLayoutLogin() 
	{
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout(1,5));

		textFieldEmail = new JTextField();
		textFieldEmail.setMaximumSize(textFieldsize);
		textFieldEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

		passwordField = new JPasswordField();
		passwordField.setMaximumSize(textFieldsize);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setEchoChar('*');

		labelEmail = new JLabel("EMAIL");
		labelEmail.setMaximumSize(labelsize);
		labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelPassword = new JLabel("PASSWORD");
		labelPassword.setMaximumSize(labelsize);
		labelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelStatus = new JLabel();
		labelStatus.setMaximumSize(labelsize);
		labelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

		paneUserPass = new JPanel();
		paneUserPass.setLayout(new BoxLayout(paneUserPass, BoxLayout.PAGE_AXIS));
		paneUserPass.add(Box.createRigidArea(new Dimension(110,20)));
		paneUserPass.add(labelEmail);
		paneUserPass.add(Box.createRigidArea(new Dimension(110,1)));
		paneUserPass.add(textFieldEmail);
		paneUserPass.add(Box.createRigidArea(new Dimension(110,1)));
		paneUserPass.add(labelPassword);
		paneUserPass.add(Box.createRigidArea(new Dimension(110,1)));
		paneUserPass.add(passwordField);
		paneUserPass.add(Box.createRigidArea(new Dimension(110,2)));
		paneUserPass.add(labelStatus);

		buttonLogin = new JButton("Login");
		buttonLogin.setEnabled(true);
		buttonLogin.addActionListener(this);
		buttonLogin.setMaximumSize(buttonsize);
		buttonLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);

		paneLogin = new JPanel();
		paneLogin.setLayout(new BoxLayout(paneLogin, BoxLayout.PAGE_AXIS));
		paneLogin.add(buttonLogin);

		buttonGoToRegister = new JButton("New user?\r\n Register");
		buttonGoToRegister.setEnabled(true);
		buttonGoToRegister.addActionListener(this);
		buttonGoToRegister.setMaximumSize(buttonsize);
		buttonGoToRegister.setAlignmentX(Component.RIGHT_ALIGNMENT);

		paneGoToRegister = new JPanel();
		paneGoToRegister.setLayout(new BoxLayout(paneGoToRegister, BoxLayout.PAGE_AXIS));
		//pane2.add(Box.createRigidArea(new Dimension(110,20)));
		//pane2.add(icon);
		paneGoToRegister.add(Box.createRigidArea(new Dimension(110,1)));
		paneGoToRegister.add(buttonGoToRegister);

		getContentPane().add(paneUserPass,BorderLayout.CENTER);
		getContentPane().add(paneGoToRegister,BorderLayout.WEST);
		getContentPane().add(paneLogin,BorderLayout.EAST);
		getContentPane().revalidate();
	}
	public void drawLayoutRegister()
	{
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout(1,5));
		labelEmailReg = new JLabel("EMAIL");
		labelEmailReg.setMaximumSize(labelsize);
		labelEmailReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelPasswordReg = new JLabel("PASSWORD");
		labelPasswordReg.setMaximumSize(labelsize);
		labelPasswordReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelRePassReg = new JLabel("RETYPE PASSWORD");
		labelRePassReg.setMaximumSize(labelsize);
		labelRePassReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		

		textFieldEmailReg = new JTextField();
		textFieldEmailReg.setMaximumSize(textFieldsize);
		textFieldEmailReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		textFieldPasswordReg = new JTextField();
		textFieldPasswordReg.setMaximumSize(textFieldsize);
		textFieldPasswordReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		textFieldRePassReg = new JTextField();
		textFieldRePassReg.setMaximumSize(textFieldsize);
		textFieldRePassReg.setAlignmentX(Component.CENTER_ALIGNMENT);

		

		paneRegisterData = new JPanel();
		paneRegisterData.setLayout(new BoxLayout(paneRegisterData, BoxLayout.PAGE_AXIS));
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,20)));
		paneRegisterData.add(labelEmailReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,1)));
		paneRegisterData.add(textFieldEmailReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,1)));
		paneRegisterData.add(labelPasswordReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,1)));
		paneRegisterData.add(textFieldPasswordReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,2)));
		paneRegisterData.add(labelRePassReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,1)));
		paneRegisterData.add(textFieldRePassReg);
		paneRegisterData.add(Box.createRigidArea(new Dimension(110,2)));
		

		buttonRegister = new JButton("Register");
		buttonRegister.setEnabled(true);
		buttonRegister.addActionListener(this);
		buttonRegister.setMaximumSize(buttonsize);
		buttonRegister.setAlignmentX(Component.RIGHT_ALIGNMENT);

		buttonGoToLogin = new JButton("Go to Login");
		buttonGoToLogin.setEnabled(true);
		buttonGoToLogin.addActionListener(this);
		buttonGoToLogin.setMaximumSize(buttonsize);
		buttonGoToLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);

		paneRegisterButton = new JPanel();
		paneRegisterButton.setLayout(new BoxLayout(paneRegisterButton, BoxLayout.PAGE_AXIS));
		paneRegisterButton.add(Box.createRigidArea(new Dimension(110,20)));
		paneRegisterButton.add(buttonRegister);
		paneRegisterButton.add(Box.createRigidArea(new Dimension(110,1)));
		paneRegisterButton.add(buttonGoToLogin);
		paneRegisterButton.add(Box.createRigidArea(new Dimension(110,1)));

		getContentPane().add(paneRegisterData,BorderLayout.CENTER);
		getContentPane().add(paneRegisterButton,BorderLayout.EAST);
		getContentPane().revalidate();
	}
	public void actionPerformed(ActionEvent e)

	{
		if (e.getSource() == buttonLogin)
		{
			login();
		}
		if (e.getSource() == buttonGoToRegister)
		{
			drawLayoutRegister();
		}
		if (e.getSource() == buttonGoToLogin)
		{
			drawLayoutLogin();
		}
		if (e.getSource() == buttonRegister)
		{
			register();
		}
		
		
		
		
	}
	public boolean checkIfUserExists(String userName)
	{
		
		File tmpDir = new File("C:\\Plannerdata\\"+userName+".txt");
		boolean exists = tmpDir.isFile();
		if (exists == true) {
			return true;
		}
		return false;
	
	}
	public boolean passwordcorrect(String password) throws IOException {
		 BufferedReader Buff = new BufferedReader(new FileReader("C:\\Plannerdata\\"+textFieldEmail.getText()+".txt"));
	     String text = Buff.readLine();
	     if (text.equals(passwordField.getText())) {
	    	 return true;
	     }
		
		return false;
		
	}
	public boolean savedInfo(String userName, String password) throws IOException {
		
		
		if (checkIfUserExists(userName)==true) {
			if (passwordcorrect(password)==true) {
				return true;
			}
			else {
				return false;
			}
			
		}
		return false;
	}

	public void login() 
	{
		
		userName = textFieldEmail.getText();
		passWord = passwordField.getText();
		
		if (!userName.matches("^[a-zA-Z0-9.]+[@]{1}+[a-zA-Z0-9]+[a-zA-Z0-9-.]+[.]{1}+[a-zA-Z0-9]+$"))
		{
			labelStatus.setText("ILLEGAL USERNAME OR PASSWORD");
		}
		else
		{
			labelStatus.setText("LOGIN IN");
			try {
				if (savedInfo(userName, passWord) == true){
					drawPlaner();
					
				}
				else {
					labelStatus.setText("ILLEGAL USERNAME OR PASSWORD");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
	private void register()
	{
		
		String userNameReg = textFieldEmailReg.getText();
		String passwordreg = textFieldPasswordReg.getText();
		String rePassReg = textFieldRePassReg.getText();
		if (!userNameReg.matches("^[a-zA-Z0-9.]+[@]{1}+[a-zA-Z0-9]+[a-zA-Z0-9-.]+[.]{1}+[a-zA-Z0-9]+$") 
				|| !passwordreg.equals(rePassReg) || passwordreg.length() <6 || passwordreg.length() >20 || userNameReg.contains("!!!"))
		{
			labelEmailReg.setText("ILLEGAL USERNAME OR PASSWORD");
		}
		else
		{
			
			File txtfile = new File("C:\\Plannerdata\\"+userNameReg+".txt");
			
			FileWriter fw = null;
			try {
				fw = new FileWriter(txtfile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				fw.write(passwordreg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            drawLayoutLogin();
		}
	}
	public String encryptedStringProducer(String username, String stringToEncrypt)
	{
		int key = keyProducer(username);
		//System.out.println(stringToEncrypt);
		String encryptedMessage = "";
		for (int i=0; i<stringToEncrypt.length(); i++)
		{
			encryptedMessage = encryptedMessage + (int)stringToEncrypt.charAt(i)*(key+i) + " ";
		}
		//System.out.println(encryptedMessage);
		key = 0;
		return encryptedMessage;
	}

	public String decryptedStringProducer(String username, String stringToDecrypt)
	{
		int key = keyProducer(username);
		String decryptedMessage = "";
		String[] decryptedMessageArray = stringToDecrypt.split(" ");
		for (int i = 0; i<decryptedMessageArray.length; i++)
		{
			decryptedMessage = decryptedMessage + (char)(Integer.parseInt(decryptedMessageArray[i])/(key+i));
		}
		//System.out.println(decryptedMessage);
		key = 0;
		return decryptedMessage;
	}

	public int keyProducer(String usernameToGenKeyFrom)
	{
		int key = 0;
		for (int i=0; i<usernameToGenKeyFrom.length(); i++)
		{
			key = key + (int)usernameToGenKeyFrom.charAt(i)*(int)usernameToGenKeyFrom.charAt(1);
		}
		return key;
	}
	
	

}

