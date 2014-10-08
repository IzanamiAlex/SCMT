package shared.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;


public abstract class AbstractDAO<T> {
    
    public AbstractDAO(){
        this("localhost", "scmt", "postgres", "chopper", "org.postgresql.Driver");
    }
    
    public AbstractDAO(String host, String database, String login, String password,String driver) {
        this.host = host;
        this.database = database;
        this.login = login;
        this.password = password;
        loadedDriver = false;
        loadDriver(driver);
    }
    
    public abstract int store(T entity)throws SQLException;
    
    public abstract int update(T entity)throws SQLException;
    
    public abstract int delete(T entity)throws SQLException; 
    
    public abstract T find(String identifier)throws SQLException;
    
    public abstract Set<T> load(String condition)throws SQLException;

    public final boolean isLoadedDriver() {
        return loadedDriver;
    }
    
    protected final Connection getConnection(){
        Connection connection = null;
        String urlConnection = "jdbc:postgresql://"+ host +"/" + database;
        try {
            connection = DriverManager.getConnection(urlConnection, login, password);
        }catch (Exception e) {}
        finally{
            return connection;
        } 
    }
    
    protected final void closeConnection(Connection connection){
        try {
            if ( connection != null )
                if ( !connection.isClosed() )    // Si no esta cerrada, se cierra
                    connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    private void loadDriver(String driver){
        try {
            if ( !loadedDriver ) {
                Class.forName(driver);
                loadedDriver = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String host;
    private String database;
    private String login;
    private String password;
    private boolean loadedDriver;
    
}