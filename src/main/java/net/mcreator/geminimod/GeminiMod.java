package net.mcreator.geminimod;

import net.mcreator.geminimod.ai.MobAIEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("gemini_mod")
public class GeminiMod {

    public GeminiMod() {
        MinecraftForge.EVENT_BUS.register(MobAIEvents.class);
    }
}
