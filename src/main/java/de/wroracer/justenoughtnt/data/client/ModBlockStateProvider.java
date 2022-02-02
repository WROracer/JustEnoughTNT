package de.wroracer.justenoughtnt.data.client;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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

        simpleBlock(ModBlocks.EXAMPLE_TNT.get());
        simpleBlock(ModBlocks.TNT_X5.get());
        simpleBlock(ModBlocks.TNT_X10.get());
        simpleBlock(ModBlocks.TNT_X20.get());
        simpleBlock(ModBlocks.TNT_X50.get());
        simpleBlock(ModBlocks.TNT_X100.get());
        simpleBlock(ModBlocks.TNT_X500.get());

        simpleBlock(ModBlocks.TNT_X1K.get());
        simpleBlock(ModBlocks.DRAIN_TNT.get());
        simpleBlock(ModBlocks.DRAIN_TNT_X5.get());

        simpleBlock(ModBlocks.JUMPING_TNT.get());
        simpleBlock(ModBlocks.ONE_JUMP_TNT.get());
        simpleBlock(ModBlocks.RUSSIAN_ROULETTE_TNT.get());
        simpleBlock(ModBlocks.DUPSTEP_TNT.get());
        simpleBlock(ModBlocks.RAIN_TNT.get());

        //simpleBlock(ModBlocks.METAL_PRESS.get(),models().cube("metal_press",modId("block/metal_press_bottom"),modId("block/metal_press_top"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side")));

    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(JustEnoughTNT.MOD_ID, path);
    }
}
