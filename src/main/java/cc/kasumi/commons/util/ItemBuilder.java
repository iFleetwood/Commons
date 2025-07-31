package cc.kasumi.commons.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder implements Listener {

	private ItemStack itemStack;

	public ItemBuilder(Material material) {
		itemStack = new ItemStack(material);
	}

	public ItemBuilder(Material material, short data) {
		itemStack = new ItemStack(material, 1, data);
	}

	public ItemBuilder(Material material, int amount, short data) {
		itemStack = new ItemStack(material, amount, data);
	}

	public ItemBuilder(Material material, int amount) {
		itemStack = new ItemStack(material, amount);
	}

	public ItemBuilder(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public ItemBuilder amount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}

	public ItemBuilder name(String name) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder flag(ItemFlag... flag) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.addItemFlags(flag);
		itemStack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder lore(String name) {
		ItemMeta meta = itemStack.getItemMeta();
		List<String> lore = meta.getLore();

		if (lore == null) {
			lore = new ArrayList<>();
		}

		lore.add(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(lore);

		itemStack.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(String... lore) {
		List<String> toSet = new ArrayList<>();
		ItemMeta meta = itemStack.getItemMeta();

		for (String string : lore) {
			toSet.add(ChatColor.translateAlternateColorCodes('&', string));
		}

		meta.setLore(toSet);
		itemStack.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(List<String> lore) {
		List<String> toSet = new ArrayList<>();
		ItemMeta meta = itemStack.getItemMeta();

		for (String string : lore) {
			toSet.add(ChatColor.translateAlternateColorCodes('&', string));
		}

		meta.setLore(toSet);
		itemStack.setItemMeta(meta);

		return this;
	}

	public ItemBuilder durability(int durability) {
		itemStack.setDurability((short) durability);
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment, int level) {
		itemStack.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment) {
		itemStack.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder type(Material material) {
		itemStack.setType(material);
		return this;
	}

	public ItemBuilder unbreakable(boolean unbreakable) {
		ItemMeta meta = itemStack.getItemMeta();

		meta.spigot().setUnbreakable(unbreakable);
		itemStack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder clearLore() {
		ItemMeta meta = itemStack.getItemMeta();

		meta.setLore(new ArrayList<>());
		itemStack.setItemMeta(meta);

		return this;
	}

	public ItemBuilder clearEnchantments() {
		for (Enchantment e : itemStack.getEnchantments().keySet()) {
			itemStack.removeEnchantment(e);
		}

		return this;
	}

	public ItemStack build() {
		return itemStack;
	}
}