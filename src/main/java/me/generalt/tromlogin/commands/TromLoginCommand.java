package me.generalt.tromlogin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import static me.generalt.tromlogin.TromLogin.getPlugin;
import static me.generalt.tromlogin.utils.MessageUtil.parsedMessage;

public class TromLoginCommand implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        switch (args[0].toLowerCase()) {
            case "reload":
                if (!sender.hasPermission("tromlogin.reload")) {
                    getPlugin().reloadConfig();
                    getPlugin().getComponentLogger().info(parsedMessage("reloadConfig", null));
                    sender.sendMessage(parsedMessage("reloadConfig", null));
                    return true;
                }
            case "test":
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
