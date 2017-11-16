package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
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
    Frame frame;
    String studnr;


    public void testStudnr(){
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

    @FXML
    public void nextbuttonpressed(ActionEvent actionEvent)
    {
        if (TXTstudentnummer.getText().length() == 0)
        {
            return;
        }

        if (!moveOn) {
            firstClick = System.currentTimeMillis();
            studnr = TXTstudentnummer.getText();
            testStudnr();

        }
        else {
            if (studnr.equals(TXTstudentnummer.getText())){
                testStudnr();

            }else{
            long secondClick = System.currentTimeMillis();
            long totalClickTime = secondClick - firstClick;
            String Age = String.valueOf(age.getValue());
            String Sex = String.valueOf(sex.getValue());

            Object[] possibilities = {"1", "2", "3", "4", "5"};
            String satisfied = (String) JOptionPane.showInputDialog(frame,
                    "Hvordan oppfattet du å bli rettet på?\n" + "På en skala fra 1 til 5 der 1 er Veldig Irriterende\n"
                            + "og 5 er Veldig Hjelpsom.", "Hvordan oppfattet du å bli rettet på?", JOptionPane.PLAIN_MESSAGE, null,
                    possibilities,
                    "3");

            String writeToFile = String.valueOf(totalClickTime + "|" + testMethod + "|" + Age + "|" + Sex + "|" + satisfied);
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
}
