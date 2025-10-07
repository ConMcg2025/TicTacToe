package dk.easv.tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GamemodeController {
    @FXML
    private ChoiceBox<String> modeBox;

    public void launchGamemode() throws IOException {
        if (Objects.equals(modeBox.getValue(), "Normal")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicTacView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe | Normal Mode");
            stage.setResizable(false);
            stage.show();
        }else{
            System.out.println("failed");
        }

    }

}
