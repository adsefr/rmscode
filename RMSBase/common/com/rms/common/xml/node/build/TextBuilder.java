package com.rms.common.xml.node.build;

import com.rms.common.xml.node.TextNode;

public interface TextBuilder extends NodeBuilder<TextBuilder, TextNode> {

	TextBuilder content(String content);
}
