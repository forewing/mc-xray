package com.jbesu.mc.xray.player;

import java.util.UUID;

import com.jbesu.mc.xray.tasks.Task;

public class PlayerInfo {
    private UUID uuid;

    private Task task;

    public PlayerInfo(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isXRay() {
        return task != null;
    }
}
