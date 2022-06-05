package com.sirdanieliii.Operation_EggHunt.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Utilities {

    public static boolean eggInInventory(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); i++) { // Loops through player's inventory
            ItemStack item = player.getInventory().getItem(i);
            try {
                if (Objects.requireNonNull(item).getType().equals(Material.ITEM_FRAME) && Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).hasCustomModelData())
                    if (Objects.requireNonNull(item.getItemMeta()).getCustomModelData() == 1) {
                        return true;
                    }
            } catch (NullPointerException ignored) {
            }
        }
        return false;
    }


    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;
        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }
}
