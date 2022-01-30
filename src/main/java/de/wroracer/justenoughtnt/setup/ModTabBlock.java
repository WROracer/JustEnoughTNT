package de.wroracer.justenoughtnt.setup;


import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModTabBlock extends CreativeModeTab {

    public static final ModTabBlock MAIN_TAB = new ModTabBlock(JustEnoughTNT.MOD_ID+".main", ModBlocks.EXAMPLE_TNT);


    private final RegistryObject<Block> block;
    public ModTabBlock(String label, RegistryObject<Block> block) {
        super(label);
        this.block = block;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(block.get().asItem());
    }

    public static void register(){

    }
}
