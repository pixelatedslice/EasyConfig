package com.pixelatedslice.easyconfig.api.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilder {
    @NullMarked
    interface FirstStep extends FactoryNodeBuilder {
        <T> KeyStep.Value<T> value();

        <T> KeyStep.Env<T> env();

        KeyStep.Container container();

        KeyStep.Collection collection();
    }

    @NullMarked
    interface KeyStep<Next extends FactoryNodeBuilder> extends FactoryNodeBuilder {
        Next key(String key);

        @NullMarked
        interface Value<T> extends FactoryNodeBuilder, KeyStep<ValueStep.FirstStep<T>> {
        }

        @NullMarked
        interface Env<T> extends FactoryNodeBuilder, KeyStep<EnvStep.VariableStep<T>> {
        }

        @NullMarked
        interface Container extends FactoryNodeBuilder, KeyStep<GroupStep.Container> {
        }

        @NullMarked
        interface Collection extends FactoryNodeBuilder, KeyStep<GroupStep.Collection> {
        }
    }

    @NullMarked
    interface ParentStep<Next extends FactoryNodeBuilder> extends FactoryNodeBuilder {
        Next parent(@Nullable Node parent);
    }

    @NullMarked
    interface ValueStep<T>
            extends FactoryNodeBuilder, ParentStep<ValueStep<T>> {

        @NullMarked
        interface CurrentValueStep<T> extends ValueStep<T> {
            DefaultValueAndExtrasStep<T> value(@Nullable T value);
        }

        @NullMarked
        interface CurrentDefaultValueStep<T> extends ValueStep<T> {
            ValueAndExtrasStep<T> defaultValue(@Nullable T defaultValue);
        }

        @NullMarked
        interface FirstStep<T> extends ValueStep<T>, CurrentValueStep<T>,
                CurrentDefaultValueStep<T> {
        }

        @NullMarked
        interface DefaultValueAndExtrasStep<T>
                extends ValueStep<T>, CurrentDefaultValueStep<T>,
                ExtrasStep<T> {
        }

        @NullMarked
        interface ValueAndExtrasStep<T>
                extends ValueStep<T>, CurrentValueStep<T>, ExtrasStep<T> {
        }

        @NullMarked
        interface ExtrasStep<T> extends FactoryNodeBuilder, BuildStep<ValueNode<T>> {
            ExtrasStep<T> validator(@Nullable Validator<T> validator);

            ExtrasStep<T> serializer(@Nullable Serializer<T> serializer);
        }
    }

    @NullMarked
    interface EnvStep<Next extends EnvStep<Next, T>, T> extends FactoryNodeBuilder {

        @NullMarked
        interface VariableStep<T> extends FactoryNodeBuilder, ParentStep<VariableStep<T>> {
            AdapterValidatorStep<T> variable(String variable);
        }

        @NullMarked
        interface AdapterValidatorStep<T> extends FactoryNodeBuilder, ParentStep<AdapterValidatorStep<T>>,
                BuildStep<EnvNode<T>> {
            AdapterValidatorStep<T> adapter(@Nullable Function<String, @Nullable T> adapter);

            AdapterValidatorStep<T> validator(@Nullable Validator<T> validator);
        }
    }

    @NullMarked
    interface GroupStep<Next extends FactoryNodeBuilder, NodeType extends Node> extends FactoryNodeBuilder {
        Next children(@Nullable FactoryNodeBuilder @Nullable ... nodes);

        Next children(java.util.@Nullable Collection<FactoryNodeBuilder> nodes);

        @NullMarked
        interface Original<NodeType extends Node>
                extends FactoryNodeBuilder, GroupStep<Buildable<NodeType>, NodeType>, ParentStep<Original<NodeType>> {
        }

        @NullMarked
        interface Buildable<NodeType extends Node>
                extends FactoryNodeBuilder, BuildStep<NodeType>, ParentStep<Buildable<NodeType>> {
        }

        @NullMarked
        interface Container
                extends FactoryNodeBuilder, GroupStep<Container.Buildable, ContainerNode>,
                ParentStep<Container> {

            @NullMarked
            interface Buildable extends GroupStep.Buildable<ContainerNode> {
            }
        }

        @NullMarked
        interface Collection
                extends FactoryNodeBuilder, GroupStep<Collection.Buildable, CollectionNode>,
                ParentStep<Collection> {

            @NullMarked
            interface Buildable extends GroupStep.Buildable<CollectionNode> {
            }
        }
    }

    @NullMarked
    interface BuildStep<NodeType extends Node> extends FactoryNodeBuilder {
        NodeType build();
    }
}
