package me.generalt.tromlogin;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class TromLogin extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info(ChatColor.translateAlternateColorCodes('&',config.getString("prefix")+"TromLogin has been enabled!"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = getConfig();
        if (event.getPlayer().hasPermission("tromlogin.bypass")) {
            return;
        }

        if (config.getBoolean("broadcastWelcomeMessage")) {
            String welcomeMessage = config.getString("joinMessage");
            welcomeMessage = welcomeMessage.replace("{player}", event.getPlayer().getName());

            if (event.getPlayer().hasPermission("tromlogin.broadcast")) {
                getServer().broadcastMessage(welcomeMessage);
            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FileConfiguration config = getConfig();

        if (config.getBoolean("broadcastWelcomeMessage")) {
            String leaveMessage = config.getString("leaveMessage");
            leaveMessage = leaveMessage.replace("{player}", event.getPlayer().getName());

            if (event.getPlayer().hasPermission("tromlogin.broadcast")) {
                getServer().broadcastMessage(leaveMessage);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = getConfig();
        if (cmd.getName().equalsIgnoreCase("tlreloadconf")) {
            // Check if the sender has permission to reload the plugin
            if (!sender.hasPermission("tromlogin.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("prefix")+"You do not have permission."));
                return true;
            }

            reloadConfig(); // Reload the configuration
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("prefix")+"Plugin configuration reloaded."));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("tltest")) {
            if (!sender.hasPermission("tromlogin.test")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("prefix")+"You do not have permission."));
                return true;
            }

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("joinMessage")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("leaveMessage")));
            return true;
        }
        return false;
    }
}