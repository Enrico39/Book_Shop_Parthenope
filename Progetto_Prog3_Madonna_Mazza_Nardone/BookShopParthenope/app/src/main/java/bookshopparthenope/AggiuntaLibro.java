package bookshopparthenope;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.BookFactory;
import bookshopparthenope.Model.UserManagement.DBService;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Questa classe rappresenta la finestra di dialogo che consente di aggiungere un nuovo libro al database del negozio di libri.
 * L'interfaccia utente è definita in un file FXML che include diversi campi di testo per inserire le informazioni del libro, come il titolo, l'autore, l'anno di pubblicazione, il numero di pagine, la quantità disponibile, il prezzo e la descrizione.
 * La finestra di dialogo include anche una coppia di menu a discesa per selezionare la categoria e la sottocategoria del libro.
 * Quando l'utente fa clic sul pulsante "Inserisci", le informazioni del libro vengono prese dai campi di testo e dai menu a discesa e utilizzate per creare un nuovo oggetto Book utilizzando il pattern Factory.
 * Infine, l'oggetto Book viene inserito nel database utilizzando il metodo insertFactoryBook() e viene visualizzata una finestra di dialogo di conferma.
 * Se l'utente fa clic sul pulsante "Annulla", la finestra di dialogo viene chiusa.
 */
public class AggiuntaLibro implements Initializable{

  @FXML
  private Label textErrorBook;
  @FXML
  private   TextField anno;

  @FXML
  private Button annulla;

  @FXML
  private   TextField autore;


  @FXML
  private   TextArea descrizione;

  @FXML
  private Button inserisciLibroButton;

  @FXML
  private   TextField isbn;

  @FXML
  private   TextField pagine;

  @FXML
  private  TextField prezzo;

  @FXML
  private   TextField quantita;



  @FXML
  public TextField titolo;



  static String tit;
  static String aut;
  static String ann;
  static String pag;

  static int quantita_int;
  static  String isb;
  static float prezzo_float;
  static String desc;

  /**
   * Questo metodo insertBook viene utilizzato in un'applicazione JavaFX per inserire un nuovo libro in un database. Quando l'utente clicca sul pulsante "Aggiungi libro", il metodo viene chiamato.
   * Prima di inserire il libro, il metodo controlla se tutti i campi obbligatori (titolo, autore, anno, pagine, quantità, ISBN, prezzo, descrizione, categoria e sottocategoria) sono stati compilati. Se un campo è vuoto, viene visualizzato un messaggio di errore. In caso contrario, i dati vengono raccolti dai campi di input e vengono creati un oggetto BookFactory e un oggetto Book utilizzando i valori della categoria e della sottocategoria selezionate dall'utente.
   * Infine, viene chiamato il metodo insertFactoryBook() dell'oggetto sLibrobook, che inserisce il libro nel database. Viene quindi visualizzato un messaggio di conferma e la finestra di inserimento viene chiusa.
   * @param event
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @FXML
  void insertBook(ActionEvent event) throws SQLException, ClassNotFoundException {
    if (titolo.getText()==null || autore.getText()==null || anno.getText()==null || pagine.getText()==null || quantita.getText()==null || isbn.getText()==null || prezzo.getText()==null || descrizione.getText()==null || categoria.getValue()==null || sottocategoria.getValue()==null)
    {
      textErrorBook.setVisible(true);
    } else {
      textErrorBook.setVisible(false);
      tit= titolo.getText();
      aut=autore.getText();
      ann=anno.getText();
      pag=pagine.getText();
      quantita_int = Integer.parseInt(quantita.getText());
      isb=isbn.getText();
      String price = prezzo.getText().replace(',', '.');
      prezzo_float = Float.parseFloat(price);
      desc= descrizione.getText();

      String cat=categoria.getValue();
      String scat=sottocategoria.getValue();
      BookFactory libro= new BookFactory();
      Book libroBook = libro.createBook(cat, scat);
      Book sLibrobook = libroBook.sBook();
      sLibrobook.insertFactoryBook();
      Alert alertok2 = new Alert(Alert.AlertType.INFORMATION);
      alertok2.setTitle("Libro Aggiunto");
      alertok2.setHeaderText("'"+titolo.getText()+"' Aggiunto correttamente!");
      alertok2.showAndWait();
      ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
  }

  public static String getTitolo(){
    return tit;
  }
  public static String getAutore(){
    return aut;
  }

  public static String getAnno(){
    return ann;
  }

  public static String getPagine(){
    return pag;
  }
  public static int getQuantita(){
    return quantita_int;
  }


  public static String getIsbn(){
    return isb;
  }



  public static Float getPrice(){

    return prezzo_float;
  }


  public static String getDescription(){
    return desc;
  }

  /**
   * Chiusura della scena per l'inserimento del nuovo libro
   * @param event
   */
  @FXML
  public void cancel(ActionEvent event) {
    Stage stage = (Stage) annulla.getScene().getWindow();
    stage.close();
  }




  @FXML
  private ComboBox<String> categoria;

  @FXML
  private ComboBox<String> sottocategoria;
  public String categoriaScelta;
  public String sottoCategoriaScelta;
  private String[] categorie = {"Letteratura","Arte e Musica","Biografia","Fumetti","Computer e Tech","Cucina","Educazione","Intrattenimento","Business"};
  @FXML
  public void setSottocategoria(ActionEvent event) {
  }


  /**
   * Questo metodo è un'implementazione del metodo initialize dell'interfaccia Initializable. Esso viene chiamato automaticamente dopo che l'interfaccia utente definita in FXML è stata caricata, e l'oggetto controller è stato istanziato.
   * In questo metodo, viene aggiunto un elenco di elementi all'oggetto categoria di tipo ComboBox. Gli elementi sono passati come parametro alla funzione addAll che li aggiunge all'elenco. Successivamente, viene impostato un listener sull'oggetto categoria che invoca il metodo getChoice quando viene selezionato un elemento dalla lista.
   * In sostanza, il metodo inizializza la ComboBox con l'elenco di categorie e imposta un listener per la selezione dell'utente.
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
    categoria.getItems().addAll(categorie);
    categoria.setOnAction(this::getChoice);


  }

  /**
   * Questo metodo viene chiamato quando l'utente seleziona una categoria dal menu a discesa delle categorie.
   * In base alla categoria selezionata, il metodo cambia le opzioni del menu a discesa della sottocategoria per corrispondere alle sottocategorie appropriate.
   * Ad esempio, se l'utente seleziona "Letteratura", il metodo imposta le opzioni del menu a discesa della sottocategoria per includere "Antologie", "Classici", "Contemporanei", "Lingue Straniere" e "Letterature".
   * Il metodo è implementato utilizzando uno switch-case che esamina il valore della categoria selezionata e imposta di conseguenza le opzioni del menu a discesa della sottocategoria. Il metodo successivo getChoice2 viene poi chiamato quando l'utente seleziona una sottocategoria dal menu a discesa della sottocategoria.
   * @param event
   */
  public void getChoice(ActionEvent event) {
    if (sottocategoria.isDisabled()){
      sottocategoria.setDisable(false);
    }
    categoriaScelta = categoria.getValue();
    switch (categoriaScelta) {
      case "Letteratura" -> {
        String[] sLetteratura = {"Antologie", "Classici", "Contemporanei", "Lingue Straniere", "Letterature"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sLetteratura);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Arte e Musica" -> {
        String[] sArte = {"Storia dell'arte", "Calligrafia", "Disegno", "Fashion Design"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sArte);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Biografia" -> {
        String[] sBiografia = {"Etnica e Culturale", "Europea", "Storica", "Personaggi Famosi e Leader", "Militare"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sBiografia);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Fumetti" -> {
        String[] sFumetti = {"Comici", "Marvel", "Misteriosi", "DC", "Fantasy"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sFumetti);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Computer e Tech" -> {
        String[] sComputer = {"Apple", "CAD", "Certificazioni", "Informatica", "Database"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sComputer);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Cucina" -> {
        String[] sCucina = {"Asiatica", "Cucina Calda", "BBQ", "Arti Culinarie", "Dolci"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sCucina);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Educazione" -> {
        String[] sEducazione = {"Almanacchi", "Atlanti e Mappe", "Cataloghi", "Scolastici"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sEducazione);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Intrattenimento" -> {
        String[] sIntrattenomento = {"Rompicapo", "Barzellette", "Giochi", "Film"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sIntrattenomento);
        sottocategoria.setOnAction(this::getChoice2);
      }
      case "Business" -> {
        String[] sBusiness = {"Carriera", "Economia", "Finanza", "Industria", "Internazionale"};
        sottocategoria.getItems().clear();
        sottocategoria.getItems().addAll(sBusiness);
        sottocategoria.setOnAction(this::getChoice2);
      }
      default -> System.out.println("error");
    }


  }


  /**
   * Il metodo getChoice2() viene chiamato quando viene selezionata una sottocategoria dal menu a tendina sottocategoria. L'evento ActionEvent viene passato come argomento del metodo e viene utilizzato per ottenere il valore della sottocategoria scelta dall'utente tramite il metodo getValue() e assegnarlo alla variabile sottoCategoriaScelta. In pratica, questo metodo memorizza la scelta dell'utente della sottocategoria.
   * @param event
   */
  public void getChoice2(ActionEvent event) {
    sottoCategoriaScelta = sottocategoria.getValue();
  }

}


