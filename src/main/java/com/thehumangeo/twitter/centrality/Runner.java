package com.thehumangeo.twitter.centrality;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by bparrish on 3/21/15.
 */
public class Runner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public Runner() {

    }

    @Override
    public void run() {
        try {
            new TwitterAPIRequestor();
        } catch (IOException e) {
            logger.error("unable to connect twitter api requestor", e);
        }
    }

    public static void main(String... args) {
        Runner runner = new Runner();

        runner.run();
    }
}
