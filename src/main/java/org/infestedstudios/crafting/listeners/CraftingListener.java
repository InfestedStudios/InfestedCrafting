package org.infestedstudios.crafting.listeners;

import org.bukkit.entity.Player;
import org.infestedstudios.crafting.struct.CraftingRecipe;

public interface CraftingListener {
    void onCraft(CraftingRecipe recipe, Player player);
}

