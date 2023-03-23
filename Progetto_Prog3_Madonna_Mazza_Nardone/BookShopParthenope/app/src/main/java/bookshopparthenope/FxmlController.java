package bookshopparthenope;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Questa classe rappresenta un controller per la gestione dei file FXML dell'applicazione "Book Shop Parthenope". Contiene metodi che permettono di effettuare il logout, la visualizzazione del profilo, l'effettuazione del login, la visualizzazione della home, del carrello, l'inserimento di un nuovo libro, la visualizzazione delle informazioni di pagamento, la visualizzazione dell'elenco degli ordini e l'accesso al pannello di amministrazione. Inoltre, alcuni metodi si occupano di impostare la scena e lo stage per la visualizzazione dei file FXML.
 */
public class FxmlController {

    /**
     * questo metodo effettua il logout dell'utente e lo riporta alla schermata di login. Imposta inoltre una finestra di dialogo di tipo Alert per notificare all'utente che il logout è stato effettuato con successo.
     * @param root
     * @param actionButton
     * @throws IOException
     */
    public void logout(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/login.fxml"));
        Stage window = (Stage) actionButton.getScene().getWindow();
        window.setScene(new Scene(root,650,450));
        window.centerOnScreen();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Log-out effettuato!");
        alert.showAndWait();
    }


    /**
     * questo metodo permette di visualizzare la schermata del profilo dell'utente. Riceve in input un oggetto Parent che rappresenta la radice della gerarchia dei nodi dell'interfaccia grafica e un evento ActionEvent. In base all'evento passato, viene recuperata la finestra corrente tramite il metodo getSource() e impostata la scena per visualizzare la schermata del profilo.
     * @param root
     * @param event
     * @throws IOException
     */
    public void profile(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/profilo.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * questo metodo permette di visualizzare la schermata di login dell'applicazione. Riceve in input un oggetto Parent che rappresenta la radice della gerarchia dei nodi dell'interfaccia grafica e un oggetto Button. In base all'oggetto passato, viene recuperata la finestra corrente tramite il metodo getScene() e impostata la scena per visualizzare la schermata di login.
     * @param root
     * @param actionButton
     * @throws IOException
     */
    public void login(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/login.fxml"));
        Stage window = (Stage) actionButton.getScene().getWindow();
        window.setScene(new Scene(root,650,450));
    }

    /**
     * questo metodo è utilizzato per effettuare il login dell'utente. Riceve in input un oggetto Parent che rappresenta la radice della gerarchia dei nodi dell'interfaccia grafica, un oggetto Stage che rappresenta la finestra dell'applicazione, un oggetto Scene che rappresenta la scena dell'applicazione, un evento ActionEvent e una stringa fxmlFile che rappresenta il percorso del file FXML da caricare. Il metodo carica il file FXML specificato, imposta la scena e la finestra con i valori passati in input e mostra la finestra.
     * @param root
     * @param stage
     * @param scene
     * @param event
     * @param fxmlFile
     * @throws IOException
     */
    public void effettuaLogin(Parent root,Stage stage, Scene scene,ActionEvent event,String fxmlFile)throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * questo metodo permette di visualizzare la schermata principale dell'applicazione (la home). Riceve in input un oggetto Parent che rappresenta la radice della gerarchia dei nodi dell'interfaccia grafica e un evento ActionEvent. In base all'evento passato, viene recuperata la finestra corrente tramite il metodo getSource() e impostata la scena per visualizzare la schermata principale.
     * @param root
     * @param event
     * @throws IOException
     */
    public void home(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * questo metodo permette di visualizzare la schermata del carrello dell'utente. Riceve in input un oggetto Parent che rappresenta la radice della gerarchia dei nodi dell'interfaccia grafica e un evento ActionEvent. In base all'evento passato, viene recuperata la finestra corrente tramite il metodo getSource() e impostata la scena per visualizzare la schermata del carrello.
     * @param root
     * @param event
     * @throws IOException
     */
    public void cart(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/carrello.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * questo metodo apre la finestra per l'inserimento di un nuovo libro. Carica il file FXML inserimentoLibro.fxml e imposta la scena e lo stage per la visualizzazione della finestra. Inoltre, imposta il titolo della finestra, l'icona e la dimensione fissa della finestra.
     * @param root
     * @param actionButton
     * @throws IOException
     */
    public void openInsert(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/inserimentoLibro.fxml"));
        //Stage window = (Stage) actionButton.getScene().getWindow();
        //window.setScene(new Scene(root,671,444));
        //stage.centerOnScreen();
        Stage stage = new Stage();
        stage.setTitle("Inserimento Libro");
        stage.setScene(new Scene(root, 671,444));
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
        Image icon = new Image("bookshopparthenope/gui/images/logoDesktop.png");
        stage.getIcons().add(icon);
        // Hide this current window (if this is what you want)
    }


    /**
     * questo metodo apre la finestra per l'inserimento delle informazioni di pagamento. Carica il file FXML paymentInfo.fxml e imposta la scena e lo stage per la visualizzazione della finestra.
     * @param root
     * @param event
     * @throws IOException
     */
    public void payment(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/paymentInfo.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * questo metodo apre la finestra per la visualizzazione dell'elenco degli ordini. Carica il file FXML orderview.fxml e imposta la scena e lo stage per la visualizzazione della finestra.
     * @param root
     * @param event
     * @throws IOException
     */
    public void orderview(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/orderview.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * questo metodo apre la finestra del pannello di amministrazione. Carica il file FXML adminpanel.fxml e imposta la scena e lo stage per la visualizzazione della finestra.
     * @param root
     * @param event
     * @throws IOException
     */
    public void adminpanel(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/adminpanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
