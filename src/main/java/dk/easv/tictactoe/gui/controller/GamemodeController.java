package dk.easv.tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GamemodeController {
    @FXML
    private ChoiceBox<String> modeBox;

    public void openWindow(String mode) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+ mode +"View.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe | "+ mode +" Mode");
        stage.setResizable(false);
        stage.show();
    }

    public void launchGamemode() throws IOException {
        if (Objects.equals(modeBox.getValue(), "Normal")) {
            openWindow("Normal");
        }
        if (Objects.equals(modeBox.getValue(), "Easy")) {
            openWindow("Easy");
        }
        if (Objects.equals(modeBox.getValue(), "Hardcore")) {
            openWindow("Hardcore");
        }

    }

}
