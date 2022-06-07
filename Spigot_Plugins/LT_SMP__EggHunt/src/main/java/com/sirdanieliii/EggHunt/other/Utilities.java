package com.sirdanieliii.EggHunt.other;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static com.sirdanieliii.EggHunt.configuration.ConfigManager.cmdHeader;

public class Utilities {

    public static boolean itemIsEgg(ItemStack item) {
        if (Objects.requireNonNull(item).getType().equals(Material.ITEM_FRAME) && Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).hasCustomModelData())
            return Objects.requireNonNull(item.getItemMeta()).getCustomModelData() == 1;
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

    public static String locationToString(Location location) {
        return (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ();
    }

}
