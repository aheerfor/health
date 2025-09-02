package anders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;

//import static anders.DateTimeUtil.now;

public class NewNutrientScreen {
    private static final Logger logger = Logger.getLogger(NewNutrientScreen.class.getName());

    public static void startWallet(GridPane grid) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        //primaryStage.setTitle("Hello World");

        //GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Scene scene = new Scene(grid, 300, 275);
        //primaryStage.setsetScene(scene);

        Text scenetitle = new Text("New nutrient");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label walletName = new Label("Nutrient name:");
        grid.add(walletName, 0, 1);
        TextField walletnameTextField = new TextField();
        grid.add(walletnameTextField, 1, 1);


        Label doselabel = new Label("Daily grams:");
        grid.add(doselabel, 0, 2);
        TextField doseText = new TextField();
        grid.add(doseText, 1, 2);
/*
        Label address = new Label("Address:");
        grid.add(address, 0, 3);
        TextField addressBox = new TextField();
        grid.add(addressBox, 1, 3);
*/
        Button btn = new Button("Save");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() { //SAVE

            @Override
            public void handle(ActionEvent e) {
                int pos = 0;
                try {

                    if (!DAO.openConnection()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error.");
                        alert.setContentText("Transaction mismatch-2");
                        alert.showAndWait();
                        //actiontarget.setText("ERROR: same wallet!!");
                        return;
                    }
                    pos = 1;
                    actiontarget.setText("---");
                    pos = 2;
                    //String coinname = coinBox.getText().trim();
                    pos = 3;
                    String walletname = walletnameTextField.getText().trim();
                    pos = 4;
                    String nutrientName = walletnameTextField.getText().trim();
                    Nutrient w1 = NutrientDAO.getByName(nutrientName);
                    pos = 5;
                    if (w1 != null) {
                        pos = 6;
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error.");
                        alert.setContentText("Wallet exists");
                        alert.showAndWait();
                        DAO.closeConnection();
                        return;
                    }

                    pos = 7;
                    Nutrient w2 = new Nutrient();
                    w2.name = nutrientName;
                    w2.dailygrams = DateTimeUtil.TextFieldToDouble(doseText);
                    w2.save();


                    //double balance = 0;//Double.valueOf(balanceText.getText());
                    pos = 9;
                    pos = 11;
                    double dkkvalue = 0;
                    int coinid = 0;
                    if (!DAO.closeConnection()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error.");
                        alert.setContentText("Transaction mismatch-2B");
                        alert.showAndWait();
                        //actiontarget.setText("ERROR: same wallet!!");
                        return;
                    }
                    pos = 27;
                    //actiontarget.setFill(Color.FIREBRICK);
                    //actiontarget.setText("Saved1");
                    walletnameTextField.clear();
                    doseText.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Saved");
                    alert.setHeaderText("Saved.");
                    alert.setContentText("Nutrient saved");
                    alert.showAndWait();
                } catch(Exception ex) {
                    logger.trace("StartWallet-hndle "+ex+ " pos="+pos);
                    Breaker.breaker();
                }
            }
        });


        //StackPane root = new StackPane();
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }

}
