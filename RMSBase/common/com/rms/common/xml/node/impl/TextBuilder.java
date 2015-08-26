package com.rms.common.xml.node.impl;

import com.rms.common.xml.node.TextNode;

public interface TextBuilder extends NodeBuilder<TextBuilder, TextNode> {

	TextBuilder content(String content);
}
