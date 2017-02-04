package mobtown.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapperFactory}
 */
public class ObjectMapperFactoryTest {

    private static final LocalDateTime TIME = LocalDateTime.of(1955, 11, 12, 22, 4);
    private static final String JSON = "{\"time\":\"1955-11-12T22:04:00\"}";
    private static ObjectMapper mapper;

    @BeforeClass
    public static void beforeClass() {
        mapper = new ObjectMapperFactory().provide();
    }

    @Test
    public void it_serializes_java_time_types_as_ISO8601() throws JsonProcessingException {
        final String json = mapper.writeValueAsString(new TimeHolder(TIME));
        assertThat(json).isEqualTo(JSON);
    }

    @Test
    public void it_deserializes_ISO8601_strings() throws IOException {
        final TimeHolder holder = mapper.readValue(JSON, TimeHolder.class);
        assertThat(holder.time).isEqualTo(TIME);
    }

    private static class TimeHolder {

        public final LocalDateTime time;

        @JsonCreator
        private TimeHolder(@JsonProperty("time") final LocalDateTime time) {
            this.time = time;
        }
    }
}
