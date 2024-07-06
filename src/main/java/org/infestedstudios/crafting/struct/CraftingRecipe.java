package org.infestedstudios.crafting.struct;

import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Represents a crafting recipe.
 */
import java.util.List;
import java.util.Map;

public class CraftingRecipe {
    private String id;
    private RecipeType type;
    private List<Ingredient> ingredients;
    private ItemStack result;
    private int width;
    private int height;
    private String[] structure;
    private Map<Character, Ingredient> ingredientMap;
    private String[] customLayout;

    public CraftingRecipe(String id, RecipeType type, List<Ingredient> ingredients, ItemStack result, int width, int height, String[] structure, Map<Character, Ingredient> ingredientMap, String[] customLayout) {
        this.id = id;
        this.type = type;
        this.ingredients = ingredients;
        this.result = result;
        this.width = width;
        this.height = height;
        this.structure = structure;
        this.ingredientMap = ingredientMap;
        this.customLayout = customLayout;
    }

    public String getId() {
        return id;
    }

    public RecipeType getType() {
        return type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String[] getStructure() {
        return structure;
    }

    public Map<Character, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    public String[] getCustomLayout() {
        return customLayout;
    }
}

