package io.github.mindcraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

public class MySQL {
	
	public Plugin plugin;
	
    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;

    public Connection connection;

    /**
     * Creates a new MySQL instance
     */
    public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password) {
        this.plugin = plugin;
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = null;
    }

    public Connection openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
    		plugin.getLogger().info("Connected to Database!");
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not connect to MySQL server! because: " + e.getMessage());
        }
        return connection;
    }

    public boolean checkConnection() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }
}