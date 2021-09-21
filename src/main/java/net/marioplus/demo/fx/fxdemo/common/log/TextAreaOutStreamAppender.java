package net.marioplus.demo.fx.fxdemo.common.log;

import ch.qos.logback.core.OutputStreamAppender;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;

/**
 * LogStreamAppender
 *
 * @author marioplus12
 * @since 2021-09-21
 */
@Slf4j
public class TextAreaOutStreamAppender<E> extends OutputStreamAppender<E> {

    private static final DelegatingOutputStream DELEGATING_OUTPUT_STREAM = new DelegatingOutputStream();

    @Override
    public void start() {
        setOutputStream(DELEGATING_OUTPUT_STREAM);
//        log.debug("TextAreaOutStreamAppender -> start");
        super.start();
    }

    public static void setStaticOutputStream(OutputStream outputStream) {
        DELEGATING_OUTPUT_STREAM.setOutputStream(outputStream);
//        log.debug("TextAreaOutStreamAppender -> setStaticOutputStream");
    }
}
