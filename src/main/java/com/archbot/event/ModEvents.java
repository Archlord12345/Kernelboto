package com.archbot.event;

import com.archbot.ArchbotMod;
import com.archbot.entity.ArchbotEntity;
import com.archbot.entity.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchbotMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ARCHBOT.get(), ArchbotEntity.createAttributes().build());
    }
}
