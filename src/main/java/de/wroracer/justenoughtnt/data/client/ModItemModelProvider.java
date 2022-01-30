package de.wroracer.justenoughtnt.data.client;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, JustEnoughTNT.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("sulfur_ore",modLoc("block/sulfur_ore"));
        withExistingParent("deepslate_sulfur_ore",modLoc("block/deepslate_sulfur_ore"));
        //withExistingParent("metal_press",modLoc("block/metal_press"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated,"sulfur_dust");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0","item/"+name);
    }
}
