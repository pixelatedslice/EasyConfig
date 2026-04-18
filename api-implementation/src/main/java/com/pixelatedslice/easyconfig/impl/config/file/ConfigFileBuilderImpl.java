package com.pixelatedslice.easyconfig.impl.config.file;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.config.BuilderWithConfigNodes;
import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import com.pixelatedslice.easyconfig.api.config.file.ConfigFileBuilder;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
import com.pixelatedslice.easyconfig.api.exception.ComplexInsteadOfSimpleTypeUsedException;
import com.pixelatedslice.easyconfig.api.utils.type_token.TypeTokenUtils;
import com.pixelatedslice.easyconfig.impl.config.node.ConfigNodeBuilderImpl;
import com.pixelatedslice.easyconfig.impl.config.node.ConfigNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.section.ConfigSectionBuilderImpl;
import com.pixelatedslice.easyconfig.impl.config.section.ConfigSectionImpl;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

@AutoService(ConfigFileBuilder.class)
public class ConfigFileBuilderImpl implements ConfigFileBuilder {
    private final ConfigSection rootSection = ConfigSectionImpl.newRootSection();
    private final Collection<ConfigNode<?>> nodes = new ArrayList<>();
    private final Collection<ConfigSection> sections = new ArrayList<>();
    private Path filePathWithoutExtension;

    @Override
    public @NonNull ConfigFileBuilder filePath(@NonNull Path filePathWithoutExtension) {
        this.filePathWithoutExtension = filePathWithoutExtension;
        return this;
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @NonNull Class<T> simpleType,
            @NonNull Consumer<ConfigNodeBuilder<T>> nodeBuilder) {
        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, typeToken, nodeBuilder);
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        var builder = new ConfigNodeBuilderImpl<>(key, typeToken, null, null, this.rootSection);
        nodeBuilder.accept(builder);
        builder.parent(this.rootSection);
        this.nodes.add(builder.build());
        return this;
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken) {
        this.nodes.add(new ConfigNodeImpl<>(key, typeToken, null, null, this.rootSection, new ArrayList<>()));
        return this;
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @NonNull Class<T> simpleType) {
        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, typeToken);
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @Nullable T value,
            @NonNull TypeToken<T> typeToken) {
        this.nodes.add(new ConfigNodeImpl<>(key, typeToken, value, null, this.rootSection, new ArrayList<>()));
        return this;
    }

    @Override
    public @NonNull <T> BuilderWithConfigNodes node(@NonNull String key, @Nullable T value,
            @NonNull Class<T> simpleType) {
        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, value, typeToken);
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key, @NonNull T valueWithSimpleType) {
        var typeToken = TypeToken.of(valueWithSimpleType.getClass());
        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }
        return this.node(key, typeToken);
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull String key,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);
        builder.key(key);
        builder.parent(this.rootSection);
        this.nodes.add(builder.build());
        return this;
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);
        builder.typeToken(typeToken);
        builder.parent(this.rootSection);
        this.nodes.add(builder.build());
        return this;
    }

    @Override
    public <T> @NonNull ConfigFileBuilder node(@NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);
        builder.parent(this.rootSection);
        this.nodes.add(builder.build());
        return this;
    }

    @Override
    public @NonNull ConfigFileBuilder section(@NonNull String key,
            @NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder) {
        var builder = new ConfigSectionBuilderImpl(this.rootSection);
        nestedSectionBuilder.accept(builder);
        builder.key(key);
        builder.parent(this.rootSection);
        this.sections.add(builder.build());
        return this;
    }

    @Override
    public @NonNull ConfigFileBuilder section(@NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder) {
        var builder = new ConfigSectionBuilderImpl(this.rootSection);
        nestedSectionBuilder.accept(builder);
        builder.parent(this.rootSection);
        this.sections.add(builder.build());
        return this;
    }

    @Override
    public @NonNull ConfigFile build() {
        try (var mut = this.rootSection.mutable()) {
            mut.setNodes(this.nodes);
            mut.setSections(this.sections);
        }

        return new ConfigFileImpl(
                this.rootSection,
                this.filePathWithoutExtension
        );
    }
}
