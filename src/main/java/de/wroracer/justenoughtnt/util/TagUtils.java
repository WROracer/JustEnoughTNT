package de.wroracer.justenoughtnt.util;

import net.minecraft.tags.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class TagUtils {
    private TagUtils() {throw new IllegalAccessError("Utility class");}


    public static boolean containsSafe(Tag<Item> tag, ItemStack stack) {
        return containsSafe(tag, stack.getItem());
    }

    public static boolean containsSafe(Tag<Block> tag, BlockState state) {
        return containsSafe(tag, state.getBlock());
    }

    public static boolean containsSafe(Tag<EntityType<?>> tag, Entity entity) {
        return containsSafe(tag, entity.getType());
    }

    /**
     * Checks if the given tag contains the given object and also works around the "tag used before
     * bound" crash that some mod packs produce.
     * <p>
     * Also see the other {@code containsSafe} methods, which may save you some keystrokes.
     *
     * @param tag The tag
     * @param obj The object (Item, Block, EntityType, etc.)
     * @param <T> The object type
     * @return True if the tag contains the object, false if it does not or the workaround hack
     * fails to fetch unbound tags
     */
    public static <T> boolean containsSafe(Tag<T> tag, T obj) {
        return containsSafe(tag, obj, true);
    }

    private static <T> boolean containsSafe(Tag<T> tag, T obj, boolean firstAttempt) {
        try {
            return tag.contains(obj);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
        return firstAttempt && containsSafe(tag, obj, false);
    }
}