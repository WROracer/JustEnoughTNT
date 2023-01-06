package de.wroracer.justenoughtnt.setup;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.block.DupstepTNT;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

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
                            .strength(1.0f, 1.0f),
                    ModEntities.EXAMPLE_TNT));

    //TNTX5 (5 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X5 = register("tnt_x5",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 80 * 2, ModEntities.TNT_X5));

    //TNTX10 (10 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X10 = register("tnt_x10",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 6 * 20, ModEntities.TNT_X10));

    // TNTX20 (20 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X20 = register("tnt_x20",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 10 * 20, ModEntities.TNT_X20));

    // TNTX50 (50 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X50 = register("tnt_x50",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 3 * 20, ModEntities.TNT_X50));

    // TNTX100 (100 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X100 = register("tnt_x100",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 20 * 20, ModEntities.TNT_X100));

    // tntX500 (500 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X500 = register("tnt_x500",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 20 * 20, ModEntities.TNT_X500));

    // TNTX1K (1000 times stronger than normal TNT)
    public static final RegistryObject<Block> TNT_X1K = register("tnt_x1k",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 20 * 20, ModEntities.TNT_X1K));

    public static final RegistryObject<Block> DRAIN_TNT = register("drain_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 8 * 20, ModEntities.DRAIN_TNT));

    //*/
    // drain tnt x5 (5 times stronger than normal drain tnt)
    public static final RegistryObject<Block> DRAIN_TNT_X5 = register("drain_tnt_x5",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 8 * 20, ModEntities.DRAIN_TNT_5));

    // jumping tnt. it jumps
    public static final RegistryObject<Block> JUMPING_TNT = register("jumping_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 3 * 20, ModEntities.JUMPING_TNT));

    public static final RegistryObject<Block> CIRCLE_TNT = register("circle_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.GRASS), 3 * 20, ModEntities.CIRCLE_TNT));

    public static final RegistryObject<Block> CUBE_TNT = register("cube_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.CUBE_TNT));

    public static final RegistryObject<DupstepTNT> DUPSTEP_TNT = register("dupstep_tnt",
            () -> new DupstepTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20));

    public static final RegistryObject<Block> FLAT_TNT = register("flat_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.FLAT_TNT));

    public static final RegistryObject<Block> FUSE_TNT = register("fuse_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.FUSE_TNT));

    public static final RegistryObject<Block> ONE_JUMP_TNT = register("one_jump_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.ONE_JUMP_TNT));
    //ore miner tnt block
    public static final RegistryObject<Block> ORE_MINER_TNT = register("ore_miner_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.ORE_MINER_TNT));
    //Rain TNT<
    public static final RegistryObject<Block> RAIN_TNT = register("rain_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.RAIN_TNT));
    //Russian Roulette TNT
    public static final RegistryObject<Block> RUSSIAN_ROULETTE_TNT = register("russian_roulette_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 0, ModEntities.RUSSIAN_ROULETTE_TNT));
    //BlockChange TNT
    public static final RegistryObject<Block> BLOCK_CHANGE_TNT = register("block_change_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.BLOCK_CHANGE_TNT));

    //Farming TNT
    public static final RegistryObject<Block> FARMING_TNT = register("farming_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.FARMING_TNT));

    // island tnt
    public static final RegistryObject<Block> ISLAND_TNT = register("island_tnt",
            () -> new BaseTNTBlock<>(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .sound(SoundType.GRASS)
                    .strength(1.0f, 1.0f), 3 * 20, ModEntities.ISLAND_TNT));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name,
                () -> new BlockItem(ret.get(), new Item.Properties().tab(ModTabBlock.MAIN_TAB)));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    public static void register() {
    }
}
