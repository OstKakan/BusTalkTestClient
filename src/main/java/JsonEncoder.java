import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JsonEncoder implements Encoder.Text<UserMessage> {

    @Override
    public String encode(UserMessage userMessage) throws EncodeException {


        return userMessage.toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
