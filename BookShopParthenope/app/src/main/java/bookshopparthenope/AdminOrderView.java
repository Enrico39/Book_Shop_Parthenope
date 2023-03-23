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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * La classe AdminOrderView rappresenta un controller JavaFX che gestisce l'interfaccia grafica di un'applicazione per la gestione degli ordini e degli utenti di un negozio online.
 * Alcune funzionalità principali della classe includono l'aggiunta di colonne e righe alle tabelle degli ordini e degli utenti, il recupero dei dati degli ordini e degli utenti dal database tramite DBService, il ridimensionamento delle colonne delle tabelle e la gestione degli eventi di selezione delle righe delle tabelle degli utenti.
 * La classe implementa l'interfaccia Initializable che fornisce il metodo initialize utilizzato per inizializzare il controller e le sue proprietà.
 */
public class AdminOrderView implements Initializable {
  @FXML
  private Button buttonBS;
  @FXML
  private Button logoutButton;
  @FXML
  private TableView<ObservableList<String>> orderTable;
  @FXML
  private TableView<ObservableList<String>> productTable;
  @FXML
  private TableView<ObservableList> usernameTable;
  FxmlController control = new FxmlController();
  @FXML
  private Parent root;

  /**
   * Logout utente
   * @param event
   * @throws IOException
   */
  @FXML
  void logout(ActionEvent event) throws IOException {
    control.logout(root, logoutButton);
  }

  /**
   * Switch scena su vista libri
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToBS(ActionEvent event) throws IOException {
    control.adminpanel(root, event);
  }

  /**
   * Questo metodo serve a create una nuova colonna nella tabella degli utenti del database. In particolare, il metodo recupera la lista degli utenti dal database utilizzando il metodo showUsersList() del servizio di database (DBService), itera attraverso le colonne del risultato, crea una nuova colonna per ogni colonna del risultato, imposta la larghezza preferita della colonna, il suo stile di allineamento e la factory del valore della cella.
   * La factory del valore della cella è una Callback che converte il valore della cella in un oggetto SimpleObjectProperty. Nel processo, se il valore non è un numero, viene utilizzata una SimpleObjectProperty per il valore come stringa.
   * Infine, la nuova colonna viene aggiunta alla tabella degli utenti, che è identificata come usernameTable.
   */
  void addusercol() {
    try {
      ResultSet rs = DBService.showUsersList();
      TableColumn col = null;
      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        final int j = i;
        col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
        col.setPrefWidth(195);
        col.setStyle("-fx-alignment: CENTER;");
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
          public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
            Object value = param.getValue().get(j);

            try {
              return new SimpleObjectProperty<>(Integer.parseInt(value.toString()));
            } catch (NumberFormatException ex) {
              return new SimpleObjectProperty<>(value);
            }
          }
        });
      }
      usernameTable.columnResizePolicyProperty();
      usernameTable.getColumns().addAll(col);


    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }


  private ObservableList<ObservableList> utenti;
  ObservableList<ObservableList<String>> ordini;
  ObservableList<ObservableList<String>> prodotti;


  /**
   * Questo metodo legge i dati degli utenti dal database e li visualizza in una tabella. In particolare, crea una lista osservabile di righe di dati, dove ogni riga è rappresentata come una lista osservabile di valori.
   * Per ogni riga nel ResultSet, viene creata una lista osservabile di valori e aggiunta alla lista osservabile di righe.
   * Infine, la tabella viene configurata per visualizzare i dati della lista osservabile di righe.
   * Inoltre, il metodo aggiunge un listener sulla selezione di una riga nella tabella degli utenti, in modo che quando una riga viene selezionata, vengano visualizzati nella tabella gli ordini relativi all'utente selezionato tramite il metodo addordercol() e addorderrow().
   */
  public void adduserrow() {
    utenti = FXCollections.observableArrayList();
    ResultSet rs;
    try {
      rs = DBService.showUsersList();

      while (rs.next()) {
        //Iterate Row
        ObservableList row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(rs.getString(i));
        }
        utenti.add(row);

      }

      usernameTable.setItems(utenti);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {

    }

    usernameTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        username = (String) newSelection.get(0);
        orderTable.getColumns().clear();
        orderTable.getItems().clear();
        productTable.getColumns().clear();
        productTable.getItems().clear();
        addordercol();
        addorderrow();
      }
    });
  }


  String username;


  /**
   * Questo metodo aggiunge colonne alla tabella degli ordini (orderTable) per un determinato utente (username), utilizzando i dati recuperati dal database tramite il metodo DBService.showOrderList(username).
   * Vengono creati tre oggetti TableColumn corrispondenti alle colonne "Codice Ordine", "Pagamento" e "Data Acquisto".
   * Per ogni colonna viene impostata la larghezza preferita, lo stile di allineamento e la cella valore della factory, che recupera il valore della cella dell'indice corrispondente nella ObservableList e lo restituisce come SimpleObjectProperty.
   * Infine, ogni TableColumn viene aggiunto alla TableView degli ordini. Se si verifica un'eccezione SQLException o ClassNotFoundException, viene generata un'eccezione RuntimeException.
   */
  void addordercol() {
    try {
      ResultSet rs = DBService.showOrderList(username);

      TableColumn<ObservableList<String>, String> col = null;

      col = new TableColumn("Codice Ordine");
      col.setPrefWidth(120);
      col.setStyle("-fx-alignment: CENTER;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(0));
        }
      });
      orderTable.getColumns().add(col);

      col = new TableColumn("Pagamento");
      col.setPrefWidth(100);
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(1));
        }
      });
      orderTable.getColumns().add(col);

      col = new TableColumn("Data Acquisto");
      col.setPrefWidth(100);
      col.setStyle("-fx-alignment: CENTER-RIGHT;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(2));
        }
      });
      orderTable.getColumns().add(col);

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Il metodo addorderrow() ha la responsabilità di popolare la tabella degli ordini dell'utente selezionato.
   * In particolare, il metodo esegue una query al database per ottenere tutti gli ordini dell'utente selezionato, itera sul ResultSet e per ogni riga crea un ObservableList contenente i dati della riga stessa.
   * Successivamente, tutti gli ObservableList vengono aggiunti all'ObservableList ordini che viene poi impostato come modello della tabella degli ordini (orderTable). Infine, il metodo registra un listener sulla selezione degli ordini nella tabella, in modo che possa popolare la tabella dei prodotti associati a un ordine selezionato.
   */
  void addorderrow() {
    ordini = FXCollections.observableArrayList();
    ResultSet rs;
    try {
      rs = DBService.showOrderList(username);
      while (rs.next()) {
        //Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(rs.getString(i));
        }
        ordini.add(row);

      }

      orderTable.setItems(ordini);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {
    }

    orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        codice = newSelection.get(0);
        productTable.getColumns().clear();
        productTable.getItems().clear();
        addproductcol();
        addproductrow();
      }
    });
  }

  String codice;

  /**
   * Questo metodo fa parte di una classe che implementa l'interfaccia Initializable di JavaFX e viene chiamato quando viene inizializzato il controller associato alla scena.
   * In questo caso, il metodo initialize chiama i metodi addusercol e adduserrow per inizializzare la tabella degli utenti con le colonne e le righe appropriate. Ciò significa che quando la scena viene caricata, la tabella degli utenti viene riempita con i dati ottenuti dal database.
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
    addusercol();
    adduserrow();
  }


  /**
   * Il metodo addproductcol() crea e aggiunge le colonne alla tabella productTable per mostrare i dettagli dei prodotti. Viene eseguito un'interrogazione del database tramite il metodo showProductList() del servizio DB, che restituisce un ResultSet contenente i dati dei prodotti.
   *
   * Per ogni colonna viene creato un oggetto TableColumn con il nome della colonna e la larghezza desiderata. Successivamente, viene impostato un oggetto Callback come CellValueFactory per ogni colonna. La funzione call del Callback restituisce un oggetto SimpleObjectProperty contenente il valore della cella nella riga corrente e nella colonna corrente. Il valore è ottenuto dall'oggetto param dell'ObservableList restituito dalla chiamata a getValue(), passando l'indice della colonna corrente.
   *
   * Infine, ogni colonna viene aggiunta alla tabella tramite il metodo getColumns().add(). Se si verificano eccezioni SQLException o ClassNotFoundException, viene generata un'eccezione RuntimeException con il relativo messaggio.
   */
  void addproductcol() {
    try {
      ResultSet rs = DBService.showProductList(codice);

      TableColumn<ObservableList<String>, String> col = null;

      col = new TableColumn("Titolo Libro");
      col.setPrefWidth(220);
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(0));
        }
      });
      productTable.getColumns().add(col);

      col = new TableColumn("Autore Libro");
      col.setPrefWidth(145);
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(1));
        }
      });
      productTable.getColumns().add(col);

      col = new TableColumn("Qt.");
      col.setPrefWidth(45);
      col.setStyle("-fx-alignment: CENTER-RIGHT;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(2));
        }
      });
      productTable.getColumns().add(col);

      col = new TableColumn("Prezzo cad.");
      col.setPrefWidth(65);
      col.setStyle("-fx-alignment: CENTER-RIGHT;");
      col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
          return new SimpleObjectProperty<>(param.getValue().get(3));
        }
      });
      productTable.getColumns().add(col);

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Questo metodo legge una lista di prodotti dal database utilizzando il codice specificato come parametro e crea una riga per ogni prodotto nella tabella "productTable". Utilizza "ObservableList" per rappresentare ogni riga della tabella, e "FXCollections" per creare e gestire le liste osservabili. La lista osservabile dei prodotti viene poi assegnata alla tabella come dati.
   * In caso di eccezioni durante la lettura dei dati dal database, viene stampato un messaggio di errore.
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

      productTable.setItems(prodotti);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {

    }
  }
}
