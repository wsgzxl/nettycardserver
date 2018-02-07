package netty;


import logic.LogicMain;
import net.Heartbeat;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture; 
import io.netty.channel.ChannelInitializer; 
import io.netty.channel.ChannelOption; 
import io.netty.channel.EventLoopGroup; 
import io.netty.channel.nio.NioEventLoopGroup; 
import io.netty.channel.socket.SocketChannel; 
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.RequestHandlerId;
import dispatcher.HandlerDispatcher;

public class Server{
   
    final Logger logger=LoggerFactory.getLogger(getClass());
   
    public static void main(String[] args) throws Exception {
    	
    	
    	Server server = new Server();
        server.start(9999);
        
    }
    
    public void start(int port)  throws Exception{
    	
    	logger.info("start server!");
    	LogicMain.getInstance();
    
    	//配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                	ch.pipeline().addLast(new TcpEncoder());
                                	ch.pipeline().addLast(new TcpDecoder());
                                	ch.pipeline().addLast(new ServerHandler());
                                	ch.pipeline().addLast("idleStateHandler",new IdleStateHandler(10,10,0));
                                	ch.pipeline().addLast("hearbeat",new Heartbeat());
                                }
                            }).option(ChannelOption.SO_BACKLOG, 1024) //最大客户端连接数为1000
             .childOption(ChannelOption.SO_KEEPALIVE, true)
             .childOption(ChannelOption.TCP_NODELAY,true);
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            logger.info("斗牛服务器已经启动...!");
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
         
        }
        finally {
        	//优雅退出，释放线程池资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}