package app;

/**
 * The controller class for the main window of the application. Handles user inputs and 
 * contains the camera.
 * 
 * @author Ronen Orland
 */
import java.net.URL;
import java.util.ResourceBundle;

import field.Electrode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import medium.Medium;
import particles.Particle;

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

    private Simulation simulation;              // The simulation
    private double speedMultiplier = 1;         // The simulation speed multiplier
    
    
    void updateElectrode(ActionEvent event)
    {
        String choice = electrodeChoiceBox.getValue();
        
        try
        {
            Class<?> type = Class.forName(choice);
            Electrode config = (Electrode) type.newInstance();
            simulation.setElectrode(config);
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
            Class<?> type = Class.forName(choice);
            Medium med = (Medium) type.newInstance();
            simulation.setMedium(med);
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
        simulation.reset();
    }
    
    
    void addParticle(ActionEvent event)
    {
        String choice = electrodeChoiceBox.getValue();
        
        try
        {
            Class<?> type = Class.forName(choice);
            Particle particle = (Particle) type.newInstance();
            simulation.addParticle(particle);
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
            simulation.setFrequency(frequency);
            
            frequencyText.setText(frequencyInput.getText() + "Hz");
        }
        catch (Exception e)
        {
            // Don't update the frequency if bad input
        }
    }
    
    
    void updateVoltage(ActionEvent event)
    {
        double voltage;
        
        try
        {
            voltage = Double.parseDouble(voltageInput.getText());
            simulation.setVoltage(voltage);
            
            frequencyText.setText(frequencyInput.getText() + "V");
        }
        catch (Exception e)
        {
            // Don't update the voltage if bad input
        }
    }
    
    /**
     * Starts the simulation
     * 
     * @param event the pressing of the play button
     */
    void play(ActionEvent event)
    {
        simulation.play();
    }
    
    /**
     * Pauses the simulation
     * 
     * @param event the pressing of the pause button
     */
    void pause(ActionEvent event)
    {
        simulation.pause();
    }
    
    /**
     * Stops the simulation. Resets particles to their initial positions and the simulation
     * speed to real time.
     * 
     * @param event the pressing of the stop button
     */
    void stop(ActionEvent event)
    {
        simulation.stop();
        
        speedMultiplier = 1;
        simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
        speedText.setText(speedMultiplier + "x");
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
            simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
            speedText.setText(speedMultiplier + "x");
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
            simulation.setDeltaTime(Simulation.DEFAULT_TIME_STEP * speedMultiplier);
            speedText.setText(speedMultiplier + "x");
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
        
        // initialize choicebuttons with their selections -> read appropriate folders and use the class names as strings
        
        // initialize simulation's lower and upper bounds
    }
}
