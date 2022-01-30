package de.wroracer.justenoughtnt.setup;


import de.wroracer.justenoughtnt.block.ExampleTNT;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore",()->
            new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0f,3.0f)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block",()->
            new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(3.0f,3.0f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> EXAMPLE_TNT = register("example_tnt",()->
            new ExampleTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.SAND)
                    .strength(1.0f,1.0f)
            ));


    private static <T extends Block>RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name,block);
    }

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name,()->new BlockItem(ret.get(),new Item.Properties().tab(ModTabBlock.MAIN_TAB)));
        return ret;
    }

    public static void register(){}
}
