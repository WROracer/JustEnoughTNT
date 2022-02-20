package de.wroracer.justenoughtnt.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BaseTNTRenderer<T extends BaseTNTBlock> extends EntityRenderer<BaseTNT> {

    private T block;

    public BaseTNTRenderer(EntityRendererProvider.Context context, T bock) {
        super(context);
        this.block = bock;
    }

    public void render(BaseTNT baseTNT, float p_116178_, float p_116179_, PoseStack p_116180_, MultiBufferSource p_116181_, int p_116182_) {
        p_116180_.pushPose();
        p_116180_.translate(0.0D, 0.5D, 0.0D);
        int i = baseTNT.getFuse();
        if ((float)i - p_116179_ + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - p_116179_ + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            p_116180_.scale(f1, f1, f1);
        }

        p_116180_.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        p_116180_.translate(-0.5D, -0.5D, 0.5D);
        p_116180_.mulPose(Vector3f.YP.rotationDegrees(90.0F));


        TntMinecartRenderer.renderWhiteSolidBlock(block.defaultBlockState(), p_116180_, p_116181_, p_116182_, i / 5 % 2 == 0);

        p_116180_.popPose();
        super.render(baseTNT, p_116178_, p_116179_, p_116180_, p_116181_, p_116182_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BaseTNT baseTNT) {
        return Objects.requireNonNull(block.getRegistryName());
    }

}
