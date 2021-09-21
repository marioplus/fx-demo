package net.marioplus.demo.fx.fxdemo.common;

import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * FileChooserBuilder
 *
 * @author marioplus12
 * @since 2021-09-21
 */
@Slf4j
public class FileChooserBuilder {

    /**
     * 标题
     */
    private String title;
    /**
     * 文件开始路径
     */
    private File initialDirectory;

    private Collection<FileChooser.ExtensionFilter> extensionFilters = new ArrayList<>();

    private FileChooserBuilder() {
    }

    public static FileChooserBuilder build() {
        return new FileChooserBuilder();
    }

    public FileChooserBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FileChooserBuilder initialDirectory(String initialDirectory) {
        this.initialDirectory = new File(initialDirectory);
        return this;
    }

    public FileChooserBuilder initialDirectory(StartDirEnum startDir) {
        this.initialDirectory = new File(startDir.path);
        return this;
    }

    public FileChooserBuilder addExtensionFilter(String description, String... extensions) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(description, extensions);
        extensionFilters.add(filter);
        return this;
    }

    public FileChooserBuilder addExtensionFilter(ExtensionFilterEnum filterEnum) {
        extensionFilters.addAll(filterEnum.filters);
        return this;
    }

    public FileChooserBuilder addExtensionFilter(Collection<FileChooser.ExtensionFilter> filters) {
        extensionFilters.addAll(filters);
        return this;
    }

    public FileChooser builder() {
        return builder(null);
    }

    public FileChooser builder(FileChooser fc) {
        if (fc == null) {
            fc = new FileChooser();
        }
        fc.setTitle(title);
        fc.setInitialDirectory(initialDirectory);
        fc.getExtensionFilters().setAll(extensionFilters);
        return fc;
    }

    public enum ExtensionFilterEnum {

        EXCEL(Collections.singletonList(
                new FileChooser.ExtensionFilter("选择excel文件", "*.xlsx", "*.xls")
        ));

        private final List<FileChooser.ExtensionFilter> filters;

        ExtensionFilterEnum(List<FileChooser.ExtensionFilter> filters) {
            this.filters = filters;
        }
    }

    public enum StartDirEnum {
        /**
         * 用户文件夹
         */
        USER_HOME(System.getProperty("user.home")),
        /**
         * 桌面
         */
        DASKTOP(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()),
        ;

        private final String path;

        StartDirEnum(String path) {
            this.path = path;
        }
    }

}
