package bookshopparthenope;
import  bookshopparthenope.Model.CartManagement.Cart;
import bookshopparthenope.Model.BookManagement.BookFactory;
import bookshopparthenope.Model.CartManagement.CartTotal;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static bookshopparthenope.LoginController.userCart;

/**
 * La classe HomeController è una classe del package bookshopparthenope e implementa l'interfaccia Initializable, che fornisce un metodo initialize() che viene chiamato dopo che il file FXML associato alla classe viene caricato. La classe è responsabile della gestione della home page dell'applicazione BookShopParthenope.
 */
public class HomeController implements Initializable {

    private Parent root;
    @FXML
    private Button counter;
    @FXML
    private AnchorPane backimg;
    @FXML
    private Button searchBarButton;

    @FXML
    private TextField serachBar;

    @FXML
    private TableView<ObservableList> tableBook;

    @FXML
    private ImageView image;
    @FXML
    private Label dettaglioAnno;
    @FXML
    private Label dettaglioTitolo;
    @FXML
    private Label dettaglioAutore;

    @FXML
    private Text dettaglioDescrizione;

    @FXML
    private Label dettaglioPagine;

    @FXML
    private Label dettaglioPrezzo;

    @FXML
    private Label dettaglioQuantita;
    @FXML
    private Label dettaglioIsbn;
    @FXML
    private AnchorPane dettagli;

    @FXML
    private Button logoutButton;
    @FXML
    private Button profileButton;
    FxmlController control =new FxmlController();
    @FXML
    private Button cerca;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboSottocategoria;

    /**
     * Logout utente
     * @param event
     * @throws IOException
     */
    @FXML
    void Logout(ActionEvent event) throws IOException {
        control.logout(root,logoutButton);
    }

    /**
     * Passaggio alla pagina del profilo utente
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToProfile(ActionEvent event) throws IOException {
        control.profile(root,event);
    }

    /**
     * Passaggio alla pagina del carrello utente
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToCart(ActionEvent event)  throws IOException {
        control.cart(root,event);
    }
    Cart cart = new Cart();

    /**
     * Questo metodo è associato ad un evento di clic sul pulsante "Aggiungi al carrello" e ha lo scopo di aggiungere un libro al carrello dell'utente.
     *
     * In primo luogo, controlla se il libro selezionato è disponibile in magazzino, controllando il testo del campo "dettaglioQuantita". Se il testo è "Out of stock.", viene visualizzato un messaggio di errore all'utente che indica che il libro non è attualmente disponibile.
     *
     * Se il libro è disponibile, viene estratto il numero di copie richieste dall'utente, utilizzando il metodo "requestedCopies" della classe "DBService". Successivamente, viene verificato se il numero di copie richieste è disponibile in magazzino, utilizzando il metodo "verificaDisponibilita" della stessa classe. Se il numero di copie richieste è uguale al numero di copie disponibili in magazzino, viene visualizzato un messaggio di errore all'utente che indica che non è possibile aggiungere più copie di quelle disponibili.
     *
     * Se il numero di copie richieste è inferiore al numero di copie disponibili in magazzino, viene creato un oggetto "BookFactory" con i dettagli del libro e aggiunto all'array "cart" che rappresenta il carrello dell'utente. Viene inoltre aggiornato il totale del carrello e il contatore del numero di libri nel carrello viene aggiornato.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void AddToCart(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (Objects.equals(dettaglioQuantita.getText(), "Out of stock.")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Libro non disponibile");
            alert.setHeaderText(bookTitle + " attualmente non disponibile. Ci scusiamo per l'inconveniente.");
            alert.showAndWait();
        } else {
            // Estrai il numero di copie che l'utente sta cercando di aggiungere al carrello
            int requestedCopies = DBService.requestedCopies(bookTitle,userCart);
            if (requestedCopies ==  DBService.verificaDisponibilita(bookTitle)) {
                // Mostra un messaggio di errore all'utente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Non puoi aggiungere più di " + requestedCopies + " copie di " + bookTitle + " al carrello. " +
                        "Sono disponibili solo " + requestedCopies + " copie.");
                alert.showAndWait();
            } else {
                // Aggiungi il libro al carrello
                BookFactory libroCart = new BookFactory(userCart, bookTitle, bookAuthor, bookPrice);
                cart.add(libroCart);
                CartTotal cartTotal = new CartTotal(cart);
                setCounter();
            }
        }
    }





    String bookTitle;
    float bookPrice;
    String bookAuthor;
    String username;


    /**
     * Il metodo cerca è associato all'evento di click del pulsante di ricerca e viene eseguito quando l'utente cerca un libro all'interno del sistema.
     *
     * Il metodo inizia recuperando i valori delle categorie e sottocategorie selezionate dall'utente dalle rispettive ComboBox. Quindi, viene eliminata la visualizzazione delle colonne correnti dalla tabella tableBook utilizzando il metodo getColumns().clear().
     *
     * Successivamente, il metodo addcolumn(scat) viene chiamato per aggiungere le colonne per la sottocategoria selezionata, e il metodo addrow(scat) viene chiamato per aggiungere le righe dei libri corrispondenti a quella sottocategoria nella tabella tableBook.
     *
     * In sintesi, il metodo cerca aggiorna la tabella dei risultati della ricerca in base alla sottocategoria selezionata dall'utente.
     * @param event
     */
    @FXML
    void cerca(ActionEvent event) {
        String cat = comboCategoria.getValue();
        String scat = comboSottocategoria.getValue();
        tableBook.getColumns().clear();
        addcolumn(scat);
        addrow(scat);
    }

    /**
     * Questo metodo si occupa di aggiungere le colonne alla tabella che mostra i libri. La tabella viene pulita dalle colonne precedenti, poi viene interrogato il database per ottenere un ResultSet che contiene i dati da visualizzare nella tabella. Successivamente viene iterato attraverso il ResultSet per creare una TableColumn per ogni colonna presente nel ResultSet. Viene quindi impostata la larghezza preferita per ogni colonna e il modo in cui i dati devono essere allineati. Infine, viene aggiunto ogni TableColumn alla TableView.
     * Inoltre, questo metodo converte tutti i valori numerici delle celle in Float o Integer per evitare errori di formattazione. Se la sottocategoria è nulla, viene mostrata la lista completa dei libri.
     * @param scat
     */
    public void addcolumn(String scat) {
        try {
            ResultSet rs;
            if (scat==null)
            {
                rs=DBService.showAllBooksToUser();
            }
            else{
                rs = DBService.showBooksToUser(scat);}

            for (int i = 0; i <  rs.getMetaData().getColumnCount();  i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setResizable(false);
                if (i==0)
                    col.setPrefWidth(265);
                else
                if(i==1)
                    col.setPrefWidth(135);
                else
                    col.setPrefWidth(66);
                if (i==2){col.setStyle("-fx-alignment: CENTER-RIGHT;");}
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
                    public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
                        Object value = param.getValue().get(j);
                        try {
                            return new SimpleObjectProperty<>(Float.parseFloat(value.toString()));
                        } catch (NumberFormatException e) {
                            try {
                                return new SimpleObjectProperty<>(Integer.parseInt(value.toString()));
                            } catch (NumberFormatException ex) {
                                return new SimpleObjectProperty<>(value);
                            }
                        }
                    }
                });

                tableBook.getColumns().removeAll(col);
                tableBook.columnResizePolicyProperty();
                tableBook.getColumns().addAll(col);


            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }


    /**
     * Il metodo addrow viene utilizzato per aggiungere righe a una TableView che mostra i libri disponibili in base alla categoria scelta dall'utente. La tabella viene popolata recuperando i dati dal database attraverso il metodo DBService.showBooksToUser(scat), dove scat rappresenta la sottocategoria selezionata dall'utente. I risultati della query vengono quindi iterati e ogni riga viene aggiunta alla TableView come un'ObservableList.
     * Inoltre, viene impostato un ChangeListener sulla selezione della riga, in modo da visualizzare i dettagli del libro selezionato nella parte destra della finestra.
     * @param scat
     */
    void addrow(String scat){
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            if (scat==null)
            {
                rs=DBService.showAllBooksToUser();
            }
            else{
                rs = DBService.showBooksToUser(scat);}

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));

                }
                data.add(row);

            }


            tableBook.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e){
        }

        tableBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bookTitle = (String) newSelection.get(0);
                try {
                    ResultSet dettagliLibro= DBService.showBooksDetails(bookTitle);
                    changeInfo(dettagliLibro);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    private ObservableList<ObservableList> data;




    @FXML
    void setCategoria (ActionEvent event){

    }

    @FXML
    void setSottocategoria (ActionEvent event){

    }

    String[] comboCategorie = {"Letteratura", "Arte e Musica", "Biografia", "Fumetti", "Computer e Tech", "Cucina", "Educazione", "Intrattenimento", "Business"};

    /**
     * Il metodo initialize viene chiamato automaticamente quando la schermata viene caricata. In questo caso, il metodo esegue le seguenti operazioni:
     *
     * Aggiunge le categorie disponibili all'elenco a discesa della categoria (comboCategoria) utilizzando comboCategoria.getItems().addAll(comboCategorie).
     * Imposta l'azione da eseguire quando viene selezionata una categoria nell'elenco a discesa utilizzando comboCategoria.setOnAction(this::getChoice).
     * Aggiunge le colonne alla tabella utilizzando addcolumn(null).
     * Aggiunge le righe alla tabella utilizzando addrow(null).
     * Imposta un messaggio di avviso (Label) che verrà visualizzato nella tabella nel caso in cui non ci siano libri disponibili.
     * Chiama il metodo setCounter() per impostare il contatore dei libri nel carrello utente.
     * @param arg0
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param arg1
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize (URL arg0, ResourceBundle arg1){
        comboCategoria.getItems().addAll(comboCategorie);
        comboCategoria.setOnAction(this::getChoice);
        addcolumn(null);
        addrow(null);
        Label label = new Label("Nessun libro trovato :(");
        tableBook.setPlaceholder(label);
        setCounter();
    }

    private void setCounter() {
        try {
            counter.setText(Integer.toString(DBService.countproducts(userCart)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String categoriaScelta;
    public String sottoCategoriaScelta;

    /**
     * getChoice viene chiamato quando un'opzione viene selezionata dal menu a discesa comboCategoria. In base all'opzione selezionata, il metodo imposta il menu a discesa comboSottocategoria con le relative opzioni. Il metodo inoltre disabilita il pulsante "cerca" fino a quando non viene selezionata una sottocategoria.
     * @param event
     */
    public void getChoice (ActionEvent event){

        if (comboSottocategoria.isDisabled()) {
            comboSottocategoria.setDisable(false);
        }
        categoriaScelta = comboCategoria.getValue();

        switch (categoriaScelta) {
            case "Letteratura":
                String[] sLetteratura = {"Antologie", "Classici", "Contemporanei", "Lingue Straniere", "Letterature"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sLetteratura);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Arte e Musica":
                String[] sArte = {"Storia dell'arte", "Calligrafia", "Disegno", "Fashion Design"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sArte);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Biografia":
                String[] sBiografia = {"Etnica e Culturale", "Europea", "Storica", "Personaggi Famosi e Leader", "Militare"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sBiografia);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Fumetti":
                String[] sFumetti = {"Comici", "Marvel", "Misteriosi", "DC", "Fantasy"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sFumetti);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Computer e Tech":
                String[] sComputer = {"Apple", "CAD", "Certificazioni", "Informatica", "Database"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sComputer);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Cucina":
                String[] sCucina = {"Asiatica", "Cucina Calda", "BBQ", "Arti Culinarie", "Dolci"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sCucina);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Educazione":
                String[] sEducazione = {"Almanacchi", "Atlanti e Mappe", "Cataloghi", "Scolastici"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sEducazione);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Intrattenimento":
                String[] sIntrattenomento = {"Rompicapo", "Barzellette", "Giochi", "Film"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sIntrattenomento);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            case "Business":
                String[] sBusiness = {"Carriera", "Economia", "Finanza", "Industria", "Internazionale"};
                comboSottocategoria.getItems().clear();
                comboSottocategoria.getItems().addAll(sBusiness);
                comboSottocategoria.setOnAction(this::getChoice2);
                cerca.setDisable(true);
                break;
            default:
                System.out.println("error");
                cerca.setDisable(true);
                break;
        }
    }

    /**
     * Il secondo metodo getChoice2 viene chiamato quando un'opzione viene selezionata dal menu a discesa comboSottocategoria e imposta la sottocategoria scelta. Il pulsante "cerca" viene abilitato.
     * @param event
     */
    public void getChoice2 (ActionEvent event){
        sottoCategoriaScelta = comboSottocategoria.getValue();
        cerca.setDisable(false);
    }

    /**
     * Mostra tutti i libri
     * @param event
     */
    @FXML
    void showAll(ActionEvent event) {
        tableBook.getColumns().clear();

        addcolumn(null);
        addrow(null);
    }

    /**
     *Questo metodo chiama il metodo "clearSelection()" sul modello di selezione della tabella "tableBook", che deseleziona qualsiasi riga precedentemente selezionata nella tabella.
     *
     * Successivamente, il metodo chiama il metodo "randomBooksDetails()" del servizio DBService, che restituisce un ResultSet contenente i dettagli di un libro casuale dal database. Infine, il metodo "changeInfo()" viene chiamato per aggiornare l'interfaccia utente con i dettagli del libro appena selezionato.
     *
     * Se si verifica un'eccezione di SQLException o ClassNotFoundException durante l'esecuzione del metodo, viene generata una RuntimeException con l'eccezione originale come causa.
     * @param event
     */
    @FXML
    void randomBook(ActionEvent event) {
        try {
            tableBook.getSelectionModel().clearSelection();

            ResultSet dettagliLibro= DBService.randomBooksDetails();
            changeInfo(dettagliLibro);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Questo metodo prende in input un ResultSet dettagliLibro, che contiene i dettagli di un libro estratti dal database. Il metodo imposta la visibilità degli elementi dell'interfaccia utente backimg e dettagli in modo che solo il dettaglio del libro sia visibile. Inoltre, il metodo imposta il testo delle etichette dettaglioTitolo, dettaglioAutore, dettaglioIsbn, dettaglioPrezzo, dettaglioQuantita, dettaglioDescrizione, dettaglioAnno e dettaglioPagine a seconda dei valori del ResultSet dettagliLibro. Infine, il metodo imposta i valori di tre variabili globali bookPrice, bookAuthor e bookTitle in base ai valori del ResultSet dettagliLibro. Queste variabili globali possono essere utilizzate in altre parti del codice per eseguire altre operazioni sul libro selezionato.
     * @param dettagliLibro
     * @throws SQLException
     */
    private void changeInfo(ResultSet dettagliLibro) throws SQLException {
        backimg.setVisible(false);
        dettagli.setVisible(true);
        dettaglioTitolo.setText(dettagliLibro.getString(1));
        dettaglioAutore.setText(dettagliLibro.getString(2));
        dettaglioIsbn.setText(dettagliLibro.getString(3));
        dettaglioPrezzo.setText(dettagliLibro.getString(4));
        if (Objects.equals(dettagliLibro.getString(5), "0")){
            dettaglioQuantita.setText("Out of stock.");}
        else
        {            dettaglioQuantita.setText(dettagliLibro.getString(5));}
        dettaglioDescrizione.setText(dettagliLibro.getString(6));
        dettaglioAnno.setText(dettagliLibro.getString(7));
        dettaglioPagine.setText(dettagliLibro.getString(8));
        bookPrice=dettagliLibro.getFloat(4);
        bookAuthor= dettagliLibro.getString(2);
        bookTitle=dettagliLibro.getString(1);
    }


    /**
     *Questo metodo viene chiamato quando l'utente preme il pulsante di ricerca per cercare un libro specifico. Il metodo ottiene il testo dalla barra di ricerca, cancella le colonne della tabella e le aggiunge di nuovo (per evitare duplicati), quindi chiama il metodo addrowSearch per aggiungere i risultati della ricerca alla tabella. Quest'ultimo metodo prende come parametro il testo di ricerca e lo utilizza per eseguire una query sul database e ottenere i libri che corrispondono alla ricerca. Infine, i risultati della ricerca vengono aggiunti alla tabella attraverso il metodo addRow.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void searchTitle(ActionEvent event) throws SQLException, ClassNotFoundException {
        String searchText = serachBar.getText();
        tableBook.getColumns().clear();
        addcolumn(null);
        addrowSearch(searchText);

    }


    /**
     *Questo metodo popola la tabella dei libri con i risultati di una ricerca effettuata in base al titolo. In pratica, esegue una query sul database che seleziona tutti i libri che contengono il testo cercato nel titolo, e poi aggiunge ogni riga dei risultati alla tabella. La tabella viene popolata utilizzando l'oggetto ObservableList, che rappresenta una lista di oggetti che può essere "osservata" da altri oggetti e notificata quando viene modificata. Alla fine, l'ObservableList viene impostato come dati della tabella mediante il metodo setItems().
     * @param searchText
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addrowSearch(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        ResultSet rs=DBService.searchBookByTitle(searchText);

        while (rs.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
        }
        tableBook.setItems(data);
    }

}

