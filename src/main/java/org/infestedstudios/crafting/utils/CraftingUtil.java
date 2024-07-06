package org.infestedstudios.crafting.utils;

import me.temper.json.JsonAPI;
import me.temper.json.storage.StorageMode;
import org.infestedstudios.crafting.struct.CraftingRecipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Utility class for handling the serialization and deserialization of CraftingRecipe objects using the JsonAPI.
 */
public class CraftingUtil {

    private static final JsonAPI jsonAPI = JsonAPI.getInstance();

    /**
     * Serializes a CraftingRecipe object to a JSON string.
     * @param recipe The CraftingRecipe object to serialize.
     * @return The JSON string representation of the recipe.
     */
    public static String serializeRecipeToJson(CraftingRecipe recipe) {
        String tempFileName = "temp_" + UUID.randomUUID() + ".json";
        jsonAPI.store(tempFileName, recipe, StorageMode.DISK_ONLY);
        try {
            return new String(Files.readAllBytes(Paths.get(tempFileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            jsonAPI.deleteAsync(tempFileName); // Clean up the temporary file
        }
    }

    /**
     * Deserializes a JSON string to a CraftingRecipe object.
     * @param json The JSON string to deserialize.
     * @return The CraftingRecipe object.
     */
    public static CraftingRecipe deserializeJsonToRecipe(String json) {
        String tempFileName = "temp_" + UUID.randomUUID() + ".json";
        try {
            Files.write(Paths.get(tempFileName), json.getBytes());
            final CraftingRecipe[] recipe = new CraftingRecipe[1];
            jsonAPI.load(tempFileName, CraftingRecipe.class, (Consumer<CraftingRecipe>) loadedRecipe -> recipe[0] = loadedRecipe, StorageMode.DISK_ONLY);
            return recipe[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            jsonAPI.deleteAsync(tempFileName); // Clean up the temporary file
        }
    }
}
