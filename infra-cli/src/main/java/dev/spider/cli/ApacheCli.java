package dev.spider.cli;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author lgc
 * @apiNote java cli use apache-cli
 */
public class ApacheCli {
    static final String greeting = "Hello,motherfucker。。。";
    static final String timeFormatSuffix = "The fucking time is: ";
    static final String LAUNCH_BANNER = """
                +-------------------------------------------+
                |                                           |
                |    LGC CLI LAUNCHING, POWER BY JAVA ENV   |
                |                                           |
                +-------------------------------------------+
            """;
    static final String infoPrefix = """
            [INFO] #
            """;
    static final String DebugPrefix = """
            [DEBUG] #
            """;

    public static void main(String[] args) {
        Logger log = LogManager.getLogger(ApacheCli.class);

        Options options = new Options();
        Option test = Option.builder().argName("随便").option("T").longOpt("Test").numberOfArgs(2).optionalArg(false).type(String.class).desc("Test Builder Mode").build();
        Option help = Option.builder().option("h").longOpt("help").optionalArg(true).desc("print usage ").build();
        Option time = Option.builder().argName("time").option("t").longOpt("time").optionalArg(true).type(String.class).desc("format time use your pattern").build();
        Option debug = Option.builder().option("x").longOpt("debug").optionalArg(true).desc("turn debug mode").build();
        options.addOption(test);
        options.addOption(help);
        options.addOption(time);
        options.addOption(debug);
        DefaultParser dp = new DefaultParser();
        try {
            CommandLine cl = dp.parse(options, args);
            log.info(LAUNCH_BANNER);
            if (cl.hasOption("x")) {
                log.atLevel(Level.DEBUG);
                log.debug("DEBUG MODE LAUNCH.....");
            }

            //help
            if (cl.hasOption("h")) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp(greeting, "", options, "");
                return;
            }

            if (cl.hasOption("t")) {
                String format = cl.getOptionValue("t");
                if (Objects.isNull(format)) {
                    log.info("{}:{}", timeFormatSuffix, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } else {
                    log.info("{}:{}", timeFormatSuffix, new SimpleDateFormat(format).format(new Date()));
                }
            } else {
                log.debug("nb");
            }
        } catch (MissingArgumentException missingArgumentException) {
            Option option = missingArgumentException.getOption();
            String opt = option.getLongOpt();
            log.warn("WARING option:{}", opt);
        } catch (ParseException e) {
            log.warn("Parse FAIL AT:{}", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
