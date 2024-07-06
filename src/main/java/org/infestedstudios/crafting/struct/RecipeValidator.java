package org.infestedstudios.crafting.struct;

public class RecipeValidator {

    /**
     * Validates a crafting recipe.
     * Ensures that the recipe has all required fields and the structure is valid.
     *
     * @param recipe The crafting recipe to validate.
     * @return true if the recipe is valid, false otherwise.
     */
    public static boolean validate(CraftingRecipe recipe) {
        // Basic validation checks
        if (recipe.getId() == null || recipe.getId().isEmpty()) return false;
        if (recipe.getIngredients() == null || recipe.getIngredients().isEmpty()) return false;
        if (recipe.getResult() == null) return false;
        if (recipe.getWidth() <= 0 || recipe.getHeight() <= 0) return false;
        return true;
    }
}

