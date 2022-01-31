package de.wroracer.justenoughtnt.data;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, JustEnoughTNT.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SULFUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULFUR_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SULFUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULFUR_ORE.get());

        tag(ModTags.Blocks.ORES_SULFUR)
                .add(ModBlocks.SULFUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULFUR_ORE.get());

    }
}
