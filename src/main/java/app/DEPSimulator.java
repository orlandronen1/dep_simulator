package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The application that launches the simulator
 * 
 * @author Ronen Orland
 */
public class DEPSimulator extends Application
{
    static MainWindowController controller;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            controller = new MainWindowController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
