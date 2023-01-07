package de.wroracer.justenoughtnt.data.client;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    private ExistingFileHelper existingFileHelper;

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, JustEnoughTNT.MOD_ID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SULFUR_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_SULFUR_ORE.get());

        simpleBlock(ModBlocks.EXAMPLE_TNT.get(), getTopSideBottomsModel("example_tnt"));
        simpleBlock(ModBlocks.TNT_X5.get(), getTopSideBottomsModel("tnt_x5"));
        simpleBlock(ModBlocks.TNT_X10.get(), getTopSideBottomsModel("tnt_x10"));
        simpleBlock(ModBlocks.TNT_X20.get(), getTopSideBottomsModel("tnt_x20"));
        simpleBlock(ModBlocks.TNT_X50.get(), getTopSideBottomsModel("tnt_x50"));
        simpleBlock(ModBlocks.TNT_X100.get(), getTopSideBottomsModel("tnt_x100"));
        simpleBlock(ModBlocks.TNT_X500.get(), getTopSideBottomsModel("tnt_x500"));

        simpleBlock(ModBlocks.TNT_X1K.get(), getTopSideBottomsModel("tnt_x1k"));
        simpleBlock(ModBlocks.DRAIN_TNT.get(), getTopSideBottomsModel("drain_tnt"));
        simpleBlock(ModBlocks.DRAIN_TNT_X5.get(), getTopSideBottomsModel("drain_tnt_x5"));

        simpleBlock(ModBlocks.JUMPING_TNT.get(), getTopSideBottomsModel("jumping_tnt"));
        simpleBlock(ModBlocks.ONE_JUMP_TNT.get(), getTopSideBottomsModel("one_jump_tnt"));
        simpleBlock(ModBlocks.RUSSIAN_ROULETTE_TNT.get(), getTopSideBottomsModel("russian_roulette_tnt"));
        simpleBlock(ModBlocks.DUPSTEP_TNT.get(), getTopSideBottomsModel("dupstep_tnt"));
        simpleBlock(ModBlocks.RAIN_TNT.get(), getTopSideBottomsModel("rain_tnt"));
        simpleBlock(ModBlocks.ORE_MINER_TNT.get(), getTopSideBottomsModel("ore_miner_tnt"));
        simpleBlock(ModBlocks.FLAT_TNT.get(), getTopSideBottomsModel("flat_tnt"));
        simpleBlock(ModBlocks.CIRCLE_TNT.get(), getTopSideBottomsModel("circle_tnt"));
        simpleBlock(ModBlocks.CUBE_TNT.get(), getTopSideBottomsModel("cube_tnt"));
        simpleBlock(ModBlocks.FUSE_TNT.get(), getTopSideBottomsModel("fuse_tnt"));
        simpleBlock(ModBlocks.BLOCK_CHANGE_TNT.get(), getTopSideBottomsModel("block_change_tnt"));
        simpleBlock(ModBlocks.FARMING_TNT.get(), getTopSideBottomsModel("farming_tnt"));
        simpleBlock(ModBlocks.ISLAND_TNT.get(), getTopSideBottomsModel("island_tnt"));

        //simpleBlock(ModBlocks.METAL_PRESS.get(),models().cube("metal_press",modId("block/metal_press_bottom"),modId("block/metal_press_top"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side")));

    }

    private BlockModelBuilder getTopSideBottomsModel(String folderName) {
        //Folder Struckture: textures/block/tnt/folderName/top.pnng ... side.png ... bottom.png
        return models().cube(folderName, modId("block/tnt/" + folderName + "/bottom"),
                modId("block/tnt/" + folderName + "/top"), modId("block/tnt/" + folderName + "/side"),
                modId("block/tnt/" + folderName + "/side"), modId("block/tnt/" + folderName + "/side"),
                modId("block/tnt/" + folderName + "/side"));
    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(JustEnoughTNT.MOD_ID, path);
    }
}
