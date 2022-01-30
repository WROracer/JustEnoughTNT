package de.wroracer.justenoughtnt.data;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundProvider extends SoundDefinitionsProvider {
    protected ModSoundProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, JustEnoughTNT.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {

        //this.add(modId("pressing_working"),SoundDefinition.definition().subtitle("me_system.subtitle.pressing").with(SoundDefinition.Sound.sound(modId("pressing_sound"), SoundDefinition.SoundType.SOUND)));

    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(JustEnoughTNT.MOD_ID, path);
    }
}
