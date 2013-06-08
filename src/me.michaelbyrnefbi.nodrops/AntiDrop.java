package me.michaelbyrnefbi.nodrops;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiDrop extends JavaPlugin implements Listener {

	public void onEnable(){
		getLogger().info("SkyrimAntiDrop has been Enabled");
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		saveConfig();
	}      
	public void onDisable(){
		getLogger().info("SkyrimAntiDrop has been Disabled");
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "-" + ChatColor.WHITE +"]" + ChatColor.RED + " You are not allowed to drop items!");
		saveConfig();
	}
	@EventHandler
	public void onInventoryEvent(InventoryClickEvent event)  {
		event.setCancelled(true);
		HumanEntity HumanEntity = event.getWhoClicked();
		if(!HumanEntity.hasPermission("Skyrim.AntiDrop") || (HumanEntity.isOp())){
			event.setCancelled(true);
		}
		if (HumanEntity instanceof Player) {
			((Player)HumanEntity).sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "-" + ChatColor.WHITE +"]" + ChatColor.RED + " You are not allowed to drop items!"); 

		} else {
		}
		   
                  }
         	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
            for (String word : e.getMessage().split(" ")) {
                    if (getConfig().getStringList("badwords").contains(word) || (e.getPlayer().hasPermission("Skyrim.ChatBypass"))) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "-" + ChatColor.WHITE +"]" + ChatColor.RED + " You are not allowed to swear!");
                   return;
                    }
}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("vote"))
			player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "-" + ChatColor.WHITE + "] " + ChatColor.GREEN + "Voting on the server has many advantages, including supporting us. Increasing staff chance and getting more SkyrimGems. Click on the links below");
		PluginManager pm = getServer().getPluginManager();
		List<String> commands = this.getConfig().getStringList("LinkOne");
		for (String command : commands);
			List<String> commands1 = this.getConfig().getStringList("LinkTwo");
		for (String command : commands1)	
		player.sendMessage(command);
		List<String> commands3 = this.getConfig().getStringList("LinkThree");
		for (String command : commands3)
		player.sendMessage(command);
		return true;
	}
}