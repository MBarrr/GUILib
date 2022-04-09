package mbarrr.github.guilib;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;

//TODO
//add getter for guiLib

public class GUI implements Listener {

    private List<GUI> childGUIS;
    private GUI parentGUI;
    private List<Inventory> pages;
    private GUILib guiLib;

    public GUI(GUILib guiLib, int size, String title, Plugin instance, GUI parentGUI) {
        instance.getServer().getPluginManager().registerEvents(this, instance);

        this.guiLib = guiLib;

        childGUIS = new ArrayList<>();
        pages = new ArrayList<>();

        // Create a new inventory, with no owner. size and title taken from args
        this.parentGUI = parentGUI;



        //load the first page
        addPage(size,title);

        if(parentGUI != null){
            pages.get(0).setItem(0, guiLib.getParentGUIArrow());
        }
    }

    public void addPage(int size, String title){

        //add right page changer to old rightmost page
        Inventory newPage = Bukkit.createInventory(null, size, title);

        if(!pages.isEmpty()) {
            //Get the current rightmost page
            Inventory nextPageFromRight = pages.get(pages.size()-1);

            //Add right arrow to current rightmost page
            nextPageFromRight.setItem(nextPageFromRight.getSize()-1, guiLib.getRightArrow());

            //add left arrow to new rightmost page
            newPage.setItem(size-9, guiLib.getLeftArrow());
        }
        //add left page changer to new rightmost page

        pages.add(newPage);
    }

    public void removePage(int index){
        //if index is negative, return
        if(index < 0) return;

        //If pages is empty or there is only one page, return
        if(pages.isEmpty() || pages.size() == 1) return;

        //If index is out of bounds, return
        if(index > pages.size()-1) return;


        //remove page
        pages.remove(index);

        //if page 1 is removed, get the new page 1 and lose its left arrow
        if(index == 0){
            Inventory newFirstPage = pages.get(0);
            newFirstPage.setItem(newFirstPage.getSize()-9, null);
        }

        //If this page was on the end, find the new page that will be on the end and remove right arrow from it.
        if(index == pages.size()){ //It was on the end
            Inventory newLastPage = pages.get(pages.size()-1);
            newLastPage.setItem(newLastPage.getSize()-1, null);
        }
    }

    public void addChildGUI(GUI gui){
        childGUIS.add(gui);
    }

    public GUI getChildGUI(int index){
        return childGUIS.get(index);
    }

    public void setChildGUI(GUI gui, int index){
        childGUIS.set(index, gui);
    }

    //add an item to the GUI
    public void addItem(ItemStack itemStack, NamespacedKey key, int tag, int position, int page){

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, tag);
        itemStack.setItemMeta(itemMeta);

        if(pages.size()+1 < page) return;

        pages.get(page).setItem(position, itemStack);
    }

    // You can open the inventory with this
    public void openInventory(Player player, int page) {
        player.openInventory(pages.get(page));
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        //stop the code if the inventory is not the menu we created
        if (!pages.contains(e.getClickedInventory())) return;

        //get clicked item
        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        //the clicked item is neither null nor air, and the inventory and player who is clicking are correct
        checkIfArrows(e);
        clickValid(e);
        e.setCancelled(true);
    }

    private void checkIfArrows(InventoryClickEvent e){
        //check if item is an arrow or points to a child/parent gui, and return if it does not
        if(!guiLib.hasItemAction(e.getCurrentItem(), guiLib.getArrowKey())) return;

        int itemAction = guiLib.getItemAction(e.getCurrentItem(), guiLib.getArrowKey());

        if(!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();

        switch(itemAction){
            case 0:
                //go left
                openInventory(player, pages.indexOf(e.getClickedInventory())-1);
                break;
            case 1:
                //go right
                openInventory(player, pages.indexOf(e.getClickedInventory())+1);
                break;
            case 2:
                //open parent gui
                parentGUI.openInventory(player, 0);
                break;
            default:
                break;
        }
    }


    //item clicked on is neithern null nor air, override this method for whateever tf
    public void clickValid(InventoryClickEvent e){


    }


    protected Inventory getInv(int page){
        return pages.get(page);
    }

    // Cancel dragging in our inventory

}
