package meru.io.watch;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatchService {

    private WatchService watcherService;
    private FileWatchListener fileWatchListener;

    public FileWatchService(Path path) throws IOException {

        watcherService = FileSystems.getDefault()
                                                 .newWatchService();
        path.register(watcherService,
                      ENTRY_CREATE,
                      ENTRY_DELETE,
                      ENTRY_MODIFY);

    }

    public void addFileWatchListener(FileWatchListener fileWatchListener) {
        this.fileWatchListener = fileWatchListener;
    }
    
    public void start() {
        
        for (;;) {
            WatchKey watchKey;
            try {
                watchKey = watcherService.take();
            } catch (InterruptedException x) {
                return;
            }
            
            if (!watchKey.pollEvents().isEmpty()) {
                fileWatchListener.fileModified();
            }
            
/*            
            for (WatchEvent<?> event: watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                
                if (kind == OVERFLOW) {
                    continue;
                }
                
                Path fileName = ((WatchEvent<Path>)event).context();
                System.out.println(fileName);
                
                
                if (!watchKey.reset()) {
                    break;
                }
            } */
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        FileWatchService s = new FileWatchService(new File("c:\\temp").toPath());
        
        s.start();
    }

}
