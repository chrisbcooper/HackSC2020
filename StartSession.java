package events;

import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Desktop.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartSession extends JFrame
{    
	private static String userName;
	private static int userAge;
	private static File userFile = new File("Chris.log");
	private static double height;
	private static int numSessions;

	private static File htmlFile;
	//Progress track = new Progress();

	public StartSession() 
	{

		try{
			JComponent picPanel = new JLabel(new ImageIcon(ImageIO.read(new File("yogaTime.jpg"))));

			JPanel topPan = new JPanel(new BorderLayout());
			JPanel midPan = new JPanel(new BorderLayout());
			JPanel botPan = new JPanel(new BorderLayout());

			topPan.setOpaque(false);
			midPan.setOpaque(false);
			botPan.setOpaque(false);

			JLabel welcome = new JLabel("Hi! Welcome back, " + userFile.getName().substring(0,userFile.getName().indexOf(".")),  SwingConstants.CENTER);
			JLabel filler = new JLabel("");
			JButton launchSession = new JButton("Start New Session!");
			JButton seeProgress = new JButton("See Current Progress");
			JButton changeUser = new JButton("Sign in as Different User");
			JButton quit = new JButton("Finish for Today");

			changeUser.setHorizontalTextPosition(SwingConstants.CENTER);
			launchSession.setHorizontalTextPosition(SwingConstants.RIGHT);

			welcome.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			launchSession.setFont(new Font("Arial", Font.PLAIN, 23));
			seeProgress.setFont(new Font("Arial", Font.PLAIN, 23));
			changeUser.setFont(new Font("Arial", Font.PLAIN, 23));
			quit.setFont(new Font("Arial", Font.PLAIN, 23));
			

			launchSession.setToolTipText("Click here to launch a new class");
			changeUser.setToolTipText("Click here to change user");
			seeProgress.setToolTipText("Click here to see all your progress");


			//seeProgress.setPreferredSize(190,40);
			//quit.setPreferredSize(190,40);

			//launchSession.setMaximumSize(new Dimension(500,400));
			//launchSession.setPreferredSize(new Dimension(100,100));

			setContentPane(picPanel);
			setLayout(new GridLayout(3,1));

			/*
			launchSession.setBorder(null);
			launchSession.setBorderPainted(true);
			launchSession.setContentAreaFilled(false);
			launchSession.setOpaque(false);*/


			launchSession.setOpaque(false);
			launchSession.setContentAreaFilled(false);
			launchSession.setBorderPainted(false);
			
			changeUser.setOpaque(false);
			changeUser.setContentAreaFilled(false);
			changeUser.setBorderPainted(false);

			seeProgress.setOpaque(false);
			seeProgress.setContentAreaFilled(false);
			seeProgress.setBorderPainted(false);

			quit.setOpaque(false);
			quit.setContentAreaFilled(false);
			quit.setBorderPainted(false);
			
			topPan.add(welcome, BorderLayout.CENTER);
		   
			midPan.add(launchSession, BorderLayout.LINE_START);
			midPan.add(seeProgress, BorderLayout.CENTER);
			midPan.add(changeUser, BorderLayout.LINE_END);

			botPan.add(quit, BorderLayout.CENTER);

			welcome.setVerticalAlignment(SwingConstants.CENTER);

			add(topPan);
			add(midPan);
			add(botPan);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1240,660);
			setVisible(true);

			
			//track.main();

			launchSession.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							startNewYoga();
							Toolkit.getDefaultToolkit().beep();
						}
					}});

			changeUser.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							Toolkit.getDefaultToolkit().beep();
							makeProgress();
						}
					}});

			seeProgress.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							Toolkit.getDefaultToolkit().beep();
							readProgress();
						}
					}});

			quit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							Toolkit.getDefaultToolkit().beep();
							dispose();
						}
					}});

		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	public void startNewYoga()
	{
		JFrame options = new JFrame("Choose Pose");

		options.setLayout(new GridLayout(1,3));
		options.setVisible(true);
		options.setSize(500,380);

		try
		{
			JButton pose1 = new JButton(new ImageIcon(ImageIO.read(new File("pose1.jpg"))));
			JButton pose2 = new JButton(new ImageIcon(ImageIO.read(new File("pose2.jpg"))));
			JButton pose3 = new JButton(new ImageIcon(ImageIO.read(new File("pose4.jpg"))));

			pose1.setOpaque(false);
			pose1.setContentAreaFilled(false);
			pose1.setBorderPainted(false);

			pose2.setOpaque(false);
			pose2.setContentAreaFilled(false);
			pose2.setBorderPainted(false);

			pose3.setOpaque(false);
			pose3.setContentAreaFilled(false);
			pose3.setBorderPainted(false);

			pose1.setToolTipText("Warrior Two");
			pose2.setToolTipText("Shallow Squat");
			pose3.setToolTipText("Tree");

			options.add(pose1);
			options.add(pose2);
			options.add(pose3);

			pose1.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						/*executeCommand("sudo ifconfig en0 down");
						executeCommand("sudo route flush");
						executeCommand("sudo ifconfig en0 up");*/
						htmlFile = new File("warrior.html");
						//Desktop.getDesktop().browse(htmlFile.toURI());
						//executeCommand("parallel python3 dataServer.py ::: ")
						//executeCommand("python3 dataServer.py " + userFile.getName().substring(0,userFile.getName().indexOf(".")) + " " + 0 + " ; ");
						//executeCommand(" aai app start ; " );
						executeCommand("open warrior.html");
						

					}catch(Exception qw)
					{qw.printStackTrace();}
				}
			});

			pose2.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{

						

						//executeCommand("python3 dataServer.py " + userFile.getName().substring(0,userFile.getName().indexOf(".")) + " " + 2);
						//executeCommand("aai app start");
						htmlFile = new File("shallow.html");
						executeCommand("open shallow.html");
						//Desktop.getDesktop().browse(htmlFile.toURI());


					}catch(Exception er)
					{er.printStackTrace();}
				}
			});

			pose3.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						
						//executeCommand("ls");
						//executeCommand("python3 dataServer.py " + userFile.getName().substring(0,userFile.getName().indexOf(".")) + " " + 1);
						//executeCommand("aai app start");
						htmlFile = new File("tree.html");

						executeCommand("open tree.html");

						//Desktop.getDesktop().browse(htmlFile.toURI());
					}catch(Exception we)
					{we.printStackTrace();}
				}
			});
		}catch(Exception p)
		{p.printStackTrace();}
	}

	public String executeCommand(String command) 
	{

	    StringBuffer output = new StringBuffer();

	    Process p;
	    try {
	        Runtime rt = Runtime.getRuntime();
	        p = rt.exec(command);
	        p.waitFor();
	        BufferedReader reader = 
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));

	        String line = "";           
	        while ((line = reader.readLine())!= null) {
	            output.append(line + "\n");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return output.toString();

	}


	public void actionPerformed(ActionEvent e) 
	{
		Toolkit.getDefaultToolkit().beep();
	}

	private static void createAndShowGUI() 
	{

		//Create and set up the window where everything appears.
		
		JFrame frame = new StartSession();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(620,330);

		try{
   
			frame.setSize(620,330);
			//frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("yogaTime.jpg")))));

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		frame.pack();
		frame.setVisible(true);
	}

	
	public void makeProgress()
	{
	JFrame f = new JFrame("User");

	try{
		JComponent picPanel = new JLabel(new ImageIcon(ImageIO.read(new File("yogatime2.jpg"))));

		f.setContentPane(picPanel);

		JLabel filler = new JLabel("");

		f.setLayout(new GridLayout(6,1));

		JPanel topPan = new JPanel(new BorderLayout());
		JPanel midPan = new JPanel(new BorderLayout());
		JPanel botPan = new JPanel(new BorderLayout());

		JTextField info = new JTextField("Enter your name");
		JButton submit = new JButton("Submit");
		JLabel l1 = new JLabel("Name: ", SwingConstants.CENTER);
		JLabel command = new JLabel("Please enter your name so we can save your progress", SwingConstants.CENTER);

		midPan.add(filler, BorderLayout.CENTER);

		l1.setFont(new Font("Arial", Font.PLAIN, 22));
		command.setFont(new Font("Arial", Font.PLAIN, 22));

		submit.setFont(new Font("Arial", Font.PLAIN, 20));

		topPan.setOpaque(false);
		midPan.setOpaque(false);
		botPan.setOpaque(false);

		topPan.add(command, BorderLayout.CENTER);

		//botPan.add(l1, BorderLayout.LINE_START);
		botPan.add(info, BorderLayout.CENTER);
		botPan.add(submit, BorderLayout.LINE_END);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600,400);
		f.setVisible(true);

		f.add(topPan);
		f.add(midPan);
		f.add(botPan);

		submit.addActionListener(new ActionListener() 
		{
	        
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String newFileName = info.getText();
					l1.setText("Name has been submitted");	

					JFrame newFrame = new JFrame("More info");	
				 	newFrame.setVisible(true);
				 	newFrame.setSize(600,400);

					JComponent picPanel = new JLabel(new ImageIcon(ImageIO.read(new File("yogatime2.jpg"))));

					newFrame.setContentPane(picPanel);

					JLabel lAge = new JLabel("Age: ");
					JLabel lHeight = new JLabel("Height: ");
					JLabel empty = new JLabel(" ");
				 	
					newFrame.setLayout(new GridLayout(3,1));

					
					JPanel topPan = new JPanel(new GridLayout(1,2));
					JPanel midPan = new JPanel(new GridLayout(1,2));
					JPanel botPan = new JPanel(new GridLayout(1,1));

					JPanel filler = new JPanel(new BorderLayout());

					JButton done = new JButton("Submit");

					
					filler.setOpaque(false);
					topPan.setOpaque(false);
					midPan.setOpaque(false);
					botPan.setOpaque(false);

					JTextField infoAge = new JTextField("Enter your age!");
					JTextField infoHeight = new JTextField("And your height!");

					userFile = new File(newFileName+".log");
					
					
					if(userFile.exists())
					{
						newFrame.dispose();
						main(null);
					}
					else
					{
						try
						{
							FileWriter fw = new FileWriter(newFileName+".log", true);
						
							topPan.add(lAge);
							topPan.add(infoAge);
							
							//filler.add(empty, BorderLayout.CENTER);
							midPan.add(lHeight);
							midPan.add(infoHeight);
							 
							botPan.add(done);

							newFrame.add(filler);
							newFrame.add(topPan);
							newFrame.add(filler);
							newFrame.add(midPan);
							newFrame.add(filler);
							newFrame.add(botPan);

							done.addActionListener(new ActionListener() 
							{
	        
									@Override
									public void actionPerformed(ActionEvent what) 
									{
										try
										{
											FileWriter fw = new FileWriter(newFileName+".log");

											String realAge = infoAge.getText();
											String realHeight = infoHeight.getText();

											fw.write("Name: " + newFileName);
											fw.write("\nAge: " + realAge);
											fw.write("\nHeight: " + realHeight);
											fw.write("\nNumber of Sessions: " + 0);
											newFrame.dispose();
											fw.close();
										}catch(Exception iwjnd)
										{
											iwjnd.printStackTrace();
										}
										
									}
							});

						}
						catch(Exception thisE)
						{
							thisE.printStackTrace();
						}
					}
				}
				catch(Exception aE)
				{
					aE.printStackTrace();
				}


			}});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}




	public static void writeFile()
	{
		try{	
			FileWriter fw = new FileWriter(userFile, true);
			Scanner keyboard = new Scanner(System.in);

			System.out.println("Please enter your age: ");
			userAge = keyboard.nextInt();

			System.out.println("Please enter your height: ");
			height = keyboard.nextDouble();

			numSessions = 0;
			fw.write("Name: " + userName + "\nAge: " + userAge + "\nHeight: " + height+ "\nNumber of Sessions: " + numSessions);
			System.out.println("Saved!");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void readProgress()
	{	
		try
		{
			Scanner fileRead = new Scanner(userFile);


			JFrame showProgress = new JFrame("Show Progress");

			showProgress.setBackground(new Color(255,255,255));
			showProgress.setLayout(new GridLayout(1,2));
			showProgress.setVisible(true);
			showProgress.setSize(800,500);


			JButton pose1 = new JButton(new ImageIcon(ImageIO.read(new File("pose1.jpg"))));
			JButton pose2 = new JButton(new ImageIcon(ImageIO.read(new File("pose2.jpg"))));
			JButton pose3 = new JButton(new ImageIcon(ImageIO.read(new File("pose4.jpg"))));			

			JButton back = new JButton("Done");

			back.setHorizontalTextPosition(SwingConstants.CENTER);

			JLabel[] prog = new JLabel[4];

			JPanel leftShow = new JPanel(new GridLayout(4,1));
			JPanel midShow = new JPanel(new GridLayout(3,1));

			midShow.add(pose1);
			midShow.add(pose2);
			midShow.add(pose3);

			pose1.setOpaque(false);
			pose1.setContentAreaFilled(false);
			pose1.setBorderPainted(false);

			pose2.setOpaque(false);
			pose2.setContentAreaFilled(false);
			pose2.setBorderPainted(false);

			pose3.setOpaque(false);
			pose3.setContentAreaFilled(false);
			pose3.setBorderPainted(false);

			try
			{
				JLabel defaultPanel = new JLabel(new ImageIcon(ImageIO.read(new File(userFile.getName().substring(0,userFile.getName().indexOf(".")) + "-progress" + ".png"))));
				JLabel panel1 = new JLabel(new ImageIcon(ImageIO.read(new File("pose1.jpg"))));
				JLabel panel2 = new JLabel(new ImageIcon(ImageIO.read(new File("pose2.jpg"))));
				JLabel panel3 = new JLabel(new ImageIcon(ImageIO.read(new File("pose4.jpg"))));
				
				pose1.setToolTipText("Warrior Two");
				pose2.setToolTipText("Shallow Squat");
				pose3.setToolTipText("Tree");


				JPanel rightShow = new JPanel(new BorderLayout());
				rightShow.add(defaultPanel, BorderLayout.CENTER);
				rightShow.add(back, BorderLayout.PAGE_END);

				String tempStringFull;

				int i = 0;

				while(fileRead.hasNextLine())
				{

					/*
						0 = name
						1 = age
						2 = height
						3 = numSessions
					*/

					tempStringFull = fileRead.nextLine();

					prog[i] = new JLabel(("\n"+tempStringFull));

					leftShow.add(prog[i]);
				}


				showProgress.add(leftShow);
				//showProgress.add(midShow);
				showProgress.add(rightShow);
				

				/*
				pose1.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							rightShow.add(panel1);
							showProgress.add(rightShow, 0,2);
						}
						
					}
				});

				pose2.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							rightShow.add(panel1);
							showProgress.add(rightShow, 0,2);
						}
					}
				});

				pose3.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JButton btn =  (JButton) e.getSource();
						if(btn.isEnabled())
						{
							rightShow.add(panel1);
							showProgress.add(rightShow, 0,2);
						}
					}
				});*/

				back.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						showProgress.dispose();
					}
				});

				
				/*
				pose3.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent actionEvent) {
				        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				        if(abstractButton.getModel().isSelected());
				        {
				        	
				        	rightShow.add(panel3,0,0);
				        }

				        showProgress.setVisible(false);
				showProgress.setVisible(true);
				      }});*/
					
				}catch(Exception aw)
				{
					aw.printStackTrace();
				}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new StartSession();
			}
		});
	}
}