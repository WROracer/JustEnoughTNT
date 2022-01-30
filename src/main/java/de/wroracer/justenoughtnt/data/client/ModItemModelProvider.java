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
        withExistingParent("silver_block",modLoc("block/silver_block"));
        withExistingParent("silver_ore",modLoc("block/silver_ore"));
        //withExistingParent("metal_press",modLoc("block/metal_press"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated,"silver_ingot");
        builder(itemGenerated,"silver_dust");
        builder(itemGenerated,"silver_plate");
        builder(itemGenerated,"iron_plate");
        builder(itemGenerated,"form_disk");
        builder(itemGenerated,"blank_disk");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0","item/"+name);
    }
}
