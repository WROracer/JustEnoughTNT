package de.wroracer.justenoughtnt.data;


import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.data.client.ModBlockStateProvider;
import de.wroracer.justenoughtnt.data.client.ModItemModelProvider;
import de.wroracer.justenoughtnt.data.loot.ModLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = JustEnoughTNT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(blockTags);
        gen.addProvider(new ModItemTagsProvider(gen, blockTags, existingFileHelper));

        gen.addProvider(new ModLootTables(gen));
        gen.addProvider(new ModRecipeProvider(gen));

        gen.addProvider(new ModSoundProvider(gen, existingFileHelper));
    }
}
