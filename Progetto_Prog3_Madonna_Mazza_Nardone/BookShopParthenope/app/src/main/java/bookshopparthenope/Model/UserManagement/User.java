package bookshopparthenope.Model.UserManagement;


import java.sql.SQLException;

/**
 * Questa classe rappresenta un utente generico del negozio di libri (bookshop). È una classe astratta che contiene i campi dati username e password con i relativi metodi get e set.
 * questa classe fornisce un modello generale per gli utenti del negozio di libri, con un metodo di accesso al sistema astratto che deve essere implementato dalle sottoclassi per fornire l'implementazione specifica per il tipo di utente.
 */
public abstract class User {

    protected String username;
    protected String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     *  la classe fornisce un template method, ovvero un metodo che definisce lo scheletro di un algoritmo, delegando alcune parti specifiche alle sottoclassi tramite i metodi astratti. In questo caso, il template method viene definito implicitamente come il metodo logIn() che richiama il metodo astratto primitivo che deve essere implementato dalle sottoclassi.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
// Template method

    //Metodo "abstract" primitivo che deve essere implementato
    protected abstract boolean logIn() throws SQLException, ClassNotFoundException;


}
