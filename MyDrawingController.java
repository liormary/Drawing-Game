/*******************************************************************
Drawing game
Lior Mary 
*********************************************************************/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class MyDrawingController {

	@FXML
	private Pane drawing;

	@FXML
	private ToggleGroup g1;

	@FXML
	private ToggleGroup g2;

	@FXML
	private ToggleGroup g3;

	@FXML
	private RadioButton line;

	@FXML
	private RadioButton circle;

	@FXML
	private RadioButton rectangle;

	@FXML
	private RadioButton red;

	@FXML
	private RadioButton yellow;

	@FXML
	private RadioButton blue;

	@FXML
	private RadioButton green;

	@FXML
	private RadioButton no;

	@FXML
	private RadioButton yes;
	
	private Color colorPicked;

	private boolean fillPicked;
	
	private int shapePicked;
	
	private double mousePressedX = 0;
	
	private double mousePressedY = 0;
	
	private double radius = 0;
	
	//reset all the values before the game starts
	public void initialize() {
		colorPicked = null;
		fillPicked = false;
		shapePicked= 0;
		yes.setUserData(true);
		no.setUserData(false);
		yellow.setUserData(Color.YELLOW);
		blue.setUserData(Color.BLUE);
		red.setUserData(Color.RED);
		green.setUserData(Color.GREEN);
		line.setUserData(1);
		circle.setUserData(2);
		rectangle.setUserData(3);
	}

	//Save the mouse press location
	@FXML
    void mousePressed(MouseEvent event) {
		mousePressedX = event.getX();
		mousePressedY = event.getY();
    }
	
	//Create the shapes on the pane
	@FXML
	void mouseReleased(MouseEvent event) {
		shapePicked = (int) g1.getSelectedToggle().getUserData(); //save the shape that the user picked
		colorPicked = (Color) g2.getSelectedToggle().getUserData(); //save the color that the user picked
		fillPicked = (boolean) g3.getSelectedToggle().getUserData(); //save if the user wants fill or not
		
		if (shapePicked == 1) { // if the user pick line
			Line ln = new Line(mousePressedX, mousePressedY, event.getX(), event.getY());
			ln.setStroke(colorPicked);
			drawing.getChildren().add(ln);
		}
		else if (shapePicked == 2) { //if the user pick circle
			radius = Math.sqrt(Math.pow(mousePressedX - event.getX(), 2) + 
					                    Math.pow(mousePressedY - event.getY(), 2));
			Circle ci = new Circle(event.getX(), event.getY(), radius, colorPicked);
			ci.setStroke(colorPicked);
			if (!fillPicked) { //without fill
				ci.setFill(Color.TRANSPARENT);
			}
			drawing.getChildren().add(ci);

		}
		else if (shapePicked == 3) { //if the user pick rectangle
			Rectangle rect = new Rectangle(event.getX(), event.getY(), 
					  Math.abs(event.getX()- mousePressedX), Math.abs(event.getY()- mousePressedY));
			rect.setStroke(colorPicked);
			if (!fillPicked) { //without fill
				rect.setFill(Color.TRANSPARENT);
			}
			else {
				rect.setFill(colorPicked); //with fill
			}
			drawing.getChildren().add(rect);
		}
	}
	
	//clear all the shapes in the pane
	@FXML
	void clearPressed(ActionEvent event) {
		drawing.getChildren().clear();
	}

	//remove the last shape that the user add
	@FXML
	void undoPressed(ActionEvent event) {
		int count = drawing.getChildren().size();
		if (count > 0) {
			drawing.getChildren().remove(count-1);
		}
		
	}

}
