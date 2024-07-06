package org.infestedstudios.crafting.examples;

import com.cryptomorin.xseries.XMaterial;
import org.infestedstudios.crafting.CraftingAPI;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;
import org.infestedstudios.crafting.struct.CraftingRecipe;
import org.infestedstudios.crafting.struct.RecipeBuilder;
import org.infestedstudios.crafting.struct.RecipeType;

import java.util.Optional;

public class FurnaceRecipeExample {
    public static void createFurnaceRecipe() {
        // Define ingredients and results
        Ingredient rubyOre = new Ingredient(XMaterial.REDSTONE_ORE, 1, Optional.empty());
        ItemStack refinedRuby = new ItemStack(XMaterial.REDSTONE, 1, Optional.of(54321)); // Custom model data for the refined ruby

        // Create the furnace recipe
        CraftingRecipe rubySmeltingRecipe = RecipeBuilder.create("ruby_smelting")
                .setType(RecipeType.FURNACE)
                .addIngredient('R', rubyOre)
                .setResult(refinedRuby)
                .build();

        // Add the recipe to the API
        CraftingAPI.addRecipe(rubySmeltingRecipe);
    }
}
