package com.wx;

import com.wx.wxfx.entity.Transmission;
import com.wx.wxfx.handler.AuthHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

public class AuthHandlerTest{
    @Test
    public void testAuthHandler() {
        EmbeddedChannel channel = new EmbeddedChannel(new AuthHandler());
        Transmission transmission = Transmission.newBuilder().setID(1).build();
        channel.writeInbound(transmission);
        channel.finish();
    }
}
