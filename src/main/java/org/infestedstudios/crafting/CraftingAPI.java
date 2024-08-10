package org.infestedstudios.crafting;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import org.infestedstudios.crafting.item.Ingredient;
import org.infestedstudios.crafting.item.ItemStack;
import org.infestedstudios.crafting.listeners.CraftingListener;
import org.infestedstudios.crafting.managers.CraftingEventManager;
import org.infestedstudios.crafting.managers.RecipeManager;
import org.infestedstudios.crafting.managers.RecipeManagerImpl;
import org.infestedstudios.crafting.struct.*;
import org.infestedstudios.crafting.struct.CraftingRecipe;
import org.infestedstudios.crafting.utils.CraftingUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Main API class for interacting with crafting recipes.
 */

/**
 * @Temper - Need to test every recipe and make some complex recipes.
 * s
 */

public class CraftingAPI {
    private static RecipeManager recipeManager = new RecipeManagerImpl();
    private static Plugin plugin;

    public static void init(Plugin plugin) {
        CraftingAPI.plugin = plugin;
    }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public static void addRecipe(CraftingRecipe recipe) {
        if (RecipeValidator.validate(recipe)) {
            recipeManager.addRecipe(recipe);
            switch (recipe.getType()) {
                case WORKBENCH:
                    registerWorkbenchRecipe(recipe);
                    break;
                case FURNACE:
                    registerFurnaceRecipe(recipe);
                    break;
                case CUSTOM:
                    registerCustomRecipe(recipe);
                    break;
                default:
                    break;
            }
            saveRecipeToPluginPath(recipe);
        }
    }

    public static void removeRecipe(String recipeId) {
        recipeManager.removeRecipe(recipeId);
        deleteRecipeFromPluginPath(recipeId);
    }

    public static void loadRecipeFromJsonFile(String filePath) {
        try {
            CraftingRecipe recipe = CraftingUtil.deserializeJsonToRecipe(new String(Files.readAllBytes(Paths.get(filePath))));
            addRecipe(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRecipeToJsonFile(CraftingRecipe recipe, String filePath) {
        try {
            Files.write(Paths.get(filePath), CraftingUtil.serializeRecipeToJson(recipe).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveRecipeToPluginPath(CraftingRecipe recipe) {
        File recipesFolder = new File(plugin.getDataFolder(), "recipes");
        if (!recipesFolder.exists()) {
            recipesFolder.mkdirs();
        }
        File recipeFile = new File(recipesFolder, recipe.getId() + ".json");
        saveRecipeToJsonFile(recipe, recipeFile.getPath());
    }

    private static void deleteRecipeFromPluginPath(String recipeId) {
        File recipeFile = new File(plugin.getDataFolder(), "recipes" + File.separator + recipeId + ".json");
        if (recipeFile.exists()) {
            recipeFile.delete();
        }
    }

    public static void registerCraftingListener(CraftingListener listener) {
        CraftingEventManager.registerListener(listener);
    }

    public static List<CraftingRecipe> searchRecipesByIngredient(Ingredient ingredient) {
        return RecipeSearch.searchByIngredient(ingredient);
    }

    public static List<CraftingRecipe> searchRecipesByResult(ItemStack resultItem) {
        return RecipeSearch.searchByResult(resultItem);
    }

    private static void registerWorkbenchRecipe(CraftingRecipe recipe) {
        NamespacedKey key = new NamespacedKey(plugin, recipe.getId());
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, convertToBukkitItemStack(recipe.getResult()));

        shapedRecipe.shape(recipe.getStructure());

        for (Map.Entry<Character, Ingredient> entry : recipe.getIngredientMap().entrySet()) {
            shapedRecipe.setIngredient(entry.getKey(), entry.getValue().getMaterial().parseMaterial());
        }

        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerFurnaceRecipe(CraftingRecipe recipe) {
        NamespacedKey key = new NamespacedKey(plugin, recipe.getId());
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(key, convertToBukkitItemStack(recipe.getResult()), recipe.getIngredients().get(0).getMaterial().parseMaterial(), 0.1f, 200);
        Bukkit.addRecipe(furnaceRecipe);
    }

    private static void registerCustomRecipe(CraftingRecipe recipe) {
        NamespacedKey key = new NamespacedKey(plugin, recipe.getId());
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, convertToBukkitItemStack(recipe.getResult()));
        shapedRecipe.shape(recipe.getCustomLayout());

        for (Map.Entry<Character, Ingredient> entry : recipe.getIngredientMap().entrySet()) {
            shapedRecipe.setIngredient(entry.getKey(), entry.getValue().getMaterial().parseMaterial());
        }

        Bukkit.addRecipe(shapedRecipe);
    }

    private static org.bukkit.inventory.ItemStack convertToBukkitItemStack(ItemStack itemStack) {
        org.bukkit.inventory.ItemStack bukkitItemStack = new org.bukkit.inventory.ItemStack(itemStack.getMaterial().parseMaterial(), itemStack.getAmount());
        itemStack.getCustomModelData().ifPresent(modelData -> {
            org.bukkit.inventory.meta.ItemMeta meta = bukkitItemStack.getItemMeta();
            meta.setCustomModelData(modelData);
            bukkitItemStack.setItemMeta(meta);
        });
        return bukkitItemStack;
    }


    public static void autoFillCraftingGrid(Player player, InventoryView inventoryView, CraftingRecipe recipe) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Map<Character, Ingredient> ingredientMap = recipe.getIngredientMap();
            Map<Character, Integer> ingredientsNeeded = new HashMap<>();
            ingredientMap.forEach((symbol, ingredient) -> {
                ingredientsNeeded.put(symbol, ingredient.getAmount());
            });

            CraftingInventory craftingInventory = (CraftingInventory) inventoryView.getTopInventory();
            Map<Integer, org.bukkit.inventory.ItemStack> toBeAdded = new HashMap<>();

            // Fill the crafting grid based on the recipe structure
            for (int row = 0; row < recipe.getHeight(); row++) {
                for (int col = 0; col < recipe.getWidth(); col++) {
                    char symbol = recipe.getStructure()[row].charAt(col);
                    if (symbol != ' ' && ingredientMap.containsKey(symbol)) {
                        Ingredient ingredient = ingredientMap.get(symbol);
                        int inventorySlot = row * 3 + col;
                        org.bukkit.inventory.ItemStack playerItemStack = new org.bukkit.inventory.ItemStack(ingredient.getMaterial().parseMaterial(), ingredient.getAmount());

                        if (player.getInventory().containsAtLeast(playerItemStack, ingredientsNeeded.get(symbol))) {
                            toBeAdded.put(inventorySlot, playerItemStack);
                            ingredientsNeeded.put(symbol, ingredientsNeeded.get(symbol) - ingredient.getAmount());
                        }
                    }
                }
            }

            Bukkit.getScheduler().runTask(plugin, () -> {
                // Clear the crafting grid
                craftingInventory.clear();

                // Add items to the crafting grid
                toBeAdded.forEach((slot, itemStack) -> {
                    craftingInventory.setItem(slot, itemStack);
                    player.getInventory().removeItem(itemStack);
                });

                player.updateInventory();
            });
        });
    }
}
