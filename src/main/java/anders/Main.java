package anders;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import org.apache.poi.ss.formula.functions.Oct2Dec;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.apache.log4j.Logger;


public class Main  extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args){

        //System.out.println("Hello, World!");
        //Main m = new Main();
        StandardServiceRegistryBuilder standardServiceRegistryBuilder =
                new StandardServiceRegistryBuilder();

        StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder
                .configure()
                .build();

        //m.gold_report();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean ok = true;
        //Main.csv_udgave = true;
        logger.trace("Start");

        DAO.openConnection();
        DAO.closeConnection();
        TabPane tabPane = new TabPane();

        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        GridPane grid4 = new GridPane();
        GridPane grid5 = new GridPane();
        GridPane grid6 = new GridPane();
        GridPane grid7 = new GridPane();
        GridPane grid8 = new GridPane();

        Tab tab1 = new Tab("New Nutrient", grid1);
        Tab tab2 = new Tab("Reference"  , grid2);
        Tab tab3 = new Tab("Effect" , grid3);
        Tab tab4 = new Tab("Dose" , grid4);
        Tab tab5 = new Tab("Sellorder" , grid5);
        Tab tab6 = new Tab("EndSellorder" , grid6);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);
        tabPane.getTabs().add(tab6);

        VBox vBox = new VBox(tabPane);
        //Scene scene = new Scene(vBox);
        Scene scene = new Scene(vBox, 500, 675); //x,y
        primaryStage.setScene(scene);



        NewNutrientScreen.startWallet(grid1);
        ReferenceScreen.startReference(grid2);
        EffectScreen.startEffect(grid3);
        DoseScreen.startDose(grid4);

        primaryStage.show();
    }

}