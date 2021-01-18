package com.jbesu.mc.xray.tasks;

import java.util.ArrayList;

import com.jbesu.mc.xray.App;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ChunkTask extends Task {
    private ArrayList<Chunk> runningChunks;

    public ChunkTask(App app, Player player) {
        super(app, player);
        calculateRunningChunks();
    }

    private void calculateRunningChunks() {
        runningChunks = new ArrayList<>();

        Location location = player.getLocation();
        int chunkSize = 16;
        int chunkX = location.getChunk().getX();
        int chunkZ = location.getChunk().getZ();

        int rangeBlocks = range * chunkSize;

        for (int x = chunkX - rangeBlocks; x <= chunkX + rangeBlocks; x += chunkSize) {
            for (int z = chunkZ - rangeBlocks; z <= chunkZ + rangeBlocks; z += chunkSize) {
                runningChunks.add(location.getWorld().getChunkAt(x, z));
            }
        }
    }

    public void start() {
        // TODO
    }

    public void stop() {
        // TODO
    }
}
