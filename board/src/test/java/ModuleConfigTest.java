import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dssns.board.BoardApplication;
import com.dssns.common.event.NotificationEvent;
import com.dssns.common.event.enums.EventSourceType;
import com.dssns.common.event.enums.EventType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BoardApplication.class)
class ModuleConfigTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void instantSerializationSuccessTest() throws Exception {
    // given
    NotificationEvent event = NotificationEvent.builder()
        .eventType(EventType.COMMENT)
        .receiverUserId(1L)
        .eventUserId(2L)
        .eventSourceId(3L)
        .eventSourceType(EventSourceType.POST)
        .message("Test message")
        .createdAt(Instant.now())
        .build();

    // when
    String json = objectMapper.writeValueAsString(event);

    // then
    assertNotNull(json);
    assertTrue(json.contains("\"createdAt\""));
    System.out.println("json = " + json);
  }
}