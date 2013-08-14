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
			
			Player currentplayer = (Player)sender;
		    World w = currentplayer.getWorld();
		    String godmode = (String)plugin.metadata.getMetadata(currentplayer, "GODMODE", plugin);
		    
		    if(godmode == null || godmode == "0") {
		    	
		    	currentplayer.setGameMode(GameMode.CREATIVE);
		    	
			    w.playSound(currentplayer.getLocation(), Sound.EXPLODE, 10, 1);
		    	currentplayer.sendMessage("§6GOD MODE ACTIVATED");
			    plugin.metadata.setMetadata(currentplayer, "GODMODE", (Object)"1", plugin);
		    } else if(godmode == "1") {

		    	currentplayer.setGameMode(GameMode.SURVIVAL);
		    	
			    w.playSound(currentplayer.getLocation(), Sound.HURT_FLESH, 10, 1);
			    currentplayer.sendMessage("§cGOD MODE DEACTIVATED");
			    plugin.metadata.setMetadata(currentplayer, "GODMODE", (Object)"0", plugin);
		    }
		    
			return true;
		}
		return false;
	}
	
}
