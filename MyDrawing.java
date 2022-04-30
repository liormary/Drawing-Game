/*******************************************************************
Connect the FXML file to the code
Lior Mary 
*********************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyDrawing extends Application{ 

	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("draw.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("draw"); 
		stage.setScene(scene); 
		stage.show(); 
	} 

	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	} 
}
