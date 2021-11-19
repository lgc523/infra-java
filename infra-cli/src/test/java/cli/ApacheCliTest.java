package cli;

import dev.spider.cli.ApacheCli;
import org.junit.Test;

/**
 * @author lgc
 */
public class ApacheCliTest {

    @Test
    public void testApacheCli() throws Exception {
        String args[] = {"-h"};
        ApacheCli.main(args);
    }

    @Test
    public void testApacheCliWithTParam() throws Exception {
        String args[] = {"-t yyyy-MM-dd HH:mm:ss"};
        String args1[] = {"-t"};
        ApacheCli.main(args);
    }

    @Test
    public void testApacheCliDebugMode() throws Exception {
        String args[] = {"-x", "-t yyyy-MM-dd HH:mm:ss"};
        ApacheCli.main(args);
    }
}
