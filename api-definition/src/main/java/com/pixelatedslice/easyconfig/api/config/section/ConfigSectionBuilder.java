package com.pixelatedslice.easyconfig.api.config.section;

import com.pixelatedslice.easyconfig.api.config.node.BuilderWithConfigNodes;
import org.jspecify.annotations.NonNull;

public interface ConfigSectionBuilder extends BuilderWithConfigNodes, BuilderWithConfigSections {

    @NonNull ConfigSectionBuilder key(@NonNull String key);

    @NonNull ConfigSectionBuilder parent(@NonNull ConfigSection parent);

    @NonNull ConfigSectionBuilder comments(@NonNull String @NonNull ... comments);

    @NonNull ConfigSection build();
}
