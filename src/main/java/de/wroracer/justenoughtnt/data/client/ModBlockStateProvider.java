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
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock(ModBlocks.SILVER_ORE.get());

        //simpleBlock(ModBlocks.METAL_PRESS.get(),models().cube("metal_press",modId("block/metal_press_bottom"),modId("block/metal_press_top"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side"),modId("block/metal_press_side")));

    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(JustEnoughTNT.MOD_ID,path);
    }
}
