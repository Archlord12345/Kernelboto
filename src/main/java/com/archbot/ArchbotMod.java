package com.archbot;

import com.archbot.ai.ArchbotAIEvents;
import com.archbot.ai.GeminiAI;
import com.archbot.entity.ModEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArchbotMod.MODID)
public class ArchbotMod {
    public static final String MODID = "archbot";

    public ArchbotMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.ENTITY_TYPES.register(modEventBus);

        GeminiAI.initialize();
        MinecraftForge.EVENT_BUS.register(ArchbotAIEvents.class);
    }
}
