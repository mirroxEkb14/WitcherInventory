package service.output;

import inventory.Backpack;
import model.categories.BackpackCategory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static service.logger.LoggerHandler.getLogger;

/**
 * The class contains methods to write/read files where
 * the items from Backpack are contained
 */
public final class FileService {

    private static final String backpackPath = "src/main/java/service/output/backpack_save.csv";

    private static final Logger log =
            getLogger(FileService.class.getSimpleName()).orElseThrow(() -> new IllegalArgumentException("class.getName() not passed"));

    // write Backpack items to a file
    public static void saveBackpack() {
        StringBuilder sb = new StringBuilder("Backpack - the left side of the inventory\n");
        Map<BackpackCategory, List<String>> backpackMap = Backpack.getInstance().getBackpack();
        for (BackpackCategory item : backpackMap.keySet().stream().toList()) {
            // "WEAPON: item1;item2
            //  ARMOR: item1;item2"
            sb.append(String.format("%s: ", item.toString()));
            sb.append(String.format("%s\n", String.join(";", backpackMap.get(item))));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(backpackPath))) {
            bw.write(sb.toString());
            bw.newLine();
            bw.write(getDateTime());
        } catch (IOException e) {
            log.severe("I/O error during writing.");
        }
    }

    // read Backpack file
    public static String readBackpack() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(backpackPath))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            sb.append("\n");
        } catch (IOException e) {
            log.severe("I/O error during reading.");
        }
        return sb.toString();
    }

    private static String getDateTime() {
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/D/Y, EEEE HH:mma");
        return formatter.format(dt);
    }
}
