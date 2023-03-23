package bookshopparthenope.Model.CartManagement;

import java.sql.SQLException;

/**
 * Questa classe è un'interfaccia che definisce un contratto per le classi che implementano la logica di business per modificare la quantità di un prodotto nel carrello di un utente. La classe definisce solo un metodo, execute(), che deve essere implementato dalle classi che implementano l'interfaccia.
 * Tale metodo restituisce un intero che rappresenta la disponibilità del prodotto nel magazzino, se la quantità richiesta è superiore a quella disponibile, altrimenti restituisce -1. La classe può generare eccezioni di tipo SQLException e ClassNotFoundException.
 */
public interface CommandQuantity {
    int execute() throws SQLException, ClassNotFoundException;
}
