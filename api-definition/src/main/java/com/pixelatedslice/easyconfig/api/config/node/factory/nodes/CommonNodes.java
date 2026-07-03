package com.pixelatedslice.easyconfig.api.config.node.factory.nodes;

import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.CommonNodesBoolean;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.character.CommonNodesCharSequence;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.character.CommonNodesCharacter;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.character.CommonNodesString;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesCollection;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesList;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesMap;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesSet;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.filesystem.CommonNodesFile;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.filesystem.CommonNodesPath;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.floating.CommonNodesDouble;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.floating.CommonNodesFloat;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.integral.CommonNodesByte;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.integral.CommonNodesInteger;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.integral.CommonNodesLong;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.integral.CommonNodesShort;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.networking.CommonNodesInetAddress;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.networking.CommonNodesURI;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.networking.CommonNodesURL;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.other.CommonNodesPattern;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.other.CommonNodesUUID;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.precise.CommonNodesBigDecimal;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.precise.CommonNodesBigInteger;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.time.*;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface CommonNodes extends
        CommonNodesBoolean,
        CommonNodesCharacter, CommonNodesCharSequence, CommonNodesString,
        CommonNodesCollection, CommonNodesList, CommonNodesMap, CommonNodesSet,
        CommonNodesFile, CommonNodesPath,
        CommonNodesDouble, CommonNodesFloat,
        CommonNodesByte, CommonNodesInteger, CommonNodesLong, CommonNodesShort,
        CommonNodesInetAddress, CommonNodesURI, CommonNodesURL,
        CommonNodesPattern, CommonNodesUUID,
        CommonNodesBigDecimal, CommonNodesBigInteger,
        CommonNodesDuration, CommonNodesInstant, CommonNodesLocalDateTime, CommonNodesPeriod, CommonNodesZonedDateTime {
    CommonNodes INSTANCE = new CommonNodes() {
    };
}
