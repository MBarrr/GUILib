package mbarrr.github.guilib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUILib extends JavaPlugin {

    private String pathStart = "guilib_";

    NamespacedKey arrowKey = new NamespacedKey(this, pathStart+"arrow");

    private ItemStack leftArrow;
    private ItemStack rightArrow;
    private ItemStack parentGUIArrow;

    @Override
    public void onEnable() {



        loadItems();
        this.getCommand("Debug").setExecutor(new Debug(new secGUI(9, "test", this, null)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadItems(){
        leftArrow = giveItemTags(arrowKey, 0, Material.MUSIC_DISC_CAT);
        rightArrow = giveItemTags(arrowKey, 1, Material.MUSIC_DISC_CAT);
        parentGUIArrow = giveItemTags(arrowKey, 2, Material.MUSIC_DISC_BLOCKS);
    }

    private ItemStack giveItemTags(NamespacedKey key, int tag, Material material){

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, tag);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
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

    public static GUILib getInstance(){
        return (GUILib) Bukkit.getPluginManager().getPlugin("GUILib");
    }
}
