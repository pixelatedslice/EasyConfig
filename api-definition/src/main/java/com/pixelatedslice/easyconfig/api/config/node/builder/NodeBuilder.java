package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.google.common.reflect.TypeToken;
import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@NullMarked
public interface NodeBuilder {

    @NullMarked
    interface BaseOriginal<T> {
        T build();
    }

    @NullMarked
    interface BaseChild<Previous> {
        Previous complete();
    }

    @NullMarked
    interface FirstStep extends NodeBuilder {

        ContainerFinalStep.Original key(String key);
    }

    @NullMarked
    interface ValueAbstractStep<T> extends NodeBuilder {
        ValueFinalStep<T> defaultValue(@Nullable T defaultValue);

        ValueFinalStep<T> value(@Nullable T value);

        ValueAbstractStep<T> validator(Validator<T> validator);

        ValueFinalStep<T> serializer(Serializer<T> serializer);

        @NullMarked
        interface Original<T> extends ValueAbstractStep<T>, BaseOriginal<ValueNode<T>> {

            ValueFinalStep.Original<T> defaultValue(@Nullable T defaultValue);

            ValueFinalStep.Original<T> value(@Nullable T value);

            ValueAbstractStep.Original<T> validator(Validator<T> validator);

            ValueFinalStep.Original<T> serializer(Serializer<T> serializer);
        }

        @NullMarked
        interface Child<T, ParentNode extends NodeBuilder> extends ValueAbstractStep<T>, BaseChild<ParentNode> {

            ValueFinalStep.Child<T, ParentNode> defaultValue(@Nullable T defaultValue);

            ValueFinalStep.Child<T, ParentNode> value(@Nullable T value);

            ValueAbstractStep.Child<T, ParentNode> validator(Validator<T> validator);

            ValueFinalStep.Child<T, ParentNode> serializer(Serializer<T> serializer);
        }

    }

    @NullMarked
    interface PredefinedEnv<T extends @Nullable Object> {

        Function<String, @Nullable T> adapter();


        String key();
    }

    @NullMarked
    interface ValueSafeStep<T> extends ValueAbstractStep<T> {

        EnvAdapterStep<T> env(String env);

        default EnvFinalStep<T> env(PredefinedEnv<T> env) {
            return this.env(env.key()).adapter(env.adapter());
        }

        @NullMarked
        interface Original<T> extends ValueSafeStep<T>, ValueAbstractStep.Original<T> {

            @Override
            EnvAdapterStep.Original<T> env(String env);
        }

        @NullMarked
        interface Child<T, ParentNode extends NodeBuilder>
                extends ValueSafeStep<T>, ValueAbstractStep.Child<T, ParentNode> {

            @Override
            EnvAdapterStep.Child<T, ParentNode> env(String env);
        }
    }

    @NullMarked
    interface EnvAdapterStep<T> extends NodeBuilder {

        EnvFinalStep<T> adapter(@Nullable Function<String, @Nullable T> adapter);

        EnvAdapterStep<T> validator(@Nullable Validator<T> validator);

        @NullMarked
        interface Original<T> extends EnvAdapterStep<T> {

            @Override
            EnvFinalStep.Original<T> adapter(@Nullable Function<String, @Nullable T> adapter);

            @Override
            EnvAdapterStep.Original<T> validator(@Nullable Validator<T> validator);
        }

        @NullMarked
        interface Child<T, Previous extends NodeBuilder> extends EnvAdapterStep<T> {

            @Override
            EnvFinalStep.Child<T, Previous> adapter(@Nullable Function<String, @Nullable T> adapter);

            @Override
            EnvAdapterStep.Child<T, Previous> validator(@Nullable Validator<T> validator);
        }
    }

    @NullMarked
    interface EnvFinalStep<T> extends NodeBuilder {

        EnvFinalStep<T> validator(@Nullable Validator<T> validator);

        @NullMarked
        interface Original<T> extends EnvFinalStep<T>, BaseOriginal<EnvNode<T>> {

            @Override
            EnvFinalStep.Original<T> validator(@Nullable Validator<T> validator);
        }

        @NullMarked
        interface Child<T, ParentNode extends NodeBuilder> extends EnvFinalStep<T>, BaseChild<ParentNode> {

            @Override
            EnvFinalStep.Child<T, ParentNode> validator(@Nullable Validator<T> validator);
        }
    }

    @NullMarked
    interface ValueFinalStep<T> extends ValueAbstractStep<T> {

        @NullMarked
        interface Original<T> extends ValueFinalStep<T>, ValueAbstractStep.Original<T> {

            @Override
            ValueFinalStep.Original<T> defaultValue(@Nullable T defaultValue);

            @Override
            ValueFinalStep.Original<T> value(@Nullable T value);

            @Override
            ValueFinalStep.Original<T> validator(Validator<T> validator);

            @Override
            ValueFinalStep.Original<T> serializer(Serializer<T> serializer);
        }

        @NullMarked
        interface Child<T, ParentBuilder extends NodeBuilder>
                extends ValueFinalStep<T>, ValueAbstractStep.Child<T, ParentBuilder> {
        }
    }

    @NullMarked
    interface CollectionStep extends NodeBuilder {

        ContainerSafeStep.Child<? extends CollectionStep> append();

        @NullMarked
        interface Original extends CollectionStep, BaseOriginal<CollectionNode> {

            @Override
            ContainerSafeStep.Child<CollectionStep.Original> append();
        }

        @NullMarked
        interface Child<ParentNode extends NodeBuilder> extends CollectionStep, BaseChild<ParentNode> {

            @Override
            ContainerSafeStep.Child<CollectionStep.Child<ParentNode>> append();

        }

    }

    @NullMarked
    interface ContainerSafeStep extends NodeBuilder {

        @CheckReturnValue
        CollectionStep collection();

        @CheckReturnValue
        ContainerSafeStep.Child<? extends NodeBuilder> append(String key);

        @NullMarked
        interface Original extends ContainerSafeStep, BaseOriginal<ContainerNode> {

            @Override
            CollectionStep.Original collection();

            @Override
            ContainerSafeStep.Child<? extends ContainerSafeStep.Original> append(String key);
        }

        @NullMarked
        interface Child<Parent extends NodeBuilder> extends ContainerFinalStep, BaseChild<Parent> {
            @Override
            ContainerSafeStep.Child<? extends ContainerSafeStep.Child<Parent>> append(String key);

            @Override
            CollectionStep.Child<Parent> collection();
        }
    }

    @NullMarked
    interface ContainerFinalStep extends ContainerSafeStep {


        @CheckReturnValue
        <T> ValueSafeStep<T> of(TypeToken<T> token);


        @CheckReturnValue
        default <T> ValueSafeStep<T> of(Class<T> clazz) {
            return this.of(TypeToken.of(clazz));
        }

        @NullMarked
        interface Original extends ContainerFinalStep, ContainerSafeStep.Original {

            @Override
            ContainerFinalStep.Child<ContainerFinalStep.Original> append(String key);

            @Override
            <T> ValueSafeStep.Original<T> of(TypeToken<T> token);

            @Override
            default <T> ValueSafeStep.Original<T> of(Class<T> clazz) {
                return this.of(TypeToken.of(clazz));
            }
        }

        @NullMarked
        interface Child<Parent extends NodeBuilder> extends ContainerFinalStep, ContainerSafeStep.Child<Parent> {
            @Override
            ContainerFinalStep.Child<ContainerFinalStep.Child<Parent>> append(String key);

            @Override
            <T> ValueSafeStep.Child<T, Parent> of(TypeToken<T> token);

            @Override
            default <T> ValueSafeStep.Child<T, Parent> of(Class<T> clazz) {
                return this.of(TypeToken.of(clazz));
            }
        }
    }
}