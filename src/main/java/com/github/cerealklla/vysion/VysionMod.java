package com.github.cerealklla.vysion;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * NeoForge add-on for Vivecraft -- a per-player hybrid VR input mode (see
 * {@code context/design-document.md} for the full design and the real Mixin targets found by
 * reading Vivecraft's own source, 2026-10-18). Entirely client-dist ({@code Dist.CLIENT}) -- there
 * is no server-side component to this mod at all (VR headset rendering/input has no server-side
 * concept), so this single class is the whole mod entry point; NeoForge simply never loads it on a
 * dedicated server. Scaffolded only, no real logic yet.
 */
@Mod(value = VysionMod.MODID, dist = Dist.CLIENT)
public class VysionMod {

    public static final String MODID = "vysion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VysionMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        LOGGER.info("Vysion client setup -- no hybrid-input logic implemented yet, see context/design-document.md.");
    }
}
