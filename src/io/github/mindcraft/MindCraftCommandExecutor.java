package io.github.mindcraft;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MindCraftCommandExecutor implements CommandExecutor {
	
	private MindCraft plugin;
	
	public MindCraftCommandExecutor(MindCraft plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if(cmd.getName().equalsIgnoreCase("iddqd")) {
				callCommand_iddqd(sender,cmd,label,args);
			}
		} catch(Exception e) {
			this.plugin.getLogger().info("Exception: "+e);
		}
		return false;
	}

	public boolean callCommand_iddqd(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player)sender;
		    World w = player.getWorld();
		    String godmode = (String)plugin.metadata.getMetadata(player, "GODMODE", plugin);
		    
		    if(godmode == null || godmode == "0") {
		    	
		    	plugin.db.setMetadata(player, "GODMODE", "1");
		    	player.setGameMode(GameMode.CREATIVE);
		    	
			    w.playSound(player.getLocation(), Sound.EXPLODE, 10, 1);
			    player.sendMessage("§6GOD MODE ACTIVATED");
			    plugin.metadata.setMetadata(player, "GODMODE", (Object)"1", plugin);
		    } else if(godmode == "1") {

		    	plugin.db.setMetadata(player, "GODMODE", "0");
		    	player.setGameMode(GameMode.SURVIVAL);
		    	
			    w.playSound(player.getLocation(), Sound.HURT_FLESH, 10, 1);
			    player.sendMessage("§cGOD MODE DEACTIVATED");
			    plugin.metadata.setMetadata(player, "GODMODE", (Object)"0", plugin);
		    }
		    
			return true;
		}
		return false;
	}
	
}
