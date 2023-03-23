package bookshopparthenope;



import bookshopparthenope.Model.UserManagement.Customer;
import bookshopparthenope.Model.LoginRegisterManagementDAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Questo è il codice di un controller Java per una schermata di registrazione del negozio di libri (BookShopParthenope).
 * Il controller gestisce la logica di registrazione dell'utente. Ha una serie di campi di input, come il nome, il cognome, l'email, l'username e la password.
 * L'utente deve inserire tutte queste informazioni correttamente per poter registrarsi.
 * In caso di errori nell'input, il controller mostra un messaggio di errore appropriato per aiutare l'utente a correggere i problemi e registrarsi correttamente.
 * Il controller utilizza JavaFX per la gestione della GUI e per la gestione degli eventi.
 * La schermata di registrazione è rappresentata da un file FXML associato a questo controller.
 */
public class RegisterController{


    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerCognome;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerNome;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerUsername;

    @FXML
    private Label wrongSignup;
    FxmlController controller=new FxmlController();

    /**
     * Se l'utente inserisce informazioni corrette, il controller crea un nuovo oggetto Customer e lo passa al metodo UserDAO.insertNewCustomer() per salvare l'utente nel database.
     * Se l'operazione va a buon fine, il controller mostra un messaggio di conferma e passa l'utente alla schermata di login.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws IOException
     */
    @FXML
    void registerUser(ActionEvent event) throws SQLException, ClassNotFoundException, InterruptedException, IOException {

        if (registerPassword.getText().length() < 5) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Password troppo breve");
        }else if (registerNome.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un nome valido");
        } else if (registerCognome.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un cognome valido");
        } else if (!registerEmail.getText().contains("@")) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci una mail valida");
        } else if (registerUsername.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un username valido");
        }else {
            Customer cliente = new Customer(registerUsername.getText(), registerPassword.getText());
            cliente.setEmail(registerEmail.getText());
            cliente.setSurname(registerCognome.getText());
            cliente.setName(registerNome.getText());
            if(UserDAO.insertNewCustomer(cliente)){
                switchToLogin(event);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Esito Registrazione");
                alert.setHeaderText(cliente.getUsername()+", registrazione completata con successo!");
                alert.setContentText("Effettua il Log-in per accedere al catalogo.");
                alert.showAndWait();
            }else {
                wrongSignup.setVisible(true);
                wrongSignup.setText("Username non disponibile.");
            }


        }

    }

    /**
     * La funzione switchToLogin viene chiamata quando l'utente preme il pulsante "loginButton".
     * Essa invoca un metodo "login" definito nella classe FxmlController, a cui passa come parametri il nodo radice dell'interfaccia utente e il riferimento al pulsante che è stato premuto.
     * Il metodo "login" del FxmlController si occupa di caricare la schermata di login e di passare alla nuova schermata il riferimento al pulsante che è stato premuto.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        controller.login(root,loginButton);
    }



}
