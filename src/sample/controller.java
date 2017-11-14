package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller
{
    @FXML private ChoiceBox fakultet;
    @FXML private TextField TXTstudentnummer;
    @FXML private Label wrongPassword;
    @FXML private ChoiceBox sex;
    @FXML private ChoiceBox age;

    boolean moveOn = false;
    long firstClick;
    String testMethod;


    @FXML
    public void nextbuttonpressed(ActionEvent actionEvent)
    {
        if (TXTstudentnummer.getText().length() == 0)
        {
            return;
        }

        if (!moveOn) {
            firstClick = System.currentTimeMillis();
            int lastChar = Integer.parseInt(TXTstudentnummer.getText().substring(TXTstudentnummer.getText().length() - 1, TXTstudentnummer.getText().length()));
            if (lastChar % 2 == 0) {
                if (TXTstudentnummer.getText().toLowerCase().contains("s")) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Skriv inn studentnummer uten 's' foran", ButtonType.OK);
                    alert.showAndWait();

                    testMethod = "Alert";
                    moveOn = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Skriv inn studentnummer med 's' foran", ButtonType.OK);
                    alert.showAndWait();

                    testMethod = "Alert";
                    moveOn = true;
                }
            } else {
                if (TXTstudentnummer.getText().toLowerCase().contains("s")) {
                    wrongPassword.setVisible(true);
                    wrongPassword.setText("Skriv inn studentnummer uten 's' foran");
                    TXTstudentnummer.setStyle("-fx-text-box-border: red ;");

                    testMethod = "Red text";
                    moveOn = true;
                } else {
                    wrongPassword.setVisible(true);
                    wrongPassword.setText("Skriv inn studentnummer med 's' foran");
                    TXTstudentnummer.setStyle("-fx-text-box-border: red ;");

                    testMethod = "Red text";
                    moveOn = true;
                }
            }
        }
        else {
            long secondClick = System.currentTimeMillis();
            long totalClickTime = secondClick - firstClick;
            String Age = String.valueOf(age.getValue());
            String Sex = String.valueOf(sex.getValue());

            String writeToFile = String.valueOf(totalClickTime + "|" + testMethod + "|" + Age + "|" + Sex);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("MMIMappe3.txt", true));
                writer.append("\n");
                writer.append(writeToFile);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not save to file");
            }
            Platform.exit();
        }
    }
}
