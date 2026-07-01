package com.pixelatedslice.easyconfig.api.config.node.env;

import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderEnvStep;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;

// All nulls in DefinedEnv are the culprits for DataFlowIssue warnings, but they are handled correctly in the code.
@SuppressWarnings({"unused", "DataFlowIssue"})
@NullMarked
public final class EnvKeys {

    public static final FactoryNodeBuilderEnvStep.PreDefined<String> DEBUGGER_ID = new DefinedEnv<>("DEBUGGER_ID",
            (String t) -> t);
    public static final FactoryNodeBuilderEnvStep.PreDefined<Boolean> DEBUGGING_ENABLED = new DefinedEnv<>(
            "DEBUGGER_ENABLED",
            Boolean::parseBoolean);
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable Integer> DISPLAY = new DefinedEnv<>("DISPLAY",
            (String input) -> {
                try {
                    return Integer.parseInt(input.substring(1));
                } catch (NumberFormatException e) {
                    return null;
                }
            });
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable Path> EDITOR = DefinedEnv.path("EDITOR");
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable Path> HOME = DefinedEnv.path("HOME");
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable LangResult> LANG = new DefinedEnv<>("LANG",
            (String input) -> {
                String[] split = input.split("\\.", 2);
                return (split.length != 2) ? null : new LangResult(Locale.of(split[0]), split[1]);
            });
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable Collection<Path>> PATHS = new DefinedEnv<>(
            "PATH",
            (String input) -> {
                var split = input.split(";");
                try {
                    return Stream.of(split).map((String stringPath) -> {
                        try {
                            return Path.of(stringPath).toRealPath();
                        } catch (IllegalArgumentException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
                } catch (RuntimeException e) {
                    return null;
                }
            });
    public static final FactoryNodeBuilderEnvStep.PreDefined<@Nullable Path> SHELL = DefinedEnv.path("SHELL");
    public static final FactoryNodeBuilderEnvStep.PreDefined<String> USER = new DefinedEnv<>("USER", (String t) -> t);

    private EnvKeys() {

    }

    public record LangResult(Locale language, String encoding) {

    }

    @NullMarked
    record DefinedEnv<T extends @Nullable Object>(String key, Function<String, @Nullable T> adapter)
            implements FactoryNodeBuilderEnvStep.PreDefined<T> {

        @SuppressWarnings("DataFlowIssue")
        static DefinedEnv<@Nullable Path> path(String key) {
            return new DefinedEnv<>(key, (String input) -> {
                try {
                    return Path.of(input).toRealPath();
                } catch (IllegalArgumentException | IOException ex) {
                    return null; // The culprit
                }
            });
        }

    }
}
