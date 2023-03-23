package bookshopparthenope.Model.CartManagement;

import bookshopparthenope.Model.UserManagement.DBService;

import java.sql.SQLException;


/**
 * Questa classe implementa un'interfaccia "CommandQuantity" e rappresenta il comando di aggiunta di una certa quantità di un libro al carrello di un utente.
 * Nel costruttore vengono passati come parametri il carrello, il titolo del libro, l'username dell'utente e la quantità da aggiungere. Il metodo "execute" controlla la disponibilità del libro nel database attraverso la classe DBService e, se la quantità richiesta è disponibile, aggiunge la quantità al carrello. In caso contrario, ritorna la disponibilità effettiva del libro. Il metodo "execute" può generare un'eccezione di tipo SQLException o ClassNotFoundException, in caso di errori di accesso al database.
 */
public class AggiungiQuantitaCommand implements CommandQuantity {
    private Cart carrello;
    private String titolo;
    private String username;
    private int quantita;


    /**
     * Il metodo è un costruttore della classe AggiungiQuantitaCommand e ha come obiettivo di inizializzare i parametri della classe con i valori passati come argomenti al momento della creazione di un oggetto di tipo AggiungiQuantitaCommand.
     * In particolare, il metodo riceve come parametri un oggetto Cart rappresentante il carrello dell'utente, una stringa titolo rappresentante il titolo del libro a cui si vuole aggiungere la quantità, una stringa username rappresentante l'username dell'utente che sta effettuando l'operazione e un intero quantita rappresentante la quantità di libri che si vuole aggiungere al carrello.
     * Il metodo assegna quindi i valori dei parametri alle variabili di istanza corrispondenti tramite l'uso del riferimento this.
     * @param carrello
     * @param titolo
     * @param username
     * @param quantita
     */
    public  AggiungiQuantitaCommand(Cart carrello, String titolo, String username, int quantita) {
        this.carrello = carrello;
        this.titolo = titolo;
        this.username = username;
        this.quantita = quantita;

    }


    /**
     * Il metodo execute() implementa l'interfaccia CommandQuantity e aggiunge una certa quantità di un libro nel carrello dell'utente. Il metodo prima controlla se la quantità richiesta è disponibile nel database utilizzando il metodo verificaDisponibilita() della classe DBService. Se la quantità richiesta supera la disponibilità, il metodo restituisce la quantità disponibile.
     * Altrimenti, aggiunge la quantità richiesta al carrello utilizzando il metodo aggiungiQuantitaCarrello() della classe Cart e restituisce il valore -1. Inoltre, il metodo può generare un'eccezione SQLException o ClassNotFoundException se ci sono problemi durante l'interazione con il database.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public int execute () throws SQLException, ClassNotFoundException {
        int disponibilita = DBService.verificaDisponibilita(titolo);
        if (disponibilita < quantita) {
            return disponibilita;
        } else {
            carrello.aggiungiQuantitaCarrello(titolo, quantita, username);
            return -1;

        }
    }
}
