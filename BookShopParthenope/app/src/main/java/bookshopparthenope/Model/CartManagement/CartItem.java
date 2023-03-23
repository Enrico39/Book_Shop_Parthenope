package bookshopparthenope.Model.CartManagement;

/**
 * Il codice definisce un'interfaccia CartItem che definisce i metodi che un oggetto che rappresenta un elemento del carrello deve implementare.
 * In particolare, un oggetto CartItem deve essere in grado di restituire il prezzo, l'autore, il titolo e l'utente che ha inserito l'elemento nel carrello.
 * Inoltre, l'interfaccia definisce un metodo accept che accetta un CartItemVisitor, ovvero un oggetto che implementa l'interfaccia CartItemVisitor, che può essere utilizzato per eseguire operazioni sull'elemento del carrello.
 */
public interface CartItem {
    public float getPrice();
    public String getAuthor();
    public String getTitle();
    public String getUsername();

    public void accept(CartItemVisitor visitor);
}
