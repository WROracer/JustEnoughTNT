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
        withExistingParent("sulfur_ore", modLoc("block/sulfur_ore"));
        withExistingParent("deepslate_sulfur_ore", modLoc("block/deepslate_sulfur_ore"));
        withExistingParent("example_tnt", modLoc("block/example_tnt"));
        withExistingParent("tnt_x5", modLoc("block/tnt_x5"));
        withExistingParent("tnt_x10", modLoc("block/tnt_x10"));
        withExistingParent("tnt_x20", modLoc("block/tnt_x20"));
        withExistingParent("tnt_x50", modLoc("block/tnt_x50"));
        withExistingParent("tnt_x100", modLoc("block/tnt_x100"));
        withExistingParent("tnt_x500", modLoc("block/tnt_x500"));

        withExistingParent("tnt_x1k", modLoc("block/tnt_x1k"));

        withExistingParent("drain_tnt", modLoc("block/drain_tnt"));
        withExistingParent("drain_tnt_x5", modLoc("block/drain_tnt_x5"));

        withExistingParent("jumping_tnt", modLoc("block/jumping_tnt"));

        withExistingParent("one_jump_tnt", modLoc("block/one_jump_tnt"));

        withExistingParent("russian_roulette_tnt", modLoc("block/russian_roulette_tnt"));
        withExistingParent("dupstep_tnt", modLoc("block/dupstep_tnt"));

        withExistingParent("rain_tnt", modLoc("block/rain_tnt"));

        withExistingParent("ore_miner_tnt", modLoc("block/ore_miner_tnt"));
        withExistingParent("flat_tnt", modLoc("block/flat_tnt"));
        withExistingParent("circle_tnt", modLoc("block/circle_tnt"));
        withExistingParent("cube_tnt", modLoc("block/cube_tnt"));
        withExistingParent("fuse_tnt", modLoc("block/fuse_tnt"));
        withExistingParent("block_change_tnt", modLoc("block/block_change_tnt"));
        // withExistingParent("farming_tnt", modLoc("block/farming_tnt"));

        //withExistingParent("metal_press",modLoc("block/metal_press"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "sulfur_dust");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
