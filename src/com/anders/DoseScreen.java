package com.anders;

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

import static com.anders.EffectScreen.checkCH;

//import static anders.DateTimeUtil.now;

public class DoseScreen {
    private static final Logger logger = Logger.getLogger(DoseScreen.class.getName());

    public static void startDose(GridPane grid) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        //primaryStage.setTitle("Hello World");

        //GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Scene scene = new Scene(grid, 300, 275);
        //primaryStage.setsetScene(scene);

        Text scenetitle = new Text("New dose");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label nutrientLabel = new Label("Nutrient:");
        grid.add(nutrientLabel, 0, 1);
        TextField nutrientText = new TextField();
        grid.add(nutrientText, 1, 1);


        Label commentLabel = new Label("Comment:");
        grid.add(commentLabel, 0, 2);
        TextField commentText = new TextField();
        grid.add(commentText, 1, 2);

        Label minLabel = new Label("Min gram:");
        grid.add(minLabel, 0, 3);
        TextField minText = new TextField();
        grid.add(minText, 1, 3);

        Label maxLabel = new Label("Max gram:");
        grid.add(maxLabel, 0, 4);
        TextField maxText = new TextField();
        grid.add(maxText, 1, 4);

        Label refLabel = new Label("Reference:");
        grid.add(refLabel, 0, 5);
        TextField refText = new TextField();
        grid.add(refText, 1, 5);

        Button btn = new Button("Save");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        nutrientText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(nutrientText, 45);
            }

        });

        commentText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(commentText,45);
            }

        });

        minText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(minText, 45);
            }

        });

        maxText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(maxText,45);
            }

        });

        refText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(refText, 100);
            }

        });

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
                    String nutrient = nutrientText.getText().trim();
                    pos = 4;
                    String comment = commentText.getText().trim();

                    String ref = refText.getText().trim();

                    Double min = DateTimeUtil.TextFieldToDouble(minText);
                    Double max = DateTimeUtil.TextFieldToDouble(maxText);


                    pos = 7;
                    Dose w2 = new Dose();
                    w2.nutrient = nutrient;
                    w2.comment = comment;
                    w2.min = min;
                    w2.max = max;
                    w2.reference = ref;
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
                    nutrientText.clear();
                    commentText.clear();
                    minText.clear();
                    maxText.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Saved");
                    alert.setHeaderText("Saved.");
                    alert.setContentText("Dose saved");
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
