package mbarrr.github.guilib;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ObjectViewer<T extends IItemObject> extends GUI{

    public ObjectViewer(GUILib guiLib, int size, String title, Plugin instance, GUI parentGUI) {
        super(guiLib, size, title, instance, parentGUI);
    }


    public void loadCustomItems(List<T> items, NamespacedKey menuKey){
        getInv(0).clear();

        for(int i = 0; i < items.size(); i++){
            IItemObject customItem = items.get(i);
            ItemStack icon = customItem.getItem();

            String adminInfo = customItem.getAdminInfo();

            if(adminInfo == null) continue;

            List<String> lore = icon.getItemMeta().getLore();
            lore.add("********** ADMIN INFO **********");

            lore.add(adminInfo);

            ItemMeta meta = icon.getItemMeta();
            meta.setLore(lore);
            icon.setItemMeta(meta);

            addItem(icon, menuKey, i, i, 0);
        }
    }
}
