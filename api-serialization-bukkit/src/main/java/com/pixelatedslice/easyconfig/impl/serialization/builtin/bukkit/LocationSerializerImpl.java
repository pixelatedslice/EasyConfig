package com.pixelatedslice.easyconfig.impl.serialization.builtin.bukkit;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
import com.pixelatedslice.easyconfig.api.serialization.builtin.BuiltInBukkitSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class LocationSerializerImpl implements BuiltInBukkitSerializer<Location> {
    private static volatile LocationSerializerImpl INSTANCE;

    private LocationSerializerImpl() {
    }

    public static LocationSerializerImpl instance() {
        if (INSTANCE == null) {
            synchronized (LocationSerializerImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationSerializerImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void serialize(@Nullable Location value, @NonNull ConfigSectionBuilder sectionBuilder) {
        sectionBuilder.node(
                "world",
                ((value != null) && (value.getWorld() != null)) ? value.getWorld().getName() : null,
                String.class
        );
        sectionBuilder.node("x", (value != null) ? value.getX() : null, Double.class);
        sectionBuilder.node("y", (value != null) ? value.getY() : null, Double.class);
        sectionBuilder.node("z", (value != null) ? value.getZ() : null, Double.class);
        sectionBuilder.node("yaw", (value != null) ? value.getYaw() : null, Float.class);
        sectionBuilder.node("pitch", (value != null) ? value.getPitch() : null, Float.class);
    }

    @Override
    public @NonNull Location deserialize(@NonNull ConfigSection section) {
        var world = section
                .node(TypeToken.of(String.class), "world")
                .flatMap(ConfigNode::value)
                .map(Bukkit::getWorld)
                .orElse(null);
        var x = section.node(TypeToken.of(Double.class), "x").flatMap(ConfigNode::value).orElseThrow();
        var y = section.node(TypeToken.of(Double.class), "y").flatMap(ConfigNode::value).orElseThrow();
        var z = section.node(TypeToken.of(Double.class), "z").flatMap(ConfigNode::value).orElseThrow();
        var yaw = section.node(TypeToken.of(Float.class), "yaw")
                .flatMap(ConfigNode::value)
                .orElseThrow();
        var pitch = section.node(TypeToken.of(Float.class), "pitch")
                .flatMap(ConfigNode::value)
                .orElseThrow();

        return new Location(
                world,
                x, y, z,
                yaw, pitch
        );
    }

    @Override
    @NonNull
    public TypeToken<Location> forType() {
        return new TypeToken<>() {
        };
    }
}
