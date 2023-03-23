package bookshopparthenope.Model.BookManagement;

import bookshopparthenope.Model.CartManagement.CartItem;
import bookshopparthenope.Model.CartManagement.CartItemVisitor;

import java.sql.SQLException;


/**
 * La classe Book è una classe astratta che rappresenta un libro in un sistema di gestione dei libri.
 * La classe ha vari campi come title, price, author, username e subcategory.
 * Questa classe contiene metodi astratti come getCategory(), insertFactoryBook(), sBook() e metodi concreti come getPrice(), getAuthor(), getUsername() e getTitle().
 * La classe implementa anche l'interfaccia CartItem e utilizza il design pattern Visitor tramite l'implementazione del metodo accept().
 * Questa classe è stata progettata per fornire una base per la creazione di libri specifici, come ad esempio libri di testo, libri di narrativa, libri di cucina, ecc.
 */
public abstract class  Book implements CartItem {
  protected String subcategory;

  /**
   * Questo metodo è un costruttore della classe Book. Quando viene creato un nuovo oggetto Book, il costruttore viene chiamato per inizializzare il valore della variabile di istanza "subcategory" con il valore passato come parametro durante la creazione dell'oggetto. In pratica, questo metodo permette di specificare la sottocategoria di appartenenza del libro al momento della creazione dell'oggetto.
   * @param subcategory
   */
  public Book (String subcategory) {
    this.subcategory = subcategory;
  }

  public abstract String getCategory();
  public abstract void insertFactoryBook() throws SQLException, ClassNotFoundException;

  public abstract Book sBook() ;

  private String title;
  private float price;
  String author;
  String username;

  /**
   * Questo codice definisce un costruttore per la classe Book che accetta due parametri: title e price. Questi parametri vengono usati per inizializzare le proprietà title e price dell'oggetto Book. In altre parole, quando si crea un nuovo oggetto Book usando questo costruttore, si deve specificare il titolo e il prezzo del libro, che vengono poi assegnati alle rispettive proprietà dell'oggetto.
   * @param title
   * @param price
   */
  public Book(String title, float price) {
    this.title = title;
    this.price=price;
  }

  /**
   * Il metodo getPrice() restituisce il prezzo del libro come un valore di tipo float. In altre parole, questo metodo viene utilizzato per ottenere il prezzo del libro.
   * @return
   */
  public float getPrice() {
    return price;
  }

  /**
   * Il metodo getAuthor() restituisce l'autore del libro come tipo String. In altre parole, questo metodo viene utilizzato per ottenere l'autore del libro.
   * @return
   */
  public String getAuthor() {

    return author;
  }


  /**
   * Il metodo getUsername() restituisce l'username del libro come tipo String. In altre parole, questo metodo viene utilizzato per ottenere l'username dell'utente.
   * @return
   */
  public String getUsername() {
    return username;
  }


  /**
   * Il metodo getTitle() restituisce il titolo del libro come tipo String. In altre parole, questo metodo viene utilizzato per ottenere il titolo del libro.
   * @return
   */
  public String getTitle() {
    return title;
  }

  /**
   * Questo metodo implementa il design pattern Visitor ed è utilizzato per accettare un oggetto di tipo CartItemVisitor che visiti l'oggetto di tipo Book su cui è invocato il metodo. In questo caso, il metodo visit() del CartItemVisitor viene chiamato passando l'oggetto Book come parametro. L'utilizzo di questo pattern consente di separare l'algoritmo di elaborazione dei dati dall'oggetto su cui viene eseguito e di fornire una struttura flessibile ed estensibile per l'aggiunta di nuovi visitatori e nuovi elementi visitabili.
   * @param visitor
   */
  @Override
  public void accept(CartItemVisitor visitor){
    visitor.visit(this);
  }
}
