package application.controllers;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntryController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void startSingleplayerGame(ActionEvent event) {
		startGame(event, "Single");
	}
	
	public void startMultiplayerGame(ActionEvent event) {
		startGame(event, "Multi");
	}
	
	private void startGame(ActionEvent event, String mode) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/GameBoard.fxml"));
			
			ObservableList<Node> list = root.getChildrenUnmodifiable();
			list.get(list.size() - 1).setId(mode);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
