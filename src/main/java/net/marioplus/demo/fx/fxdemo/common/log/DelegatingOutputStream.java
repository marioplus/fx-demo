package net.marioplus.demo.fx.fxdemo.common.log;

import lombok.extern.slf4j.Slf4j;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * DelegatingOutputStream
 *
 * @author marioplus12
 * @since 2021-09-21
 */
@Slf4j
public class DelegatingOutputStream extends FilterOutputStream {

    public DelegatingOutputStream() {
        super(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });
//        log.debug("DelegatingOutputStream -> init");
    }

    void setOutputStream(OutputStream out) {
        this.out = out;
//        log.debug("DelegatingOutputStream -> setOutputStream");
    }
}
