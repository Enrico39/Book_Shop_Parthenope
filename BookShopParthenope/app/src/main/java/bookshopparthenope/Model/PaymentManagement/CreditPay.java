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
 * La classe CreditPay implementa l'interfaccia PaymentStrategy ed è una strategia di pagamento con carta di credito.
 */
public class CreditPay implements PaymentStrategy {

  /**
   *  La classe implementa il metodo calculateTotal per calcolare il totale dell'importo dell'ordine più le eventuali spese di spedizione. In particolare, aggiunge una commissione fissa di 4.9F al prezzo dell'ordine.
   * @param amount
   * @return
   */
  @Override
  public float calculateTotal(float amount) {
    // implement logic to calculate total amount for Credit Card payment
    return amount + 4.9F;
  }


  /**
   * Il metodo validation si occupa di gestire la validazione del pagamento con carta di credito.
   * Viene mostrata una finestra di conferma che chiede all'utente di confermare l'acquisto e di procedere con il pagamento con carta di credito.
   * In caso di conferma, viene mostrata una finestra di dialogo di conferma dell'ordine inviato correttamente e successivamente, il metodo aggiunge l'ordine alla lista degli ordini e aggiorna lo stato della disponibilità dei libri nel carrello.
   * Infine, il metodo cancella il carrello dell'utente.
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
    alertconfirm.setHeaderText("Sei sicuro di voler procedere all'invio dell'ordine pagando con carta di credito?");
    alertconfirm.setContentText("Questa azione non puo' essere annullata.");

    Optional<ButtonType> result = alertconfirm.showAndWait();
    if (result.get() == ButtonType.OK) {
      Alert alertok = new Alert(Alert.AlertType.INFORMATION);
      alertok.setTitle("Ordine Inviato!");
      alertok.setHeaderText("Hai inviato correttamente il tuo ordine!");
      alertok.showAndWait();
      String cod_ordine=DBService.addToOrderList(userCart,"Carta di credito");
      DBService.copyCartToOrder(userCart,cod_ordine);
      DBService.updateBookDisponibility(userCart);
      DBService.deleteAllCart(userCart);

      FxmlController controller= new FxmlController();
      /**
       * il metodo richiama il metodo home della classe FxmlController per tornare alla home page dell'applicazione.
       */
      controller.home(root, event);
    }
  }
}
