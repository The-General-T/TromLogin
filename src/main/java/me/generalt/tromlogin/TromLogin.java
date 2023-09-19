package me.generalt.tromlogin;

import me.generalt.tromlogin.commands.TromLoginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import static java.util.Objects.requireNonNull;

public class TromLogin extends JavaPlugin implements Listener {
    private static TromLogin plugin; // Creates an instance of the plugin to reference in other files

    @Override
    public void onEnable() {
        plugin = this; // Setter for the plugin instance
        requireNonNull(getCommand("tromlogin")).setExecutor(new TromLoginCommand());
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info("TromLogin has been enabled!");
    }

    // Getter for the plugin instance
    public static TromLogin getPlugin() {
        return plugin;
    }
}