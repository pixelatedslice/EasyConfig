# Easy Config

**Note:** JavaDoc is AI Generated.

## Config File

[Open File](./api-definition/src/main/java/com/pixelatedslice/easyconfig/api/config/ConfigFile.java)
<br>
Intended usage:

```java
import java.util.List;

ConfigFile myConfig = ConfigFile.builder()
        .node("location", builder -> {
            builder.type(Location.class);
            builder.serializer(LocationSerializer.instance()); // Built-in serializers are singletons
            builder.defaultValue(new Location(x, y, z));
        })
        .node("names", builder -> {
            // Example: Map<String, Integer> becomes .type(Map.class, String.class, Integer.class)
            builder.type(
                    List.class,
                    String.class
            );
        })
        .section("identity", builder -> {
            builder.node("name", String.class);
            builder.node("age", Integer.class);
        })
        .build();
```

"`myConfig`" defines the structure of the

## Ideas

## Config Sections

Example YAML File:

```yaml
tln: "top level" # This is a ConfigNode<String>
location: # This is a ConfigSection
  x: 0.0 # ConfigNode<Double>
  y: 0.0 # ConfigNode<Double>
  z: 14.2 # ConfigNode<Double>
names: # ConfigNode<List<String>>
  - "Jeremy"
  - "John"
  - "Angela"
```