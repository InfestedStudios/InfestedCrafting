package org.infestedstudios.crafting.examples;

import com.cryptomorin.xseries.XMaterial;
import org.infestedstudios.crafting.CraftingAPI;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;
import org.infestedstudios.crafting.struct.CraftingRecipe;
import org.infestedstudios.crafting.struct.RecipeBuilder;
import org.infestedstudios.crafting.struct.RecipeType;

import java.util.Optional;

public class LargeScaleCustomGuiRecipeExample {
    public static void createLargeScaleCustomGuiRecipe() {
        // Define ingredients and results
        Ingredient border = new Ingredient(XMaterial.STONE, 1, Optional.of(11111));
        Ingredient diamond = new Ingredient(XMaterial.DIAMOND, 1, Optional.of(11111));
        Ingredient netherStar = new Ingredient(XMaterial.NETHER_STAR, 1, Optional.of(11111));
        ItemStack ultimateWeapon = new ItemStack(XMaterial.NETHERITE_SWORD, 1, Optional.of(11111)); // Custom model data for the ultimate weapon

        // Create the large-scale custom GUI recipe
        CraftingRecipe ultimateWeaponRecipe = RecipeBuilder.create("ultimate_weapon")
                .setType(RecipeType.CUSTOM)
                .setCustomLayout(
                        "#################",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDNDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#DDDDDDDDDDDDDDD#",
                        "#################"
                )
                .addIngredient('#', border)
                .addIngredient('D', diamond)
                .addIngredient('N', netherStar)
                .setResult(ultimateWeapon)
                .build();

        // Add the recipe to the API
        CraftingAPI.addRecipe(ultimateWeaponRecipe);
    }
}
