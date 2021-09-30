package cn.qisee.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
public class RedisDemo  {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valueOp;

    @Resource(name = "jsonRedisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder()
                .sources(RedisDemo.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
        RedisDemo redisDemo = context.getBean(RedisDemo.class);
        redisDemo.doSth();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        return redisTemplate;
    }

    @Bean(name = "jsonRedisTemplate")
    public RedisTemplate<String, Object> jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    public KeyExpireListener keyExpireListener(RedisMessageListenerContainer container) {
        return new KeyExpireListener(container);
    }

    public void doSth() {
        valueOp.set("hello", "world");
        redisTemplate.expire("hello", 2, TimeUnit.SECONDS);
        System.out.println(valueOp.get("hello"));
    }

    class KeyExpireListener extends KeyExpirationEventMessageListener{

        public KeyExpireListener(RedisMessageListenerContainer container){
            super(container);
        }

        @Override
        public void onMessage(Message message, byte[] pattern) {
            System.out.println("expire key" + message.toString());
            System.out.println("expire body" + new String(message.getBody()));
            super.onMessage(message, pattern);
        }


    }
    public void sede() {
        ExportTask task = new ExportTask();
        task.curCount = 10L;
        task.total = 10L;
        task.downloadUrl = "/xxxx/xxx";
        task.status = "process";
        task.uuid = "123123-123";
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            json = om.writerWithDefaultPrettyPrinter().writeValueAsString(task);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        valueOperations.set("key", json);
        String value = (String) valueOperations.get("key");
        try {
            ExportTask exportTask = om.readValue(value, ExportTask.class);
            System.out.println(exportTask);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class ExportTask {

    String uuid;

    String downloadUrl;

    Long curCount;

    Long total;

    String status;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Long getCurCount() {
        return curCount;
    }

    public void setCurCount(Long curCount) {
        this.curCount = curCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExportTask{" +
                "uuid='" + uuid + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", curCount=" + curCount +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}
