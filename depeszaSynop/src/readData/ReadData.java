package readData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReadData implements IReadData {

    public String acquireRemoteData(String address) throws IOException {
        URL link = new URL(address);
        try (InputStream inputStream = link.openStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
