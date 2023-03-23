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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static bookshopparthenope.LoginController.userCart;

/**
 * Questa classe è il controller per la schermata del profilo utente in un'applicazione di un negozio di libri (bookshop). La classe implementa l'interfaccia Initializable per inizializzare l'interfaccia utente e contiene una serie di metodi annotati con @FXML che vengono chiamati quando l'utente interagisce con gli elementi dell'interfaccia.
 * Il controller contiene riferimenti a vari elementi dell'interfaccia utente, come ad esempio la tabella degli ordini, la tabella dei prodotti, il pulsante del carrello e il pulsante di logout. Implementa inoltre un metodo initialize che viene chiamato quando la schermata viene caricata per la prima volta e che viene utilizzato per impostare i valori iniziali degli elementi dell'interfaccia utente.
 */
public class ProfileController implements Initializable {
  @FXML
  private TableView<ObservableList<String>> productUserTable;
  @FXML
  private TableView<ObservableList<String>> orderUserTable;
  @FXML
  private Text cognome;
  @FXML
  private Text email;
  @FXML
  private Text nome;

  @FXML
  private Text username;
  @FXML
  private Button cartButton;

  @FXML
  private Button homeButton;

  @FXML
  private Button logoutButton;
  Parent root;
  FxmlController control = new FxmlController();

  /**
   * Logout utente
   * @param event
   * @throws IOException
   */
  @FXML
  void Logout(ActionEvent event) throws IOException {
    control.logout(root, logoutButton);
  }

  /**
   * Switch pagina Carrello utente
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToCart(ActionEvent event) throws IOException {
    control.cart(root, event);
  }

  /**
   * Switch pagina Home utente
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToHome(ActionEvent event) throws IOException {
    control.home(root, event);

  }


  /**
   *
   * @param location
   * The location used to resolve relative paths for the root object, or
   * {@code null} if the location is not known.
   *
   * @param resources
   * The resources used to localize the root object, or {@code null} if
   * the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(userCart);
    try {
      ResultSet rsProfile= DBService.getUserInfo(userCart);
      nome.setText(rsProfile.getString(1));
      cognome.setText(rsProfile.getString(2));
      email.setText(rsProfile.getString(3));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    addordercol();
    addorderrow();
  }


  /**
   * Questo metodo definisce le colonne di una tabella JavaFX chiamata "orderUserTable" che visualizza l'elenco degli ordini effettuati da un utente (indicato da "userCart").
   * In particolare, questo metodo definisce tre colonne della tabella: "Codice Ordine", "Metodo Pagamento" e "Data Acquisto".
   * Per ogni colonna, viene definito il suo titolo, la sua larghezza e l'allineamento del testo al suo interno.
   * Inoltre, viene definita una funzione di callback che associa il valore di ogni cella al valore di una colonna nell'elenco degli ordini restituito dal metodo "showOrderList" del servizio di database "DBService".
   * Questo metodo è utilizzato per popolare la tabella degli ordini dell'utente con i dati recuperati dal database. Se si verifica un'eccezione SQL o ClassNotFoundException durante l'esecuzione di questo metodo, viene lanciata un'eccezione di runtime.
   */
  void addordercol() {
    try {
      ResultSet rs = DBService.showOrderList(userCart);

      TableColumn<ObservableList<String>, String> col = null;

      col = new TableColumn("Codice Ordine");
      col.setPrefWidth(160);
      col.setStyle("-fx-alignment: CENTER;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(0));
        }
      });
      orderUserTable.getColumns().add(col);

      col = new TableColumn("Metodo Pagamento");
      col.setPrefWidth(125);
      col.setStyle("-fx-alignment: CENTER;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(1));
        }
      });
      orderUserTable.getColumns().add(col);

      col = new TableColumn("Data Acquisto");
      col.setPrefWidth(160);
      col.setStyle("-fx-alignment: CENTER;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(2));
        }
      });
      orderUserTable.getColumns().add(col);

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }



  ObservableList<ObservableList<String>> ordini;
  ObservableList<ObservableList<String>> prodotti;
  String codice;



  /**
   *Questo metodo popola la tabella JavaFX chiamata "orderUserTable" con i dati degli ordini dell'utente (indicato da "userCart"). Per fare ciò, questo metodo recupera l'elenco degli ordini dal database utilizzando il metodo "showOrderList" del servizio di database "DBService" e lo trasforma in una lista osservabile "ordini". Successivamente, per ogni riga dell'elenco degli ordini, viene creata una lista osservabile "row" che contiene i dati di ogni colonna della riga. Queste liste osservabili vengono quindi aggiunte alla lista osservabile "ordini". Infine, la tabella viene impostata con i dati della lista osservabile "ordini" utilizzando il metodo "setItems" della tabella.
   * Inoltre, questo metodo imposta un listener sulla proprietà "selectedItemProperty" della tabella "orderUserTable". Quando una riga viene selezionata nella tabella, viene recuperato il valore della prima colonna (codice ordine) della riga selezionata e viene chiamato il metodo "addproductcol" e "addproductrow" per popolare la tabella "productUserTable" con i dettagli dei prodotti associati all'ordine selezionato. Se si verifica un'eccezione SQL o ClassNotFoundException durante l'esecuzione di questo metodo, viene stampato un messaggio di errore. Se si verifica un'eccezione NullPointerException, questa viene semplicemente gestita e ignorata.
   */
  void addorderrow() {
    ordini = FXCollections.observableArrayList();
    ResultSet rs;
    try {
      rs = DBService.showOrderList(userCart);
      while (rs.next()) {
        //Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(rs.getString(i));
        }
        ordini.add(row);

      }

      orderUserTable.setItems(ordini);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {
    }

    orderUserTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        codice = newSelection.get(0);
        productUserTable.getColumns().clear();
        productUserTable.getItems().clear();
        addproductcol();
        addproductrow();
      }
    });
  }

  /**
   * Il metodo addproductcol() viene utilizzato per aggiungere le colonne alla tabella productUserTable che visualizza i dettagli dei prodotti dell'ordine selezionato dall'utente nella tabella orderUserTable. In particolare, questo metodo esegue le seguenti operazioni:
   *
   * Ottiene un ResultSet contenente i dettagli dei prodotti dell'ordine selezionato chiamando il metodo showProductList() della classe DBService.
   * Per ogni colonna da visualizzare, crea un oggetto TableColumn, imposta le sue proprietà (larghezza, allineamento, valore della cella) e la aggiunge alla tabella productUserTable.
   * Utilizza un Callback per associare i dati delle celle alla relativa colonna della tabella, prendendo i valori dal ResultSet.
   * Se si verificano eccezioni durante l'esecuzione delle operazioni (come SQLException e ClassNotFoundException), il metodo solleva una RuntimeException con il messaggio dell'eccezione.
   */
  void addproductcol() {
    try {
      ResultSet rs = DBService.showProductList(codice);

      TableColumn<ObservableList<String>, String> col = null;

      col = new TableColumn("Titolo Libro");
      col.setPrefWidth(256);
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(0));
        }
      });
      productUserTable.getColumns().add(col);

      col = new TableColumn("Autore Libro");
      col.setPrefWidth(180);
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(1));
        }
      });
      productUserTable.getColumns().add(col);

      col = new TableColumn("Qt.");
      col.setPrefWidth(65);
      col.setStyle("-fx-alignment: CENTER-RIGHT;");

      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(2));
        }
      });
      productUserTable.getColumns().add(col);

      col = new TableColumn("Prezzo cad.");
      col.setPrefWidth(75);
      col.setStyle("-fx-alignment: CENTER-RIGHT;");

      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(3));
        }
      });
      productUserTable.getColumns().add(col);

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }


  /**
   * Questo metodo recupera i dettagli dei prodotti per un determinato ordine selezionato dall'utente e li visualizza in una tabella. In particolare, il metodo richiama il metodo "showProductList" di "DBService" per recuperare i dati dal database per l'ordine selezionato e quindi inserisce i dati in una "ObservableList" denominata "prodotti". Successivamente, il metodo imposta la "prodotti" come set di dati per una tabella denominata "productUserTable", che a sua volta visualizza i dati dei prodotti nella tabella in modo organizzato in righe e colonne.
   */
  void addproductrow() {
    prodotti = FXCollections.observableArrayList();
    ResultSet rs;
    try {
      rs = DBService.showProductList(codice);

      while (rs.next()) {
        //Iterate Row
        ObservableList row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(rs.getString(i));
        }
        prodotti.add(row);

      }

      productUserTable.setItems(prodotti);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {

    }
  }
}
