package me.badcodinghabits.commandments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author BadCodingHabits
 */
public abstract class SubCommand {

    private final JavaPlugin plugin;
    private final MainCommand parent;
    private final String name;
    private final String usage;
    private final String permission;

    /**
     * Gets necessary data normally assigned through the plugin.yml, however, due to the roundabout methods for utilizing
     * {@link SubCommand}, it has to be circumvented, so these are 'pseudo-commands' which are not assigned an actual
     * {@link org.bukkit.command.CommandExecutor}.
     *
     * @param plugin the plugin hosting the command, supplied through {@link MainCommand#getPlugin()}
     * @param parent the {@link MainCommand} which also hosts the {@link SubCommand}
     * @param name the key/reference for the sub command
     * @param usage how the {@link org.bukkit.entity.Player} sees the command's instructions
     * @param permission the required permission needed by the {@link org.bukkit.entity.Player}
     */
    public SubCommand(JavaPlugin plugin, MainCommand parent, String name, String usage, String permission) {
        this.plugin = plugin;
        this.parent = parent;
        this.name = name.toLowerCase();
        this.usage = usage;
        this.permission = permission.toLowerCase();
    }

    /**
     * A branch of {@link #execute(CommandSender, Command, String, String[])}, however, checks if the {@link org.bukkit.entity.Player}
     * attempting to run this command has the required permission.
     *
     * @param sender the initiator of the command
     * @param parent the initial command, as provided by {@link MainCommand}
     * @param label how the command was referenced
     * @param args additional arguments for the command
     *
     * @return if the {@link org.bukkit.entity.Player} had the required permission
     */
    public boolean executeIfPermissible(CommandSender sender, Command parent, String label, String[] args) {
        if (sender.hasPermission(permission)) {
            this.execute(sender, parent, label, args);
            return true;
        }
        return false;
    }

    /**
     * Essentially identical to the normal {@link org.bukkit.command.CommandExecutor#onCommand(CommandSender, Command, String, String[])},
     * however, is later used by {@link #executeIfPermissible(CommandSender, Command, String, String[])}, the return value
     * {@link Boolean} also has no true value, but can be used if needed.
     *
     * @param sender the initiator of the command
     * @param parent the initial command, as provided by {@link MainCommand}
     * @param label how the command was referenced
     * @param args additional arguments for the command
     *
     * @return if abstract conditions are met, at the developers discretion
     */
    public abstract boolean execute(CommandSender sender, Command parent, String label, String[] args);

    /**
     * Gets the {@link JavaPlugin} hosting the command, and registering its parent, {@link MainCommand}.
     *
     * @return the plugin hosting the command
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the {@link MainCommand} parent, where the subsequent sub commands are managed.
     *
     * @return the host of the command
     */
    public MainCommand getParent() {
        return parent;
    }

    /**
     * Gets the key/reference to the command, interchangeable with the 'label' parameter, which was kept to coincide
     * with the original {@link org.bukkit.command.CommandExecutor#onCommand(CommandSender, Command, String, String[])}.
     *
     * @return the key/reference for the command
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user instructions which are displayed (optionally) when commands are improperly formatted.
     *
     * @return the user instructions
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Gets the permission-string required for the {@link org.bukkit.entity.Player} to perform this command.
     *
     * @return the required {@link org.bukkit.entity.Player} permission
     */
    public String getPermission() {
        return permission;
    }
}
