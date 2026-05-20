package com.pixelatedslice.easyconfig.api.config.node.env;

import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;

public final class EnvKeys {

    public static final NodeBuilder.PredefinedEnv<Boolean> DEBUGGING_ENABLED = new DefinedEnv<>("DEBUGGER_ENABLED", Boolean::parseBoolean);
    public static final NodeBuilder.PredefinedEnv<String> DEBUGGER_ID = new DefinedEnv<>("DEBUGGER_ID", t -> t);
    public static final NodeBuilder.PredefinedEnv<Path> SHELL = DefinedEnv.path("SHELL");
    public static final NodeBuilder.PredefinedEnv<Collection<Path>> PATHS = new DefinedEnv<>("PATH", input -> {
        var split = input.split(";");
        try {
            return Stream.of(split).map(stringPath -> {
                try {
                    return Path.of(stringPath).toRealPath();
                } catch (IllegalArgumentException | IOException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        }catch (RuntimeException e){
            return null;
        }
    });
    public static final NodeBuilder.PredefinedEnv<Path> EDITOR = DefinedEnv.path("EDITOR");
    public static final NodeBuilder.PredefinedEnv<Path> HOME = DefinedEnv.path("HOME");
    public static final NodeBuilder.PredefinedEnv<String> USER = new DefinedEnv<>("USER", t -> t);
    public static final NodeBuilder.PredefinedEnv<Integer> DISPLAY = new DefinedEnv<>("DISPLAY", input -> {
        try {
            return Integer.parseInt(input.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    });
    public static final NodeBuilder.PredefinedEnv<LangResult> LANG = new DefinedEnv<>("LANG", input -> {
        String[] split = input.split("\\.", 2);
        if (split.length != 2) {
            return null;
        }
        return new LangResult(Locale.of(split[0]), split[1]);
    });

    private EnvKeys() {

    }

    public record LangResult(Locale language, String encoding) {

    }

    record DefinedEnv<T>(String key, Function<String, T> adapter) implements NodeBuilder.PredefinedEnv<T> {

        static DefinedEnv<Path> path(String key) {
            return new DefinedEnv<>(key, input -> {
                try {
                    return Path.of(input).toRealPath();
                } catch (IllegalArgumentException | IOException ex){
                    return null;
                }
            });
        }

    }
}
