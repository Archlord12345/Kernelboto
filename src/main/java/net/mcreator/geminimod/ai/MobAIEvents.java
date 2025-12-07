package net.mcreator.geminimod.ai;

import net.mcreator.geminimod.entity.IntelligentMobEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class MobAIEvents {

    private static final Map<UUID, Integer> aiUpdateTimers = new HashMap<>();
    private static final int AI_UPDATE_INTERVAL = 100; // 100 ticks = 5 seconds

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof IntelligentMobEntity) {
            IntelligentMobEntity mob = (IntelligentMobEntity) entity;
            UUID mobId = mob.getUUID();

            int timer = aiUpdateTimers.getOrDefault(mobId, 0);
            if (timer <= 0) {
                String mobState = "Health: " + mob.getHealth() + ", Target: " + (mob.getTarget() != null ? mob.getTarget().getName().getString() : "none");
                try {
                    String nextAction = GeminiAI.getNextAction(mobState);
                    mob.goalSelector.clear();
                    if (nextAction.equals("wander")) {
                        mob.goalSelector.addGoal(1, new RandomStrollGoal(mob, 1.0D));
                        mob.goalSelector.addGoal(2, new RandomLookAroundGoal(mob));
                    } else if (nextAction.equals("attack") && mob.getTarget() != null) {
                        // Attack logic will be added here
                    } else if (nextAction.equals("flee")) {
                        // Flee logic will be added here
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                aiUpdateTimers.put(mobId, AI_UPDATE_INTERVAL);
            } else {
                aiUpdateTimers.put(mobId, timer - 1);
            }
        }
    }
}
