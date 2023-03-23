package bookshopparthenope;

import bookshopparthenope.Model.CartManagement.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static bookshopparthenope.LoginController.userCart;

/**
 * La classe CartController è un controller JavaFX per la schermata del carrello dell'utente nel sistema di gestione del negozio di libri. Contiene diversi elementi dell'interfaccia utente, tra cui una tabella che elenca i libri presenti nel carrello, un selettore della quantità di copie per ciascun libro e un bottone per rimuovere un libro dal carrello.
 *
 * Il controller si occupa di popolare la tabella con i dati relativi ai libri presenti nel carrello e di fornire all'utente diverse opzioni per interagire con essi, come la selezione di un libro per visualizzarne il titolo e la possibilità di rimuoverlo dal carrello. Inoltre, il controller gestisce la logica del pagamento, offrendo all'utente una scelta di metodi di pagamento diversi tra loro.
 */
public class CartController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane cartMenu;
    @FXML
    private Label titoloLibro;
    @FXML
    private ComboBox<Integer> comboQt;
    @FXML
    private Label idTotal;
    @FXML
    private Button deleteFromCartButton;
    @FXML
    private Button homeButton;
    @FXML
    private TableView<ObservableList> cartTable;
    private ObservableList<ObservableList> data;
    @FXML
    private Button logoutButton;

    @FXML
    private ComboBox<String> paymentMethodComboBox;
    @FXML
    private Button profileButton;
    Parent root;
    FxmlController controller=new FxmlController();

    /**
     * Logout utente
     * @param event
     * @throws IOException
     */
    @FXML
    void logout(ActionEvent event)  throws IOException {
        controller.logout(root,logoutButton);
    }

    /**
     * Switch alla schermata Home
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToHome(ActionEvent event)  throws IOException {
        controller.home(root,event);
    }


    /**
     * Switch alla schermata Profilo utente
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToProfile(ActionEvent event)  throws IOException {
        controller.profile(root,event);
    }
    Cart cart = new Cart();
    CartTotal totaleCarrello= new CartTotal(cart);

    /**
     * Questo metodo aggiunge le colonne alla tabella degli acquisti dell'utente (userCart) utilizzando i dati ottenuti da un ResultSet restituito dal metodo showCartBooks () del servizio DBService.
     *
     * Per ogni colonna nella tabella, il metodo imposta il nome della colonna come il nome del campo corrispondente nella tabella del database e imposta anche la larghezza preferita della colonna.
     *
     * Inoltre, il metodo definisce una funzione di callback che viene utilizzata per impostare il valore delle celle della colonna. La funzione controlla se il valore della cella è un numero e se sì, se è un intero o un float, quindi lo restituisce come un oggetto SimpleObjectProperty, che viene poi utilizzato per visualizzare il valore nella cella.
     *
     * Infine, le colonne vengono aggiunte alla tabella cartTable e la politica di ridimensionamento delle colonne viene impostata sulla tabella. In caso di eccezione, viene stampato un messaggio di errore sulla console.
     */
    public void addcolumn() {
        try {
            ResultSet rs = DBService.showCartBooks(userCart);

            for (int i = 0; i <  rs.getMetaData().getColumnCount();  i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());


                if (i==0)
                    col.setPrefWidth(233);
                else if(i==1)
                    col.setPrefWidth(130);
                else
                {  col.setMaxWidth(120);col.setStyle("-fx-alignment: CENTER-RIGHT;");}


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

                cartTable.columnResizePolicyProperty();
                cartTable.getColumns().addAll(col);


            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }


    /**
     * Questo metodo popola una tabella chiamata cartTable con i dati recuperati dal database attraverso il metodo showCartBooks della classe DBService. La tabella è popolata con le righe e le colonne restituite dal ResultSet.
     *
     * All'interno del ciclo while, vengono recuperati tutti i valori di ogni riga e inseriti in una ObservableList chiamata row, che viene a sua volta aggiunta alla lista principale data (una ObservableList di ObservableList).
     *
     * Infine, viene chiamato il metodo setItems() sulla tabella cartTable per popolarla con i dati in data. Inoltre, viene aggiunto un listener sulla selezione della riga della tabella, in modo da estrarre le informazioni selezionate e disattivare o attivare alcuni pulsanti a seconda della selezione.
     */
    void addrow(){
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = DBService.showCartBooks(userCart);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            cartTable.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (NullPointerException e){
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cartTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bookTitle = (String) newSelection.get(0);
                titoloLibro.setVisible(true);
                titoloLibro.setText("Libro selezionato: "+bookTitle);
                deleteFromCartButton.setDisable(false);
                comboQt.setDisable(false);
                errorLabel.setVisible(false);

            }

        });
    }


    /**
     * Questo metodo viene chiamato quando viene inizializzata la finestra dell'applicazione e viene utilizzato per impostare diversi aspetti della tabella del carrello della spesa, nonché per verificare se ci sono prodotti nel carrello e nascondere il menu del carrello se non ci sono prodotti.
     *
     * In particolare, il metodo esegue le seguenti operazioni:
     *
     * Imposta la selezione della tabella su "multipla".
     * Aggiunge le colonne alla tabella del carrello chiamando il metodo addcolumn().
     * Aggiunge le righe alla tabella del carrello chiamando il metodo addrow().
     * Aggiunge gli elementi della lista comboQuantita alla combobox comboQt.
     * Imposta il totale del carrello chiamando il metodo setTotal().
     * Verifica se il numero di prodotti nel carrello è zero utilizzando il metodo DBService.countproducts() e nasconde il menu del carrello se necessario.
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
        cartTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        addcolumn();
        addrow();
        comboQt.getItems().addAll(comboQuantita);
        setTotal();
        try {
            if (DBService.countproducts(userCart)==0){
                cartMenu.setVisible(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    String bookTitle;

    Integer[] comboQuantita = {1,2,3,4,5,6,7,8,9,10};

    Invoker invoker = new Invoker();


    /**
     * Il metodo changeQt è un gestore di eventi che viene chiamato quando l'utente cambia la quantità di un libro nel carrello e fa clic sul pulsante "Aggiorna". Il metodo esegue le seguenti operazioni:
     *
     * Ottiene l'indice della riga selezionata nella tabella del carrello.
     * Ottiene la nuova quantità di copie selezionata dall'utente dalla casella combinata comboQt.
     * Crea un nuovo comando AggiungiQuantitaCommand passando il carrello, il titolo del libro, il nome utente e la nuova quantità come parametri.
     * Imposta il comando appena creato nell'oggetto invoker.
     * Esegue il comando attraverso l'invoker, che restituisce la quantità disponibile nel magazzino per quel libro, in caso di successo, oppure -1 se non ci sono abbastanza copie disponibili.
     * Se la quantità disponibile è inferiore alla quantità richiesta, visualizza un messaggio di errore con la quantità disponibile.
     * In caso contrario, chiama il metodo setTotal per aggiornare il totale del carrello, chiama il metodo addrow per aggiornare la tabella del carrello e nasconde il messaggio di errore.
     * Infine, seleziona di nuovo la riga che era stata selezionata inizialmente nella tabella del carrello.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void changeQt(ActionEvent event) throws SQLException, ClassNotFoundException {
        int index = cartTable.getSelectionModel().getSelectedIndex();
        nCopie = comboQt.getValue();
        CommandQuantity addToCartCommand = new AggiungiQuantitaCommand(cart,bookTitle,userCart,nCopie);
        invoker.setCommand(addToCartCommand);
        int qtDisponibile;
        qtDisponibile=invoker.executeCommand();
        if(qtDisponibile!=-1){
            errorLabel.setVisible(true);
            errorLabel.setText("Non ci sono abbastanza copie in magazzino per questo libro.\nCopie disponibili: "+ qtDisponibile);}
        else{
            setTotal();
            addrow();
            errorLabel.setVisible(false);
        }

        cartTable.getSelectionModel().select(index);

    }


    Integer nCopie;

    /**
     *Questo metodo gestisce l'evento di clic sul pulsante "Elimina dal carrello". In particolare, ottiene la riga selezionata nella tabella del carrello e il titolo del libro associato, quindi crea un oggetto Command per rimuovere il libro dal carrello e lo passa all'oggetto Invoker. Infine, aggiorna la tabella del carrello e l'etichetta del totale chiamando rispettivamente i metodi addrow() e setTotal(). Se la lista del carrello è vuota, disabilita il menu del carrello. Se ci sono errori durante l'eliminazione del libro dal carrello, mostra un messaggio di errore.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void deleteFromCart(ActionEvent event) throws SQLException, ClassNotFoundException {
        int numSelectedRows = cartTable.getSelectionModel().getSelectedIndices().size();
        ObservableList<ObservableList> selectedRows = cartTable.getSelectionModel().getSelectedItems();
        List<ObservableList> rowsToRemove = new ArrayList<>(selectedRows);
        for (ObservableList row : rowsToRemove) {
            DBService.deleteCart(userCart, (String)row.get(0));
            data.remove(row);
        }
        cartTable.getSelectionModel().clearSelection();
        addrow();
        setTotal();
        titoloLibro.setVisible(false);
        if (DBService.countproducts(userCart)==0){
            cartMenu.setVisible(false);
        }
        if (numSelectedRows > 1) {
            titoloLibro.setVisible(false);
        } else {
            titoloLibro.setVisible(true);
        }
        if (numSelectedRows == 0) {
            comboQt.setDisable(true);
        } else {
            comboQt.setDisable(false);
        }
    }


    /**
     * Il metodo setTotal imposta il valore totale del carrello sul componente idTotal della GUI. In particolare, il valore del totale viene calcolato attraverso la chiamata al metodo getTotal dell'oggetto totaleCarrello. Se si verificano errori durante l'accesso al database, viene lanciata un'eccezione di tipo RuntimeException. Il valore del totale viene convertito in una stringa formattata con due decimali utilizzando il metodo String.format.
     */
    void setTotal(){
        try {
            idTotal.setText(String.format("%.2f", totaleCarrello.getTotal()));
            tot=totaleCarrello.getTotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static float tot;

    /**
     * Switch alla pagina per effettuare il pagamento
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToPayment(ActionEvent event) throws IOException {
        controller.payment(root,event);
    }


    @FXML
    private Button deleteAllFromCartButton;


    /**
     * Il metodo deleteAllFromCart è un'azione eseguita quando l'utente seleziona l'opzione per eliminare tutti i libri dal carrello. Questo metodo utilizza il servizio DBService per eliminare tutti i libri presenti nel carrello dell'utente specificato (userCart).
     *
     * Successivamente, il metodo aggiorna la visualizzazione della tabella del carrello richiamando il metodo addrow(), quindi aggiorna il totale del carrello richiamando il metodo setTotal(). Infine, disabilita la selezione della quantità (comboQt) e il pulsante "Elimina dal carrello" (deleteFromCartButton), nasconde l'etichetta del titolo del libro selezionato (titoloLibro) e nasconde il menu del carrello se non ci sono più libri presenti nel carrello (cartMenu).
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void deleteAllFromCart(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBService.deleteAllCart(userCart);
        addrow();
        setTotal();
        titoloLibro.setVisible(false);
        comboQt.setDisable(true);
        deleteFromCartButton.setDisable(true);
        if (DBService.countproducts(userCart) == 0) {
            cartMenu.setVisible(false);
        }
    }
}

