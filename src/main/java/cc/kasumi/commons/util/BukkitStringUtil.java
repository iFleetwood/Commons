package cc.kasumi.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BukkitStringUtil {

    private static final char data = '@';
    private static final char splitter = ';';
    private static final char inventoryDataSplitter = '!';
    private static final char inventoryContentsDataSplitter = '-';
    private static final char enchantmentSplitter = '=';
    private static final char enchantmentDataSplitter = '/';

    public static String locationToString(Location location) {
        return location.getWorld().getName() + splitter + location.getX() + splitter + location.getY() + splitter + location.getZ() + splitter + location.getYaw() + splitter + location.getPitch();
    }

    public static Location locationFromString(String string) {
        String[] args = string.split(";");

        return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5]));
    }

    public static String typeDataToString(TypeData typeData) {
        return typeData.getType().toString() + splitter + typeData.getData();
    }

    public static TypeData typeDataFromString(String string) {
        String[] arguments = string.split(";");

        return new TypeData(Material.matchMaterial(arguments[0]), Short.parseShort(arguments[1]));
    }

    public static String itemStackToString(ItemStack itemStack) {
        StringBuilder builder = new StringBuilder();

        builder.append(itemStack.getType().toString());
        builder.append(data).append(itemStack.getDurability());
        builder.append(data).append(itemStack.getAmount());

        Map<Enchantment, Integer> enchantments = itemStack.getEnchantments();

        if (!enchantments.isEmpty()) {
            builder.append(data).append("e");

            int i = enchantments.size();
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                builder.append(entry.getKey().getId()).append(enchantmentDataSplitter).append(entry.getValue()); // Probably change enchantmentDataSplitter

                if (--i > 0) {
                    builder.append(enchantmentSplitter);
                }
            }
        }

        if (!itemStack.hasItemMeta()) {
            return builder.toString();
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta.getDisplayName() != null) {
            builder.append(data).append("n").append(itemMeta.getDisplayName());
        }

        if (itemMeta.getLore() != null) {
            builder.append(data).append("l").append(itemMeta.getLore());
        }

        return builder.toString();
    }

    public static ItemStack itemStackFromString(String string) {
        String[] arguments = string.split("@");

        Material type = Material.matchMaterial(arguments[0]);
        int amount = Integer.parseInt(arguments[2]);
        short data = Short.parseShort(arguments[1]);

        ItemStack toReturn = new ItemStack(type, amount, data);
        ItemMeta itemMeta = toReturn.getItemMeta();

        for (int i = 3; i < arguments.length; i++) {
            String argument = arguments[i];
            String first = argument.substring(0, 1);
            String formattedArgument = argument.substring(1);

            if (first.equals("e")) {
                String[] enchantments = formattedArgument.split(enchantmentSplitter + "");

                for (String enchantment : enchantments) {
                    String[] values = enchantment.split(enchantmentDataSplitter + "");

                    itemMeta.addEnchant(Enchantment.getById(Integer.valueOf(values[0])), Integer.parseInt(values[1]), true);
                }

                continue;
            }

            if (first.equals("n")) {
                itemMeta.setDisplayName(MessageUtil.color(formattedArgument));
                continue;
            }

            if (first.equals("l")) {
                List<String> lore = Arrays.asList(formattedArgument.split(","));

                int j = 0;
                for (String loreLine : lore) {
                    int length = loreLine.length() - 1;
                    String formattedLoreLine = loreLine.substring(1, length);

                    lore.set(j++, MessageUtil.color(formattedLoreLine));
                }

                itemMeta.setLore(lore);
            }
        }

        toReturn.setItemMeta(itemMeta);

        return toReturn;
    }

    public static String inventoryToString(Inventory inventory) {
        StringBuilder builder = new StringBuilder();

        int size = inventory.getSize();

        builder.append(size).append(inventoryDataSplitter);
        builder.append(inventory.getName()).append(inventoryDataSplitter);

        for (int i = 0; i < size; i++) {
            ItemStack itemStack = inventory.getItem(i);

            if (ItemStackUtil.isNullOrAir(itemStack)) {
                continue;
            }

            if (i != 0) {
                builder.append(splitter);
            }

            builder.append(i).append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(itemStack));
        }

        return builder.toString();
    }

    public static Inventory inventoryFromString(String string) {
        String[] arguments = string.split(inventoryDataSplitter + "");

        Inventory toReturn = Bukkit.createInventory(null, Integer.valueOf(arguments[0]), MessageUtil.color(arguments[1]));

        for (String stringContents : arguments[2].split(splitter + "")) {
            String[] data = stringContents.split(inventoryContentsDataSplitter + "");

            toReturn.setItem(Integer.parseInt(data[0]), itemStackFromString(data[1]));
        }

        return toReturn;
    }

    public static String playerInvToString(PlayerInv inventory) {
        StringBuilder builder = new StringBuilder();
        ItemStack[] contents = inventory.getContents();
        ItemStack[] armorContents = inventory.getArmorContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack itemStack = contents[i];

            if (ItemStackUtil.isNullOrAir(itemStack)) {
                continue;
            }

            if (i != 0) {
                builder.append(splitter);
            }

            builder.append(i).append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(itemStack));
        }

        ItemStack helmet = armorContents[3];
        ItemStack chestPlate = armorContents[2];
        ItemStack leggings = armorContents[1];
        ItemStack boots = armorContents[0];

        if (!ItemStackUtil.isNullOrAir(helmet)) {
            builder.append(splitter);

            builder.append("h").append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(helmet));
        }

        if (!ItemStackUtil.isNullOrAir(chestPlate)) {
            builder.append(splitter);

            builder.append("c").append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(chestPlate));
        }

        if (!ItemStackUtil.isNullOrAir(leggings)) {
            builder.append(splitter);

            builder.append("l").append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(leggings));
        }

        if (!ItemStackUtil.isNullOrAir(boots)) {
            builder.append(splitter);

            builder.append("b").append(inventoryContentsDataSplitter);
            builder.append(itemStackToString(boots));
        }

        return builder.toString();
    }

    public static PlayerInv playerInvFromString(String string) {
        ItemStack[] contents = new ItemStack[36];
        ItemStack[] armorContents = new ItemStack[4];

        for (String stringContent : string.split(splitter + "")) {
            String[] data = stringContent.split(inventoryContentsDataSplitter + "");

            String data1 = data[0];
            String data2 = data[1];

            if (data1.equals("b")) {
                armorContents[0] = itemStackFromString(data2);
                continue;
            }

            if (data1.equals("l")) {
                armorContents[1] = itemStackFromString(data2);
                continue;
            }

            if (data1.equals("c")) {
                armorContents[2] = itemStackFromString(data2);
                continue;
            }

            if (data1.equals("h")) {
                armorContents[3] = itemStackFromString(data2);
                continue;
            }

            contents[Integer.parseInt(data1)] = itemStackFromString(data2);
        }

        return new PlayerInv(contents, armorContents);
    }
}
