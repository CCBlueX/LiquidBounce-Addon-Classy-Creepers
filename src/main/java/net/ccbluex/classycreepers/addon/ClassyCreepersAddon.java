package net.ccbluex.classycreepers.addon;

import net.ccbluex.classycreepers.addon.feature.AccessoryTexture;
import net.ccbluex.liquidbounce.features.addon.LiquidBounceAddon;

public final class ClassyCreepersAddon extends LiquidBounceAddon {
    private static ClassyCreepersAddon instance;

    @Override
    public void onInitialize() {
        registerModules(ModuleClassyCreepers.INSTANCE);
        instance = this;
    }

    @Override
    public void onStarted() {
        // Fabric Loader alone does not expose mod assets as a Minecraft resource pack.
        getMc().getTextureManager().registerAndLoad(AccessoryTexture.ID, new AccessoryTexture());
    }

    public static boolean shouldRender() {
        // Mixins remain installed even when LiquidBounce disables or rolls back an add-on.
        return instance != null && instance.getRunning() && ModuleClassyCreepers.INSTANCE.getEnabled();
    }
}
