package de.wroracer.justenoughtnt.setup;

import java.util.function.Supplier;

import de.wroracer.justenoughtnt.block.ExampleTNT;
import de.wroracer.justenoughtnt.block.TNTX10;
import de.wroracer.justenoughtnt.block.TNTX100;
import de.wroracer.justenoughtnt.block.TNTX20;
import de.wroracer.justenoughtnt.block.TNTX5;
import de.wroracer.justenoughtnt.block.TNTX50;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

        public static final RegistryObject<Block> SULFUR_ORE = register("sulfur_ore",
                        () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                                        .sound(SoundType.STONE)
                                        .strength(3.0f, 3.0f)
                                        .requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE = register("deepslate_sulfur_ore",
                        () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                                        .sound(SoundType.STONE)
                                        .strength(5.0f, 5.0f)
                                        .requiresCorrectToolForDrops()));

        /*  TNT  */

        //example TNT
        public static final RegistryObject<Block> EXAMPLE_TNT = register("example_tnt",
                        () -> new ExampleTNT(
                                        BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                                        .sound(SoundType.GRASS)
                                                        .strength(1.0f, 1.0f)));

        //TNTX5 (5 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X5 = register("tnt_x5",
                        () -> new TNTX5(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        //TNTX10 (10 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X10 = register("tnt_x10",
                        () -> new TNTX10(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX20 (20 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X20 = register("tnt_x20",
                        () -> new TNTX20(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX50 (50 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X50 = register("tnt_x50",
                        () -> new TNTX50(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX100 (100 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X100 = register("tnt_x100",
                        () -> new TNTX100(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
                return Registration.BLOCKS.register(name, block);
        }

        private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
                RegistryObject<T> ret = registerNoItem(name, block);
                Registration.ITEMS.register(name,
                                () -> new BlockItem(ret.get(), new Item.Properties().tab(ModTabBlock.MAIN_TAB)));
                return ret;
        }

        public static void register() {
        }
}
