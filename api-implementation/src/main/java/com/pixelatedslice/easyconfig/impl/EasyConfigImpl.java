package com.pixelatedslice.easyconfig.impl;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.CopiedEasyConfig;
import com.pixelatedslice.easyconfig.api.EasyConfig;
import com.pixelatedslice.easyconfig.api.exception.BuiltInSerializerOverrideException;
import com.pixelatedslice.easyconfig.api.exception.BuiltInSerializerUnregisterException;
import com.pixelatedslice.easyconfig.api.exception.ModificationOfNonCopiedEasyConfigInstanceException;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.JsonFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.TomlFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.YamlFileFormat;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.*;

@AutoService(EasyConfig.class)
public sealed class EasyConfigImpl implements EasyConfig permits CopiedEasyConfigImpl {
    private static volatile EasyConfigImpl INSTANCE;
    private final CommonFormatProviders commonFormatProviders;
    private final Map<Class<? extends FileFormat>, FileFormatProvider<?>> providers;
    private final Map<TypeToken<?>, Serializer<?>> serializers;

    protected EasyConfigImpl() {
        this.providers = new HashMap<>();
        this.serializers = new HashMap<>();
        this.commonFormatProviders = new CommonFormatProvidersImpl(this);
    }

    protected EasyConfigImpl(
            @NonNull Map<@NonNull Class<? extends FileFormat>, @NonNull FileFormatProvider<?>> providers,
            @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers
    ) {
        Objects.requireNonNull(providers);
        Objects.requireNonNull(serializers);
        this.providers = providers;
        this.serializers = serializers;
        this.commonFormatProviders = new CommonFormatProvidersImpl(this);
    }

    // For service loader
    public static EasyConfig provider() {
        return INSTANCE;
    }

    public static EasyConfigImpl instance() {
        if (INSTANCE == null) {
            synchronized (EasyConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EasyConfigImpl();
                }
            }
        }

        return INSTANCE;
    }

    private void copiedInstanceCheck() {
        if (!(this instanceof CopiedEasyConfig)) {
            throw new ModificationOfNonCopiedEasyConfigInstanceException();
        }
    }

    @Override
    public @NonNull CopiedEasyConfig copy() {
        return new CopiedEasyConfigImpl(new HashMap<>(this.providers), new HashMap<>(this.serializers));
    }

    @Override
    @NonNull
    public Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers() {
        return Collections.unmodifiableMap(this.serializers);
    }


    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T> Optional<@NonNull Serializer<T>> serializer(@NonNull TypeToken<T> clazz) {
        Objects.requireNonNull(clazz);
        return Optional.ofNullable((Serializer<T>) this.serializers.get(Objects.requireNonNull(clazz)));
    }


    @Override
    public void registerSerializers(@NonNull Serializer<?> @NonNull ... serializers)
            throws ModificationOfNonCopiedEasyConfigInstanceException {
        this.copiedInstanceCheck();

        Objects.requireNonNull(serializers);

        Map<Serializer<?>, Serializer<?>> illegal = null;
        for (var serializer : serializers) {
            var previous = this.serializers.get(serializer.forType());

            if ((previous == null) || previous.equals(serializer)) {
                continue;
            }

            if (Serializer.isBuiltIn(previous)) {
                if (illegal == null) {
                    illegal = new HashMap<>(4);
                }
                illegal.put(previous, serializer);
            }
        }

        if (illegal != null) {
            throw new BuiltInSerializerOverrideException(illegal);
        }

        for (var ser : serializers) {
            this.serializers.put(ser.forType(), ser);
        }
    }


    @Override
    public void unregisterSerializers(@NonNull TypeToken<?> @NonNull ... typeTokens) {
        this.copiedInstanceCheck();

        Objects.requireNonNull(typeTokens);

        List<TypeToken<?>> illegal = null;
        for (var typeToken : typeTokens) {
            var serializer = this.serializers.get(typeToken);
            if (serializer == null) {
                continue;
            }

            if (Serializer.isBuiltIn(serializer)) {
                if (illegal == null) {
                    illegal = new ArrayList<>(4);
                }
                illegal.add(serializer.forType());
            }
        }

        if (illegal != null) {
            throw new BuiltInSerializerUnregisterException(illegal);
        }

        for (var serializer : typeTokens) {
            this.serializers.remove(serializer);
        }
    }


    @Override
    @NonNull
    public Map<@NonNull Class<? extends FileFormat>, @NonNull FileFormatProvider<?>> providers() {
        return Collections.unmodifiableMap(this.providers);
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends FileFormat> @NonNull Optional<@NonNull FileFormatProvider<T>> provider(
            @NonNull Class<T> fileFormatClass
    ) {
        Objects.requireNonNull(fileFormatClass);

        var found = this.providers.get(Objects.requireNonNull(fileFormatClass));
        if (found == null) {
            return Optional.empty();
        }

        return found.fileFormatClass().equals(fileFormatClass)
                ? Optional.of((FileFormatProvider<T>) found)
                : Optional.empty();

    }

    @Override
    public void registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException {
        this.copiedInstanceCheck();

        Objects.requireNonNull(providers);

        var map = new HashMap<Class<? extends FileFormat>, FileFormatProvider<?>>();
        for (FileFormatProvider<?> provider : providers) {
            map.put(provider.fileFormatClass(), provider);
        }
        this.providers.putAll(map);
    }


    @Override
    public void unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException {
        this.copiedInstanceCheck();

        Objects.requireNonNull(providers);

        for (FileFormatProvider<?> provider : providers) {
            this.providers.remove(provider.fileFormatClass());
        }
    }

    @Override
    public @NonNull CommonFormatProviders commonFormatProviders() {
        return this.commonFormatProviders;
    }

    static class CommonFormatProvidersImpl implements CommonFormatProviders {
        private final EasyConfig easyConfig;

        CommonFormatProvidersImpl(EasyConfig easyConfig) {
            this.easyConfig = easyConfig;
        }

        @Override
        public @NonNull Optional<@NonNull FileFormatProvider<JsonFileFormat>> json() {
            return this.easyConfig.provider(JsonFileFormat.class);
        }

        @Override
        public @NonNull Optional<@NonNull FileFormatProvider<TomlFileFormat>> toml() {
            return this.easyConfig.provider(TomlFileFormat.class);
        }

        @Override
        public @NonNull Optional<@NonNull FileFormatProvider<YamlFileFormat>> yaml() {
            return this.easyConfig.provider(YamlFileFormat.class);
        }
    }
}