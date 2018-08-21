package app;

import java.io.File;
/**
 * The controller class for the main window of the application. Handles user inputs and 
 * contains the camera.
 * 
 * @author Ronen Orland
 */
import java.net.URL;
import java.util.ResourceBundle;

import field.*;
import medium.*;
import particles.Particle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainWindowController {

    final static double SPEED_MIN = 0.25;           // Minimum speed multiplier of simulation
    final static double SPEED_MAX = 8;              // Maximum speed multiplier of simulation
    
    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private ChoiceBox<String> electrodeChoiceBox; // Chooses the electrode configuration
    @FXML private Button electrodeUpdateButton;         // Updates the electrode configuration
    
    @FXML private ChoiceBox<String> mediumChoiceBox;    // Chooses the medium
    @FXML private Button mediumUpdateButton;            // Updates the medium
    
    @FXML private ChoiceBox<String> particlesChoiceBox; // Chooses the particle to add
    @FXML private Button resetParticlesButton;          // Removes the particles in the simulation
    @FXML private Button addParticleButton;             // Adds the currently selected particle to the simulation

    @FXML private TextField frequencyInput;             // User input for frequency
    @FXML private Button frequencyUpdateButton;         // Updates the frequency to the user's current input
    @FXML private Text frequencyText;                   // The text displaying the current frequency

    @FXML private TextField voltageInput;       // User input for voltage
    @FXML private Button voltageUpdateButton;   // Updates the voltage to the user's current input
    @FXML private Text voltageText;             // The text displaying the current frequency
    
    @FXML private Button playButton;            // Starts/resumes the simulation
    @FXML private Button pauseButton;           // Pauses the simulation
    @FXML private Button stopButton;            // Stops the simulation, resetting particles to their initial positions

    @FXML private Button slowerButton;          // Decreases the simulation speed
    @FXML private Button fasterButton;          // Increases the simulation speed
    @FXML private Text speedText;               // Text that displays the simulation speed rate

//    private Simulation simulation;              // The simulation
    private double speedMultiplier = 1;         // The simulation speed multiplier
    
    
    void updateElectrode(ActionEvent event)
    {
        String choice = electrodeChoiceBox.getValue();
        
        try
        {
            Class<?> type = Class.forName("field." + choice);
            Electrode config = (Electrode) type.newInstance();
            Simulation.setElectrode(config);
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    
    void updateMedium(ActionEvent event)
    {
        String choice = mediumChoiceBox.getValue();
        
        try
        {
            Class<?> type = Class.forName("medium." + choice);
            Medium med = (Medium) type.newInstance();
            Simulation.setMedium(med);
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    
    void resetParticles(ActionEvent event)
    {
        Simulation.reset();
    }
    
    
    void addParticle(ActionEvent event)
    {
        String choice = particlesChoiceBox.getValue();
        
        try
        {
            Class<?> type = Class.forName("particles." + choice);
            Particle particle = (Particle) type.newInstance();
            Simulation.addParticle(particle);
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    
    void updateFrequency(ActionEvent event)
    {
        double frequency;
        
        try
        {
            frequency = Double.parseDouble(frequencyInput.getText());
            Simulation.setFrequency(frequency);
            
            frequencyText.setText(frequencyInput.getText() + "Hz");
            System.err.println("frequency updated");
        }
        catch (Exception e)
        {
            // Don't update the frequency if bad input
            System.err.println("frequency not updated");
        }
    }
    
    
    void updateVoltage(ActionEvent event)
    {
        double voltage;
        
        try
        {
            voltage = Double.parseDouble(voltageInput.getText());
            Simulation.setVoltage(voltage);
            
            voltageText.setText(voltageInput.getText() + "V");
            System.err.println("voltage updated");
        }
        catch (Exception e)
        {
            // Don't update the voltage if bad input
            System.err.println("voltage not updated");
        }
    }
    
    /**
     * Starts the simulation
     * 
     * @param event the pressing of the play button
     */
    void play(ActionEvent event)
    {
        Simulation.play();

        System.err.println("playing");
    }
    
    /**
     * Pauses the simulation
     * 
     * @param event the pressing of the pause button
     */
    void pause(ActionEvent event)
    {
        Simulation.pause();
        System.err.println("pausing");
    }
    
    /**
     * Stops the simulation. Resets particles to their initial positions and the simulation
     * speed to real time.
     * 
     * @param event the pressing of the stop button
     */
    void stop(ActionEvent event)
    {
        Simulation.stop();
        
        speedMultiplier = 1;
        Simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
        speedText.setText(speedMultiplier + "x");
        System.err.println("stopping");
    }
    
    /**
     * Slows down the simulation speed
     * 
     * @param event the pressing of the slow down button
     */
    void slower(ActionEvent event)
    {
        if (speedMultiplier > SPEED_MIN)
        {
            speedMultiplier /= 2;
            Simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
            speedText.setText(speedMultiplier + "x");
            System.err.println("slowing");
        }
    }
    
    /**
     * Speeds up the simulation speed
     * 
     * @param event the pressing of the speed up button
     */
    void faster(ActionEvent event)
    {
        if (speedMultiplier < SPEED_MAX)
        {
            speedMultiplier *= 2;
            Simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
            speedText.setText(speedMultiplier + "x");
            System.err.println("speeding");
        }
    }
    
    
    @FXML
    void initialize() 
    {
        electrodeUpdateButton.setOnAction(this::updateElectrode);
        mediumUpdateButton.setOnAction(this::updateMedium);
        resetParticlesButton.setOnAction(this::resetParticles);
        addParticleButton.setOnAction(this::addParticle);
        frequencyUpdateButton.setOnAction(this::updateFrequency);
        voltageUpdateButton.setOnAction(this::updateVoltage);
        playButton.setOnAction(this::play);
        pauseButton.setOnAction(this::pause);
        stopButton.setOnAction(this::stop);
        slowerButton.setOnAction(this::slower);
        fasterButton.setOnAction(this::faster);
        
//        simulation = new Simulation();
        // Initialize choicebuttons with their selections -> read appropriate folders and use the class names as strings
        initializeChoiceBoxes();
        Simulation.main();
    }
    
    
    private void initializeChoiceBoxes()
    {
        File folder;
        String[] files;
        int cut;
        
        // Electrodes
        folder = new File("src/main/java/field");
        files = folder.list();
        ObservableList<String> electrodes = electrodeChoiceBox.getItems();
        
        for (String file : files)
        {
            cut = file.indexOf(".");
            file = file.substring(0, cut);
            
            if (file.equals("Electrode"))
                continue;
            
            electrodes.add(file);
        }
        electrodeChoiceBox.setItems(electrodes);
        
        // Mediums
        folder = new File("src/main/java/medium");
        files = folder.list();
        ObservableList<String> mediums = mediumChoiceBox.getItems();
        
        for (String file : files)
        {
            cut = file.indexOf(".");
            file = file.substring(0, cut);
            
            if (file.equals("Medium"))
                continue;
            
            mediums.add(file);
        }
        mediumChoiceBox.setItems(mediums);
        
        // Particles
        folder = new File("src/main/java/particles");
        files = folder.list();
        ObservableList<String> particles = particlesChoiceBox.getItems();
        
        for (String file : files)
        {
            cut = file.indexOf(".");
            file = file.substring(0, cut);
            
            if (file.equals("Particle") || file.equals("TestParticle"))
                continue;
            
            particles.add(file);
        }
        particlesChoiceBox.setItems(particles);
    }
}
