package com.pixelatedslice.easyconfig.api.config.node;

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

    interface BaseOriginal<T> {
        T build();
    }

    interface BaseChild<Previous> {
        Previous complete();
    }

    interface FirstStep extends NodeBuilder {

        ContainerFinalStep.Original key(String key);
    }

    interface ValueAbstractStep<T> extends NodeBuilder {
        ValueFinalStep<T> defaultValue(@Nullable T defaultValue);

        ValueFinalStep<T> value(@Nullable T value);

        ValueAbstractStep<T> validator(Validator<T> validator);

        ValueFinalStep<T> serializer(Serializer<T> serializer);

        interface Original<T> extends ValueAbstractStep<T>, BaseOriginal<ValueNode<T>> {

            ValueFinalStep.Original<T> defaultValue(@Nullable T defaultValue);

            ValueFinalStep.Original<T> value(@Nullable T value);

            ValueAbstractStep.Original<T> validator(Validator<T> validator);

            ValueFinalStep.Original<T> serializer(Serializer<T> serializer);
        }

        interface Child<T, ParentNode extends NodeBuilder> extends ValueAbstractStep<T>, BaseChild<ParentNode> {

            ValueFinalStep.Child<T, ParentNode> defaultValue(@Nullable T defaultValue);

            ValueFinalStep.Child<T, ParentNode> value(@Nullable T value);

            ValueAbstractStep.Child<T, ParentNode> validator(Validator<T> validator);

            ValueFinalStep.Child<T, ParentNode> serializer(Serializer<T> serializer);
        }

    }

    interface PredefinedEnv<T extends @Nullable Object> {

        Function<String, @Nullable T> adapter();


        String key();
    }

    interface ValueSafeStep<T> extends ValueAbstractStep<T> {

        EnvAdapterStep<T> env(String env);

        default EnvFinalStep<T> env(PredefinedEnv<T> env) {
            return this.env(env.key()).adapter(env.adapter());
        }

        interface Original<T> extends ValueSafeStep<T>, ValueAbstractStep.Original<T> {

            @Override
            EnvAdapterStep.Original<T> env(String env);
        }

        interface Child<T, ParentNode extends NodeBuilder>
                extends ValueSafeStep<T>, ValueAbstractStep.Child<T, ParentNode> {

            @Override
            EnvAdapterStep.Child<T, ParentNode> env(String env);
        }
    }

    interface EnvAdapterStep<T> extends NodeBuilder {

        EnvFinalStep<T> adapter(Function<String, @Nullable T> adapter);

        EnvAdapterStep<T> validator(Validator<T> validator);

        interface Original<T> extends EnvAdapterStep<T> {

            @Override
            EnvFinalStep.Original<T> adapter(Function<String, @Nullable T> adapter);

            @Override
            EnvAdapterStep.Original<T> validator(Validator<T> validator);
        }

        interface Child<T, Previous extends NodeBuilder> extends EnvAdapterStep<T> {

            @Override
            EnvFinalStep.Child<T, Previous> adapter(Function<String, @Nullable T> adapter);

            @Override
            EnvAdapterStep.Child<T, Previous> validator(Validator<T> validator);
        }
    }

    interface EnvFinalStep<T> extends NodeBuilder {

        EnvFinalStep<T> validator(Validator<T> validator);

        interface Original<T> extends EnvFinalStep<T>, BaseOriginal<EnvNode<T>> {

            @Override
            EnvFinalStep.Original<T> validator(Validator<T> validator);
        }

        interface Child<T, ParentNode extends NodeBuilder> extends EnvFinalStep<T>, BaseChild<ParentNode> {

            @Override
            EnvFinalStep.Child<T, ParentNode> validator(Validator<T> validator);
        }
    }

    interface ValueFinalStep<T> extends ValueAbstractStep<T> {

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

        interface Child<T, ParentBuilder extends NodeBuilder>
                extends ValueFinalStep<T>, ValueAbstractStep.Child<T, ParentBuilder> {
        }
    }

    interface CollectionStep extends NodeBuilder {

        ContainerSafeStep.Child<? extends CollectionStep> append();

        interface Original extends CollectionStep, BaseOriginal<CollectionNode> {

            @Override
            ContainerSafeStep.Child<CollectionStep.Original> append();
        }

        interface Child<ParentNode extends NodeBuilder> extends CollectionStep, BaseChild<ParentNode> {

            @Override
            ContainerSafeStep.Child<CollectionStep.Child<ParentNode>> append();

        }

    }

    interface ContainerSafeStep extends NodeBuilder {

        @CheckReturnValue
        CollectionStep collection();

        @CheckReturnValue
        ContainerSafeStep.Child<? extends NodeBuilder> append(String key);

        interface Original extends ContainerSafeStep, BaseOriginal<ContainerNode> {

            @Override
            CollectionStep.Original collection();

            @Override
            ContainerSafeStep.Child<? extends ContainerSafeStep.Original> append(String key);
        }

        interface Child<Parent extends NodeBuilder> extends ContainerFinalStep, BaseChild<Parent> {
            @Override
            ContainerSafeStep.Child<? extends ContainerSafeStep.Child<Parent>> append(String key);

            @Override
            CollectionStep.Child<Parent> collection();
        }
    }

    interface ContainerFinalStep extends ContainerSafeStep {


        @CheckReturnValue
        <T> ValueSafeStep<T> of(TypeToken<T> token);


        @CheckReturnValue
        default <T> ValueSafeStep<T> of(Class<T> clazz) {
            return this.of(TypeToken.of(clazz));
        }


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
