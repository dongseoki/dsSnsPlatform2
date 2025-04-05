import com.dssns.common.user_activity.UserActivityWish;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {

  @Test
  void testObjectMapper() throws JsonProcessingException {
    // Test the ObjectMapper functionality here
    // For example, you can create an instance of ObjectMapper and use it to serialize/deserialize objects
    // ObjectMapper objectMapper = new ObjectMapper();
    // Your test code goes here
    ObjectMapper objectMapper = new ObjectMapper();
    UserActivityWish userActivityWish = UserActivityWish.builder()
        .eventType("like")
        .targetType("post")
        .targetId(String.valueOf("1"))
        .timestamp(Instant.now().toString())
        .userId(String.valueOf("1")).build();
    String string = objectMapper.writeValueAsString(userActivityWish);
    System.out.println("string = " + string);
  }
}
