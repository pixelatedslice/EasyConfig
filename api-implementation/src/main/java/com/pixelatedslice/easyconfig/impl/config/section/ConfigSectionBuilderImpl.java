package com.pixelatedslice.easyconfig.impl.config.section;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
import com.pixelatedslice.easyconfig.api.exception.ComplexInsteadOfSimpleTypeUsedException;
import com.pixelatedslice.easyconfig.api.utils.type_token.TypeTokenUtils;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import com.pixelatedslice.easyconfig.impl.config.node.ConfigNodeBuilderImpl;
import com.pixelatedslice.easyconfig.impl.config.node.EnvConfigNodeBuilderImpl;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("DuplicatedCode")
@AutoService(ConfigSectionBuilder.class)
public class ConfigSectionBuilderImpl implements ConfigSectionBuilder {
    private final Collection<ConfigNodeBuilder<?>> childNodes = new ArrayList<>();
    private final Collection<ConfigSectionBuilder> nestedSections = new ArrayList<>();
    private final List<String> comments = new ArrayList<>();
    private String key;
    private ConfigSection parent;

    public ConfigSectionBuilderImpl(@NonNull ConfigSection parent) {
        Objects.requireNonNull(parent);

        this.parent = parent;
    }

    public ConfigSectionBuilderImpl() {
    }

    @Override
    public @NonNull ConfigSectionBuilder key(@NonNull String key) {
        Objects.requireNonNull(key);
        this.key = key;
        return this;
    }

    @Override
    public @NonNull ConfigSectionBuilder parent(@NonNull ConfigSection parent) {
        Objects.requireNonNull(parent);
        this.parent = parent;
        return this;
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @NonNull Class<T> simpleType,
            @NonNull Consumer<ConfigNodeBuilder<T>> nodeBuilder) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(simpleType);
        Objects.requireNonNull(nodeBuilder);

        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, typeToken, nodeBuilder);
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(nodeBuilder);

        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);

        builder.key(key);
        builder.typeToken(typeToken);

        this.node(builder);
        return this;
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(typeToken);
        this.node(new ConfigNodeBuilderImpl<T>().key(key).typeToken(typeToken));
        return this;
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @NonNull Class<T> simpleType) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(simpleType);

        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, typeToken);
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @Nullable T value,
            @NonNull TypeToken<T> typeToken) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(typeToken);
        this.node(new ConfigNodeBuilderImpl<T>().key(key).value(value).typeToken(typeToken));
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @Nullable T value,
            @NonNull Class<T> simpleType) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(simpleType);

        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, value, typeToken);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key, @NonNull T valueWithSimpleType) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(valueWithSimpleType);

        TypeToken<T> typeToken = TypeToken.of((Class<T>) valueWithSimpleType.getClass());

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        return this.node(key, valueWithSimpleType, typeToken);
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull String key,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(nodeBuilder);

        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);

        builder.key(key);

        this.node(builder);
        return this;
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(nodeBuilder);

        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);

        builder.typeToken(typeToken);

        this.node(builder);
        return this;
    }

    @Override
    public <T> @NonNull ConfigSectionBuilder node(@NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder) {
        Objects.requireNonNull(nodeBuilder);

        var builder = new ConfigNodeBuilderImpl<T>();
        nodeBuilder.accept(builder);

        this.node(builder);
        return this;
    }

    private void node(@NonNull ConfigNodeBuilder<?> nodeBuilder) {
        Objects.requireNonNull(nodeBuilder);
        this.childNodes.add(nodeBuilder);
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator) {
        this.node(new ConfigNodeBuilderImpl<T>(key, typeToken, null, null, validator, this.parent));
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @NonNull Class<T> simpleType,
            Validator<T> validator) {
        this.node()
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @Nullable T value,
            @NonNull TypeToken<T> typeToken, Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @Nullable T value, @NonNull Class<T> simpleType,
            Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken, Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull Class<T> simpleType, Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull Class<T> simpleType,
            Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder node(@NonNull String key, @NonNull T valueWithSimpleType,
            Validator<T> validator) {
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(envKey);
        Objects.requireNonNull(typeToken);

        this.childNodes.add(new EnvConfigNodeBuilderImpl<>(
                key,
                envKey,
                typeToken
        ));
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull TypeToken<T> typeToken) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(typeToken);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        this.childNodes.add(new EnvConfigNodeBuilderImpl<>(
                key,
                key,
                typeToken
        ));
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull Class<T> simpleType) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(envKey);
        Objects.requireNonNull(simpleType);

        var typeToken = TypeToken.of(simpleType);

        if (!TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            throw new ComplexInsteadOfSimpleTypeUsedException();
        }

        this.childNodes.add(new EnvConfigNodeBuilderImpl<>(
                key,
                envKey,
                typeToken
        ));
        return this;
    }

    @Override
    public @NonNull <T> ConfigSectionBuilder env(@NonNull String key, @NonNull Class<T> simpleType) {
        return this.env(key, key, simpleType);
    }

    @Override
    public @NonNull ConfigSectionBuilder comments(@NonNull String @NonNull ... comments) {
        Objects.requireNonNull(comments);
        Collections.addAll(this.comments, comments);
        return this;
    }

    @Override
    public @NonNull ConfigSectionBuilder addComment(@NonNull String comment) {
        Objects.requireNonNull(comment);
        this.comments.add(comment);
        return this;
    }

    @Override
    public @NonNull ConfigSectionBuilder section(@NonNull String key,
            @NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(nestedSectionBuilder);
        var builder = new ConfigSectionBuilderImpl();
        nestedSectionBuilder.accept(builder);
        builder.key(key);
        this.section(builder);
        return this;
    }

    @Override
    public @NonNull ConfigSectionBuilder section(@NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder) {
        Objects.requireNonNull(nestedSectionBuilder);
        var builder = new ConfigSectionBuilderImpl();
        nestedSectionBuilder.accept(builder);
        this.section(builder);
        return this;
    }

    private void section(@NonNull ConfigSectionBuilder nestedSectionBuilder) {
        Objects.requireNonNull(nestedSectionBuilder);
        this.nestedSections.add(nestedSectionBuilder);
    }

    @Override
    public @NonNull ConfigSection build() {
        var section = new ConfigSectionImpl(
                Objects.requireNonNull(this.key),
                this.parent,
                new ArrayList<>(),
                new ArrayList<>(),
                Objects.requireNonNull(this.comments)
        );

        try (var mutable = section.mutable()) {
            for (var childNode : this.childNodes) {
                childNode.parent(section);
                mutable.addNodes(childNode.build());
            }

            for (var nestedSection : this.nestedSections) {
                nestedSection.parent(section);
                mutable.addSections(nestedSection.build());
            }
            return section;
        }
    }
}
