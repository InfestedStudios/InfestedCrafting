package org.infestedstudios.crafting.struct;

import org.infestedstudios.crafting.CraftingAPI;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearch {

    /**
     * Searches for recipes by a specific ingredient.
     *
     * @param ingredient The ingredient to search for.
     * @return A list of recipes that contain the specified ingredient.
     */
    public static List<CraftingRecipe> searchByIngredient(Ingredient ingredient) {
        List<CraftingRecipe> result = new ArrayList<>();
        for (CraftingRecipe recipe : CraftingAPI.getRecipeManager().getAllRecipes()) {
            if (recipe.getIngredients().contains(ingredient)) {
                result.add(recipe);
            }
        }
        return result;
    }

    /**
     * Searches for recipes by the result item.
     *
     * @param resultItem The result item to search for.
     * @return A list of recipes that produce the specified result item.
     */
    public static List<CraftingRecipe> searchByResult(ItemStack resultItem) {
        List<CraftingRecipe> result = new ArrayList<>();
        for (CraftingRecipe recipe : CraftingAPI.getRecipeManager().getAllRecipes()) {
            if (recipe.getResult().equals(resultItem)) {
                result.add(recipe);
            }
        }
        return result;
    }
}
