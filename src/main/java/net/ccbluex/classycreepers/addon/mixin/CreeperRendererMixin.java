package net.ccbluex.classycreepers.addon.mixin;

import net.ccbluex.classycreepers.addon.feature.CreeperClassinessFeatureRenderer;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperRenderer.class)
public abstract class CreeperRendererMixin extends MobRenderer<Creeper, CreeperRenderState, CreeperModel> {
    protected CreeperRendererMixin(EntityRendererProvider.Context context, CreeperModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void classycreepers$addAccessories(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new CreeperClassinessFeatureRenderer(this));
    }
}
