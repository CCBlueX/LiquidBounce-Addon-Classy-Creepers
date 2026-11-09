package net.ccbluex.classycreepers.addon.feature;

import net.minecraft.client.renderer.texture.TextureContents;
import net.minecraft.server.packs.resources.ResourceManager;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class AccessoryTextureTest {
    @Test
    void loadsOriginalColorsWhenNoModResourcePackIsAvailable() throws Exception {
        // Reproduces running without Fabric API's resource loader: vanilla cannot find the PNG.
        var resources = ResourceManager.Empty.INSTANCE;
        assertThrows(FileNotFoundException.class, () -> TextureContents.load(resources, AccessoryTexture.ID));

        try (var contents = AccessoryTexture.readContents(resources)) {
            var image = contents.image();
            assertEquals(64, image.getWidth());
            assertEquals(32, image.getHeight());
            assertEquals(0xFFCC9900, image.getPixel(0, 0), "Gold frame and chain");
            assertEquals(0xFF000000, image.getPixel(16, 0), "Black hat");
            assertEquals(0xFF96D3FF, image.getPixel(0, 20), "Blue monocle glass");
            assertEquals(0xFF5C2929, image.getPixel(0, 22), "Red hat band");
        }
    }
}
