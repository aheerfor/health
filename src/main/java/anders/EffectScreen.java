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

public class EffectScreen {
    public  static TextField referenceText = new TextField();;
    public  static TextField causeText = new TextField();
    private static final Logger logger = Logger.getLogger(EffectScreen.class.getName());

    public static void startEffect(GridPane grid) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        //primaryStage.setTitle("Hello World");

        //GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Scene scene = new Scene(grid, 300, 275);
        //primaryStage.setsetScene(scene);

        Text scenetitle = new Text("Effect");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label causeLabel = new Label("Cause:");
        grid.add(causeLabel, 0, 1);
        grid.add(causeText, 1, 1);

        Label relationLabel = new Label("Relation:");
        grid.add(relationLabel, 0, 2);
        TextField relationText = new TextField();
        grid.add(relationText, 1, 2);

        Label effectlabel = new Label("Effect:");
        grid.add(effectlabel, 0, 3);
        TextField effectText = new TextField();
        grid.add(effectText, 1, 3);

        Label referenceLabel = new Label("Reference:");
        grid.add(referenceLabel, 0, 4);
        grid.add(referenceText, 1, 4);

        Button btn = new Button("Save");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 5);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        causeText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(causeText, Effect.effect_size);
            }

        });
        relationText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(relationText, Effect.relation_size);
            }

        });
        effectText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(effectText, Effect.effect_size);
            }

        });
        referenceText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(referenceText, Effect.reference_size);
            }

        });



        btn.setOnAction(new EventHandler<ActionEvent>() { //SAVE

            @Override
            public void handle(ActionEvent e) {
                int pos = 0;
                boolean too_long = false;
                logger.error("BTN 1");
                btn.setDisable(true);
                try {
                    too_long |= checkCH(causeText,Effect.cause_size);
                    too_long |= checkCH(effectText,Effect.effect_size);
                    too_long |= checkCH(relationText, Effect.relation_size);
                    too_long |= checkCH(referenceText, Effect.reference_size);
                    if (too_long) {
                        btn.setDisable(false);
                        return;
                    }

                    if (!DAO.openConnection()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error.");
                        alert.setContentText("Transaction mismatch-2");
                        alert.showAndWait();
                        //actiontarget.setText("ERROR: same wallet!!");
                        btn.setDisable(false);
                        return;
                    }
                    logger.error("BTN 2");
                    pos = 1;
                    actiontarget.setText("---");
                    pos = 2;
                    //String coinname = coinBox.getText().trim();
                    pos = 3;
                    String cause = causeText.getText().trim();
                    pos = 4;
                    String effect = effectText.getText().trim();
                    String relation = relationText.getText().trim();
                    String reference = referenceText.getText().trim();


                    pos = 7;
                    Effect w2 = new Effect();
                    w2.cause = cause;
                    w2.effect = effect;
                    w2.relation = relation;
                    w2.reference = reference;
                    logger.error("BTN 3");
                    w2.save();
                    logger.error("BTN 4");


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
                        btn.setDisable(false);
                        return;
                    }
                    logger.error("BTN 5");
                    pos = 27;
                    //actiontarget.setFill(Color.FIREBRICK);
                    //actiontarget.setText("Saved1");
                    //causeText.clear();
                    effectText.clear();
                    relationText.clear();
                    /*
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Saved");
                    alert.setHeaderText("Saved.");
                    alert.setContentText("Effect saved");
                    alert.showAndWait();

                     */
                } catch(Exception ex) {
                    logger.trace("StartWallet-hndle "+ex+ " pos="+pos);
                    Breaker.breaker();
                }
                logger.error("BTN 6");
                btn.setDisable(false);

            }
        });


        //StackPane root = new StackPane();
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }

    public static boolean checkCH(TextField tf, int size) {
        boolean ret = false;
        String s = tf.getText().trim();
        if (s.startsWith("CH")) {
            logger.error("<"+s+ ">");
            Breaker.breaker();
            s = s.substring(2).trim();
            tf.setText(s);

        }
        if (s.length() > size) {
            Popup.showError("Long "+s);
            ret = true;
        }
        return ret;
    }

}
