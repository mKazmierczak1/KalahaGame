package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.model.GameBoard;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameSceneController implements Initializable {
	
	private int turn;
	private ObservableList<Node> list;
	private GameBoard gameBoard; 
	
	@FXML
	private GridPane board;
	@FXML
	private Label turnLabel;
	
	
	public void gameInit(int numberOfStones) {
		gameBoard = new GameBoard(numberOfStones);
		updateBoard();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = board.getChildren();
		turn = 0;	
	}
	
	public void makeMove(MouseEvent event) {
		boolean wrongHole = false;
		Label hole = (Label)event.getSource();
		String nr = hole.getId().substring(4);
		int holeNumber = Integer.parseInt(nr);
		
		if (turn == 0 && holeNumber >= 0 && holeNumber < 6) {
			if (gameBoard.makeMove(holeNumber + 1) != 1) {
				turn = 1;
				turnLabel.setText("Turn: Player " + (turn + 1));
			}
				
		}
		else if (turn == 1 && holeNumber >= 7 && holeNumber < 13)  {
			if (gameBoard.makeMove(holeNumber - 6) != 1) {
				turn = 0;
				turnLabel.setText("Turn: Player " + (turn + 1));
			}
				
		}
		else
			wrongHole = true;
			
		if (!wrongHole) {
			updateBoard();
			checkIfEnd(event, false);
		}	
	}
	
	public void mouseOnHole(MouseEvent event) {
		
	}
	
	private void updateBoard() {
		int [] tab = gameBoard.getBoardCopy();
		
		for (int i = 0; i < tab.length; i++) {
			Label hole = (Label)list.get(i + 14);
			hole.setText(Integer.toString(tab[i]));
		}
	}
	
	private boolean checkIfEnd(MouseEvent event, boolean ifAI) {
		if (gameBoard.ifEndOfGame()) {
			showInfoDialog(event, gameBoard.getResults());
			return true;
		}
		
		return false;
	}
	
	private void showInfoDialog(MouseEvent event, String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Result");
		alert.setHeaderText(null);
		alert.setContentText(info);

		alert.showAndWait();
		backToMenu(event);
	}
	
	private void backToMenu(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/Entry.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
