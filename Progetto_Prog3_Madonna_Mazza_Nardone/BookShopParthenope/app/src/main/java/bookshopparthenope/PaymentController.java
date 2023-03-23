
package bookshopparthenope;

import bookshopparthenope.Model.PaymentManagement.BancomatPay;
import bookshopparthenope.Model.PaymentManagement.CashPay;
import bookshopparthenope.Model.PaymentManagement.CreditPay;
import bookshopparthenope.Model.PaymentManagement.PaymentStrategy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static bookshopparthenope.CartController.tot;

/**
 * Questa classe è un controller per la schermata di pagamento del negozio online. Gestisce la selezione del metodo di pagamento e la validazione dei dati inseriti dall'utente per effettuare il pagamento.
 * La classe contiene campi per rappresentare i diversi elementi dell'interfaccia utente, come bottoni, etichette, campi di testo e menu a discesa. Inoltre, la classe ha un riferimento al controller della schermata principale per permettere la navigazione tra le varie schermate.
 * La classe gestisce l'input dell'utente per selezionare il metodo di pagamento tra Bancomat, carta di credito o contanti. In base al metodo di pagamento scelto, la classe mostra i campi necessari per inserire i dati del pagamento, come il numero della carta, la data di scadenza, il codice di sicurezza e il nome dell'intestatario.
 * Quando l'utente preme il pulsante "Acquista", la classe chiama un metodo per validare i dati inseriti dall'utente e poi effettua il pagamento.
 * La classe utilizza la classe PaymentStrategy per calcolare il totale dell'ordine in base al metodo di pagamento scelto dall'utente.
 * Inoltre, la classe gestisce la visualizzazione degli errori e mostra il riepilogo dell'ordine, comprensivo del totale dell'ordine, delle spese di spedizione e degli eventuali costi extra.
 */
public class PaymentController implements Initializable {

  @FXML
  private Button buy;
  @FXML
  private Label errorPay;

  @FXML
  private ComboBox<Integer> pBancomatAaaa;

  @FXML
  private TextField pBancomatCvv;

  @FXML
  private ComboBox<Integer> pBancomatMm;

  @FXML
  private TextField pBancomatNome;

  @FXML
  private TextField pBancomatNumero;

  @FXML
  private ComboBox<Integer> pCartAaaa;

  @FXML
  private TextField pCartCvv;

  @FXML
  private ComboBox<Integer> pCartMm;

  @FXML
  private TextField pCartNome;

  @FXML
  private TextField pCartNumber;
  @FXML
  private Button checkShipInfo;
  @FXML
  private Button creditButton;
  @FXML
  private Button bancomatButton;
  @FXML
  private Button cashButton;
  @FXML
  private AnchorPane bancomatMethod;

  @FXML
  private Button cartButton;

  @FXML
  private ImageView cartImage;

  @FXML
  private AnchorPane cashMethod;

  @FXML
  private AnchorPane creditMethod;

  @FXML
  private Label errorShipment;
  @FXML
  private Label idExtra;
  @FXML
  private Label idArticoli;

  @FXML
  private Button homeButton;

  @FXML
  private Label idTotal;

  @FXML
  private Button profileButton;
  @FXML
  private TextField cap;

  @FXML
  private TextField citta;
  @FXML
  private TextField nr;
  @FXML
  private TextField via;

  Parent root;
  FxmlController controller=new FxmlController();

  /**
   * Switch alla schermata del Carrello utente
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToCart(ActionEvent event) throws IOException {
    controller.cart(root,event);
  }


  /**
   * Switch alla schermata Home
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToHome(ActionEvent event) throws IOException {
    controller.home(root,event);
  }


  /**
   * Switch alla schermata del profilo utente
   * @param event
   * @throws IOException
   */
  @FXML
  void switchToProfile(ActionEvent event) throws IOException {
    controller.profile(root,event);
  }


  /**
   * Il metodo "checkShip" controlla se i campi di testo per la città, il codice postale, la via e il numero civico sono vuoti o meno. Se almeno uno di questi campi è vuoto, il metodo mostra un messaggio di errore tramite la visualizzazione di un oggetto "errorShipment". In caso contrario, il metodo nasconde il messaggio di errore e abilita tre pulsanti che rappresentano le opzioni di pagamento: bancomatButton, creditButton e cashButton. Questo metodo sembra essere usato per verificare che i campi di spedizione siano stati completati correttamente prima di consentire all'utente di selezionare un metodo di pagamento.Il metodo "checkShip" controlla se i campi di testo per la città, il codice postale, la via e il numero civico sono vuoti o meno. Se almeno uno di questi campi è vuoto, il metodo mostra un messaggio di errore tramite la visualizzazione di un oggetto "errorShipment". In caso contrario, il metodo nasconde il messaggio di errore e abilita tre pulsanti che rappresentano le opzioni di pagamento: bancomatButton, creditButton e cashButton. Questo metodo è usato per verificare che i campi di spedizione siano stati completati correttamente prima di consentire all'utente di selezionare un metodo di pagamento.
   * @param event
   */
  @FXML
  void checkShip(ActionEvent event) {
    if ((citta.getText()).equals("") || (cap.getText()).equals("") || (via.getText()).equals("") || (nr.getText()).equals(""))
    {
      errorShipment.setVisible(true);
    }
    else {
      errorShipment.setVisible(false);
      bancomatButton.setDisable(false);
      creditButton.setDisable(false);
      cashButton.setDisable(false);
    }

  }


  /**
   *metodo chiamato quando l'utente seleziona l'opzione di pagamento Bancomat dalla schermata di pagamento del negozio online. Quando l'utente seleziona l'opzione di pagamento Bancomat, l'interfaccia utente mostra i campi necessari per l'inserimento dei dati del pagamento Bancomat. Questo metodo viene utilizzato per gestire la logica di visualizzazione dell'interfaccia utente quando l'utente seleziona l'opzione di pagamento Bancomat. In particolare, questo metodo imposta la visibilità degli elementi dell'interfaccia utente in modo che siano visibili solo i campi necessari per l'inserimento dei dati del pagamento Bancomat e nasconde gli altri campi.
   * @param event
   */
  @FXML
  void setBancomat(ActionEvent event) {
    metodo="bancomat";
    setRiepilogo(metodo);

  }
  float extra_cash=(tot*5)/100;
  float totaleOrdine;

  /**
   * gestore di eventi associato al pulsante "Contanti" nell'interfaccia utente della schermata di pagamento del negozio online. Quando l'utente fa clic sul pulsante "Contanti", il metodo setCash(ActionEvent event) viene eseguito e visualizza un pannello con un campo di testo in cui l'utente può visualizzare l'importo da pagare in contanti per effettuare l'ordine. Questo metodo gestisce anche la visualizzazione e la dissolvenza di altri pannelli relativi ai metodi di pagamento diversi dal contante, come la carta di credito o il bancomat, per garantire che l'utente possa inserire solo un metodo di pagamento alla volta. Il metodo non effettua effettivamente il pagamento, ma solo il raccoglimento dell'informazione per poi utilizzarla per il calcolo del totale dell'ordine e per visualizzare il riepilogo dell'ordine completo.
   * @param event
   */
  @FXML
  void setCash(ActionEvent event) {
    metodo="cash";
    setRiepilogo(metodo);

  }


  /**
   * Il metodo setCredit(ActionEvent event) è un gestore degli eventi che viene chiamato quando l'utente seleziona il metodo di pagamento con carta di credito. Quando viene chiamato, il metodo imposta la visibilità della sezione del pagamento con carta di credito su "true" e nasconde le sezioni di pagamento con bancomat e contanti. Inoltre, il metodo imposta la strategia di pagamento per l'ordine su "CreditPay", che è un'istanza della classe PaymentStrategy che viene utilizzata per calcolare il totale dell'ordine in base al metodo di pagamento scelto dall'utente.
   * @param event
   */
  @FXML
  void setCredit(ActionEvent event) {
    metodo="creditcard";
    setRiepilogo(metodo);

  }

  String metodo;


  /**
   * Questo metodo imposta il metodo di pagamento selezionato dall'utente e mostra l'interfaccia utente corrispondente per il metodo di pagamento selezionato. Inoltre, aggiorna il totale dell'ordine mostrato nell'interfaccia utente.
   *
   * La funzione accetta una stringa "method" che rappresenta il metodo di pagamento selezionato dall'utente e esegue uno switch statement per selezionare il metodo di pagamento corrispondente. Quando viene selezionato il metodo di pagamento, la funzione chiama il metodo setPaymentMethod con l'oggetto del metodo di pagamento corrispondente.
   *
   * Infine, la funzione aggiorna il totale dell'ordine e mostra l'importo extra (se presente) associato al metodo di pagamento selezionato.
   * @param method
   */
  void setRiepilogo(String method){
    cartImage.setVisible(false);
    buy.setDisable(false);
    bancomatMethod.setVisible(false);
    cashMethod.setVisible(false);
    creditMethod.setVisible(false);
    idExtra.setText("0,00");
    switch (method) {
      case "cash" -> {
        CashPay metodoCash = new CashPay();
        setPaymentMethod(metodoCash);
        cashMethod.setVisible(true);
        idExtra.setText(String.format("%.2f", extra_cash));
        errorPay.setVisible(false);
      }
      case "creditcard" -> {
        CreditPay metodoCredit = new CreditPay();
        setPaymentMethod(metodoCredit);
        creditMethod.setVisible(true);
        errorPay.setVisible(false);
        errorPay.setVisible(false);
      }
      case "bancomat" -> {
        BancomatPay metodoBancomat = new BancomatPay();
        setPaymentMethod(metodoBancomat);
        bancomatMethod.setVisible(true);
        errorPay.setVisible(false);
      }
      default -> System.out.println("error");
    }
    totaleOrdine = calculateTotalAmount(tot);
    idTotal.setText(String.format("%.2f", totaleOrdine));
  }

  private PaymentStrategy paymentMethod;

  /**
   * Il metodo setPaymentMethod definisce la strategia di pagamento che verrà utilizzata per elaborare il pagamento dell'ordine. Questo metodo accetta un oggetto PaymentStrategy come parametro e lo assegna alla variabile membro paymentMethod. La classe che contiene questo metodo deve aver implementato l'interfaccia PaymentStrategy e ha implementato il metodo pay() definito da tale interfaccia. L'oggetto paymentMethod assegnato determinerà quale strategia di pagamento verrà utilizzata quando l'utente finalizzerà l'ordine.
   * @param paymentMethod
   */
  public void setPaymentMethod(PaymentStrategy paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   *Questo metodo delega il calcolo del totale dell'importo all'oggetto PaymentMethod attualmente impostato come metodo di pagamento. Il metodo di pagamento è un'istanza di una classe che implementa l'interfaccia PaymentStrategy, che definisce il metodo calculateTotal per calcolare il totale dell'importo. La classe PaymentStrategy astrae la logica di calcolo del totale dell'importo dal metodo setRiepilogo, che può essere utilizzato per impostare diversi metodi di pagamento. In questo modo, è possibile modificare facilmente il metodo di pagamento senza dover modificare la logica del calcolo del totale.
   * @param amount
   * @return
   */
  public float calculateTotalAmount(float amount) {
    return paymentMethod.calculateTotal(amount);
  }


  /**
   *Questo metodo richiama il metodo validation dell'oggetto paymentMethod, che implementa l'interfaccia PaymentStrategy e che è stato impostato tramite il metodo setPaymentMethod. La funzione validation può effettuare la validazione del pagamento in base alla strategia di pagamento selezionata. Il metodo prende come argomento un evento ActionEvent e l'oggetto root, che è un nodo radice dell'interfaccia utente e può essere utilizzato per accedere agli elementi dell'interfaccia utente. Il metodo può generare un'eccezione IOException, SQLException o ClassNotFoundException se si verificano errori durante la validazione del pagamento.
   * @param event
   * @throws IOException
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void validatePay(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    paymentMethod.validation(root,event);
  }


  float spedizione=4.9f;

  /**
   * Questo metodo è un'implementazione del metodo "initialize" dell'interfaccia "Initializable" di JavaFX. Viene chiamato automaticamente quando l'interfaccia utente è stata caricata, di solito dopo che il file fxml e il controller sono stati associati. In questo metodo vengono inizializzati alcuni elementi dell'interfaccia utente, come l'etichetta idArticoli e idTotal, i menu a tendina pBancomatAaaa, pCartAaaa, pBancomatMm e pCartMm. Inoltre, vengono impostati i gestori degli eventi per questi menu a tendina in modo da richiamare i metodi getChoiceBancomatAaaa, getChoiceCreditAaaa, getChoiceBancomatMm e getChoiceCreditMm, quando l'utente seleziona un elemento dal menu.
   * @param location
   * The location used to resolve relative paths for the root object, or
   * {@code null} if the location is not known.
   *
   * @param resources
   * The resources used to localize the root object, or {@code null} if
   * the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    idArticoli.setText(String.format("%.2f", tot));
    idTotal.setText(String.format("%.2f", tot+spedizione));
    pBancomatAaaa.getItems().addAll(anno);
    pCartAaaa.getItems().addAll(anno);
    pBancomatMm.getItems().addAll(mesi);
    pCartMm.getItems().addAll(mesi);
    pBancomatAaaa.setOnAction(this::getChoiceBancomatAaaa);
    pCartAaaa.setOnAction(this::getChoiceCreditAaaa);
    pBancomatMm.setOnAction(this::getChoiceBancomatMm);
    pCartMm.setOnAction(this::getChoiceCreditMm);


  }
  Integer annoBancomat;
  Integer annoCredit;
  Integer meseBancomat;
  Integer meseCredit;

  /**
   * Prende il valore dell'anno di scadenza della carta di credito e lo mette in annoCredit
   * @param actionEvent
   */
  private void getChoiceCreditAaaa(ActionEvent actionEvent) {
    annoCredit=pCartAaaa.getValue();
  }

  /**
   * Prende il valore del mese di scadenza della carta di credito e lo mette in meseCredit
   * @param actionEvent
   */
  private void getChoiceCreditMm(ActionEvent actionEvent) {
    meseCredit=pCartMm.getValue();
  }


  /**
   * Prende il valore dell'anno di scadenza del Bancomat e lo mette in annoBancomat
   * @param actionEvent
   */
  private void getChoiceBancomatAaaa(ActionEvent actionEvent) {
    annoBancomat=pBancomatAaaa.getValue();
  }

  /**
   * Prende il valore del mese di scadenza del Bancomat e lo mette in meseBancomat
   * @param actionEvent
   */
  private void getChoiceBancomatMm(ActionEvent actionEvent) {
    meseBancomat=pBancomatMm.getValue();
  }

  Integer[] anno={2023,2024,2025,2026,2027,2028,2029,2030};
  Integer[] mesi={1,2,3,4,5,6,7,8,9,10,11,12};

  /**
   * Questo metodo gestisce la conferma dell'acquisto. Controlla se il metodo di pagamento scelto è bancomat o carta di credito e se tutti i campi del form sono stati compilati correttamente. Se ci sono errori, mostra un messaggio di errore. Altrimenti, nasconde il messaggio di errore e chiama il metodo validatePay() per confermare l'acquisto. Questo metodo potrebbe generare un'eccezione di tipo IOException, SQLException o ClassNotFoundException, che deve essere gestita.
   * @param event
   * @throws IOException
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @FXML
  void buyConfirm(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    if(Objects.equals(metodo, "bancomat")){
      if (meseBancomat==null||annoBancomat==null||(pBancomatNome.getText()).equals("")||(pBancomatCvv.getText()).equals("")||(pBancomatNumero.getText()).equals("")){
        errorPay.setVisible(true);
        return;
      }
    }
    if (Objects.equals(metodo, "creditcard")){
      if (meseCredit==null||annoCredit==null||(pCartNome.getText()).equals("")||(pCartCvv.getText()).equals("")||(pCartNumber.getText()).equals("")){
        errorPay.setVisible(true);
        return;
      }
    }
    errorPay.setVisible(false);
    validatePay(event);
  }
}
