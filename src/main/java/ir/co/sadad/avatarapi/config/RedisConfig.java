package ir.co.sadad.avatarapi.config;

import ir.co.sadad.avatarapi.common.cache.CacheAspectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {

    //    @Autowired
//    private RedisClusterConfig clusterProperties;
//
//
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master(clusterProperties.getMaster());
//        sentinelConfig.setSentinels(clusterProperties.getRedisNodes());
//        if (!clusterProperties.getPassword().isEmpty()) sentinelConfig.setPassword(clusterProperties.getPassword());
//        return new LettuceConnectionFactory(sentinelConfig);
//    }
//
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {

        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);


        return new ReactiveRedisTemplate<String, Object>(reactiveRedisConnectionFactory,
                RedisSerializationContext.newSerializationContext(serializer)
                        .key(new GenericToStringSerializer(String.class))
                        .hashKey(new GenericToStringSerializer<>(String.class))
                        .value(serializer)
                        .hashValue(serializer)
                        .build());
    }

    @Bean
    public CacheAspectUtils aspectUtils() {
        return new CacheAspectUtils();
    }

}
