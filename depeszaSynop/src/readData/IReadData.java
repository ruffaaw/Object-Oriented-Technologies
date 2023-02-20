package readData;

import java.io.IOException;
import java.nio.file.Path;

public interface IReadData {
    String acquireRemoteData(String address) throws IOException;
}
