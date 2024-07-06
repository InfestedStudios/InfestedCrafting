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

public class ComplexCustomGuiRecipeExample {
    public static void createComplexCustomGuiRecipe() {
        // Define ingredients and results
        Ingredient border = new Ingredient(XMaterial.STONE, 1, Optional.of(67890));
        Ingredient ironIngot = new Ingredient(XMaterial.IRON_INGOT, 1, Optional.of(67890));
        Ingredient stick = new Ingredient(XMaterial.STICK, 1, Optional.of(67890));
        ItemStack customHammer = new ItemStack(XMaterial.DIAMOND_AXE, 1, Optional.of(67890)); // Custom model data for the hammer

        // Create the custom GUI recipe
        CraftingRecipe customHammerRecipe = RecipeBuilder.create("custom_hammer")
                .setType(RecipeType.CUSTOM)
                .setCustomLayout(
                        "#########",
                        "#A#A#A#A#",
                        "#S#S#S#S#",
                        "#A#A#A#A#",
                        "#S#S#S#S#",
                        "#########"
                )
                .addIngredient('#', border)
                .addIngredient('A', ironIngot)
                .addIngredient('S', stick)
                .setResult(customHammer)
                .build();

        // Add the recipe to the API
        CraftingAPI.addRecipe(customHammerRecipe);
    }

    public static void autoFillExample(Player player, InventoryView inventoryView) {
        // Example of using the auto-fill feature
        CraftingRecipe customHammerRecipe = CraftingAPI.getRecipeManager().getRecipeById("custom_hammer").orElse(null);
        if (customHammerRecipe != null) {
            CraftingAPI.autoFillCraftingGrid(player, inventoryView, customHammerRecipe);
        }
    }
}
