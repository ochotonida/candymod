package com.ochotonida.candymod.command;

import com.ochotonida.candymod.ModConfig;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// see https://wiki.mcjty.eu/modding/index.php?title=Main_Page
public class CustomTeleporter extends Teleporter {

    public CustomTeleporter(WorldServer world, BlockPos pos) {
        super(world);
        this.worldServer = world;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    private final WorldServer worldServer;
    private double x;
    private double y;
    private double z;

    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        // The main purpose of this function is to *not* create a nether portal
        this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));

        entity.setPosition(this.x, this.y, this.z);
        entity.motionX = 0.0f;
        entity.motionY = 0.0f;
        entity.motionZ = 0.0f;
    }

    public static boolean teleport(EntityPlayer player) {
        int oldDimension = player.getEntityWorld().provider.getDimension();
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
        MinecraftServer server = player.getEntityWorld().getMinecraftServer();
        assert server != null;

        if (oldDimension == ModConfig.dimensionId) {
            WorldServer worldServer = server.getWorld(0);
            BlockPos spawn = null;
            BlockPos bed = entityPlayerMP.getBedLocation(0);
            if (bed != null) {
                spawn = EntityPlayer.getBedSpawnLocation(worldServer, bed, false);
            }
            if (spawn == null) {
                spawn = worldServer.getSpawnPoint();
            }
            server.getPlayerList().transferPlayerToDimension(entityPlayerMP, 0, new CustomTeleporter(worldServer, spawn));
            player.setPositionAndUpdate(spawn.getX(), spawn.getY(), spawn.getZ());
            return true;
        } else {
            if (oldDimension != 1) {
                WorldServer worldServer = server.getWorld(ModConfig.dimensionId);
                BlockPos pos = findSpawn(player.getPosition().getX(), player.getPosition().getZ(), worldServer);
                if (pos != null) {
                    server.getPlayerList().transferPlayerToDimension(entityPlayerMP, ModConfig.dimensionId, new CustomTeleporter(worldServer, pos));
                    player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private static BlockPos findSpawn(int posX, int posZ, World world) {
        for (int x = posX; x < posX + 16; x++) {
            for (int z = posZ; z < posZ + 16; z++) {
                BlockPos pos = new BlockPos(x, 254, z);
                for (int y = 254; y > 55; y--) {
                    if (world.isAirBlock(pos) && world.isAirBlock(pos.up()) && (world.isBlockFullCube(pos.down()) || world.getBlockState(pos.down()).getMaterial() == Material.WATER)) {
                        return pos;
                    }
                    pos = pos.down();
                }
            }
        }
        return null;
    }
}
