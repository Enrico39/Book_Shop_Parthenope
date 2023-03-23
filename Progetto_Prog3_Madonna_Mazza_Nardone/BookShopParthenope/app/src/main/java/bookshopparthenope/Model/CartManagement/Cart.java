package bookshopparthenope.Model.CartManagement;

import bookshopparthenope.Model.UserManagement.DBService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static bookshopparthenope.LoginController.userCart;


/**
 * La classe Cart è una classe che rappresenta un carrello dell'utente. Ha una lista di oggetti CartItem che rappresentano i libri presenti nel carrello e fornisce i metodi per aggiungere un libro al carrello, calcolare il totale del carrello e restituire la lista di elementi del carrello.
 *
 * Il metodo add prende un oggetto CartItem e lo aggiunge alla lista di elementi del carrello. Se il libro è già presente nel carrello dell'utente, il metodo incrementa la quantità di quel libro nel carrello. In caso contrario, il metodo aggiunge il libro al carrello dell'utente con una quantità pari a 1.
 *
 * Il metodo getTotal calcola il totale del carrello sommando il prezzo di tutti i libri presenti.
 *
 * Il metodo getItems restituisce la lista di elementi del carrello.
 *
 * Il metodo aggiungiQuantitaCarrello prende il titolo del libro, la quantità da aggiungere e il nome utente e aggiorna la quantità del libro nel carrello dell'utente specificato.La classe Cart è una classe che rappresenta un carrello dell'utente. Ha una lista di oggetti CartItem che rappresentano i libri presenti nel carrello e fornisce i metodi per aggiungere un libro al carrello, calcolare il totale del carrello e restituire la lista di elementi del carrello.
 *
 * Il metodo add prende un oggetto CartItem e lo aggiunge alla lista di elementi del carrello. Se il libro è già presente nel carrello dell'utente, il metodo incrementa la quantità di quel libro nel carrello. In caso contrario, il metodo aggiunge il libro al carrello dell'utente con una quantità pari a 1.
 *
 * Il metodo getTotal calcola il totale del carrello sommando il prezzo di tutti i libri presenti.
 *
 * Il metodo getItems restituisce la lista di elementi del carrello.
 *
 * Il metodo aggiungiQuantitaCarrello prende il titolo del libro, la quantità da aggiungere e il nome utente e aggiorna la quantità del libro nel carrello dell'utente specificato.
 */
public class Cart {

  private List<CartItem> items;

  /**
   * Questo metodo è il costruttore della classe Cart. Inizializza un nuovo oggetto Cart vuoto, creando una nuova istanza dell'oggetto ArrayList chiamata items per tenere traccia degli elementi del carrello.
   */
  public Cart() {
    items = new ArrayList<>();
  }

  /**
   * Il metodo add aggiunge un oggetto CartItem alla lista items del carrello. Inoltre, controlla se il libro è già presente nel carrello per lo stesso utente, utilizzando il metodo checkCart di DBService.
   * Se il libro è già presente, viene incrementato il numero di copie del libro nel carrello utilizzando il metodo addCopies di DBService. Se il libro non è presente nel carrello, viene aggiunto utilizzando il metodo addToCart di DBService, impostando la quantità iniziale del libro a 1.
   * @param book
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void add(CartItem book) throws SQLException, ClassNotFoundException {
    items.add(book);
    if(DBService.checkCart(book.getTitle(),book.getUsername()))
    {
      DBService.addCopies(book.getTitle(),book.getUsername());
    }
    else
    {
      DBService.addToCart(book.getTitle(),book.getPrice(), book.getAuthor(), book.getUsername(), 1);
    }

  }

  /**
   * Il metodo getTotal() restituisce il totale del prezzo di tutti i libri presenti nel carrello. Per fare ciò, itera attraverso tutti gli elementi del carrello e somma i prezzi dei libri. Il risultato viene restituito come un valore di tipo float.
   * @return
   */
  public float getTotal() {
    float total = 0.0F;
    for (CartItem item : items) {
      total += item.getPrice();
    }
    return total;
  }

  /**
   * Il metodo getItems() restituisce una lista di oggetti CartItem, che rappresentano i libri presenti nel carrello. La lista viene restituita al chiamante del metodo, che può quindi utilizzarla per visualizzare l'elenco dei libri nel carrello o per effettuare altre operazioni su di essi.
   * @return
   */
  public List<CartItem> getItems() {
    return items;
  }

  /**
   * Il metodo aggiungiQuantitaCarrello aggiorna la quantità di un determinato libro già presente nel carrello di un utente.
   * Viene chiamato quando l'utente aggiunge più copie di un libro che già si trova nel suo carrello. Il metodo utilizza la classe DBService per aggiornare il numero di copie del libro nel carrello dell'utente nel database.
   * @param titolo
   * @param quantita
   * @param username
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void aggiungiQuantitaCarrello(String titolo,int quantita, String username) throws SQLException, ClassNotFoundException {
    DBService.changeQT(quantita,userCart,titolo);
  }
}
