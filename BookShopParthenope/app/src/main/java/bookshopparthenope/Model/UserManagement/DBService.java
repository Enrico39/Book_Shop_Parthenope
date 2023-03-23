package bookshopparthenope.Model.UserManagement;

import bookshopparthenope.Model.LoginRegisterManagementDAO.UserDAO;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;

/**
 * La classe DBService è una classe di utility per implementare l'interazione con un database MySQL. La classe implementa l'interfaccia UserDAO, che fornisce i metodi per eseguire le operazioni CRUD sul database per la gestione degli utenti.
 * La classe contiene metodi per inserire, eliminare e recuperare informazioni sui libri dal database. In particolare, ci sono metodi per inserire un nuovo libro nel database, recuperare i dettagli di un libro, mostrare tutti i libri presenti nel database e mostrarne solo alcuni in base alla categoria, all'autore o ad altre specifiche. Inoltre, la classe implementa anche un metodo per la ricerca dei libri tramite una stringa iniziale del titolo.
 * La classe utilizza la classe java.sql per creare una connessione con il database e per eseguire le query. La maggior parte dei metodi restituisce un oggetto ResultSet che contiene i dati recuperati dal database.
 */


public class DBService implements UserDAO {
  public static final int MYSQL_DUPLICATE_PK = 1062;

  /**
   * Questo metodo è una funzione statica che restituisce una connessione a un database MySQL. Il metodo accetta due eccezioni controllate, ClassNotFoundException e SQLException, che devono essere gestite dal chiamante.
   * Il metodo usa la classe java.sql.DriverManager per connettersi al database MySQL. Il driver MySQL viene caricato utilizzando il metodo Class.forName(), che richiede il nome completo della classe del driver. Nel codice, il driver utilizzato è "com.mysql.cj.jdbc.Driver".
   * La stringa di connessione utilizzata per la connessione al database è "jdbc:mysql://localhost:3306/bsv2". Questa stringa indica il tipo di database, l'indirizzo del server MySQL (in questo caso, "localhost") e la porta utilizzata per la connessione (in questo caso, 3306). Il nome del database a cui connettersi è "bsv2".
   * Infine, il metodo restituisce un'istanza di java.sql.Connection che rappresenta la connessione al database. La connessione viene creata utilizzando il metodo getConnection() di DriverManager, che richiede la stringa di connessione, il nome utente e la password per accedere al database. Nel codice, il nome utente utilizzato è "root" e la password è una stringa vuota ("").
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static Connection connect() throws ClassNotFoundException, SQLException {
    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost:3306/bsv2";
    Class.forName(myDriver);
    return DriverManager.getConnection(myUrl, "root", "");
  }

  /**
   * Questo metodo è utilizzato per inserire un nuovo libro nel database "bsv2". Utilizza il metodo "connect()" per ottenere una connessione al database, quindi esegue una query SQL INSERT per inserire un nuovo record nella tabella "LIBRO". I parametri di input del metodo (TITOLO, AUTORE, ANNO_PUBBLICAZIONE, NUM_PAGINE, QTA_DISP, CATEGORIA, SOTTOCATEGORIA, ISBN, PREZZO, DESCRIZIONE) sono utilizzati per popolare le colonne della tabella. Il metodo utilizza anche un oggetto PreparedStatement per evitare attacchi SQL injection e lancia un'eccezione SQLException in caso di errori durante l'inserimento del libro nel database.
   * @param TITOLO
   * @param AUTORE
   * @param ANNO_PUBBLICAZIONE
   * @param NUM_PAGINE
   * @param QTA_DISP
   * @param CATEGORIA
   * @param SOTTOCATEGORIA
   * @param ISBN
   * @param PREZZO
   * @param DESCRIZIONE
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void insertBook(String TITOLO, String AUTORE, String ANNO_PUBBLICAZIONE, String NUM_PAGINE, int QTA_DISP, String CATEGORIA, String SOTTOCATEGORIA, String ISBN, float PREZZO, String DESCRIZIONE) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "INSERT INTO LIBRO (ID,ANNO_PUBBLICAZIONE,CATEGORIA,NUM_PAGINE,ISBN,TITOLO,AUTORE,QTA_DISP,PREZZO,SOTTOCATEGORIA,DESCRIZIONE) values ( default,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, ANNO_PUBBLICAZIONE);
    statement.setString(2, CATEGORIA);
    statement.setString(3, NUM_PAGINE);
    statement.setString(4, ISBN);
    statement.setString(5, TITOLO);
    statement.setString(6, AUTORE);
    statement.setInt(7, QTA_DISP);
    statement.setFloat(8, PREZZO);
    statement.setString(9, SOTTOCATEGORIA);
    statement.setString(10, DESCRIZIONE);
    statement.executeUpdate();
  }


  /**
   *Questo metodo si connette al database, esegue una query SQL per recuperare il titolo, l'autore e il prezzo di tutti i libri che appartengono ad una determinata sotto-categoria e restituisce un ResultSet contenente i dati.
   *  La sotto-categoria viene passata come argomento al metodo e viene utilizzata per filtrare i risultati della query attraverso la clausola WHERE nella query SQL. Il ResultSet restituito può quindi essere utilizzato per visualizzare l'elenco dei libri all'utente.
   * @param SOTTOCATEGORIA
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showBooksToUser(String SOTTOCATEGORIA) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,PREZZO from Libro where SOTTOCATEGORIA = ? ORDER BY titolo";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, SOTTOCATEGORIA);
    ResultSet rs = statement.executeQuery();
    return rs;
  }


  /**
   *Questo codice è un metodo che ritorna un oggetto ResultSet contenente l'elenco di tutti i libri presenti nel database. In particolare, la query SQL seleziona il titolo, l'ISBN, l'autore, l'anno di pubblicazione, il numero di pagine, la categoria, la sottocategoria, il prezzo e la quantità disponibile di tutti i libri nella tabella "Libro" e li ordina in base al titolo.
   * Il metodo utilizza un oggetto Connection per connettersi al database e un oggetto Statement per eseguire la query. Inoltre, il tipo di ResultSet viene impostato su "TYPE_SCROLL_SENSITIVE" per consentire la scorrimento dei risultati in avanti e all'indietro e "CONCUR_UPDATABLE" per consentire l'aggiornamento dei dati del ResultSet. Infine, la query viene eseguita e il ResultSet risultante viene restituito dal metodo.
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showBooksToAdmin() throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,ISBN,autore,ANNO_PUBBLICAZIONE,NUM_PAGINE,CATEGORIA,SOTTOCATEGORIA,PREZZO,QTA_DISP from Libro  ORDER BY titolo";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }


  /**
   *Questo codice definisce un metodo chiamato showBooksDetails che prende in input il titolo di un libro e restituisce un ResultSet che contiene i dettagli del libro corrispondente.
   * Il metodo si connette al database utilizzando il metodo connect definito altrove. Quindi esegue una query SQL che seleziona i campi titolo, autore, isbn, PREZZO, QTA_DISP, descrizione, anno_pubblicazione, Num_pagine dalla tabella Libro, dove il campo titolo corrisponde al titolo passato come parametro.
   * Viene poi eseguito un controllo per verificare se il resultset contiene almeno una riga, e se sì, il metodo restituisce il resultset contenente i dati del libro corrispondente. Se il resultset è vuoto, viene restituito null.
   * @param titolo
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showBooksDetails(String titolo) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,isbn,PREZZO,QTA_DISP,descrizione,anno_pubblicazione,Num_pagine from Libro where titolo = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, titolo);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs;
    } else return null;
  }


  /**
   *Il metodo "eliminaLibro" riceve come parametro una stringa "bookTitle" che rappresenta il titolo del libro che deve essere eliminato dal database.
   * Il metodo crea una connessione al database chiamando il metodo "connect" e poi esegue una query di eliminazione dei dati attraverso la creazione di un oggetto PreparedStatement e l'impostazione del parametro "bookTitle".
   * Infine, il metodo esegue l'eliminazione effettiva dei dati chiamando il metodo "executeUpdate" sull'oggetto PreparedStatement. In questo modo, il libro con il titolo specificato viene eliminato dal database.Il metodo "eliminaLibro" riceve come parametro una stringa "bookTitle" che rappresenta il titolo del libro che deve essere eliminato dal database.
   * Il metodo crea una connessione al database chiamando il metodo "connect" e poi esegue una query di eliminazione dei dati attraverso la creazione di un oggetto PreparedStatement e l'impostazione del parametro "bookTitle".
   * Infine, il metodo esegue l'eliminazione effettiva dei dati chiamando il metodo "executeUpdate" sull'oggetto PreparedStatement. In questo modo, il libro con il titolo specificato viene eliminato dal database.
   * @param bookTitle
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void eliminaLibro(String bookTitle) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "DELETE FROM libro WHERE titolo =?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, bookTitle);
    statement.executeUpdate();
  }


  /**
   * Questo metodo inserisce un libro nel carrello di un utente specifico, specificando il titolo del libro, il prezzo, l'autore, il nome utente e il numero di copie da acquistare.
   * Il metodo si connette al database, esegue una query SQL per inserire i dettagli del libro nel carrello dell'utente e chiude la connessione al database.
   * @param bookTitle
   * @param bookPrice
   * @param bookAuthor
   * @param username
   * @param bookCopies
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void addToCart(String bookTitle, float bookPrice, String bookAuthor, String username, int bookCopies) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "INSERT INTO carrello (id_carr,USERNAME,TITOLO,AUTORE,PREZZO,COPIE) values ( default,?,?,?,?,?)";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, username);
    statement.setString(2, bookTitle);
    statement.setString(3, bookAuthor);
    statement.setFloat(4, bookPrice);
    statement.setInt(5, bookCopies);
    statement.executeUpdate();
  }


  /**
   * Questo metodo esegue una query SQL sulla tabella "Libro" del database per selezionare i campi "titolo", "autore" e "PREZZO" di tutti i libri, in ordine alfabetico per titolo. Restituisce un oggetto ResultSet contenente i risultati della query.
   * Questi risultati possono essere utilizzati per visualizzare l'elenco completo dei libri disponibili per l'utente.
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showAllBooksToUser() throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,PREZZO from Libro  ORDER BY titolo";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }

  /**
   * Il metodo randomBooksDetails() seleziona casualmente un libro dalla tabella Libro del database e ne restituisce i dettagli come un oggetto ResultSet. La query SQL seleziona un record casuale utilizzando la clausola ORDER BY RAND() LIMIT 1, che ordina i record in modo casuale e ne seleziona solo il primo.
   * Se la query restituisce un risultato, viene restituito l'oggetto ResultSet che contiene i dettagli del libro, altrimenti viene restituito null.
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet randomBooksDetails() throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,isbn,PREZZO,QTA_DISP,descrizione,anno_pubblicazione,Num_pagine from Libro  ORDER BY RAND() LIMIT 1";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    if (rs.next()) {
      return rs;
    } else return null;
  }

  /**
   * Il metodo "searchBookByTitle" esegue una ricerca dei libri nel database in base al titolo del libro. Prende in input una stringa "InizioTitolo" che rappresenta il titolo (o la parte iniziale del titolo) del libro che si vuole cercare. Viene quindi eseguita una query SQL sul database che restituisce i libri il cui titolo contiene la stringa "InizioTitolo" (la ricerca viene fatta tramite l'operatore LIKE con la stringa cercata inserita tra i simboli %). Infine, i risultati della query vengono restituiti sotto forma di ResultSet.
   * @param InizioTitolo
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet searchBookByTitle(String InizioTitolo) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo, autore, prezzo FROM Libro WHERE titolo LIKE '%" + InizioTitolo + "%' ORDER BY titolo";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }


  /**
   * questo metodo aggiorna la quantità di un libro nel database, prendendo in input il titolo del libro e la nuova quantità.
   * @param bookTitle
   * @param quantity
   */
  public static void updateLibroQuantity(String bookTitle, Object quantity) {
  }


  /**
   *  questo metodo restituisce le informazioni sull'utente, come nome, cognome ed email, dato il suo username, prendendo in input lo username.
   * @param userCart
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet getUserInfo(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT name, surname,email FROM customer WHERE username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs;
    } else return null;
  }


  /**
   * questo metodo restituisce un ResultSet contenente i libri che hanno nel titolo la stringa passata come parametro
   * @param InizioTitolo
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet searchBookByTitleAdmin(String InizioTitolo) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,ISBN,autore,ANNO_PUBBLICAZIONE,NUM_PAGINE,CATEGORIA,SOTTOCATEGORIA,PREZZO,QTA_DISP FROM Libro WHERE titolo LIKE '%" + InizioTitolo + "%' ORDER BY titolo";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }


  /**
   * questo metodo restituisce un ResultSet contenente i libri che non sono al momento disponibili (la quantità disponibile è zero). La query viene eseguita nel database e l'output viene ordinato per titolo.
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet searchBookOOS() throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,ISBN,autore,ANNO_PUBBLICAZIONE,NUM_PAGINE,CATEGORIA,SOTTOCATEGORIA,PREZZO,QTA_DISP FROM Libro WHERE qta_disp = 0 ORDER BY titolo";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }


  /**
   *  questo metodo restituisce un ResultSet contenente i libri presenti nel carrello di uno specifico utente. La query viene eseguita nel database e l'output viene ordinato per titolo.
   * @param userCart
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showCartBooks(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,PREZZO,COPIE FROM carrello WHERE username=? order by titolo";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    ResultSet rs = statement.executeQuery();
    return rs;

  }


  /**
   * questo metodo controlla se un libro è già presente nel carrello di un utente, prendendo in input il titolo del libro e lo username dell'utente. Restituisce true se il libro è presente, false altrimenti.
   * @param title
   * @param username
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static boolean checkCart(String title, String username) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT * FROM carrello WHERE username=? and titolo=?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, username);
    statement.setString(2, title);
    ResultSet rs = statement.executeQuery();
    if (rs.next())
      return true;
    else
      return false;
  }

  /**
   * questo metodo aggiunge una copia di un libro al carrello di uno specifico utente, prendendo in input il titolo del libro e lo username dell'utente.
   * @param title
   * @param username
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void addCopies(String title, String username) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "UPDATE carrello SET copie = copie + 1 WHERE titolo = ? AND username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, title);
    statement.setString(2, username);
    statement.executeUpdate();
  }


  /**
   * questo metodo rimuove un libro dal carrello di uno specifico utente, prendendo in input lo username dell'utente e il titolo del libro da rimuovere.
   * @param userCart
   * @param bookTitle
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void deleteCart(String userCart, String bookTitle) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = " DELETE FROM carrello  WHERE titolo = ? AND username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, bookTitle);
    statement.setString(2, userCart);
    statement.executeUpdate();
  }


  /**
   * questo metodo cambia la quantità di un libro nel carrello di uno specifico utente, prendendo in input il numero di copie, lo username dell'utente e il titolo del libro.
   * @param nCopie
   * @param userCart
   * @param bookTitle
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void changeQT(Integer nCopie, String userCart, String bookTitle) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "UPDATE carrello SET copie = ? WHERE titolo = ? AND username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setInt(1, nCopie);
    statement.setString(2, bookTitle);
    statement.setString(3, userCart);
    statement.executeUpdate();
  }


  /**
   * questo metodo aggiunge un certo valore alla quantità disponibile di un libro nel database, prendendo in input il titolo del libro e il valore da aggiungere.
   * @param value
   * @param bookTitle
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void addQtToBook(Integer value, String bookTitle) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "UPDATE libro SET QTA_DISP = QTA_DISP + ? WHERE titolo = ? ";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setInt(1, value);
    statement.setString(2, bookTitle);
    statement.executeUpdate();
  }


  /**
   *  questo metodo restituisce la somma totale dei prezzi dei libri presenti nel carrello di uno specifico utente, prendendo in input lo username dell'utente.
   * @param userCart
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static float totalSum(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT SUM(prezzo * copie) as total FROM carrello WHERE username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs.getFloat(1);

    }

    return 0;
  }


  /**
   * questo metodo restituisce il numero di prodotti presenti nel carrello di uno specifico utente, prendendo in input lo username dell'utente.
   * @param userCart
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static int countproducts(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT COUNT(*) FROM carrello WHERE username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs.getInt(1);

    }

    return 0;
  }


  /**
   * questo metodo rimuove tutti i libri presenti nel carrello di uno specifico utente, prendendo in input lo username dell'utente.
   * @param userCart
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void deleteAllCart(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = " DELETE FROM carrello WHERE username = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    statement.executeUpdate();
  }


  /**
   * questo metodo aggiunge un nuovo ordine nella tabella
   * @param userCart
   * @param metodo
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static String addToOrderList(String userCart, String metodo) throws SQLException, ClassNotFoundException {
    SecureRandom random = new SecureRandom();
    String randomString = new BigInteger(130, random).toString(32).substring(0, 10).toUpperCase();
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    Connection conn = connect();
    String query = " insert into ordine(METODO,DATA,CODICE,USERNAME) VALUES(?,?,?,?) ";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, metodo);
    statement.setDate(2, sqlDate);
    statement.setString(3, randomString);
    statement.setString(4, userCart);
    statement.executeUpdate();
    return randomString;
  }

  /**
   * Aggiunge un nuovo prodotto all'ordine nel database con il codice dell'ordine specificato, titolo, autore, prezzo e quantità.
   * @param codiceOrdine
   * @param titolo
   * @param autore
   * @param prezzo
   * @param qt
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet addToProductList(String codiceOrdine, String titolo, String autore, float prezzo, int qt) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = " insert into prodotto_ordine(id,codice_ordine,qta,prezzo,autore,titolo) VALUES(default,?,?,?,?,?)";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, codiceOrdine);
    statement.setInt(2, qt);
    statement.setFloat(3, prezzo);
    statement.setString(4, autore);
    statement.setString(5, titolo);
    statement.executeUpdate();
    return null;
  }


  /**
   * Cerca nel database il titolo del libro specificato e restituisce la quantità disponibile di quel libro.
   * @param titolo
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static int verificaDisponibilita(String titolo) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = " Select qta_disp from libro where titolo=?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, titolo);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs.getInt(1);

    }
    return 0;
  }


  /**
   * Copia tutti i prodotti presenti nel carrello di un utente specifico in un nuovo ordine identificato dal codice dell'ordine specificato.
   * @param userCart
   * @param codiceOrdine
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void copyCartToOrder(String userCart, String codiceOrdine) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "INSERT INTO prodotto_ordine ( codice_ordine,qta,prezzo,autore,titolo) " +
            "SELECT '" + codiceOrdine + "', copie,prezzo,autore,titolo FROM carrello WHERE username = ?";

    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    statement.executeUpdate();

  }


  /**
   *  Restituisce un insieme di risultati contenente tutti gli username dei clienti registrati, in ordine alfabetico.
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showUsersList() throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT username from customer ORDER BY username";
    Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = st.executeQuery(query);
    return rs;
  }


  /**
   * Restituisce un insieme di risultati contenente tutti i codici degli ordini, i metodi di pagamento e le date degli ordini associati a un determinato username, in ordine cronologico.
   * @param username
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet showOrderList(String username) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT codice,metodo,data from ordine  where username=? ORDER BY data";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, username);
    ResultSet rs = statement.executeQuery();
    return rs;
  }


  /**
   * Restituisce un insieme di risultati contenente tutti i prodotti associati al codice dell'ordine specificato, in ordine alfabetico per titolo.
   * @param codice
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */

  public static ResultSet showProductList(String codice) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT titolo,autore,qta,prezzo from prodotto_ordine where codice_ordine=? ORDER BY titolo";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, codice);
    ResultSet rs = statement.executeQuery();

    return rs;
  }


  /**
   * Aggiorna la quantità disponibile per ogni libro presente nel carrello dell'utente specificato, sottraendo il numero di copie richieste per ogni libro dal totale delle copie disponibili.
   * @param userCart
   * @throws SQLException
   * @throws ClassNotFoundException
   */

  public static void updateBookDisponibility(String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "UPDATE libro SET qta_disp = qta_disp - (SELECT copie FROM carrello " +
            "WHERE carrello.titolo = libro.titolo) WHERE libro.titolo IN ( SELECT titolo FROM carrello where username=?)";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userCart);
    statement.executeUpdate();
  }

  /**
   * Restituisce il numero di copie richieste per un libro specifico presente nel carrello dell'utente specificato.
   * @param bookTitle
   * @param userCart
   * @return
   * @throws SQLException
   * @throws ClassNotFoundException
   */

  public static int requestedCopies(String bookTitle, String userCart) throws SQLException, ClassNotFoundException {
    Connection conn = connect();
    String query = "SELECT COPIE from carrello where titolo=? AND username=? ";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, bookTitle);
    statement.setString(2,userCart);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
      return rs.getInt("COPIE");
    } else {
      return 0;
    }
  }
}

