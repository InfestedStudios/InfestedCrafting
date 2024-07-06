package org.infestedstudios.crafting.examples;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.infestedstudios.crafting.CraftingAPI;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;
import org.infestedstudios.crafting.struct.CraftingRecipe;
import org.infestedstudios.crafting.struct.RecipeBuilder;
import org.infestedstudios.crafting.struct.RecipeType;

import java.util.Optional;

public class BasicCustomRecipeExample {
    public static void createBasicCustomRecipe() {
        // Define ingredients and results
        Ingredient ironIngot = new Ingredient(XMaterial.IRON_INGOT, 1, Optional.of(12345));
        Ingredient stick = new Ingredient(XMaterial.STICK, 1, Optional.of(12345));
        ItemStack customSword = new ItemStack(XMaterial.DIAMOND_SWORD, 1, Optional.of(12345)); // Custom model data for the sword

        // Create the custom workbench recipe
        CraftingRecipe customSwordRecipe = RecipeBuilder.create("custom_sword")
                .setType(RecipeType.WORKBENCH)
                .setStructure(
                        " I ",
                        " I ",
                        " S "
                )
                .addIngredient('I', ironIngot)
                .addIngredient('S', stick)
                .setResult(customSword)
                .build();

        // Add the recipe to the API
        CraftingAPI.addRecipe(customSwordRecipe);
    }

    public static void autoFillExample(Player player, InventoryView inventoryView) {
        // Example of using the auto-fill feature
        CraftingRecipe customSwordRecipe = CraftingAPI.getRecipeManager().getRecipeById("custom_sword").orElse(null);
        if (customSwordRecipe != null) {
            CraftingAPI.autoFillCraftingGrid(player, inventoryView, customSwordRecipe);
        }
    }
}
