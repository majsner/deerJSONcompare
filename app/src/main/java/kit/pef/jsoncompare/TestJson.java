package kit.pef.jsoncompare;

import java.io.InputStream;
import java.util.List;

public interface TestJson {

    String getName();

    List<Deer> parsePublicTimeline(InputStream inputStream);

}
