package com.archbot.ai;

import com.archbot.entity.ArchbotEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

@Mod.EventBusSubscriber
public class ArchbotAIEvents {

    private static final Map<UUID, Integer> aiUpdateTimers = new HashMap<>();
    private static final int AI_UPDATE_INTERVAL = 100; // 100 ticks = 5 seconds

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof ArchbotEntity) {
            ArchbotEntity mob = (ArchbotEntity) entity;
            UUID mobId = mob.getUUID();

            int timer = aiUpdateTimers.getOrDefault(mobId, 0);
            if (timer <= 0) {
                String mobState = "Health: " + mob.getHealth() + ", Position: " + mob.blockPosition().toString();
                String nextAction = GeminiAI.getNextAction(mobState);
                mob.goalSelector.removeAllGoals((Predicate<Goal>) goal -> true);

                String[] actionParts = nextAction.split(" ");
                String action = actionParts[0];

                if (action.equalsIgnoreCase("mine")) {
                    BlockPos targetBlock = findNearbyBlock(mob, Blocks.STONE, 10);
                    if (targetBlock != null) {
                        mob.goalSelector.addGoal(1, new MineBlockGoal(mob, targetBlock));
                    }
                } else if (action.equalsIgnoreCase("build")) {
                    BlockPos targetPos = mob.blockPosition().east(2);
                    mob.goalSelector.addGoal(1, new PlaceBlockGoal(mob, targetPos, Blocks.DIRT));
                } else {
                    mob.goalSelector.addGoal(1, new RandomStrollGoal(mob, 1.0D));
                    mob.goalSelector.addGoal(2, new RandomLookAroundGoal(mob));
                }
                aiUpdateTimers.put(mobId, AI_UPDATE_INTERVAL);
            } else {
                aiUpdateTimers.put(mobId, timer - 1);
            }
        }
    }

    private static BlockPos findNearbyBlock(LivingEntity entity, Block block, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = entity.blockPosition().offset(x, y, z);
                    if (entity.level().getBlockState(pos).is(block)) {
                        return pos;
                    }
                }
            }
        }
        return null;
    }

    public static class MineBlockGoal extends Goal {
        private final ArchbotEntity mob;
        private final BlockPos targetBlock;

        public MineBlockGoal(ArchbotEntity mob, BlockPos targetBlock) {
            this.mob = mob;
            this.targetBlock = targetBlock;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void start() {
            this.mob.getNavigation().moveTo(this.targetBlock.getX(), this.targetBlock.getY(), this.targetBlock.getZ(), 1.0D);
        }

        @Override
        public void tick() {
            if (this.mob.distanceToSqr(this.targetBlock.getX(), this.targetBlock.getY(), this.targetBlock.getZ()) < 4.0D) {
                this.mob.level().destroyBlock(this.targetBlock, true);
                this.stop();
            }
        }
    }

    public static class PlaceBlockGoal extends Goal {
        private final ArchbotEntity mob;
        private final BlockPos targetPos;
        private final Block blockToPlace;

        public PlaceBlockGoal(ArchbotEntity mob, BlockPos targetPos, Block blockToPlace) {
            this.mob = mob;
            this.targetPos = targetPos;
            this.blockToPlace = blockToPlace;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void start() {
            this.mob.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 1.0D);
        }

        @Override
        public void tick() {
            if (this.mob.distanceToSqr(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) < 4.0D) {
                this.mob.level().setBlock(this.targetPos, this.blockToPlace.defaultBlockState(), 3);
                this.stop();
            }
        }
    }
}
