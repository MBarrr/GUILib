package mbarrr.github.guilib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;

public class secGUI extends GUI{
    public secGUI(int size, String title, Plugin instance, @Nullable GUI parentGUI) {
        super(size, title, instance, parentGUI);

        addPage(size, "page2");
        addPage(size, "page3");

        addItem(new ItemStack(Material.DIRT), "test", 0, 3, 0);
        addItem(new ItemStack(Material.DIRT), "test", 1, 4, 1);
        addItem(new ItemStack(Material.DIRT), "test", 2, 2, 2);
    }



    @Override
    public void clickValid(InventoryClickEvent e){
        if(!hasItemAction(e.getCurrentItem(), "test")) return;
        int action = getItemAction(e.getCurrentItem(), "test");

        switch(action){
            case 0:
                Bukkit.broadcastMessage("test");
                break;
            case 1:
                Bukkit.broadcastMessage("this is page 2");
                break;
            case 2:
                Bukkit.broadcastMessage("my tag is: "+action);
                break;
            default:
                Bukkit.broadcastMessage("Somehow we got here");
                break;
        }

    }
}
