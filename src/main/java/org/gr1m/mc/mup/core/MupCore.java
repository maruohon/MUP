package org.gr1m.mc.mup.core;

import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(-10000)
@IFMLLoadingPlugin.TransformerExclusions("org.gr1m.mc.mup.core.MupCore")
public class MupCore implements IFMLLoadingPlugin {
    public static final Logger log = LogManager.getLogger();
    private static boolean initialized = false;

    private static MupCoreConfig config;

    public MupCore() {
        initialize();

        MixinBootstrap.init();
        
        // @formatter:off
        if (config.mc4)           Mixins.addConfiguration("mixins.mup.mc4.json");
        if (config.mc2025)        Mixins.addConfiguration("mixins.mup.mc2025.json");
        if (config.mc5694)        Mixins.addConfiguration("mixins.mup.mc5694.json");
        if (config.mc9568)        Mixins.addConfiguration("mixins.mup.mc9568.json");
        if (config.mc54026)       Mixins.addConfiguration("mixins.mup.mc54026.json");
        if (config.mc73051)       Mixins.addConfiguration("mixins.mup.mc73051.json");
        if (config.mc108469)      Mixins.addConfiguration("mixins.mup.mc108469.json");
        if (config.mc118710)      Mixins.addConfiguration("mixins.mup.mc118710.json");
        if (config.mc119971)      Mixins.addConfiguration("mixins.mup.mc119971.json");

        if (config.newlight)      Mixins.addConfiguration("mixins.mup.newlight.json");
        // @formatter:on
    }

    public static void initialize() {
        if (initialized) return;
        initialized = true;

        config = new MupCoreConfig();
        config.init(new File(((File)(FMLInjectionData.data()[6])), "config/mup.cfg"));
    }

    @Override public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override public String getModContainerClass() {
        return null;
    }

    @Nullable @Override public String getSetupClass() {
        return null;
    }

    @Override public void injectData(Map<String, Object> data) {}

    @Override public String getAccessTransformerClass() {
        return null;
    }
}