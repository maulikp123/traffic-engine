package com.conveyal.traffic.graph;

import java.net.URI;
import java.net.URISyntaxException;

import play.Logger;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class StatsPool {
	
	public JedisPool pool;
	public static String ErrorTAG = "Error in StatsPool : ";
	
	public StatsPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setTestOnBorrow(true);
//		this.pool = new JedisPool(config, "localhost");
		
		try {
	        URI redisUri = new URI(System.getenv("REDISCLOUD_URL"));
	        this.pool = new JedisPool(new JedisPoolConfig(),
	                redisUri.getHost(),
	                redisUri.getPort(),
	                Protocol.DEFAULT_TIMEOUT,
	                redisUri.getUserInfo().split(":",2)[1]);
	} catch (URISyntaxException e) {
	        // URI couldn't be parsed.
		Logger.error(ErrorTAG + "Redis URI couldn't be parsed.", e);
	}
	}
}
