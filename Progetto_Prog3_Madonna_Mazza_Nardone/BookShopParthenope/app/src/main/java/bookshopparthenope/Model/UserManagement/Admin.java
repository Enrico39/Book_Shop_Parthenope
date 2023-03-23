package bookshopparthenope.Model.UserManagement;


import bookshopparthenope.Model.LoginRegisterManagementDAO.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Questa classe modella un amministratore (Admin) del negozio di libri (bookshop). La classe estende la classe User e implementa il metodo logIn() per consentire all'amministratore di effettuare l'accesso al sistema.
 * In sintesi, questa classe rappresenta un amministratore del negozio di libri e fornisce un metodo per effettuare l'accesso al sistema.
 */
public class Admin extends User{


    /**
     * Il costruttore richiede il nome utente e la password dell'amministratore per creare un nuovo oggetto Admin.
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Il metodo logIn() esegue una query al database attraverso la classe UserDAO per verificare che l'amministratore abbia inserito il nome utente e la password corretti. Se l'autenticazione ha successo, i valori di nome utente e password vengono aggiornati e il metodo restituisce true. Altrimenti, restituisce false.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean logIn() throws SQLException, ClassNotFoundException {
        ResultSet set = UserDAO.logInAdmin(username, password);

        if (set != null) {
            this.username = set.getString(1);
            this.password = set.getString(2);
            return true;
        }
        return false;

    }
}
