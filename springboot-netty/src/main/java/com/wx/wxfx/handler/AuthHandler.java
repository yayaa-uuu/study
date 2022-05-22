package com.wx.wxfx.handler;

import com.wx.wxfx.entity.Transmission;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author Bao
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class AuthHandler extends SimpleChannelInboundHandler<Transmission> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Transmission msg) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("auth" + msg);
        System.out.println(channel);
        if (msg.getType() != Transmission.MessageType.TYPE_AUTHENTICATE) {
            channel.close();
        } else {
            //认证
            auth();
            ChannelPipeline pipeline = ctx.pipeline();
            if (pipeline.get(this.getClass()) != null) {
                pipeline.remove(this);
                pipeline.remove("auth-timeout");
                pipeline.remove("auth-disconnect");
            } else {
                log.info("auth {} has been removed", this);
            }
        }
    }

    private void auth() {

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.error("AuthHandler has been removed");
    }
}
