package dev.spider.cli;

import org.apache.commons.cli.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
import static java.util.logging.Level.*;

/**
 * java cli use apache-cli
 *
 * @author lgc
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

    /**
     * main
     *
     * @param args argLine
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger(ApacheCli.class.getName());
        Options options = new Options();
        Option help = Option.builder().option("h").longOpt("help").optionalArg(true).desc("print usage ").build();
        Option debug = Option.builder().option("x").longOpt("debug").optionalArg(true).desc("turn debug mode").build();
        Option time = Option.builder().option("f").longOpt("formatTime").optionalArg(true).desc("format time use your pattern").build();
        options.addOption(help);
        options.addOption(debug);
        options.addOption(time);
        CommandLineParser dp = new GnuParser();
        Level globalLevel = INFO;
        try {
            CommandLine cl = dp.parse(options, args);
            log.info("\n" + LAUNCH_BANNER);
            if (cl.hasOption("x")) {
                globalLevel = WARNING;
                log.log(globalLevel, "DEBUG MODE LAUNCH.....");
                System.getProperties().list(out);
                log.log(globalLevel, "-- listing properties end --");
            }

            //help
            if (cl.hasOption("h")) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp(greeting, "", options, "");
                return;
            }

            // format time
            if (cl.hasOption("f")) {
                String format = cl.getOptionValue("f");
                String pattern = "yyyy-MM-dd HH:mm:ss";
                if (Objects.nonNull(format)) {
                    pattern = format;
                }
                String msg = timeFormatSuffix + new SimpleDateFormat(pattern).format(new Date());
                log.log(globalLevel, msg);
            }
        } catch (MissingArgumentException missingArgumentException) {
            Option option = missingArgumentException.getOption();
            String opt = option.getLongOpt();
            log.log(globalLevel, "WARING option:{}", opt);
        } catch (ParseException e) {
            log.log(globalLevel, "Parse FAIL AT:{}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
