package me.generalt.tromlogin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class TromLogin extends JavaPlugin implements Listener {
    Component parsedMessage(String path, @Nullable Player p) {
        Component msg = miniMessage().deserialize(requireNonNull(getConfig().getString("prefix"))).append(miniMessage().deserialize(requireNonNull(getConfig().getString(path))));
        if (msg.contains(Component.text("{player}")) && p != null)
            msg = Component.text(msg.toString().replace("{player}", p.getName()));
        return msg;
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info("TromLogin has been enabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (getConfig().getBoolean("broadcastWelcomeMessage") && !p.hasPermission("tromlogin.bypass") && p.hasPermission("tromlogin.broadcast"))
            getServer().broadcast(parsedMessage("joinMessage", p));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (getConfig().getBoolean("broadcastWelcomeMessage") && !p.hasPermission("tromlogin.bypass") && p.hasPermission("tromlogin.broadcast"))
            getServer().broadcast(parsedMessage("leaveMessage", p));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        switch (cmd.getName().toLowerCase()) {
            case "tlreloadconf":
                if (!sender.hasPermission("tromlogin.reload")) {
                    reloadConfig(); // Reload the configuration
                    sender.sendMessage(parsedMessage("reloadConfig", null));
                    return true;
                }
            case "tltest":
                if (sender.hasPermission("tromlogin.test")) {
                    sender.sendMessage(parsedMessage("joinMessage", (Player) sender));
                    sender.sendMessage(parsedMessage("leaveMessage", (Player) sender));
                    return true;
                }
            default:
                sender.sendMessage(parsedMessage("noPermission", null));
                return true;
        }
    }
}