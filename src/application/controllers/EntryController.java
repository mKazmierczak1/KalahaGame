package application.controllers;

import java.io.IOException;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/GameScene.fxml"));
			root = loader.load();
			
			GameSceneController controller = loader.getController();
			controller.gameInit(6);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
