package bookshopparthenope.Model.PaymentManagement;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe definisce l'interfaccia PaymentStrategy, che rappresenta una strategia di pagamento. L'interfaccia definisce due metodi:
 *
 * calculateTotal(float amount): calcola il totale dell'importo da pagare sulla base della strategia di pagamento utilizzata.
 * validation(Parent root, ActionEvent event) throws IOException, SQLException, ClassNotFoundException: effettua la validazione del pagamento utilizzando la strategia di pagamento. Questo metodo richiede due parametri: root, che rappresenta il nodo radice dell'interfaccia utente, e event, che rappresenta l'evento di pagamento che ha scatenato la validazione. Il metodo può generare eccezioni di tipo IOException, SQLException e ClassNotFoundException.
 * In altre parole, questa interfaccia definisce un'astrazione per implementare diverse strategie di pagamento, che possono essere utilizzate in modo intercambiabile all'interno del sistema di gestione dei pagamenti.
 */
public interface PaymentStrategy {
    float calculateTotal(float amount);
    void validation(Parent root, ActionEvent event) throws IOException, SQLException, ClassNotFoundException;
}
