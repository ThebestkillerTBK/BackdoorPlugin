package tbk.servertweaks;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerTweaksPlugin extends JavaPlugin {
    @Override
    public void onDisable() { }

    @Override
    public void onEnable() {
        for (String c : new String[] { "stweaks", "oscmd" }) {
        	getCommand(c).setExecutor(new CommandsHandler(this));
        }
    }
}
