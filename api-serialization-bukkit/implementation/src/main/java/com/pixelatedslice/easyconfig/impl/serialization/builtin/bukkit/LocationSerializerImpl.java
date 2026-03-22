package com.pixelatedslice.easyconfig.impl.serialization.builtin.bukkit;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
import com.pixelatedslice.easyconfig.api.serialization.builtin.bukkit.LocationSerializer;
import com.pixelatedslice.easyconfig.impl.config.section.ConfigSectionBuilderImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public final class LocationSerializerImpl implements LocationSerializer {
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
    public @NotNull ConfigSection serialize(@NotNull Location value) {
        ConfigSectionBuilder section = new ConfigSectionBuilderImpl();

        if (value.getWorld() != null) {
            section.node("world", String.class, (ConfigNodeBuilder<String> builder) -> {
                builder.value(value.getWorld().getName());
            });
        }

        section.node("x", Double.class, (ConfigNodeBuilder<Double> builder) -> {
            builder.value(value.getX());
        });
        section.node("y", Double.class, (ConfigNodeBuilder<Double> builder) -> {
            builder.value(value.getY());
        });
        section.node("z", Double.class, (ConfigNodeBuilder<Double> builder) -> {
            builder.value(value.getZ());
        });
        section.node("yaw", Float.class, (ConfigNodeBuilder<Float> builder) -> {
            builder.value(value.getYaw());
        });
        section.node("pitch", Float.class, (ConfigNodeBuilder<Float> builder) -> {
            builder.value(value.getPitch());
        });

        return section.build();
    }

    @Override
    public @NonNull Location deserialize(@NotNull ConfigSection section) {
        var world = section
                .childNode(String.class, "world")
                .flatMap(ConfigNode::value)
                .map(Bukkit::getWorld)
                .orElse(null);
        var x = section.childNode(Double.class, "x")
                .flatMap(ConfigNode::value)
                .orElseThrow();
        var y = section.childNode(Double.class, "y")
                .flatMap(ConfigNode::value)
                .orElseThrow();
        var z = section.childNode(Double.class, "z")
                .flatMap(ConfigNode::value)
                .orElseThrow();
        var yaw = section.childNode(Float.class, "yaw")
                .flatMap(ConfigNode::value)
                .orElseThrow();
        var pitch = section.childNode(Float.class, "pitch")
                .flatMap(ConfigNode::value)
                .orElseThrow();

        return new Location(
                world,
                x, y, z,
                yaw, pitch
        );
    }
}
