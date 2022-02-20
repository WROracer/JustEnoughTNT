package de.wroracer.justenoughtnt.setup;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = create(ForgeRegistries.BLOCK_ENTITIES);
    public static final DeferredRegister<EntityType<?>> ENTITIES = create(ForgeRegistries.ENTITIES);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        //CONTAINERS.register(modEventBus);
        //RECIPE_SERIALIZERS.register(modEventBus);
        //TILE_ENTITYS.register(modEventBus);

        ModTabBlock.register();
        ModEntities.register();
        ModBlocks.register();
        ModItems.register();
        //ModRecepies.register();
        //ModTileEntityTypes.register();
        //ModContainerTypes.register();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModOres::addOres);
    }

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, JustEnoughTNT.MOD_ID);
    }

    /*@Mod.EventBusSubscriber(modid = JustEnoughTNT.MOD_ID,value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Client{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            ModContainerTypes.registerScreens(event);
        }
    }*/
}
