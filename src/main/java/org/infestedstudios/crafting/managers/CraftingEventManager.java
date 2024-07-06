package org.infestedstudios.crafting.managers;

import org.bukkit.entity.Player;
import org.infestedstudios.crafting.listeners.CraftingListener;
import org.infestedstudios.crafting.struct.CraftingRecipe;

import java.util.ArrayList;
import java.util.List;

public class CraftingEventManager {
    private static List<CraftingListener> listeners = new ArrayList<>();

    /**
     * Registers a crafting listener.
     *
     * @param listener The crafting listener to register.
     */
    public static void registerListener(CraftingListener listener) {
        listeners.add(listener);
    }

    /**
     * Triggers the craft event for all registered listeners.
     *
     * @param recipe The crafting recipe.
     * @param player The player who crafted the recipe.
     */
    public static void triggerCraftEvent(CraftingRecipe recipe, Player player) {
        for (CraftingListener listener : listeners) {
            listener.onCraft(recipe, player);
        }
    }
}
