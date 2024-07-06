package org.infestedstudios.crafting.struct;

import com.cryptomorin.xseries.XMaterial;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;
import org.infestedstudios.crafting.struct.CraftingRecipe;
import org.infestedstudios.crafting.struct.RecipeType;

import java.util.*;

/**
 * Builder class for creating crafting recipes.
 */
import com.cryptomorin.xseries.XMaterial;
import java.util.*;

public class RecipeBuilder {
    private String id;
    private RecipeType type;
    private String[] structure;
    private Map<Character, Ingredient> ingredientMap = new HashMap<>();
    private ItemStack result;
    private int width;
    private int height;
    private String[] customLayout;

    public static RecipeBuilder create(String id) {
        return new RecipeBuilder(id);
    }

    private RecipeBuilder(String id) {
        this.id = id;
    }

    public RecipeBuilder setType(RecipeType type) {
        this.type = type;
        return this;
    }

    public RecipeBuilder setStructure(String... structure) {
        this.structure = structure;
        this.height = structure.length;
        this.width = structure[0].length();
        return this;
    }

    public RecipeBuilder addIngredient(char symbol, Ingredient ingredient) {
        ingredientMap.put(symbol, ingredient);
        return this;
    }

    public RecipeBuilder setResult(ItemStack result) {
        this.result = result;
        return this;
    }

    public RecipeBuilder setCustomLayout(String... customLayout) {
        this.customLayout = customLayout;
        return this;
    }

    public CraftingRecipe build() {
        List<Ingredient> ingredients = new ArrayList<>(ingredientMap.values());
        return new CraftingRecipe(id, type, ingredients, result, width, height, structure, ingredientMap, customLayout);
    }
}

