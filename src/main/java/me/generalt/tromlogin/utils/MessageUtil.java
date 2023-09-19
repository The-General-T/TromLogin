package me.generalt.tromlogin.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static me.generalt.tromlogin.TromLogin.getPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessageUtil {
    static String getString(String path) {
        return getPlugin().getConfig().getString(path);
    }
    static Component parse(String s) { return MiniMessage.miniMessage().deserialize(s); }

    public static Component parsedMessage(String path, @Nullable Player p) {
        Component msg = parse(getString("prefix")).append(parse(getString(path)));
        if (msg.contains(Component.text("{player}")) && p != null)
            msg = Component.text(msg.toString().replace("{player}", p.getName()));
        return msg;
    }
}