package net.ccbluex.classycreepers.addon;

import com.mojang.blaze3d.platform.InputConstants;
import net.ccbluex.liquidbounce.features.module.ClientModule;
import net.ccbluex.liquidbounce.features.module.ModuleCategories;
import net.ccbluex.liquidbounce.utils.input.InputBind;

public final class ModuleClassyCreepers extends ClientModule {
    public static final ModuleClassyCreepers INSTANCE = new ModuleClassyCreepers();

    private ModuleClassyCreepers() {
        super("ClassyCreepers", ModuleCategories.FUN, InputConstants.UNKNOWN.getValue(), InputBind.BindAction.TOGGLE, false);
    }
}
