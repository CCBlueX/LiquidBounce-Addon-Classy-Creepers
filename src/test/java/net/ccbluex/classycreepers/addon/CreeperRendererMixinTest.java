package net.ccbluex.classycreepers.addon;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

final class CreeperRendererMixinTest {
    @Test
    void fabricAppliesAccessoryInjectionToVanillaCreeperRenderer() {
        // Fabric Loader's JUnit launcher runs the real Mixin transformer, without a game window.
        // Loading the target fails this test if the superclass, constructor or injection is invalid.
        assertTrue(Arrays.stream(CreeperRenderer.class.getDeclaredMethods())
                .anyMatch(method -> method.getName().contains("classycreepers$addAccessories")),
                "The accessory constructor handler must be merged into the vanilla renderer");
    }
}
