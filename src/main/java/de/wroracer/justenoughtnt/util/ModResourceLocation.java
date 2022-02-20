package de.wroracer.justenoughtnt.util;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.resources.ResourceLocation;

public class ModResourceLocation extends ResourceLocation {
    public ModResourceLocation(String resourceName) {
        super(addModNamespace(resourceName));
    }

    private static String addModNamespace(String resourceName) {
        if (resourceName.contains(":")) {
            return resourceName;
        }
        return JustEnoughTNT.MOD_ID + ":" + resourceName;
    }
}
