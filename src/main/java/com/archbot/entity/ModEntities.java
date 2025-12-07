package com.archbot.entity;

import com.archbot.ArchbotMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArchbotMod.MODID);

    public static final RegistryObject<EntityType<ArchbotEntity>> ARCHBOT =
            ENTITY_TYPES.register("archbot",
                    () -> EntityType.Builder.of(ArchbotEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.8f)
                            .build("archbot"));
}
