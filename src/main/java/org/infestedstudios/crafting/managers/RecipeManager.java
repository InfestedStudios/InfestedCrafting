package org.infestedstudios.crafting.managers;

import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.struct.CraftingRecipe;

import java.util.Optional;

/**
 * Interface for managing crafting recipes.
 */
public interface RecipeManager {
    void addRecipe(CraftingRecipe recipe);
    Optional<CraftingRecipe> getRecipeById(String id);
    void removeRecipe(String id);
    void addIngredientToRecipe(String recipeId, Ingredient ingredient);
    CraftingRecipe[] getAllRecipes();
}
