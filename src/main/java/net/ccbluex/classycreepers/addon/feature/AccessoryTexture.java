package net.ccbluex.classycreepers.addon.feature;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.ReloadableTexture;
import net.minecraft.client.renderer.texture.TextureContents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/** Loads the bundled texture without Fabric API, including during F3+T reloads. */
public final class AccessoryTexture extends ReloadableTexture {
    public static final Identifier ID = Identifier.fromNamespaceAndPath("classycreepers", "tophat.png");
    private static final String BUNDLED_PATH = "/assets/classycreepers/tophat.png";

    public AccessoryTexture() {
        super(ID);
    }

    @Override
    public TextureContents loadContents(ResourceManager resourceManager) throws IOException {
        return readContents(resourceManager);
    }

    static TextureContents readContents(ResourceManager resourceManager) throws IOException {
        // Honor resource packs when they supply a replacement; otherwise read our own jar.
        if (resourceManager.getResource(ID).isPresent()) {
            return TextureContents.load(resourceManager, ID);
        }

        try (var stream = AccessoryTexture.class.getResourceAsStream(BUNDLED_PATH)) {
            if (stream == null) {
                throw new FileNotFoundException("Missing bundled Classy Creepers texture: " + BUNDLED_PATH);
            }
            return new TextureContents(NativeImage.read(stream), null);
        }
    }
}
