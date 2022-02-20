package de.wroracer.justenoughtnt.setup;

import java.util.function.Supplier;

import de.wroracer.justenoughtnt.block.*;
import de.wroracer.justenoughtnt.entity.TNTX1KEntity;
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
                        () -> new BaseTNTBlock<>(
                                        BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                                        .sound(SoundType.GRASS)
                                                .strength(1.0f, 1.0f),ModEntities.EXAMPLE_TNT));

        //TNTX5 (5 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X5 = register("tnt_x5",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),80*2,ModEntities.TNT_X5));

        //TNTX10 (10 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X10 = register("tnt_x10",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),6*20,ModEntities.TNT_X10));

        // TNTX20 (20 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X20 = register("tnt_x20",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),10*20,ModEntities.TNT_X20));

        // TNTX50 (50 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X50 = register("tnt_x50",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),13*20,ModEntities.TNT_X50));

        // TNTX100 (100 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X100 = register("tnt_x100",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),20*20,ModEntities.TNT_X100));

        // tntX500 (500 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X500 = register("tnt_x500",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),20*20,ModEntities.TNT_X500));

        // TNTX1K (1000 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X1K = register("tnt_x1k",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),20 *20,ModEntities.TNT_X1K));


        public static final RegistryObject<Block> DRAIN_TNT = register("drain_tnt",
                () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                        .strength(1.0f, 1.0f)
                        .sound(SoundType.GRASS), 8 * 20, ModEntities.DRAIN_TNT));

        //*/
        // drain tnt x5 (5 times stronger than normal drain tnt)
        public static final RegistryObject<Block> DRAIN_TNT_X5 = register("drain_tnt_x5",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),8 * 20, ModEntities.DRAIN_TNT_5));

        // jumping tnt. it jumps
        public static final RegistryObject<Block> JUMPING_TNT = register("jumping_tnt",
                        () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS),3*20,ModEntities.JUMPING_TNT));

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
