/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.junit.experimental.categories.Category;
import org.omg.CORBA.portable.InputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import javafx.scene.chart.NumberAxis;

/**
 * @author hogan
 * @author Spicer
 * @author Johnson
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {
	private static final String IMG_PATH = "src/planes.jpg";
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	
	private JPanel pnlTitle;
	private JPanel pnlCenter;
	private JPanel pnlButton;
	
	// All of the default settings for configuration
	private int rNGSeed = Constants.DEFAULT_SEED;
	private double dailyMean = Constants.DEFAULT_DAILY_BOOKING_MEAN;
	private int queueSize = Constants.DEFAULT_MAX_QUEUE_SIZE;
	private double cancellation = Constants.DEFAULT_CANCELLATION_PROB;
	private double sdbooking = Constants.DEFAULT_DAILY_BOOKING_SD;	
	private double firstProb = Constants.DEFAULT_FIRST_PROB;
	private double businessProb = Constants.DEFAULT_BUSINESS_PROB;
	private double premiumProb = Constants.DEFAULT_PREMIUM_PROB;
	private double economyProb = Constants.DEFAULT_ECONOMY_PROB;
	
	// Titles
	JLabel labTitle = new JLabel("Aircraft Configuration");
	JLabel labSim = new JLabel("Simulation Settings");
	JLabel labAir = new JLabel("Aircraft Settings");
	JButton btnRun = new JButton("Run");
	JButton btnNext = new JButton("Next");
	JButton btnPrev = new JButton("Previous");
	JButton btnReset = new JButton("Reset");
	
	// All fields for Simulation Settings
	JTextField fieldRNG;
	JTextField fieldDailyMean;
	JTextField fieldQueueSize;
	JTextField fieldCancellation;
			
	// All fields for Aircraft Settings
	JTextField fieldFirst;
	JTextField fieldBusiness;
	JTextField fieldPremium;
	JTextField fieldEconomy;
			
	JLabel labEmptyR1;
	JLabel labEmptyR2;
	JLabel labEmptyR3;
	
	// Radio Button for setting if custom variables or default
	JRadioButton radioDefault;	
	JRadioButton radioCustom;
	
	// Text sliding box
	JScrollPane loggingBox;
	JTextArea logText;
	
	// Default state
	boolean defaultConstants;
	boolean displayChartOne;
	boolean displayChartTwo;
	boolean customInput = false;
	boolean allValid = true;
	
	JFreeChart lineChartOne;
	JFreeChart lineChartTwo;
	
	String[] args;
	String[] arraySummary;
	
	int[] arrayQueue;
	int[] arrayRefused;
	
	int[] numEconomy;
	int[] numPremium; 
	int[] numBusiness;
	int[] numFirst;
	int[] numTotal;
	int[] numEmpty;	
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method to create the GUI
	 */
	private void createGUI() {
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlTitle = createPanel(Color.WHITE);
		pnlCenter = createPanel(Color.WHITE);
		pnlButton = createPanel(Color.WHITE);
		
		this.pnlTitle.add(labTitle);		
		if(customInput == true) {
			loadargs(this.args);
		}
		
		// Center the titles at the top of each row
		labSim.setHorizontalAlignment(JLabel.CENTER);
		labAir.setHorizontalAlignment(JLabel.CENTER);
		
		// Empty labels for button coloum to keep formating
		labEmptyR1 = new JLabel(" ");
		labEmptyR2 = new JLabel(" ");
		labEmptyR3 = new JLabel(" ");
		
		// All fields for Simulation Settings
		fieldRNG = new JTextField(String.valueOf(rNGSeed));
		fieldDailyMean = new JTextField(String.valueOf(dailyMean));
		fieldQueueSize = new JTextField(String.valueOf(queueSize));
		fieldCancellation = new JTextField(String.valueOf(cancellation));
		
		// All fields for Aircraft Settings
		fieldFirst = new JTextField(String.valueOf(firstProb));
		fieldBusiness = new JTextField(String.valueOf(businessProb));
		fieldPremium = new JTextField(String.valueOf(premiumProb));
		fieldEconomy = new JTextField(String.valueOf(economyProb));		
		
		// Radio Button for setting if custom variables or default		
		arrayQueue = new int[Constants.DURATION];
		arrayRefused = new int[Constants.DURATION];
		arraySummary = new String[Constants.DURATION];		
		radioDefault = createJRadioButton("Default");		
		radioDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioCustom.setSelected(false);
				defaultConstants = true;
				defaultSettings();
				repaint();
			}
		});
		
		radioCustom = createJRadioButton("Custom");		
		radioCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioDefault.setSelected(false);
				defaultConstants = false;
				customSettings();
			}
		});
		
		if(this.customInput == true) {
			radioCustom.setSelected(true);
		}
		else {
			radioDefault.setSelected(true);
		}		
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRun.setEnabled(false);
				if(defaultConstants) { // run default simulator
					Simulator s = simulatorCreator(false);
					Log l = logCreator();
					runSimulationRunner(s,l);
					logginArea(s);
					simulationveriables();
				}
				else if(updateCustom() == true)  { // run custom input simulator
						Simulator s = simulatorCreator(true);
						Log l = logCreator();
						runSimulationRunner(s,l);
						logginArea(s);
						simulationveriables();
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRun.setEnabled(true);
				if(defaultConstants) {
					displayChartOne = false;
					displayChartTwo = false;
					btnNext.setEnabled(true);
					btnPrev.setEnabled(true);
					try {
						startImage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pnlTitle.removeAll();
					pnlTitle.add(labTitle, BorderLayout.CENTER);
					defaultSettings();
				}				
				else {
					displayChartOne = false;
					displayChartTwo = false;
					btnNext.setEnabled(true);
					btnPrev.setEnabled(true);
					try {
						startImage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					pnlTitle.removeAll();
					pnlTitle.add(labTitle, BorderLayout.CENTER);
					customSettings();
				}
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(displayChartOne == false && displayChartTwo == false) {
					displayChartOne = true;
					btnPrev.setEnabled(true);
					// display chart one
					displayGraphOne();
				}
				else if(displayChartOne == true && displayChartTwo == false) {
					displayChartOne = false;
					displayChartTwo = true;
					btnNext.setEnabled(false);
					// display chart two
					displayGraphTwo();					
				}				
			}
		});
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
					if(displayChartTwo == true) {
						btnNext.setEnabled(true);
						// go to graph one
						displayChartTwo = false;
						displayChartOne = true;
						displayGraphOne();						
					}
					else if(displayChartOne == true) {
						btnNext.setEnabled(true);
						// go to log display
						displayChartOne = false;
						btnPrev.setEnabled(false);
						pnlTitle.removeAll();
						pnlTitle.add(labTitle, BorderLayout.CENTER);
						printLog();						
					}
				}
			});		
		
		btnPrev.setEnabled(false);
		displayChartOne = false;
		displayChartTwo = false;
		defaultConstants = true;
		try {
			startImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(this.customInput == true) {
			customSettings();
		}
		else {
			defaultSettings();		
		}	
		repaint();
		this.setVisible(true);
	}
	
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	private JRadioButton createJRadioButton(String str) {
		JRadioButton jrb = new JRadioButton(str); 
		return jrb; 
	}
	 
	private void defaultSettings() {
		pnlButton.removeAll();
		GridLayout testLayout = new GridLayout(0, 5);
		pnlButton.setLayout(testLayout);
		pnlButton.add(labSim);
		pnlButton.add(labEmptyR1);
		pnlButton.add(labAir);
		pnlButton.add(labEmptyR2);
		pnlButton.add(labEmptyR3);
		
		//Line one of configuration
		pnlButton.add(new JLabel("RNG Seed:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_SEED)));
		pnlButton.add(new JLabel("First:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_FIRST_PROB)));
		pnlButton.add(btnRun);
		
		//Line two of Configuration
		pnlButton.add(new JLabel("Daily Mean:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_DAILY_BOOKING_MEAN)));
		pnlButton.add(new JLabel("Business:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_BUSINESS_PROB)));
		pnlButton.add(new JLabel("Configuration"));
		
		//Line three of Configuration
		pnlButton.add(new JLabel("Queue Size:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_MAX_QUEUE_SIZE)));
		pnlButton.add(new JLabel("Premium:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_PREMIUM_PROB)));
		pnlButton.add(radioDefault);
		
		//Line four of Configuration
		pnlButton.add(new JLabel("Cancellation:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_CANCELLATION_PROB)));
		pnlButton.add(new JLabel("Economy:"));
		pnlButton.add(new JLabel(String.valueOf(Constants.DEFAULT_ECONOMY_PROB)));
		pnlButton.add(radioCustom);
		
		updateDisplay();
	}
		
	private void customSettings() {
		pnlButton.removeAll();
		GridLayout testLayout = new GridLayout(0, 5);
		pnlButton.setLayout(testLayout);
		pnlButton.add(labSim);
		pnlButton.add(labEmptyR1);
		pnlButton.add(labAir);
		pnlButton.add(labEmptyR2);
		pnlButton.add(labEmptyR3);
		
		//Line one of configuration
		pnlButton.add(new JLabel("RNG Seed:"));
		pnlButton.add(fieldRNG);
		pnlButton.add(new JLabel("First:"));
		pnlButton.add(fieldFirst);
		pnlButton.add(btnRun);
		
		//Line two of Configuration
		pnlButton.add(new JLabel("Daily Mean:"));
		pnlButton.add(fieldDailyMean);
		pnlButton.add(new JLabel("Business:"));
		pnlButton.add(fieldBusiness);
		pnlButton.add(new JLabel("Configuration"));
		
		//Line three of Configuration
		pnlButton.add(new JLabel("Queue Size:"));
		pnlButton.add(fieldQueueSize);
		pnlButton.add(new JLabel("Premium:"));
		pnlButton.add(fieldPremium);
		pnlButton.add(radioDefault);
		
		//Line four of Configuration
		pnlButton.add(new JLabel("Cancellation:"));
		pnlButton.add(fieldCancellation);
		pnlButton.add(new JLabel("Economy:"));
		pnlButton.add(fieldEconomy);
		pnlButton.add(radioCustom);
		
		updateDisplay();
	}
		
	private void updateDisplay() {
		this.getContentPane().add(pnlTitle, BorderLayout.NORTH);
		this.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		this.getContentPane().add(pnlButton, BorderLayout.SOUTH);
		repaint();
		this.setVisible(true);
	}
	
	private void printLog() {
		pnlCenter.removeAll();
		loggingBox = new JScrollPane(logText);
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.add(loggingBox, BorderLayout.CENTER);
		updateDisplay();
	}
	
	private void logginArea(Simulator s) {
		logText = new JTextArea();		
		logText.append("Start of Simulation\n\n");
		logText.append(s.toString() + "\n\n");
		try {
			logText.append(s.getFlights(Constants.FIRST_FLIGHT).initialState() + "\n");
		} catch (SimulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i <= Constants.DURATION; i++) {
			logText.append(arraySummary[i]);
		}
		
		logText.append("\n End of Simulation \n");
		logText.append(s.finalState());
		
		lineChartOne = ChartFactory.createLineChart(
		         "Passenger Progression",
		         "Day","Passengers",
		         generateGraphOne(s),
		         PlotOrientation.VERTICAL,
		         true,false,false);
		
		lineChartTwo = ChartFactory.createLineChart(
				"Queue & Refused Progression", 
				"Day", "Passenger", 
				generateGraphTwo(s),
				PlotOrientation.VERTICAL,
				true, false, false);
		
		printLog();
	}
	
	private Log logCreator() {
		Log l = null;
		try {
			l = new Log();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return l;
	}
	
	private Simulator simulatorCreator(boolean custom) {
		Simulator s = null;
		if(customInput) {
			try {
				s = createSimulatorUsingArgs(args);
			} catch (SimulationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else if(custom) {
			String[] newArgs = populate();
			try {
				s = createSimulatorUsingArgs(newArgs);
			} catch (SimulationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else {
			try {
				s = new Simulator();
			} catch (SimulationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return s;
	}
	
	private void runSimulationRunner(Simulator s, Log l) {
		SimulationRunner simRun = new SimulationRunner(s, l);
		try {
			simRun.runSimulation();
			this.arrayQueue = simRun.arrayQueue;
			this.arrayRefused = simRun.arrayRefused;
			this.arraySummary = simRun.arraySummary;
		} catch (AircraftException | PassengerException | SimulationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void loadargs(String[] args) {
		this.rNGSeed = Integer.parseInt(args[1]);
		this.queueSize = Integer.parseInt(args[2]);
		this.dailyMean = Double.parseDouble(args[3]);
		this.sdbooking = Double.parseDouble(args[4]);
		this.firstProb = Double.parseDouble(args[5]);
		this.businessProb = Double.parseDouble(args[6]);
		this.premiumProb = Double.parseDouble(args[7]);
		this.economyProb = Double.parseDouble(args[8]);
		this.cancellation = Double.parseDouble(args[9]);
	}
	
	private static Simulator createSimulatorUsingArgs(String[] args) throws SimulationException {
		int seed = Integer.parseInt(args[1]);
		int maxQueueSize = Integer.parseInt(args[2]);
		double meanBookings = Double.parseDouble(args[3]);
		double sdBookings = Double.parseDouble(args[4]);
		double firstProb = Double.parseDouble(args[5]);
		double businessProb = Double.parseDouble(args[6]);
		double premiumProb = Double.parseDouble(args[7]);
		double economyProb = Double.parseDouble(args[8]);
		double cancelProb = Double.parseDouble(args[9]);
		return new Simulator(seed,maxQueueSize,meanBookings,sdBookings,firstProb,businessProb,
						  premiumProb,economyProb,cancelProb);	
	}

	private String[] populate() {
		String[] args = new String[10];
		
		args[0] = "-gui";
		args[1] = String.valueOf(rNGSeed);
		args[2] = String.valueOf(queueSize);
		args[3] = String.valueOf(dailyMean);
		args[4] = String.valueOf(sdbooking);
		args[5] = String.valueOf(firstProb);
		args[6] = String.valueOf(businessProb);
		args[7] = String.valueOf(premiumProb);
		args[8] = String.valueOf(economyProb);
		args[9] = String.valueOf(cancellation);
		
		return args;
	}
	
	private void simulationveriables() {
		pnlButton.removeAll();
		GridLayout testLayout = new GridLayout(0, 5);
		pnlButton.setLayout(testLayout);
		pnlButton.add(labSim);
		pnlButton.add(labEmptyR1);
		pnlButton.add(labAir);
		pnlButton.add(labEmptyR2);
		pnlButton.add(labEmptyR3);
		
		//Line one of configuration
		pnlButton.add(new JLabel("RNG Seed:"));
		pnlButton.add(new JLabel(String.valueOf(rNGSeed)));
		pnlButton.add(new JLabel("First:"));
		pnlButton.add(new JLabel(String.valueOf(firstProb)));
		pnlButton.add(btnRun);
		
		//Line two of Configuration
		pnlButton.add(new JLabel("Daily Mean:"));
		pnlButton.add(new JLabel(String.valueOf(dailyMean)));
		pnlButton.add(new JLabel("Business:"));
		pnlButton.add(new JLabel(String.valueOf(businessProb)));
		pnlButton.add(btnNext); // replace with next btn
		
		//Line three of Configuration
		pnlButton.add(new JLabel("Queue Size:"));
		pnlButton.add(new JLabel(String.valueOf(queueSize)));
		pnlButton.add(new JLabel("Premium:"));
		pnlButton.add(new JLabel(String.valueOf(premiumProb)));
		pnlButton.add(btnPrev); // replace with previous btn
		
		//Line four of Configuration
		pnlButton.add(new JLabel("Cancellation:"));
		pnlButton.add(new JLabel(String.valueOf(cancellation)));
		pnlButton.add(new JLabel("Economy:"));
		pnlButton.add(new JLabel(String.valueOf(economyProb)));
		pnlButton.add(btnReset); // replace with reset button
		
		updateDisplay();
	}
	
	private void displayGraphOne() {
		pnlTitle.removeAll();
		pnlCenter.removeAll();
		JLabel graphOneTitle = new JLabel(" ");
		pnlTitle.add(graphOneTitle);
		ChartPanel CP = new ChartPanel(lineChartOne);
		pnlCenter.add(CP, BorderLayout.CENTER);
		repaint();
		this.setVisible(true);		
	}
	
	private DefaultCategoryDataset generateGraphOne(Simulator s) {
		final DefaultCategoryDataset dataset = 
			      new DefaultCategoryDataset( ); 
		for(Integer i = 1; i < Constants.DURATION; i++) {
			try {
				if(i >= Constants.FIRST_FLIGHT) {
					Bookings current = s.getFlightStatus(i);
					dataset.addValue(current.getNumFirst(), "First", i);
					dataset.addValue(current.getNumBusiness(), "Business", i);
					dataset.addValue(current.getNumPremium(), "Premium", i);
					dataset.addValue(current.getNumEconomy(), "Economy", i);
					dataset.addValue(current.getTotal(), "Total", i);
					dataset.addValue(current.getAvailable(), "Empty", i);
					}
			} catch (SimulationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataset;
	}
		
	private void displayGraphTwo() {
		pnlTitle.removeAll();
		pnlCenter.removeAll();
		JLabel graphOneTitle = new JLabel(" ");
		pnlTitle.add(graphOneTitle);
		ChartPanel CP = new ChartPanel(lineChartTwo);
		pnlCenter.add(CP, BorderLayout.CENTER);
		repaint();
		this.setVisible(true);
	}
	
	private DefaultCategoryDataset generateGraphTwo(Simulator s) {
		final DefaultCategoryDataset dataset = 
			      new DefaultCategoryDataset( ); 
		for (Integer i = 0; i < Constants.DURATION; i++) {
			if (i >= Constants.MAX_QUEUING_PERIOD) {
				dataset.addValue(arrayRefused[i], "Refused", i);
				dataset.addValue(arrayQueue[i], "Queued", i);
				}
			}		
		return dataset;
	}
	
	private void startImage() throws IOException {
		pnlCenter.removeAll();
		BufferedImage img = ImageIO.read(new File(IMG_PATH));
        ImageIcon icon = new ImageIcon(img);
        JLabel image = new JLabel(icon);        
        pnlCenter.add(image, BorderLayout.CENTER);
        repaint();
        this.setVisible(true);
	}
	
	private boolean updateCustom() {
		allValid = true;
		this.rNGSeed = getAndCheckInt(fieldRNG.getText());
		this.dailyMean = getAndCheckDouble(fieldDailyMean.getText());
		this.queueSize = getAndCheckInt(fieldQueueSize.getText());
		this.cancellation = getAndCheckDouble(fieldCancellation.getText());
		
		this.firstProb = getAndCheckDouble(fieldFirst.getText());
		this.businessProb = getAndCheckDouble(fieldBusiness.getText());
		this.premiumProb = getAndCheckDouble(fieldPremium.getText());
		this.economyProb = getAndCheckDouble(fieldEconomy.getText());
		return allValid;
	}
	
	private Integer getAndCheckInt(String input) {		
		try {
		    return Integer.parseInt(input);
		  } catch (NumberFormatException e) {
			  allValid = false;
			  btnRun.setEnabled(true);
			  JOptionPane.showMessageDialog(null, "Invalid input, must be of type Integer and greater then 0");
		    return null;
		  }
	}
	
	private Double getAndCheckDouble(String input) {
		try {
		    return Double.parseDouble(input);
		  } catch (NumberFormatException e) {
			  allValid = false;
			  btnRun.setEnabled(true);
			  JOptionPane.showMessageDialog(null, "Invalid input, must be of type Double and greater then 0");
		    return null;
		  }
	}
	
	public void actionPerformed(ActionEvent e) {
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		createGUI();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("Aircraft Booking"));
	}

}
