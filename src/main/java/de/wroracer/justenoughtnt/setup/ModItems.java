package de.wroracer.justenoughtnt.setup;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot",()->
    //         new Item(new Item.Properties().tab(ModTabBlock.MAIN_TAB)));

    public static final RegistryObject<Item> SULFUR_DUST = Registration.ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties().tab(ModTabBlock.MAIN_TAB)));

    static void register() {
    }
}
