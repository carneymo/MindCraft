package io.github.mindcraft;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;


public class Database {

	private MySQL mysql;
	private MindCraft plugin;
	
	public Database(MindCraft plugin) {
		this.plugin = plugin;
		this.mysql = new MySQL(plugin, "localhost", "3306", "mindcraft", "bukkit", "microcolorimetry substantivizing");
		mysql.openConnection();
	}
	
	public void setMetadata(Player player, String MetadataKey, String MetadataValue) {
		Statement stmt;
		try {
			stmt = mysql.connection.createStatement();
			ResultSet rs = stmt.executeQuery(
				"SELECT * FROM player_metadata WHERE PlayerName='" + player.getName() + "' AND MetadataKey = '" + MetadataKey + "';"
			);
			if (!rs.next()) {
				stmt.executeUpdate(
					"INSERT INTO player_metadata (PlayerName, MetadataKey, MetadataValue) " +
					"VALUES('" + player.getName() + "', '" + MetadataKey + "', '" + MetadataValue + "');"
				);
			} else {
				stmt.executeUpdate(
					"UPDATE player_metadata SET MetadataValue = '" + MetadataValue + 
					"' WHERE PlayerName = '"+ player.getName() + "' AND MetadataKey = '" + MetadataKey + "';"
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
