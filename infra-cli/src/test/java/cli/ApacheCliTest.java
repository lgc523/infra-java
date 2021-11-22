package cli;

import dev.spider.cli.ApacheCli;
import org.junit.Test;

/**
 * @author lgc
 */
public class ApacheCliTest {

    @Test
    public void apacheCliTest() throws Exception {
        String args[] = {"-h"};
        ApacheCli.main(args);
    }

    @Test
    public void apacheCliWithTParamTest() throws Exception {
        String args[] = {"-f yyyy-MM-dd HH:mm:ss"};
        String args1[] = {"-f"};
        ApacheCli.main(args);
    }

    @Test
    public void apacheCliDebugModeTest() throws Exception {
        String args[] = {"-x", "-f yyyy-MM-dd HH:mm:ss"};
        ApacheCli.main(args);
    }
}
