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

        public static final Tag.Named<Block> ORES_SULFUR = forge("ores/sulfur");

        private static  Tag.Named<Block> forge(String path){
            return BlockTags.bind(new ResourceLocation("forge",path).toString());
        }
        private static Tag.Named<Block> mod(String path){
            return BlockTags.bind(new ResourceLocation(JustEnoughTNT.MOD_ID,path).toString());
        }

    }
    public static final class Items{

        public static final Tag.Named<Item> ORES_SULFUR = forge("ores/sulfur");

        private static Tag.Named<Item> forge(String path){
            return ItemTags.bind(new ResourceLocation("forge",path).toString());
        }
        private static Tag.Named<Item> mod(String path){
            return ItemTags.bind(new ResourceLocation(JustEnoughTNT.MOD_ID,path).toString());
        }
    }
}
