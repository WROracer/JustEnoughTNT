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
import org.checkerframework.checker.units.qual.A;

import java.util.function.BiFunction;

public final class ModEntities {

    public static final RegistryObject<EntityType<ExampleTNTEntity>> EXAMPLE_TNT = register("primed_example_tnt",
            ExampleTNTEntity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new ExampleTNTEntity(level,spawnEntity.getPosX(),spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<DrainTNTEntity>> DRAIN_TNT = register("primed_drain_tnt",
            DrainTNTEntity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new DrainTNTEntity(level,spawnEntity.getPosX(),spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<DrainTNTX5Entity>> DRAIN_TNT_5 = register("primed_drain_tnt_5",
            DrainTNTX5Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new DrainTNTX5Entity(level,spawnEntity.getPosX(),spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<JumpingTNTEntity>> JUMPING_TNT = register("primed_jumping_tnt",
            JumpingTNTEntity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new JumpingTNTEntity(level,spawnEntity.getPosX(),spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<TNTX1KEntity>> TNT_X1K = register("primed_1k_tnt",
            TNTX1KEntity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX1KEntity(level,spawnEntity.getPosX(),spawnEntity.getPosY(), spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<TNTX5Entity>> TNT_X5 = register("primed_5_tnt",
            TNTX5Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX5Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

    public static final RegistryObject<EntityType<TNTX10Entity>> TNT_X10 = register("primed_10_tnt",
            TNTX10Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX10Entity(level,spawnEntity.getPosX(), spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<TNTX20Entity>> TNT_X20 = register("primed_20_tnt",
            TNTX20Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX20Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ(), null));

    public static final RegistryObject<EntityType<TNTX50Entity>> TNT_X50 = register("primed_50_tnt",
            TNTX50Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX50Entity(level,spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<TNTX100Entity>> TNT_X100 = register("primed_100_tnt",
            TNTX100Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX100Entity(level, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ(),null));

    public static final RegistryObject<EntityType<TNTX500Entity>> TNT_X500 = register("primed_500_tnt",
            TNTX500Entity::new,
            MobCategory.AMBIENT,
            (spawnEntity, level) -> new TNTX500Entity(level,spawnEntity.getPosX(),spawnEntity.getPosY(),spawnEntity.getPosZ(),null));

    private ModEntities() {throw new IllegalAccessError("Utility class");}

    static void register() {}

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.EntityFactory<T> factory, MobCategory type, BiFunction<PlayMessages.SpawnEntity, Level, T> customClientFactory) {
        return Registration.ENTITIES.register(name, () -> EntityType.Builder.of(factory, type)
                .setCustomClientFactory(customClientFactory)
                .build(JustEnoughTNT.getId(name).toString()));
    }

    @Mod.EventBusSubscriber(modid = JustEnoughTNT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Events {
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EXAMPLE_TNT.get(), createEntityRenderer(ModBlocks.EXAMPLE_TNT.get()));

            event.registerEntityRenderer(DRAIN_TNT.get(),createEntityRenderer(ModBlocks.DRAIN_TNT.get()));
            event.registerEntityRenderer(DRAIN_TNT_5.get(),createEntityRenderer(ModBlocks.DRAIN_TNT_X5.get()));
            event.registerEntityRenderer(JUMPING_TNT.get(),createEntityRenderer(ModBlocks.JUMPING_TNT.get()));
            event.registerEntityRenderer(TNT_X1K.get(),createEntityRenderer(ModBlocks.TNT_X1K.get()));
            event.registerEntityRenderer(TNT_X5.get(),createEntityRenderer(ModBlocks.TNT_X5.get()));
            event.registerEntityRenderer(TNT_X10.get(),createEntityRenderer(ModBlocks.TNT_X10.get()));
            event.registerEntityRenderer(TNT_X20.get(),createEntityRenderer(ModBlocks.TNT_X20.get()));
            event.registerEntityRenderer(TNT_X50.get(),createEntityRenderer(ModBlocks.TNT_X50.get()));
            event.registerEntityRenderer(TNT_X100.get(),createEntityRenderer(ModBlocks.TNT_X100.get()));
            event.registerEntityRenderer(TNT_X500.get(),createEntityRenderer(ModBlocks.TNT_X500.get()));
        }

        private static EntityRendererProvider<BaseTNT> createEntityRenderer(Block base){
            if (base instanceof BaseTNTBlock){
                return new EntityRendererProvider<BaseTNT>() {
                    @Override
                    public EntityRenderer<BaseTNT> create(Context context) {
                        return new BaseTNTRenderer(context, (BaseTNTBlock) base);
                    }
                };
            }
            return createEntityRenderer(ModBlocks.EXAMPLE_TNT.get());

        }
    }
}
