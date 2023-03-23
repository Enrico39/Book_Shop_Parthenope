

package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

/**
 * Questo codice definisce la classe "ArteemusicaBook" che estende la classe "Book". La classe "ArteemusicaBook" rappresenta un libro della categoria "Arte e Musica".
 *
 * Il costruttore della classe "ArteemusicaBook" prende in input una sottocategoria di "Arte e Musica" e lo passa al costruttore della classe "Book" tramite la keyword "super".
 *
 * Il metodo "sBook" restituisce un oggetto di una sottocategoria specifica di "Arte e Musica", a seconda del valore della variabile "subcategory". Ad esempio, se "subcategory" è uguale a "Disegno", il metodo restituirà un oggetto di tipo "Disegno" (che è una classe che estende "ArteemusicaBook").
 *
 * Il metodo "getCategory" restituisce il nome della categoria "Arte e Musica".
 *
 * Infine, il metodo "insertFactoryBook" non fa nulla e viene lasciato vuoto.
 *
 *
 * ATTENZIONE: TALE COMMENTO E' VALIDO ANCHE PER LE ALTRE CLASSI NELLA DIRECTORY "Category".
 */
public class ArteemusicaBook extends Book {
  public ArteemusicaBook(String subcategory) {
    super(subcategory); }

  public ArteemusicaBook sBook(){
    return switch (subcategory) {
      case "Storia dell'arte" -> new Storiadellarte();
      case "Calligrafia" -> new Calligrafia();
      case "Disegno" -> new Disegno();
      case "Fashion Design" -> new Fashiondesign();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Arte e Musica"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}
