package de.wroracer.justenoughtnt.data;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(DataGenerator dataGenerator) {
                super(dataGenerator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

                ShapedRecipeBuilder.shaped(ModBlocks.EXAMPLE_TNT.get(), 1)
                                .unlockedBy("has_item", has(ModItems.SULFUR_DUST.get()))
                                .define('s', ModItems.SULFUR_DUST.get())
                                .define('b', Tags.Items.GUNPOWDER)
                                .pattern("sbs")
                                .pattern("bsb")
                                .pattern("sbs")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.TNT_X5.get(), 1)
                                .unlockedBy("has_item", has(Items.TNT))
                                .define('s', ModItems.SULFUR_DUST.get())
                                .define('t', Items.TNT)
                                .pattern("tst")
                                .pattern("sts")
                                .pattern("tst")
                                .save(consumer);

                ShapelessRecipeBuilder.shapeless(ModBlocks.TNT_X10.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X5.get()))
                                .requires(ModBlocks.TNT_X5.get())
                                .requires(ModBlocks.TNT_X5.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .save(consumer);

                ShapelessRecipeBuilder.shapeless(ModBlocks.TNT_X20.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X10.get()))
                                .requires(ModBlocks.TNT_X10.get())
                                .requires(ModBlocks.TNT_X10.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .save(consumer);

                ShapelessRecipeBuilder.shapeless(ModBlocks.TNT_X50.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X20.get()))
                                .requires(ModBlocks.TNT_X20.get())
                                .requires(ModBlocks.TNT_X20.get())
                                .requires(ModBlocks.TNT_X10.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .save(consumer);

                ShapelessRecipeBuilder.shapeless(ModBlocks.TNT_X100.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X50.get()))
                                .requires(ModBlocks.TNT_X50.get())
                                .requires(ModBlocks.TNT_X50.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.TNT_X500.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X100.get()))
                                .define('s', ModItems.SULFUR_DUST.get())
                                .define('t', ModBlocks.TNT_X100.get())
                                .pattern("tst")
                                .pattern("sts")
                                .pattern("tst")
                                .save(consumer);

                ShapelessRecipeBuilder.shapeless(ModBlocks.TNT_X1K.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X500.get()))
                                .requires(ModBlocks.TNT_X500.get())
                                .requires(ModBlocks.TNT_X500.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .requires(ModItems.SULFUR_DUST.get())
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.DRAIN_TNT.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X100.get()))
                                .define('s', Items.SPONGE)
                                .define('t', ModBlocks.TNT_X100.get())
                                .pattern("sss")
                                .pattern("sts")
                                .pattern("sss")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.DRAIN_TNT_X5.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.DRAIN_TNT.get()))
                                .define('s', Items.SPONGE)
                                .define('t', ModBlocks.DRAIN_TNT.get())
                                .pattern("tst")
                                .pattern("sts")
                                .pattern("tst")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.JUMPING_TNT.get(), 1)
                                .unlockedBy("has_item", has(Items.SLIME_BLOCK))
                                .define('s', Items.SLIME_BLOCK)
                                .define('t', Items.TNT)
                                .pattern("tst")
                                .pattern("sts")
                                .pattern("tst")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.ONE_JUMP_TNT.get(), 1)
                                .unlockedBy("has_item", has(Items.SLIME_BLOCK))
                                .define('s', Items.SLIME_BLOCK)
                                .define('t', Items.TNT)
                                .pattern(" s ")
                                .pattern("sts")
                                .pattern(" s ")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.RUSSIAN_ROULETTE_TNT.get(), 1)
                                .unlockedBy("has_item", has(Items.TNT))
                                .define('a', Items.ARROW)
                                .define('t', Items.TNT)
                                .pattern("a a")
                                .pattern(" t ")
                                .pattern("a a")
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModBlocks.DUPSTEP_TNT.get(), 1)
                                .unlockedBy("has_item", has(Items.TNT))
                                .define('v', Items.TNT)
                                .define('t', ModBlocks.TNT_X5.get())
                                .pattern("v v")
                                .pattern(" t ")
                                .pattern("v v")

                                .save(consumer);
                ShapedRecipeBuilder.shaped(ModBlocks.ORE_MINER_TNT.get(), 1)
                                .unlockedBy("has_item", has(ModBlocks.TNT_X20.get()))
                                .define('p', Items.DIAMOND_PICKAXE)
                                .define('t', ModBlocks.TNT_X20.get())
                                .pattern("ppp")
                                .pattern("ptp")
                                .pattern("ppp")
                                .save(consumer);

                //*/

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
                return new ResourceLocation(JustEnoughTNT.MOD_ID, path);
        }

        public static ResourceLocation checkNotNull(@Nullable ResourceLocation name) {
                Preconditions.checkNotNull(name, "Name is null, make sure the object has been registered correctly");
                return name;
        }

        public static ResourceLocation from(IForgeRegistryEntry<?> entry) {
                return checkNotNull(entry.getRegistryName());
        }
}
