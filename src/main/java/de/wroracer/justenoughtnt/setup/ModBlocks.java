package de.wroracer.justenoughtnt.setup;

import java.util.function.Supplier;

import de.wroracer.justenoughtnt.block.CircleTNT;
import de.wroracer.justenoughtnt.block.CubeTNT;
import de.wroracer.justenoughtnt.block.DrainTNT;
import de.wroracer.justenoughtnt.block.DrainTNTX5;
import de.wroracer.justenoughtnt.block.DupstepTNT;
import de.wroracer.justenoughtnt.block.ExampleTNT;
import de.wroracer.justenoughtnt.block.FlatTNT;
import de.wroracer.justenoughtnt.block.FuseTNT;
import de.wroracer.justenoughtnt.block.JumpingTNT;
import de.wroracer.justenoughtnt.block.OneJumpTNT;
import de.wroracer.justenoughtnt.block.OreMinerTNT;
import de.wroracer.justenoughtnt.block.RainTNT;
import de.wroracer.justenoughtnt.block.RussianRouletteTNT;
import de.wroracer.justenoughtnt.block.TNTX10;
import de.wroracer.justenoughtnt.block.TNTX100;
import de.wroracer.justenoughtnt.block.TNTX1K;
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
                        () -> new ExampleTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        //TNTX5 (5 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X5 = register("tnt_x5",
                        () -> new TNTX5(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        //TNTX10 (10 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X10 = register("tnt_x10",
                        () -> new TNTX10(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX20 (20 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X20 = register("tnt_x20",
                        () -> new TNTX20(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX50 (50 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X50 = register("tnt_x50",
                        () -> new TNTX50(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX100 (100 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X100 = register("tnt_x100",
                        () -> new TNTX100(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // tntX500 (500 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X500 = register("tnt_x500",
                        () -> new TNTX100(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // TNTX1K (1000 times stronger than normal TNT)
        public static final RegistryObject<Block> TNT_X1K = register("tnt_x1k",
                        () -> new TNTX1K(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // drain tnt. it drains fluids
        // /*
        public static final RegistryObject<Block> DRAIN_TNT = register("drain_tnt",
                        () -> new DrainTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        //*/
        // drain tnt x5 (5 times stronger than normal drain tnt)
        public static final RegistryObject<Block> DRAIN_TNT_X5 = register("drain_tnt_x5",
                        () -> new DrainTNTX5(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // jumping tnt. it jumps
        public static final RegistryObject<Block> JUMPING_TNT = register("jumping_tnt",
                        () -> new JumpingTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // One jump tnt
        public static final RegistryObject<Block> ONE_JUMP_TNT = register("one_jump_tnt",
                        () -> new OneJumpTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // russian roulette tnt
        public static final RegistryObject<Block> RUSSIAN_ROULETTE_TNT = register("russian_roulette_tnt",
                        () -> new RussianRouletteTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // dupstep tnt
        public static final RegistryObject<Block> DUPSTEP_TNT = register("dupstep_tnt",
                        () -> new DupstepTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));
        // rain tnt
        public static final RegistryObject<Block> RAIN_TNT = register("rain_tnt",
                        () -> new RainTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // ore miner tnt (tnt that mines ores)
        public static final RegistryObject<Block> ORE_MINER_TNT = register("ore_miner_tnt",
                        () -> new OreMinerTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.STONE)));

        // flat tnt
        public static final RegistryObject<Block> FLAT_TNT = register("flat_tnt",
                        () -> new FlatTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // circle tnt
        public static final RegistryObject<Block> CIRCLE_TNT = register("circle_tnt",
                        () -> new CircleTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        //cube tnt
        public static final RegistryObject<Block> CUBE_TNT = register("cube_tnt",
                        () -> new CubeTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
                                        .sound(SoundType.GRASS)));

        // fuse tnt
        public static final RegistryObject<Block> FUSE_TNT = register("fuse_tnt",
                        () -> new FuseTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                                        // .strength(1.0f, 1.0f)
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
