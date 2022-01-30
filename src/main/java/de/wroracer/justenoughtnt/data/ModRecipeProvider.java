package de.wroracer.justenoughtnt.data;

import com.google.common.base.Preconditions;
import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.EXAMPLE_TNT.get(),1)
                .unlockedBy("has_item",has(ModItems.SULFUR_DUST.get()))
                .define('s',ModItems.SULFUR_DUST.get())
                .define('b', Tags.Items.GUNPOWDER)
                .pattern("sbs")
                .pattern("bsb")
                .pattern("sbs")
                .save(consumer);
        /*
           ShapelessRecipeBuilder.shapeless(ModItems.SILVER_INGOT.get(),9)
                .requires(ModBlocks.SILVER_BLOCK.get())
                .unlockedBy("has_item",has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK.get())
                .define('#', ModItems.SILVER_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item",has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.METAL_PRESS.get())
                .pattern("rpr")
                .pattern("i i")
                .pattern("sas")
                .define('r', Items.REDSTONE)
                .define('p', Blocks.PISTON)
                .define('i',ModItems.SILVER_INGOT.get())
                .define('s', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('a',Blocks.ANVIL)
                .unlockedBy("has_item",has(ModItems.SILVER_INGOT.get()))
                .save(consumer);
         */
    }


    private ResourceLocation modId(String path) {
        return new ResourceLocation(JustEnoughTNT.MOD_ID,path);
    }



    public static ResourceLocation checkNotNull(@Nullable ResourceLocation name) {
        Preconditions.checkNotNull(name, "Name is null, make sure the object has been registered correctly");
        return name;
    }

    public static ResourceLocation from(IForgeRegistryEntry<?> entry) {
        return checkNotNull(entry.getRegistryName());
    }
}
