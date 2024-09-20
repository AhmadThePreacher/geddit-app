package geddit.buzl.uk;

import android.content.Context;
import java.io.File;
import java.util.Arrays;

public class MainActivity extends BridgeActivity {}

class CacheUtils {
    private static final long MAX_CACHE_SIZE = 100 * 1024 * 1024; // 100MB

    public static void clearCache(Context context) {
        try {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.isDirectory()) {
                deleteDir(cacheDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void manageCacheSize(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null && cacheDir.isDirectory()) {
            long cacheSize = getDirSize(cacheDir);
            while (cacheSize > MAX_CACHE_SIZE) {
                File oldestFile = getOldestFile(cacheDir);
                if (oldestFile != null && oldestFile.delete()) {
                    cacheSize = getDirSize(cacheDir);
                } else {
                    break;
                }
            }
        }
    }

    private static long getDirSize(File dir) {
        long size = 0;
        if (dir != null && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getDirSize(file);
                }
            }
        }
        return size;
    }

    private static File getOldestFile(File dir) {
        File oldestFile = null;
        if (dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                Arrays.sort(files, (f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
                oldestFile = files[0];
            }
        }
        return oldestFile;
    }

    private static void deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
    }
}