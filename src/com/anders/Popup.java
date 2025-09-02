package anders;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Popup {
    public static ButtonType showError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error.");
        alert.setContentText(error);
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        return result.get();
    }

    public static ButtonType showWarning(String error) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning");
        alert.setContentText(error);
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        return result.get();
    }


    public static ButtonType showConfirmation(String error) {
        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(error);
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        return result.get();
    }
}