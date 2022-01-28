package mbarrr.github.guilib;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.List;

public final class GUILib{

    Plugin instance;

    public GUILib(Plugin instance){
        this.instance = instance;
    }



    private String pathStart = "guilib_";

    NamespacedKey arrowKey = new NamespacedKey(instance, pathStart+"arrow");

    private ItemStack leftArrow = giveItemTags(arrowKey, 0, Material.MUSIC_DISC_CAT);
    private ItemStack rightArrow = giveItemTags(arrowKey, 1, Material.MUSIC_DISC_CAT);
    private ItemStack parentGUIArrow = giveItemTags(arrowKey, 2, Material.MUSIC_DISC_BLOCKS);

    public ItemStack giveItemTags(NamespacedKey key, int tag, Material material){

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, tag);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack renameItem(ItemStack item, String name){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack setItemLore(ItemStack item, List<String> lore){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public int getItemAction(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return(container.get(key, PersistentDataType.INTEGER));

    }

    public boolean hasItemAction(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(key, PersistentDataType.INTEGER);
    }

    public NamespacedKey getArrowKey(){
        return arrowKey;
    }

    public  ItemStack getLeftArrow() {
        return leftArrow;
    }

    public  ItemStack getRightArrow() {
        return rightArrow;
    }

    public  ItemStack getParentGUIArrow() {
        return parentGUIArrow;
    }
}
