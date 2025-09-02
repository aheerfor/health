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

import static anders.EffectScreen.checkCH;

//import static anders.DateTimeUtil.now;

public class ReferenceScreen {
    private static final Logger logger = Logger.getLogger(ReferenceScreen.class.getName());

    public static void startReference(GridPane grid) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        //primaryStage.setTitle("Hello World");

        //GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Scene scene = new Scene(grid, 300, 275);
        //primaryStage.setsetScene(scene);

        Text scenetitle = new Text("New reference");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label titleLabel = new Label("Title:");
        grid.add(titleLabel, 0, 1);
        TextField titleText = new TextField();
        grid.add(titleText, 1, 1);


        Label authorlabel = new Label("Author:");
        grid.add(authorlabel, 0, 2);
        TextField authorText = new TextField();
        grid.add(authorText, 1, 2);

        Label addressLabel = new Label("Webaddress:");
        grid.add(addressLabel, 0, 3);
        TextField addressText = new TextField();
        grid.add(addressText, 1, 3);

        Button btn = new Button("Save");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        authorText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(authorText,45);
            }

            });
        titleText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(titleText,100);
            }

        });
        addressText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkCH(addressText,100);
            }

        });
        btn.setOnAction(new EventHandler<ActionEvent>() { //SAVE

            @Override
            public void handle(ActionEvent e) {
                int pos = 0;
                try {
                    btn.setDisable(true);
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
                    String title = titleText.getText().trim();
                    pos = 4;
                    String author = authorText.getText().trim();
                    String webaddress = addressText.getText().trim();

                    checkCH(authorText,45);
                    checkCH(titleText,100);
                    checkCH(addressText,100);
                    pos = 7;
                    Reference w2 = new Reference();
                    w2.webaddress = webaddress;
                    w2.author = author;
                    if (w2.author.length()>45) {
                        w2.author = w2.author.substring(0,45);
                    }
                    w2.title = title;
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
                    addressText.clear();
                    authorText.clear();
                    titleText.clear();
                } catch(Exception ex) {
                    logger.trace("StartWallet-hndle "+ex+ " pos="+pos);
                    Breaker.breaker();
                }
                btn.setDisable(false);

            }
        });


        //StackPane root = new StackPane();
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }

}
