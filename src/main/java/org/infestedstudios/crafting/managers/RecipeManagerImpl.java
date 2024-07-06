package org.infestedstudios.crafting.managers;

import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.struct.CraftingRecipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the RecipeManager interface.
 */
public class RecipeManagerImpl implements RecipeManager {
    private Map<String, CraftingRecipe> recipes = new HashMap<>();

    @Override
    public void addRecipe(CraftingRecipe recipe) {
        recipes.put(recipe.getId(), recipe);
    }

    @Override
    public Optional<CraftingRecipe> getRecipeById(String id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public void removeRecipe(String id) {
        recipes.remove(id);
    }

    @Override
    public void addIngredientToRecipe(String recipeId, Ingredient ingredient) {
        CraftingRecipe recipe = recipes.get(recipeId);
        if (recipe != null) {
            recipe.getIngredientMap().put(ingredient.getMaterial().name().charAt(0), ingredient);
        }
    }

    @Override
    public CraftingRecipe[] getAllRecipes() {
        return recipes.values().toArray(new CraftingRecipe[0]);
    }
}
