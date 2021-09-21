package net.marioplus.demo.fx.fxdemo.common.log;

import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;

/**
 * TextAreaOutputStream
 *
 * @author marioplus12
 * @since 2021-09-21
 */
@Slf4j
public class TextAreaOutputStream extends OutputStream {

    private TextArea textArea;

    public TextAreaOutputStream(TextArea textArea) {
        this.textArea = textArea;

//        log.debug("TextAreaOutputStream -> init"); //OK called
    }

    @Override
    public void write(int b) {
        textArea.appendText(String.valueOf((char) b));

//        log.debug("TextAreaOutputStream -> write");
    }
}
