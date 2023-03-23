package bookshopparthenope.Model.BookManagement;

import bookshopparthenope.Model.BookManagement.Category.*;
import bookshopparthenope.Model.CartManagement.CartItem;
import bookshopparthenope.Model.CartManagement.CartItemVisitor;

/**
 * Questa classe si chiama "BookFactory" ed è responsabile della creazione di oggetti libro di diverse categorie. La classe contiene un metodo pubblico "createBook" che prende in input una categoria e una sottocategoria come stringhe e restituisce un oggetto libro della classe corrispondente. La classe implementa anche l'interfaccia "CartItem" e il metodo "accept" che accetta un oggetto "CartItemVisitor" e richiama il metodo "visit" su di esso, passando se stesso come argomento.
 * La classe contiene anche alcuni campi privati come "username", "title", "author" e "price" e un costruttore che accetta questi campi come argomenti e li utilizza per creare un oggetto "BookFactory".
 */
public class BookFactory implements CartItem  {
  public BookFactory() {

  }

  /**
   * Questo metodo "createBook" prende in input una categoria e una sottocategoria come stringhe e restituisce un oggetto "Book" della categoria corrispondente. Utilizza la struttura di controllo "switch" per selezionare la categoria corretta e creare un nuovo oggetto libro della classe corrispondente.
   * In questo caso, la classe "BookFactory" è una fabbrica di oggetti libro che si basa sulle categorie specificate per creare nuovi oggetti libro. Le categorie disponibili includono "Letteratura", "Arte e Musica", "Biografia", "Business", "Fumetti", "Computer e Tech", "Cucina", "Educazione" e "Intrattenimento". Se viene specificata una categoria non valida, il metodo restituisce "null".
   * @param category
   * @param subcategory
   * @return
   */
  public Book createBook(String category, String subcategory) {
    return switch (category) {
      case "Letteratura" -> new LetteraturaBook(subcategory);
      case "Arte e Musica" -> new ArteemusicaBook(subcategory);
      case "Biografia" -> new BiografiaBook(subcategory);
      case "Business" -> new BusinessBook(subcategory);
      case "Fumetti" -> new FumettiBook(subcategory);
      case "Computer e Tech" -> new ComputeretechBook(subcategory);
      case "Cucina" -> new CucinaBook(subcategory);
      case "Educazione" -> new EducazioneBook(subcategory);
      case "Intrattenimento" -> new IntrattenimentoBook(subcategory);
      default -> null;
    };
  }
  private String username;
  private String title;
  private String author;
  private double price;

  /**
   * Questo metodo è un costruttore per la classe "BookFactory" e viene utilizzato per creare nuovi oggetti "BookFactory" e inizializzarli con i parametri specificati.
   * In particolare, il costruttore accetta quattro argomenti: "username", "title", "author" e "price", che rappresentano rispettivamente lo username dell'utente che ha creato l'oggetto libro, il titolo del libro, l'autore del libro e il prezzo del libro.
   * Il costruttore utilizza il "this" keyword per riferirsi ai campi dell'oggetto corrente e assegnare i valori degli argomenti ai campi corrispondenti.
   * @param username
   * @param title
   * @param author
   * @param price
   */
  public BookFactory(String username, String title, String author, float price) {
    this.username=username;
    this.author=author;
    this.title = title;
    this.price=price;

  }

  public float getPrice() {
    return (float) price;
  }
  public String getAuthor() {
    return author;
  }

  public String getUsername() {
    return username;
  }
  public String getTitle() {
    return title;
  }

  /**
   * Questo metodo "accept" è un metodo di implementazione dell'interfaccia "CartItem" e viene utilizzato per accettare un "visitor" (visitatore) che implementa l'interfaccia "CartItemVisitor".
   *
   * L'interfaccia "CartItemVisitor" definisce una serie di metodi "visit" che possono essere utilizzati per visitare oggetti di diverse classi che implementano l'interfaccia "CartItem".
   *
   * Nel metodo "accept", viene chiamato il metodo "visit" del visitatore passando l'oggetto corrente ("this") come parametro. In questo modo, il visitatore può accedere alle informazioni dell'oggetto e eseguire operazioni specifiche su di esso.
   *
   * In generale, il pattern di progettazione "Visitor" viene utilizzato per separare l'algoritmo di elaborazione dei dati dall'oggetto stesso. In questo caso, l'interfaccia "CartItemVisitor" rappresenta l'algoritmo di elaborazione e viene utilizzata per visitare gli oggetti "CartItem" di diverse classi, inclusi gli oggetti "Book" prodotti dalla classe "BookFactory".Questo metodo "accept" è un metodo di implementazione dell'interfaccia "CartItem" e viene utilizzato per accettare un "visitor" (visitatore) che implementa l'interfaccia "CartItemVisitor".
   *
   * L'interfaccia "CartItemVisitor" definisce una serie di metodi "visit" che possono essere utilizzati per visitare oggetti di diverse classi che implementano l'interfaccia "CartItem".
   *
   * Nel metodo "accept", viene chiamato il metodo "visit" del visitatore passando l'oggetto corrente ("this") come parametro. In questo modo, il visitatore può accedere alle informazioni dell'oggetto e eseguire operazioni specifiche su di esso.
   *
   * In generale, il pattern di progettazione "Visitor" viene utilizzato per separare l'algoritmo di elaborazione dei dati dall'oggetto stesso. In questo caso, l'interfaccia "CartItemVisitor" rappresenta l'algoritmo di elaborazione e viene utilizzata per visitare gli oggetti "CartItem" di diverse classi, inclusi gli oggetti "Book" prodotti dalla classe "BookFactory".
   * @param visitor
   */
  @Override
  public void accept(CartItemVisitor visitor){
    visitor.visit(this);
  }



}
