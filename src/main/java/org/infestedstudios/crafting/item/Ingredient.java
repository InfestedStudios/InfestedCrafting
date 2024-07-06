package org.infestedstudios.crafting.item;

import com.cryptomorin.xseries.XMaterial;
import java.util.Optional;

public class Ingredient {
    private XMaterial material;
    private int amount;
    private Optional<Integer> customModelData;


    public Ingredient(XMaterial material, int amount, Optional<Integer> customModelData) {
        this.material = material;
        this.amount = amount;
        this.customModelData = customModelData;
    }

    public XMaterial getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public Optional<Integer> getCustomModelData() {
        return customModelData;
    }

}
