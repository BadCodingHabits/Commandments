package me.badcodinghabits.commandments;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BadCodingHabits
 */
public abstract class MainCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    /**
     * The only data needed is from the plugin used, in order to access some basic information one would normally need
     * through commands, as well as other functions.
     *
     * @param plugin the {@link JavaPlugin} hosting the command
     */
    public MainCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers a sub command into the instance of the main command, can be called via {@link #getSubCommands()} and
     * subsequently searching for the specific command "key".
     *
     * @param subCommand the {@link SubCommand} instance to be registered
     */
    public void registerSubCommand(SubCommand subCommand) {
        subCommands.put(subCommand.getName(), subCommand);
    }

    /**
     * Gets the active plugin used.
     *
     * @return the {@link JavaPlugin} hosting the command
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the map of sub commands, which is used to add, remove, and interact with sub commands without using the
     * built-in {@link #registerSubCommand(SubCommand)}.
     *
     * @return a {@link Map} hosting the {@link SubCommand} instances
     */
    public Map<String, SubCommand> getSubCommands() {
        return subCommands;
    }
}
