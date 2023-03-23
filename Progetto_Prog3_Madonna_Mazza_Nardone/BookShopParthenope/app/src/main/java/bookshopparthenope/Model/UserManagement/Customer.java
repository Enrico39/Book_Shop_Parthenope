package bookshopparthenope.Model.UserManagement;


import bookshopparthenope.Model.LoginRegisterManagementDAO.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Questa classe modella un cliente (Customer) del negozio di libri (bookshop). La classe estende la classe User e implementa il metodo logIn() per consentire al cliente di effettuare l'accesso al sistema.
 * In sintesi, questa classe rappresenta un cliente del negozio di libri e fornisce un metodo per effettuare l'accesso al sistema. Inoltre, fornisce i metodi per accedere e impostare i dati personali del cliente come nome, cognome ed email.
 */
public class Customer extends User{

    protected String name;
    protected String surname;
    protected String email;

    /**
     * Il costruttore richiede il nome utente e la password del cliente per creare un nuovo oggetto Customer.
     * @param username
     * @param password
     */
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }


    /**
     * Il metodo logIn() esegue una query al database attraverso la classe UserDAO per verificare che il cliente abbia inserito il nome utente e la password corretti. Se l'autenticazione ha successo, i valori di nome utente, nome, cognome, email e password vengono aggiornati e il metodo restituisce true. Altrimenti, restituisce false.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean logIn() throws SQLException, ClassNotFoundException {
        ResultSet set = UserDAO.logInCustomer(username, password);

        if (set != null) {
            username = set.getString(1);
            name= set.getString(2);
            surname= set.getString(3);
            email= set.getString(4);
            password = set.getString(5);
            return true;
        }
        return false;

    }
}
