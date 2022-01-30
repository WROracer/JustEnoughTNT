package de.wroracer.justenoughtnt.setup;


import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final class Blocks{
        public static final Tag.Named<Block> ORES_SILVER = forge("ores/silver");
        public static final Tag.Named<Block> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");



        private static  Tag.Named<Block> forge(String path){
            return BlockTags.bind(new ResourceLocation("forge",path).toString());
        }
        private static Tag.Named<Block> mod(String path){
            return BlockTags.bind(new ResourceLocation(JustEnoughTNT.MOD_ID,path).toString());
        }

    }
    public static final class Items{

        public static final Tag.Named<Item> ORES_SILVER = forge("ores/silver");
        public static final Tag.Named<Item> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");

        public static final Tag.Named<Item> INGOTS_SILVER = forge("ingots/silver");

        public static final Tag.Named<Item> PRESSING_FORM = mod("pressing/form");

        public static final Tag.Named<Item> MUSIC_DISK = mod("musik/disk");

        private static Tag.Named<Item> forge(String path){
            return ItemTags.bind(new ResourceLocation("forge",path).toString());
        }
        private static Tag.Named<Item> mod(String path){
            return ItemTags.bind(new ResourceLocation(JustEnoughTNT.MOD_ID,path).toString());
        }
    }
}
