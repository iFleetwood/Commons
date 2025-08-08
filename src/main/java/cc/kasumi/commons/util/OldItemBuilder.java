package cc.kasumi.commons.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OldItemBuilder {

    private Material type;
    private String name;
    private String[] lore;
    private int amount;
    private short data;
    private boolean unbreakable = false;

    public OldItemBuilder(Material type, String name) {
        this.type = type;
        this.name = name;
        this.lore = new String[0];
        this.amount = 1;
    }

    public OldItemBuilder(Material type, String name, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = 1;
    }

    public OldItemBuilder(Material type, String name, List<String> lore) {
        this.type = type;
        this.name = name;
        this.lore = lore.toArray(new String[0]);
        this.amount = 1;
    }

    public OldItemBuilder(Material type, String name, short data) {
        this(type, name);

        this.data = data;
    }

    public OldItemBuilder(Material type, String name, boolean unbreakable, String... lore) {
        this(type, name, lore);

        this.unbreakable = unbreakable;
    }

    public OldItemBuilder(Material type, String name, int amount, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
    }

    public OldItemBuilder(Material type, String name, int amount, short data) {
        this.type = type;
        this.name = name;
        this.lore = new String[0];
        this.amount = amount;
        this.data = data;
    }

    public OldItemBuilder(Material type, String name, int amount, short data, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
        this.data = data;
    }

    public OldItemBuilder(Material type, String name, int amount, short data, List<String> lore) {
        this.type = type;
        this.name = name;
        this.lore = lore.toArray(new String[0]);
        this.amount = amount;
        this.data = data;
    }

    public ItemStack build() {
        List<String> lore = new ArrayList<>();

        for (String aLore : this.lore) {
            lore.add(MessageUtil.color(aLore));
        }

        ItemStack itemStack = new ItemStack(this.type, this.amount, this.data);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(lore);
        itemMeta.spigot().setUnbreakable(this.unbreakable);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
