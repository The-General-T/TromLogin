package me.generalt.tromlogin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import static me.generalt.tromlogin.TromLogin.getPlugin;
import static me.generalt.tromlogin.utils.MessageUtil.parsedMessage;

public class ConnectionListener implements Listener {
    Boolean enabled = getPlugin().getConfig().getBoolean("broadcastWelcomeMessage");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (enabled && !p.hasPermission("tromlogin.bypass") && p.hasPermission("tromlogin.broadcast"))
            getPlugin().getServer().broadcast(parsedMessage("joinMessage", p));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (enabled && !p.hasPermission("tromlogin.bypass") && p.hasPermission("tromlogin.broadcast"))
            getPlugin().getServer().broadcast(parsedMessage("leaveMessage", p));
    }

}
