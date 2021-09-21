package net.marioplus.demo.fx.fxdemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.github.rholder.retry.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import net.marioplus.demo.fx.fxdemo.common.FileChooserBuilder;
import net.marioplus.demo.fx.fxdemo.model.User;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author marioplus
 */
@Slf4j
public class HelloController {

    @FXML
    public TextArea taLog;

    public static HelloController INSTANCE;

    public HelloController() {
        INSTANCE = this;
    }

    @FXML
    private Label welcomeText;
    @FXML
    private VBox root;

    @FXML
    protected void onHelloButtonClick() {
        FileChooser fc = FileChooserBuilder.build()
                .title("选择excel文件")
                .initialDirectory(FileChooserBuilder.StartDirEnum.DASKTOP)
                .addExtensionFilter(FileChooserBuilder.ExtensionFilterEnum.EXCEL)
                .builder();

        File file = fc.showOpenDialog(root.getScene().getWindow());
        if (file == null || !file.exists()) {
            log.error("选择文件失败");
            welcomeText.setText("选择文件失败");
        } else {
            welcomeText.setText(file.getAbsolutePath());
            log.info(file.getAbsolutePath());
            loadExcel(file);
        }

        Platform.runLater(this::retry);
    }

    public void loadExcel(File file) {
        if (file == null || !file.exists()) {
            log.error("文件不存在");
            return;
        }
        List<Object> list = EasyExcel.read(file, User.class, new AnalysisEventListener<User>() {

                    @Override
                    public void invoke(User data, AnalysisContext context) {
                        log.debug("开始文件读取：{}", file.getName());
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        log.debug("结束文件读取：{}", file.getName());
                    }
                })
                .sheet()
                .doReadSync();
        list.forEach(u -> log.info(u.toString()));
    }

    public Integer retry() {
        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfResult(res -> res == 1)
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            return retryer.call(() -> {
                log.info("sdfsdfsdf");
                return 1;
            });
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
        return null;
    }
}
