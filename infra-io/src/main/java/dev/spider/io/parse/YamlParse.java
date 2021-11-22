package dev.spider.io.parse;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author lgc
 */
public class YamlParse {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(YamlParse.class.getName());
        Yaml yaml = new Yaml();
        Map<String, Object> cfg = null;
        try (InputStream is = YamlParse.class.getClassLoader().getResourceAsStream("yaml.yaml")) {
            cfg = yaml.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Object> val : cfg.entrySet()) {
            log.info(val.getKey() + ":" + val.getValue());
        }
    }
}
