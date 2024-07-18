import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    static void createDirectories(String path, StringBuilder log) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                log.append("Directory '").append(path).append("' created successfully.\n");
            } else {
                log.append("Failed to create directory '").append(path).append("'.\n");
            }
        } else {
            log.append("Directory '").append(path).append("' already exists.\n");
        }
    }

    @Override
    public String toString() {
        return "GameProgress{" +
            "health=" + health +
            ", weapons=" + weapons +
            ", lvl=" + lvl +
            ", distance=" + distance +
            '}';
    }

    public static void saveGame(String filePath, GameProgress gameProgress) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        }
    }

    public static void zipFiles(String zipFilePath, List<String> filesToZip) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String file : filesToZip) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(new File(file).getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                }
            }
        }
    }
}