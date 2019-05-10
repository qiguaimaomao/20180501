package test1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectableChannel;

public interface ChannelHandler {
    void channelWritable(Channel channel);
	void channelReadComplate(Channel channel, ByteBuffer readBuff) throws IOException;
}
