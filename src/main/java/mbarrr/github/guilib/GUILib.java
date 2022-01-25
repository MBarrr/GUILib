package mbarrr.github.guilib;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUILib{

    private static String pathStart = "guilib_";
    private static String arrowPath = pathStart+"arrow";

    private static ItemStack leftArrow = giveItemTags(arrowPath, 0, Material.MUSIC_DISC_CAT);
    private static ItemStack rightArrow = giveItemTags(arrowPath, 1, Material.MUSIC_DISC_CAT);
    private static ItemStack parentGUIArrow = giveItemTags(arrowPath, 2, Material.MUSIC_DISC_BLOCKS);

   private static ItemStack giveItemTags(String path, int tag, Material material){
        ItemStack itemStack = new ItemStack(material);

        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
        itemcompound.setInt(path, tag);
        nmsItem.setTag(itemcompound);

        ItemStack finishedItem = CraftItemStack.asBukkitCopy(nmsItem);
        return finishedItem;
    }

    public static  String getArrowPath() {
        return arrowPath;
    }

    public static  ItemStack getLeftArrow() {
        return leftArrow;
    }

    public static  ItemStack getRightArrow() {
        return rightArrow;
    }

    public static  ItemStack getParentGUIArrow() {
        return parentGUIArrow;
    }
}
