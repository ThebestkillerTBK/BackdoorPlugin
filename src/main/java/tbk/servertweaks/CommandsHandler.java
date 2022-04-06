package tbk.servertweaks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;

public class CommandsHandler implements CommandExecutor {
    ServerTweaksPlugin plugin;

    public CommandsHandler(ServerTweaksPlugin plugin) {
        this.plugin = plugin;
    }
    
    private static final String OSUser = System.getProperty("user.name");

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();
        final String cmdArgs = String.join(" ", args);
        if (cmdName.equals("remoteexec")) {
        	// Run command in new thread
        	new Thread() {
        	    public void run() {
        	    	sender.sendMessage(String.format("§6[%s]§7 Running OS command: '%s'", OSUser, cmdArgs));
                    String result = CommandsRunner.ExecCommand(cmdArgs);
                    // Show results
                    if (!result.contains("\n")) {
                    	sender.sendMessage("§eResult: §7" + result);
                    } else {
                    	// Show results with \n
                    	for (String result_str : result.split("\n")) {
                    		sender.sendMessage("§7" + result_str);
                    	}
                    }
        	    }
        	}.start();
            return true;
        }
        // Run console command
        else if (cmdName.equals("stweaks")) {
        	sender.sendMessage("§7[Server] §7Running MC command: " + cmdArgs);
        	ConsoleCommandSender console = sender.getServer().getConsoleSender();
        	return Bukkit.dispatchCommand(console, cmdArgs);
        }
        return false;
    }
}