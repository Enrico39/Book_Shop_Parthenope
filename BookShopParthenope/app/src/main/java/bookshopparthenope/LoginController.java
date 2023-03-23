package bookshopparthenope;

import bookshopparthenope.Model.UserManagement.Admin;
import bookshopparthenope.Model.UserManagement.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Questo codice Java definisce una classe chiamata LoginController che implementa l'interfaccia Initializable.
 * Il controller è responsabile della gestione degli eventi generati dall'interfaccia utente.
 */
public class LoginController  implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<String> loginScelta;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink signupButton;

    @FXML
    private Text wrongLogIn;

    @FXML
    private TextField username;

    public static String userCart;

    /**
     * Il metodo switchToSignup viene chiamato quando viene premuto il pulsante di registrazione.
     * Questo metodo carica una nuova interfaccia utente che consente all'utente di registrarsi.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToSignup(ActionEvent event) throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/registrazione.fxml"));
        Stage window = (Stage) signupButton.getScene().getWindow();
        window.setScene(new Scene(root,580,580));
    }


    private String[] adminOrCostumerChoiceBox = {"Admin","Cliente"};

    FxmlController controller=new FxmlController();

    /**
     * Il metodo validateLogin è chiamato quando viene premuto il pulsante di login. Questo metodo verifica se l'utente ha inserito un nome utente e una password validi e selezionato un tipo di utente dalla combobox.
     * A seconda del tipo di utente selezionato, viene creato un oggetto Admin o Customer e viene effettuato il login.
     * Se il login è riuscito, viene caricata una nuova interfaccia utente. In caso contrario, viene visualizzato un messaggio di errore.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void validateLogin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        String uname = username.getText();
        String upass = password.getText();
        if (uname.equals("") || upass.equals("")) {
            wrongLogIn.setText("Inserisci tutte le credenziali.");
            wrongLogIn.setVisible(true);
        }
        else {
            String tipoUtente=loginScelta.getValue();
            if(tipoUtente== null) {
                wrongLogIn.setText("Seleziona tipo utente.");
                wrongLogIn.setVisible(true);
            }else{
                switch (tipoUtente) {
                    case ("Admin") -> {
                        Admin admin = new Admin(username.getText(), password.getText());
                        if (admin.logIn()) {
                            tipoUtente = "/bookshopparthenope/gui/adminpanel.fxml";
                        } else {
                            tipoUtente = "";
                        }
                    }
                    case ("Cliente") -> {
                        Customer customer = new Customer(username.getText(), password.getText());
                        if (customer.logIn()) {
                            userCart = username.getText();
                            tipoUtente = "/bookshopparthenope/gui/home.fxml";
                        } else {
                            tipoUtente = "";
                        }
                    }
                    default -> {
                        wrongLogIn.setText("Seleziona tipo utente");
                        wrongLogIn.setVisible(true);
                    }
                }
                if (tipoUtente.equals("")){
                    wrongLogIn.setText("Credenziali errate");
                    wrongLogIn.setVisible(true);
                }
                else{
                    controller.effettuaLogin(root,stage,scene,event,tipoUtente);
                } }
        }
    }


    /**
     * Il metodo initialize viene chiamato quando il controller viene creato. In questo metodo, viene inizializzata una combobox per la scelta del tipo di utente.
     * @param arg0
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param arg1
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        loginScelta.getItems().addAll(adminOrCostumerChoiceBox);
        loginScelta.setOnAction(this::getChoice);

    }


    /**
     * Il metodo getChoice viene chiamato quando l'utente seleziona una voce dalla combobox loginScelta. In ogni caso.
     * @param event
     */
    public void getChoice(ActionEvent event) {
        String adminOrCostumerChoiceBox = loginScelta.getValue();
    }


}
