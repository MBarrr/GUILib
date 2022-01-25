package mbarrr.github.guilib;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUILib extends JavaPlugin {

    private String pathStart;
    private String arrowPath;

    private ItemStack leftArrow;
    private ItemStack rightArrow;
    private ItemStack parentGUIArrow;

    @Override
    public void onEnable() {
        pathStart = "guilib_";
        arrowPath = pathStart+"arrow";
        this.getCommand("Debug").setExecutor(new Debug(new GUI(9, "test", this, null)));


        loadItems();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadItems(){
        leftArrow = giveItemTags(arrowPath, 0, Material.MUSIC_DISC_CAT);
        rightArrow = giveItemTags(arrowPath, 1, Material.MUSIC_DISC_CAT);
        parentGUIArrow = giveItemTags(arrowPath, 2, Material.MUSIC_DISC_BLOCKS);
    }

    private ItemStack giveItemTags(String path, int tag, Material material){
        ItemStack itemStack = new ItemStack(material);

        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
        itemcompound.setInt(path, tag);
        nmsItem.setTag(itemcompound);

        ItemStack finishedItem = CraftItemStack.asBukkitCopy(nmsItem);
        return finishedItem;
    }

    public  String getArrowPath() {
        return arrowPath;
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
