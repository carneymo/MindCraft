package io.github.mindcraft;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MindCraft extends JavaPlugin {

	public Metadata metadata;
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		getCommand("iddqd").setExecutor(new MindCraftCommandExecutor(this));
		metadata = new Metadata();
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
