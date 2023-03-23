package bookshopparthenope;

import bookshopparthenope.Model.UserManagement.DBService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * La classe AdminPanelController gestisce la visualizzazione dei dati dei libri nella tabella presente nella vista dell'admin panel del negozio di libri online.
 * In particolare, aggiunge colonne e righe alla tabella e gestisce gli eventi generati dall'interazione dell'utente con gli elementi grafici della vista.
 * Inoltre, la classe gestisce anche il logout dell'admin dal sistema e la navigazione tra le diverse viste dell'applicazione.
 */
public class AdminPanelController  implements Initializable {


    String bookTitle;
    private Object newSelection;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField comboQt;
    @FXML
    private Button inserisciLibroButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField serachBar;
    @FXML
    private TableView<ObservableList> adminTableBook;
    private ObservableList<ObservableList> data;
    @FXML
    private Button addQta;
    @FXML
    private Parent root;
    FxmlController control =new FxmlController();
    Integer[] comboQuantita = {1,5,10,50,100,200,500};

    /**
     * Il metodo "openInsert" apre una nuova schermata per consentire all'utente di inserire un nuovo libro nel sistema.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToInsert(ActionEvent event)  throws IOException {
        newSelection=null;
        bookTitle=null;
        errorLabel.setVisible(false);
        adminTableBook.getSelectionModel().clearSelection();
        FxmlController controller=new FxmlController();
        controller.openInsert(root,inserisciLibroButton);
    }


    /**
     * Logout dell'utente
     * @param event
     * @throws IOException
     */
    @FXML
    void logout(ActionEvent event) throws IOException {
        control.logout(root,logoutButton);
    }


    /**
     * Passa alla vista degli ordini utente
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToOrderView(ActionEvent event) throws IOException {
        control.orderview(root,event);
    }


    /**
     * Il metodo "addcolumn()" aggiunge le colonne alla tabella e setta la larghezza delle colonne e l'allineamento del testo. Inoltre, utilizza la classe "Callback" per definire il valore di una cella della tabella in base alla colonna a cui appartiene.
     * Se la colonna è la prima, allora il valore della cella è semplicemente una stringa.
     * Altrimenti, viene controllato se il valore è un numero e viene restituito un oggetto SimpleObjectProperty con un intero se il valore è un intero e con un float altrimenti.
     */

    public void addcolumn() {
        try {
            ResultSet rs = DBService.showBooksToAdmin();

            for (int i = 0; i <  rs.getMetaData().getColumnCount();  i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());


                if (i==0){
                    col.setPrefWidth(200);}
                else
                    col.setPrefWidth(100);
                if (i==3||i==4||i==8||i==7){
                    col.setStyle("-fx-alignment: CENTER-RIGHT;");
                }

                if (i==1){

                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
                        public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
                            Object value = param.getValue().get(j);
                            return new SimpleObjectProperty<>(value.toString());
                        }
                    });
                }
                else {
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
                        public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
                            Object value = param.getValue().get(j);
                            try {
                                float floatValue = Float.parseFloat(value.toString());
                                if (floatValue == (int) floatValue) {
                                    return new SimpleObjectProperty<>(Integer.valueOf((int) floatValue));
                                } else {
                                    return new SimpleObjectProperty<>(floatValue);
                                }
                            } catch (NumberFormatException e) {
                                try {
                                    return new SimpleObjectProperty<>(Integer.parseInt(value.toString()));
                                } catch (NumberFormatException ex) {
                                    return new SimpleObjectProperty<>(value);
                                }
                            }
                        }
                    });
                }
                adminTableBook.columnResizePolicyProperty();
                adminTableBook.getColumns().addAll(col);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    /**
     * Il metodo "addrow()" aggiunge le righe alla tabella. In particolare, legge i dati dal database attraverso il metodo "showBooksToAdmin()" di una classe di servizio "DBService" e li carica in un ObservableList, che viene poi assegnato alla tabella.
     * Inoltre, aggiunge un listener alla selezione della riga della tabella, in modo da impostare il valore di una variabile "bookTitle" quando l'utente seleziona una riga.
     * Questa variabile viene utilizzata successivamente per effettuare operazioni sulla riga selezionata. Se non ci sono libri nel database, viene stampato un messaggio di errore.
     */
    public void addrow(){
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = DBService.showBooksToAdmin();
            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            adminTableBook.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e){
            System.out.println("non sono presenti libri " );
        }
        adminTableBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bookTitle = (String) newSelection.get(0);
                errorLabel.setVisible(true);
                errorLabel.setText("Libro selezionato: "+bookTitle);
                comboQt.setDisable(false);
                addQta.setDisable(false);
            }
        });
    }


    /**
     * Il metodo deleteBook è un gestore di eventi che viene chiamato quando l'utente preme il pulsante "Elimina" per eliminare un libro dalla tabella.
     * Se il titolo del libro selezionato è nullo, viene visualizzato un messaggio di errore.
     * Altrimenti, viene chiamato il metodo eliminaLibro della classe DBService per eliminare il libro dal database.
     * Viene quindi mostrato un messaggio di conferma per chiedere all'utente se è sicuro di voler eliminare il libro selezionato.
     * Se l'utente conferma l'eliminazione, viene mostrato un messaggio di successo e la tabella viene aggiornata con i libri rimanenti.
     * Se il titolo del libro era già nascosto e l'errore era visibile, viene nascosto l'errore.
     * @param event
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @FXML
    void deleteBook(ActionEvent event) throws ClassNotFoundException, SQLException {
        comboQt.setDisable(true);
        addQta.setDisable(true);
        if (bookTitle == null) {
            errorLabel.setText("Seleziona prima un libro");
            errorLabel.setVisible(true);
        } else {
            DBService.eliminaLibro(bookTitle);
            Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertconfirm.setTitle("Conferma eliminazione");
            alertconfirm.setHeaderText("Sei sicuro di voler eliminare il libro '" +bookTitle+"'"+"?");
            alertconfirm.setContentText("Questa azione non può essere annullata.");

            Optional<ButtonType> result = alertconfirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert alertok = new Alert(Alert.AlertType.INFORMATION);
                alertok.setTitle("Libro Eliminato");
                alertok.setHeaderText("'"+bookTitle+"' eliminato correttamente!");
                alertok.showAndWait();
                bookTitle = null;
                if (errorLabel.isVisible())
                    errorLabel.setVisible(false);
                data.clear();
                addrow();
            }
        }
    }


    /**
     * Refresh della pagina cliccando su "Visualizza tutti i libri".
     * @param event
     */
    @FXML
    void refresh(ActionEvent event) {
        newSelection=null;
        bookTitle=null;
        errorLabel.setVisible(false);
        data.clear();
        addrow();
    }

    /**
     * Questo metodo viene chiamato automaticamente quando la vista FXML viene inizializzata.
     * In questo caso, il metodo chiama due metodi: addcolumn() e addrow(), che sono definiti nella stessa classe, per creare le colonne e le righe della tabella visualizzata nella vista FXML.
     * Il commento // adminTableBook.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); suggerisce che il codice era stato precedentemente modificato per consentire la selezione multipla nella tabella, ma questa funzionalità è stata poi disabilitata.
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
        addcolumn();
        addrow();
        // adminTableBook.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    /**
     * Questo metodo è associato all'evento di clic sul pulsante di ricerca e viene utilizzato per cercare un libro all'interno del database in base al titolo inserito nell'apposita barra di ricerca. In particolare, il metodo esegue le seguenti operazioni:
     *
     * -Imposta a null le variabili newSelection e bookTitle e rende invisibile la label di errore.
     * -Recupera il testo inserito nella barra di ricerca.
     * -Cancella il contenuto della tabella visualizzata nella GUI.
     * -Chiama il metodo addrowSearch() passando come parametro il testo inserito nella barra di ricerca, che si occupa di popolare la tabella con i risultati della ricerca.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void searchTitle(ActionEvent event)  throws SQLException, ClassNotFoundException {
        newSelection=null;
        bookTitle=null;
        errorLabel.setVisible(false);
        String searchText = serachBar.getText();
        adminTableBook.getItems().clear();
        addrowSearch(searchText);
    }


    /**
     * Questo metodo esegue una ricerca dei libri nel database in base al titolo del libro. Prende in input una stringa searchText, che è il testo inserito nella barra di ricerca, esegue una query SQL per cercare tutti i libri che hanno il titolo contenente la stringa cercata, e poi popola la tabella adminTableBook con i risultati della query.
     * La tabella viene svuotata prima di inserire i nuovi dati ottenuti dalla query.
     * Il metodo utilizza un'ObservableList per memorizzare i risultati della query e popolare la tabella, così da consentire la visualizzazione dei dati nella tabella in modo dinamico e reattivo.
     * @param searchText
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addrowSearch(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        ResultSet rs=DBService.searchBookByTitleAdmin(searchText);
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
        }
        adminTableBook.setItems(data);
    }


    /**
     * Questo metodo è associato ad un evento di click su un pulsante e viene eseguito quando l'utente vuole visualizzare i libri che sono esauriti (out of stock).
     * In particolare, questo metodo imposta la variabile newSelection e bookTitle a null, nasconde l'etichetta di errore (errorLabel) e disabilita le opzioni di aggiunta di quantità (comboQt e addQta).
     * Successivamente, il metodo cancella tutte le righe attualmente presenti nella tabella dei libri (adminTableBook.getItems().clear()) e chiama il metodo addrowOutOfStock(), che carica i dati dei libri esauriti dal database e li visualizza nella tabella.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void viewOOS(ActionEvent event) throws SQLException, ClassNotFoundException {
        newSelection=null;
        bookTitle=null;
        errorLabel.setVisible(false);
        comboQt.setDisable(true);
        addQta.setDisable(true);
        adminTableBook.getItems().clear();
        addrowOutOfStock();
    }


    /**
     *Questo metodo popola la tabella "adminTableBook" con i libri che sono esauriti (Out Of Stock) prelevandoli dal database attraverso l'invocazione del metodo "searchBookOOS()" della classe "DBService".
     *In particolare, il metodo "addrowOutOfStock()" crea una lista osservabile "data" contenente le righe della tabella da visualizzare, attraverso l'iterazione dei record presenti nel ResultSet restituito dal metodo "searchBookOOS()" e la creazione di una lista osservabile "row" per ogni record.
     *Infine, la lista osservabile "data" viene impostata come elemento della tabella "adminTableBook".
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addrowOutOfStock() throws SQLException, ClassNotFoundException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        ResultSet rs=DBService.searchBookOOS();
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
        }
        adminTableBook.setItems(data);
    }


    /**
     * Il metodo addQtaToBook viene chiamato quando l'utente clicca sul pulsante "Aggiungi copie" nella GUI.
     * Il metodo controlla se il campo comboQt contiene un numero intero valido.
     * In caso contrario, mostra un messaggio di errore all'utente.
     * Se il campo contiene un numero valido, viene visualizzato un messaggio di conferma all'utente chiedendo se vuole aggiungere il numero di copie specificato per il libro selezionato.
     * Se l'utente conferma l'azione, il metodo utilizza il servizio DB per aggiungere il numero di copie specificato per il libro selezionato e aggiorna la GUI chiamando il metodo addrow() per ripopolare la tabella con i dati aggiornati dal database.
     * Se l'operazione è completata con successo, viene visualizzato un messaggio di conferma all'utente.
     * Infine, il metodo azzera il campo bookTitle e newSelection, disabilita il campo comboQt e addQta, nasconde il messaggio di errore e cancella il testo dal campo comboQt.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void addQtaToBook(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            int number = Integer.parseInt(comboQt.getText());
            Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertconfirm.setTitle("Conferma");
            alertconfirm.setHeaderText("Confermi di voler aggiungere " + comboQt.getText() + " copie per '" + bookTitle + "'?");
            alertconfirm.setContentText("Questa azione non puo' essere annullata.");

            Optional<ButtonType> result = alertconfirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert alertok = new Alert(Alert.AlertType.INFORMATION);
                alertok.setTitle("Conferma copie");
                alertok.setHeaderText("Copie aggiunte correttamente!");
                alertok.showAndWait();
                DBService.addQtToBook(Integer.valueOf(comboQt.getText()), bookTitle);
                bookTitle=null;
                newSelection=null;
                comboQt.setDisable(true);
                addQta.setDisable(true);
                errorLabel.setVisible(false);
                comboQt.clear();
                addrow();


            }
        } catch (NumberFormatException ex) {
            Alert alertok = new Alert(Alert.AlertType.INFORMATION);
            alertok.setTitle("Conferma copie");
            alertok.setHeaderText("Inserire un numero intero, contenuto non valido!");
            alertok.showAndWait();
        }

    }
}
