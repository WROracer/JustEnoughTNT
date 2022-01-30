package de.wroracer.justenoughtnt.data;


import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModItems;
import de.wroracer.justenoughtnt.setup.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, JustEnoughTNT.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.ORES_SILVER,ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.ORES,Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER,ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS,Tags.Items.STORAGE_BLOCKS);

        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_WARD);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_MELLOHI);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_MALL);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_CHIRP);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_BLOCKS);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_CAT);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_13);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_FAR);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_STAL);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_WAIT);
        tag(ModTags.Items.MUSIC_DISK).add(Items.MUSIC_DISC_PIGSTEP);

        tag(ModTags.Items.PRESSING_FORM).addTag(ModTags.Items.MUSIC_DISK);



        tag(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_SILVER);
    }
}
