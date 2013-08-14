package io.github.mindcraft;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MindCraft extends JavaPlugin {

	public Metadata metadata;
	public Database db;
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		getCommand("iddqd").setExecutor(new MindCraftCommandExecutor(this));
		metadata = new Metadata();
		db = new Database(this);
		new MindCraftListener(this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

	public void onPlayerMove(PlayerMoveEvent event) {
	    // Get the player's location.
	    Location loc = event.getPlayer().getLocation();
	    // Sets loc to five above where it used to be. Note that this doesn't change the player's position.
	    loc.setY(loc.getY() + 5);
	    World w = loc.getWorld();
	    // Gets the block at the new location.
	    Block b = w.getBlockAt(loc);
	    // Sets the block to type id 1 (stone).
	    b.setTypeId(1);
	}
	
}
/*
 * Scheduled Task to run 20 ticks later
 * We can modify this to run as a separate thread and loop every X amount of ticks
 */

class MindCraftListener implements Listener {
	
	private final MindCraft plugin;
	
	public MindCraftListener(MindCraft plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		new BroadcastTask(this.plugin).runTaskLater(this.plugin,20);
	}
	
}
