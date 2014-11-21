package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
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
    
    public final int store(T entity)throws SQLException{
        String scriptStore = buildScriptStore(entity);
        int numRowsModify = executeScriptUpdateDB(scriptStore);
        return numRowsModify;
    }
    
    public final int update(T entity)throws SQLException{
        String scriptUpdate = buildScriptUpdate(entity);
        int numRowsModify = executeScriptUpdateDB(scriptUpdate);
        return numRowsModify;
    }
    
    public final int delete(T entity)throws SQLException{
        String scriptDelete = buildScriptDelete(entity);
        int numRowsModify = executeScriptUpdateDB(scriptDelete);
        return numRowsModify;
    }
    
    public  T find(String identifier)throws SQLException{
        String scriptFind = buildScriptFind(identifier);
        Set<T> setEntitys = executeQuery(scriptFind);
        Iterator<T> iterator = setEntitys.iterator();
        iterator.hasNext();
        T entity = iterator.next();
        return entity;
    }
    
    public Set<T> load(String atribute)throws SQLException{
        String scriptLoad = buildScriptLoad(atribute);
        Set<T> setEntitys = executeQuery(scriptLoad);
        return setEntitys;
    }

    public final boolean isLoadedDriver() {
        return loadedDriver;
    }
    
    public final Connection getConnection(){
        Connection connection = null;
        String urlConnection = "jdbc:postgresql://"+ host +"/" + database;
        try {
            connection = DriverManager.getConnection(urlConnection, login, password);
        }catch (Exception e) {}
        return connection;
    }
    
    protected final void closeConnection(Connection connection){
        try {
            if ( connection != null )
                if ( !connection.isClosed() )    // Si no esta cerrada, se cierra
                    connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    protected abstract String buildScriptStore(T entity);
    protected abstract String buildScriptUpdate(T entity);
    protected abstract String buildScriptDelete(T entity);
    protected abstract String buildScriptFind(String identifier);
    protected abstract String buildScriptLoad(String atribute);  
    protected abstract Set<T> buildEntitys(ResultSet resultQuery)throws SQLException;
    
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
    
    private int executeScriptUpdateDB(String script) throws SQLException{
        int numRowsModify = 0;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        numRowsModify = statement.executeUpdate(script);
        statement.close();
        closeConnection(connection);
        return numRowsModify;
    }
    
    public Set<T> executeQuery(String query) throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultQuery = statement.executeQuery(query);
        Set<T> entitys =  buildEntitys(resultQuery);
        statement.close();
        closeConnection(connection);
        return entitys;
    }
    
    private String host;
    private String database;
    private String login;
    private String password;
    private boolean loadedDriver;
}
