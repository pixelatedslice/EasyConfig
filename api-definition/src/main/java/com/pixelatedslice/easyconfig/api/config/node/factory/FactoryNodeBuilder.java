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

    interface KeyStep<Next extends FactoryNodeBuilder> extends FactoryNodeBuilder {
        Next key(String key);

        interface Value<T> extends FactoryNodeBuilder, KeyStep<ValueStep.Original<T>> {
        }

        interface Env<T> extends FactoryNodeBuilder, KeyStep<EnvStep.VariableStep<T>> {
        }

        interface Container extends FactoryNodeBuilder, KeyStep<GroupStep.Container> {
        }

        interface Collection extends FactoryNodeBuilder, KeyStep<GroupStep.Collection> {
        }
    }

    interface ParentStep<Next extends FactoryNodeBuilder> extends FactoryNodeBuilder {
        Next parent(Node parent);
    }

    interface ValueStep<Next extends ValueStep<Next, T>, T> extends FactoryNodeBuilder {
        Next defaultValue(@Nullable T defaultValue);

        Next value(@Nullable T value);

        Next validator(Validator<T> validator);

        Next serializer(Serializer<T> serializer);

        interface Original<T> extends FactoryNodeBuilder, ValueStep<Buildable<T>, T>, ParentStep<Original<T>> {
        }

        interface Buildable<T>
                extends FactoryNodeBuilder, ValueStep<Buildable<T>, T>, ParentStep<Buildable<T>>,
                BuildStep<ValueNode<T>> {
        }
    }

    interface EnvStep<Next extends EnvStep<Next, T>, T> extends FactoryNodeBuilder {
        interface VariableStep<T> extends FactoryNodeBuilder, ParentStep<VariableStep<T>> {
            AdapterValidatorStep<T> variable(String variable);
        }

        interface AdapterValidatorStep<T> extends FactoryNodeBuilder, ParentStep<AdapterValidatorStep<T>>,
                BuildStep<EnvNode<T>> {
            AdapterValidatorStep<T> adapter(Function<String, @Nullable T> adapter);

            AdapterValidatorStep<T> validator(Validator<T> validator);
        }
    }

    interface GroupStep<Next extends FactoryNodeBuilder, NodeType extends Node> extends FactoryNodeBuilder {
        Next children(FactoryNodeBuilder... nodes);

        Next children(java.util.Collection<FactoryNodeBuilder> nodes);

        interface Original<NodeType extends Node>
                extends FactoryNodeBuilder, GroupStep<Buildable<NodeType>, NodeType>, ParentStep<Original<NodeType>> {
        }

        interface Buildable<NodeType extends Node>
                extends FactoryNodeBuilder, BuildStep<NodeType>, ParentStep<Buildable<NodeType>> {
        }

        interface Container
                extends FactoryNodeBuilder, GroupStep<Container.Buildable, ContainerNode>,
                ParentStep<Container> {
            interface Buildable extends GroupStep.Buildable<CollectionNode> {
            }
        }

        interface Collection
                extends FactoryNodeBuilder, GroupStep<Collection.Buildable, CollectionNode>,
                ParentStep<Collection> {
            interface Buildable extends GroupStep.Buildable<CollectionNode> {
            }
        }
    }

    interface BuildStep<NodeType extends Node> extends FactoryNodeBuilder {
        NodeType build();
    }
}
