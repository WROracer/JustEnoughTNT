package de.wroracer.justenoughtnt.setup;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.client.renderer.BaseTNTRenderer;
import de.wroracer.justenoughtnt.entity.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;

public final class ModEntities {

        public static final RegistryObject<EntityType<ExampleTNTEntity>> EXAMPLE_TNT = register("primed_example_tnt",
                        ExampleTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new ExampleTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<DrainTNTEntity>> DRAIN_TNT = register("primed_drain_tnt",
                        DrainTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new DrainTNTEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<DrainTNTX5Entity>> DRAIN_TNT_5 = register("primed_drain_tnt_5",
                        DrainTNTX5Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new DrainTNTX5Entity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<JumpingTNTEntity>> JUMPING_TNT = register("primed_jumping_tnt",
                        JumpingTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new JumpingTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX1KEntity>> TNT_X1K = register("primed_1k_tnt",
                        TNTX1KEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX1KEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX5Entity>> TNT_X5 = register("primed_5_tnt",
                        TNTX5Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX5Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX10Entity>> TNT_X10 = register("primed_10_tnt",
                        TNTX10Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX10Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX20Entity>> TNT_X20 = register("primed_20_tnt",
                        TNTX20Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX20Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX50Entity>> TNT_X50 = register("primed_50_tnt",
                        TNTX50Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX50Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX100Entity>> TNT_X100 = register("primed_100_tnt",
                        TNTX100Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX100Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<TNTX500Entity>> TNT_X500 = register("primed_500_tnt",
                        TNTX500Entity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new TNTX500Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<CircleTNTEntity>> CIRCLE_TNT = register("primed_circle_tnt",
                        CircleTNTEntity::new,
                        MobCategory.AMBIENT,
                        ((spawnEntity, level) -> new CircleTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null)));

        public static final RegistryObject<EntityType<CubeTNTEntity>> CUBE_TNT = register("primed_cube_tnt",
                        CubeTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new CubeTNTEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<DupstepTNTEntity>> DUPSTEP_TNT = register("primed_dupstep_tnt",
                        DupstepTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new DupstepTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<FlatTNTEntity>> FLAT_TNT = register("primed_flat_tnt",
                        FlatTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new FlatTNTEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<FuseTNTEntity>> FUSE_TNT = register("primed_fuse_tnt",
                        FuseTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new FuseTNTEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<OneJumpTNTEntity>> ONE_JUMP_TNT = register("primed_one_jump_tnt",
                        OneJumpTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new OneJumpTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        public static final RegistryObject<EntityType<OreMinerTNTEntity>> ORE_MINER_TNT = register(
                        "primed_ore_miner_tnt",
                        OreMinerTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new OreMinerTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));
        //Rain TNT
        public static final RegistryObject<EntityType<RainTNTEntity>> RAIN_TNT = register("primed_rain_tnt",
                        RainTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new RainTNTEntity(level, spawnEntity.getPosX(), spawnEntity.getPosY(),
                                        spawnEntity.getPosZ(), null));
        //Russian Roulette TNT
        public static final RegistryObject<EntityType<RussianRouletteTNTEntity>> RUSSIAN_ROULETTE_TNT = register(
                        "primed_russian_roulette_tnt",
                        RussianRouletteTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new RussianRouletteTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));
        //BlockChange TNT
        public static final RegistryObject<EntityType<BlockChangeTNTEntity>> BLOCK_CHANGE_TNT = register(
                        "primed_block_change_tnt",
                        BlockChangeTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new BlockChangeTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        //farming TNT
        public static final RegistryObject<EntityType<FarmingTNTEntity>> FARMING_TNT = register("primed_farming_tnt",
                        FarmingTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new FarmingTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        //island TNT
        public static final RegistryObject<EntityType<IslandTNTEntity>> ISLAND_TNT = register("primed_island_tnt",
                        IslandTNTEntity::new,
                        MobCategory.AMBIENT,
                        (spawnEntity, level) -> new IslandTNTEntity(level, spawnEntity.getPosX(),
                                        spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

        private ModEntities() {
                throw new IllegalAccessError("Utility class");
        }

        static void register() {
        }

        private static <T extends Entity> RegistryObject<EntityType<T>> register(String name,
                        EntityType.EntityFactory<T> factory, MobCategory type,
                        BiFunction<PlayMessages.SpawnEntity, Level, T> customClientFactory) {
                return Registration.ENTITIES.register(name, () -> EntityType.Builder.of(factory, type)
                                .setCustomClientFactory(customClientFactory)
                                .build(JustEnoughTNT.getId(name).toString()));
        }

        @Mod.EventBusSubscriber(modid = JustEnoughTNT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class Events {
                @OnlyIn(Dist.CLIENT)
                @SubscribeEvent
                public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
                        event.registerEntityRenderer(EXAMPLE_TNT.get(),
                                        createEntityRenderer(ModBlocks.EXAMPLE_TNT.get()));

                        event.registerEntityRenderer(DRAIN_TNT.get(), createEntityRenderer(ModBlocks.DRAIN_TNT.get()));
                        event.registerEntityRenderer(DRAIN_TNT_5.get(),
                                        createEntityRenderer(ModBlocks.DRAIN_TNT_X5.get()));
                        event.registerEntityRenderer(JUMPING_TNT.get(),
                                        createEntityRenderer(ModBlocks.JUMPING_TNT.get()));
                        event.registerEntityRenderer(TNT_X1K.get(), createEntityRenderer(ModBlocks.TNT_X1K.get()));
                        event.registerEntityRenderer(TNT_X5.get(), createEntityRenderer(ModBlocks.TNT_X5.get()));
                        event.registerEntityRenderer(TNT_X10.get(), createEntityRenderer(ModBlocks.TNT_X10.get()));
                        event.registerEntityRenderer(TNT_X20.get(), createEntityRenderer(ModBlocks.TNT_X20.get()));
                        event.registerEntityRenderer(TNT_X50.get(), createEntityRenderer(ModBlocks.TNT_X50.get()));
                        event.registerEntityRenderer(TNT_X100.get(), createEntityRenderer(ModBlocks.TNT_X100.get()));
                        event.registerEntityRenderer(TNT_X500.get(), createEntityRenderer(ModBlocks.TNT_X500.get()));
                        event.registerEntityRenderer(CIRCLE_TNT.get(),
                                        createEntityRenderer(ModBlocks.CIRCLE_TNT.get()));
                        event.registerEntityRenderer(CUBE_TNT.get(), createEntityRenderer(ModBlocks.CUBE_TNT.get()));
                        event.registerEntityRenderer(FUSE_TNT.get(), createEntityRenderer(ModBlocks.FUSE_TNT.get()));
                        event.registerEntityRenderer(DUPSTEP_TNT.get(),
                                        createEntityRenderer(ModBlocks.DUPSTEP_TNT.get()));
                        event.registerEntityRenderer(FLAT_TNT.get(), createEntityRenderer(ModBlocks.FLAT_TNT.get()));
                        event.registerEntityRenderer(ONE_JUMP_TNT.get(),
                                        createEntityRenderer(ModBlocks.ONE_JUMP_TNT.get()));
                        event.registerEntityRenderer(ORE_MINER_TNT.get(),
                                        createEntityRenderer(ModBlocks.ORE_MINER_TNT.get()));
                        event.registerEntityRenderer(RAIN_TNT.get(), createEntityRenderer(ModBlocks.RAIN_TNT.get()));
                        event.registerEntityRenderer(RUSSIAN_ROULETTE_TNT.get(),
                                        createEntityRenderer(ModBlocks.RUSSIAN_ROULETTE_TNT.get()));
                        event.registerEntityRenderer(BLOCK_CHANGE_TNT.get(),
                                        createEntityRenderer(ModBlocks.BLOCK_CHANGE_TNT.get()));

                        event.registerEntityRenderer(FARMING_TNT.get(),
                                        createEntityRenderer(ModBlocks.FARMING_TNT.get()));

                        // island TNT
                        event.registerEntityRenderer(ISLAND_TNT.get(),
                                        createEntityRenderer(ModBlocks.ISLAND_TNT.get()));

                }

                private static EntityRendererProvider<TNTEntity> createEntityRenderer(Block base) {
                        if (base instanceof BaseTNTBlock) {
                                return new EntityRendererProvider<TNTEntity>() {
                                        @Override
                                        public EntityRenderer<TNTEntity> create(Context context) {
                                                return new BaseTNTRenderer(context, (BaseTNTBlock) base);
                                        }
                                };
                        }
                        return createEntityRenderer(ModBlocks.EXAMPLE_TNT.get());

                }
        }
}
