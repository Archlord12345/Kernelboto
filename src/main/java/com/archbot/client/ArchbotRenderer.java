package com.archbot.client;

import com.archbot.ArchbotMod;
import com.archbot.entity.ArchbotEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class ArchbotRenderer extends HumanoidMobRenderer<ArchbotEntity, net.minecraft.client.model.HumanoidModel<ArchbotEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ArchbotMod.MODID, "textures/entity/tvbot.png");

    public ArchbotRenderer(EntityRendererProvider.Context context) {
        super(context, new net.minecraft.client.model.HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ArchbotEntity entity) {
        return TEXTURE;
    }
}
