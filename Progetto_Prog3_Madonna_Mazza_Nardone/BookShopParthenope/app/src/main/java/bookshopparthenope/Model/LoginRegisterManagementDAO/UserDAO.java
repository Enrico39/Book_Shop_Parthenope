package bookshopparthenope.Model.LoginRegisterManagementDAO;

import bookshopparthenope.Model.UserManagement.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static bookshopparthenope.Model.UserManagement.DBService.MYSQL_DUPLICATE_PK;
import static bookshopparthenope.Model.UserManagement.DBService.connect;

/**
 * Questa classe definisce un'interfaccia per accedere al database degli utenti, fornendo metodi statici per l'accesso e la gestione degli utenti.
 * In particolare, fornisce metodi per eseguire il login degli amministratori e dei clienti, nonché per inserire nuovi clienti nel database. La classe utilizza la classe DBService, che contiene metodi per la connessione al database e la gestione delle query SQL.
 */
public interface UserDAO {

    /**
     * Questo metodo definisce un'operazione di accesso (login) per un amministratore dell'applicazione. Riceve come argomenti una stringa rappresentante l'username e una rappresentante la password dell'utente.
     *
     * Il metodo cerca quindi nel database un record nella tabella "admin" che abbia come username e password quelle fornite in input. Se viene trovato un record, il metodo restituisce un oggetto ResultSet contenente le informazioni trovate, altrimenti restituisce null.
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static ResultSet logInAdmin(String username, String password) throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        String query = "SELECT * FROM admin where username = ? and password = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs;
        } else return null;
    }


    /**
     * Questo metodo esegue una query al database per verificare se esiste un cliente con un determinato username e password. In particolare, il metodo si connette al database, esegue una query SQL selezionando tutti i record dalla tabella "customer" dove l'username è uguale al primo parametro e la password è uguale al secondo parametro, utilizzando un oggetto PreparedStatement.
     * Quindi, esegue la query chiamando il metodo executeQuery() e se la query restituisce un risultato (cioè un record), restituisce il risultato sotto forma di oggetto ResultSet. Altrimenti, se la query non restituisce alcun risultato, restituisce null.
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static ResultSet logInCustomer(String username, String password) throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        String query = "SELECT * FROM customer where username= ? and password =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs;
        } else return null;
    }


    /**
     * Il metodo insertNewCustomer si occupa di inserire un nuovo record nella tabella customer del database. Prende come argomento un oggetto Customer, crea una connessione al database utilizzando il metodo connect() definito nella classe DBService, quindi crea una stringa di query SQL per inserire i valori dell'oggetto Customer nella tabella.
     * Successivamente, esegue la query e restituisce true se l'inserimento è stato effettuato con successo, altrimenti restituisce false. Se l'inserimento fallisce perché esiste già un record con la stessa chiave primaria (ovvero lo stesso username), restituisce false.
     * @param cliente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static boolean insertNewCustomer(Customer cliente) throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        String apice = "'";
        String virgola = ",";
        String query = "INSERT INTO customer(username,name,surname,email,password) values ( '" +
                cliente.getUsername() + apice + virgola + apice + cliente.getName() + apice + virgola + apice + cliente.getSurname() + apice + virgola + apice + cliente.getEmail() + apice + virgola +
                apice + cliente.getPassword() + apice + ")";
        try{
            conn.prepareStatement(query).execute();
            return true;
        } catch(SQLException e){
            if(e.getErrorCode() == MYSQL_DUPLICATE_PK ){
                return false; }
        }
        return true;
    }
}
