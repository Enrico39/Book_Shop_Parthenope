package bookshopparthenope.Model.PaymentManagement;

import bookshopparthenope.FxmlController;
import bookshopparthenope.Model.UserManagement.DBService;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static bookshopparthenope.LoginController.userCart;

/**
 * Questa classe rappresenta una delle possibili strategie di pagamento implementate dal sistema di pagamento dell'applicazione. In particolare, la classe BancomatPay implementa l'interfaccia PaymentStrategy e quindi deve fornire l'implementazione dei suoi due metodi astratti: calculateTotal() e validation().
 */
public class BancomatPay implements PaymentStrategy{

  /**
   * Il metodo calculateTotal() è utilizzato per calcolare il totale dell'importo da pagare. In questo caso, l'importo da pagare viene incrementato di una commissione fissa di 4,9 euro.
   * @param amount
   * @return
   */
  @Override
  public float calculateTotal(float amount) {
    // implement logic to calculate total amount for Debit Card payment
    return amount + 4.9F;
  }


  /**
   * Il metodo validation() viene utilizzato per effettuare la validazione del pagamento con Bancomat.
   * In caso di conferma da parte dell'utente, viene mostrato un messaggio di conferma dell'ordine e viene invocato il metodo addToOrderList() della classe DBService per aggiungere l'ordine alla lista degli ordini.
   * Successivamente, viene invocato il metodo copyCartToOrder() per copiare il contenuto del carrello dell'utente nell'ordine, il metodo updateBookDisponibility() per aggiornare la disponibilità dei libri acquistati e infine il metodo deleteAllCart() per cancellare il contenuto del carrello dell'utente.
   * @param root
   * @param event
   * @throws IOException
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Override
  public void validation(Parent root, ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
    alertconfirm.setTitle("Conferma acquisto");
    alertconfirm.setHeaderText("Sei sicuro di voler procedere all'invio dell'ordine pagando con Bancomat?");
    alertconfirm.setContentText("Questa azione non puo' essere annullata.");

    Optional<ButtonType> result = alertconfirm.showAndWait();
    if (result.get() == ButtonType.OK) {
      Alert alertok = new Alert(Alert.AlertType.INFORMATION);
      alertok.setTitle("Ordine Inviato!");
      alertok.setHeaderText("Hai inviato correttamente il tuo ordine!");
      alertok.showAndWait();
      String cod_ordine=DBService.addToOrderList(userCart,"Bancomat");
      DBService.copyCartToOrder(userCart,cod_ordine);
      DBService.updateBookDisponibility(userCart);
      DBService.deleteAllCart(userCart);
/**
 * viene invocato il metodo home() della classe FxmlController per tornare alla schermata principale dell'applicazione.
 */
      FxmlController controller= new FxmlController();
      controller.home(root, event);
    }
  }
}
