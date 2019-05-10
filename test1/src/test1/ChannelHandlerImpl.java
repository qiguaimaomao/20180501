package test1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ChannelHandlerImpl implements ChannelHandler{

	@Override
	public void channelWritable(Channel channel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelReadComplate(Channel channel, ByteBuffer readBuff) throws IOException {
        readBuff.clear();
       SocketChannel socketChannel = (SocketChannel) channel;
		socketChannel.read(readBuff);

        readBuff.flip();
        System.out.println("read"+socketChannel);
        System.out.println("received : " + new String(readBuff.array()));
        
		
	}

}
