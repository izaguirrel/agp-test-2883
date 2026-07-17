package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.springframework.web.client.RestTemplate;
import io.netty.channel.Channel;
import com.google.protobuf.Message;

/**
 * Sample application for AGP testing.
 *
 * <p>This class intentionally uses outdated dependencies with known vulnerabilities
 * to trigger AGP recommendations. This is for testing purposes only.
 *
 * <p>See: https://sonatype.atlassian.net/browse/GUIDE-2883
 */
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("AGP Test Application starting...");

        // Use Jackson (CVE-2022-42003, CVE-2022-42004)
        ObjectMapper mapper = new ObjectMapper();

        // Use Guava (multiple CVEs in 31.x)
        if (Strings.isNullOrEmpty("test")) {
            logger.debug("Guava strings check");
        }

        // Use Commons Lang3 (CVEs in older versions)
        String padded = StringUtils.leftPad("test", 10);
        logger.debug("Padded: {}", padded);

        // Use SnakeYAML (CVE-2022-1471)
        Yaml yaml = new Yaml();

        // Use Spring Web (CVE-2022-22965 and others)
        RestTemplate restTemplate = new RestTemplate();

        // Use Netty (CVE-2021-37136 and others)
        // Channel class reference for compilation

        // Use Protobuf (CVE-2022-3171 and others)
        // Message class reference for compilation

        logger.info("AGP Test Application completed.");
    }
}
