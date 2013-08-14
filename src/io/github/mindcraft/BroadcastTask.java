package io.github.mindcraft;

import org.bukkit.scheduler.BukkitRunnable;

public class BroadcastTask extends BukkitRunnable {

	private final MindCraft plugin;
	
	public BroadcastTask(MindCraft plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		plugin.getServer().broadcastMessage("Welcome to MindCraft! Where everything is made up and the points don't matter!");
	}

}
