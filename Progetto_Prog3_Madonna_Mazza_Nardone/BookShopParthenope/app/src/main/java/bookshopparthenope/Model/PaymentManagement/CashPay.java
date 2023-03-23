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
 * La classe CashPay implementa l'interfaccia PaymentStrategy e definisce i comportamenti specifici per il pagamento in contanti. In particolare, il metodo calculateTotal calcola il totale dell'importo da pagare aggiungendo il 5% del totale come tassa e 4.9F come costo di spedizione.
 */
public class CashPay implements PaymentStrategy {
  @Override
  public float calculateTotal(float amount) {
    // implement logic to calculate total amount for Cash payment
    return amount + (amount * 5 / 100) + 4.9F;

  }


  /**
   * Il metodo validation presenta una finestra di dialogo all'utente per confermare l'acquisto e informarlo che pagherà in contanti al momento della consegna. Se l'utente conferma l'acquisto, l'ordine viene registrato nel database, viene copiato il contenuto del carrello nell'ordine, viene aggiornata la disponibilità dei libri e infine viene eliminato il carrello.
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
    alertconfirm.setHeaderText("Sei sicuro di voler procedere all'invio dell'ordine?\nPagherai in contanti all'arrivo del corriere.");
    alertconfirm.setContentText("Questa azione non puo' essere annullata.");

    Optional<ButtonType> result = alertconfirm.showAndWait();
    if (result.get() == ButtonType.OK) {
      Alert alertok = new Alert(Alert.AlertType.INFORMATION);
      alertok.setTitle("Ordine Inviato!");
      alertok.setHeaderText("Hai inviato correttamente il tuo ordine!");
      alertok.showAndWait();
      String cod_ordine=DBService.addToOrderList(userCart,"Contanti");
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
