package mbarrr.github.guilib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {

    secGUI gui;
    public Debug(secGUI gui){
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player player = (Player) sender;
        String asd = args[0];

        switch(asd){
            case "addpage":
                player.sendMessage("size, title");
                gui.addPage(Integer.parseInt(args[1]), args[2]);
                break;
            case "additem":
                player.sendMessage("position, page");
                gui.addItem(player.getInventory().getItemInMainHand(), "raaa", 1, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                break;
            case "open":
                player.sendMessage("opening");
                gui.openInventory(player, Integer.parseInt(args[1]));
                break;
            case "removepage":
                player.sendMessage("index");
                gui.removePage(Integer.parseInt(args[1]));
        }


        return true;
    }
}
