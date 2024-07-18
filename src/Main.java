import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder log = new StringBuilder();
        List<String> filesToZip = new ArrayList<>();

        GameProgress gameProgress1 = new GameProgress(100, 2, 1, 0.0);
        GameProgress gameProgress2 = new GameProgress(50, 1, 2, 10.5);
        GameProgress gameProgress3 = new GameProgress(200, 3, 3, 20.0);

        String saveDir = "Games/GunRunner/savegames";
        GameProgress.createDirectories(saveDir, log);

        String zipFilePath = "Games/GunRunner/savegames/zip.zip";

        GameProgress.saveGame(saveDir + "save1.dat", gameProgress1);
        GameProgress.saveGame(saveDir + "save2.dat", gameProgress2);
        GameProgress.saveGame(saveDir + "save3.dat", gameProgress3);

        filesToZip.add(saveDir + "save1.dat");
        filesToZip.add(saveDir + "save2.dat");
        filesToZip.add(saveDir + "save3.dat");

        GameProgress.zipFiles(zipFilePath, filesToZip);

        for (String file : filesToZip) {
            File saveFile = new File(file);
            if (saveFile.exists()) {
                saveFile.delete();
            }
        }
    }
}