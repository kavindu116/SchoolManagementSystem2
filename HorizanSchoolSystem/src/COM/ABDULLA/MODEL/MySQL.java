package COM.ABDULLA.MODEL;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import raven.toast.Notifications;

public class MySQL {

    private static Connection connection;

    private static boolean useLocalhost = false;
    private static boolean autoBackupToCloud = true;

    private static final long SYNC_INTERVAL_MS = 1 * 120 * 1000; // checks every 2 min also 120 seconds

    //important variables
    private static String localHost = "localhost";
    private static String localPort = "3306";
    private static String localUsername = "root";
    private static String localPassword = "Pass123$$";
    private static String localdb_name = "aluth_iskole";

    private static String cloudHost = "horizon-abdullahfareed882-dc96.h.aivencloud.com";
    private static String cloudPort = "22617";
    private static String cloudUsername = "avnadmin";
    private static String cloudPassword = "AVNS_Qj9QuPi5A4D2liO-nw4";
    private static String clouddb_name = "aluth_iskole";

    // Configurable sync interval (in milliseconds)
    public static void CreateConnection() {

        try {
            if (connection == null) {

                if (useLocalhost == false) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://" + cloudHost + ":" + cloudPort + "/" + clouddb_name + "", cloudUsername, cloudPassword);
                    System.out.println("Connected to Cloud Database");

                } else {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://" + localHost + ":" + localPort + "/" + localdb_name + "", localUsername, localPassword);
                    System.out.println("Connected to Localhost Database");
                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER, "Connected to Localhost Database");
                    startSyncTask();
                }

                
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed.");
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Database connection failed.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        CreateConnection();
        return connection;
    }

    public static ResultSet executeSearch(String query) throws Exception {
        CreateConnection();
        System.out.println("Executing Query: " + query);
        return connection.createStatement().executeQuery(query);
    }

    public static Integer executeIUD(String query) throws Exception {
        CreateConnection();
        System.out.println("Executing IUD: " + query);
        isDbUpdated = true; // Mark database as updated
        return connection.createStatement().executeUpdate(query);
    }

    // Check Internet Connectivity
    public static boolean isInternetAvailable() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://google.com").openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return (connection.getResponseCode() == 200);
        } catch (Exception e) {
            return false;
        }
    }
    private static boolean isDbUpdated = false;

    // Synchronize Localhost Database to Cloud
    public static void syncToCloud() {
        if (isDbUpdated) {
            if (!isInternetAvailable()) {
                System.out.println("Internet unavailable. Skipping sync.");
                return;
            }

            System.out.println("Starting database synchronization...");
            try {
                // Backup the local database
                ProcessBuilder backupBuilder = new ProcessBuilder(
                        "mysqldump", "-u", localUsername, "-p" + localPassword, localdb_name);
                backupBuilder.redirectOutput(new File("backup_" + localdb_name + ".sql"));
                Process backupProcess = backupBuilder.start();
                int backupResult = backupProcess.waitFor(); // Wait for the backup to finish

                if (backupResult == 0) {
                    System.out.println("Backup successful.");

                    // Restore backup to cloud database
                    ProcessBuilder restoreBuilder = new ProcessBuilder(
                            "mysql", "-h", cloudHost,
                            "-u", cloudUsername, "-p" + cloudPassword, clouddb_name);
                    restoreBuilder.redirectInput(new File("backup_" + localdb_name + ".sql"));
                    Process restoreProcess = restoreBuilder.start();
                    int restoreResult = restoreProcess.waitFor(); // Wait for the restore to finish

                    if (restoreResult == 0) {
                        System.out.println("Synchronization complete.");
                        isDbUpdated = false; // Reset the update flag
                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_CENTER, "Synchronization complete.");
                    } else {
                        System.out.println("Restore to cloud database failed.");
                    }
                } else {
                    System.out.println("Backup failed.");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error during synchronization.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No database updates to synchronize.");
        }
    }

    public static void restoreFromCloud() {
        if (!isInternetAvailable()) {
            System.out.println("Internet unavailable. Skipping restore.");
            return;
        }

        System.out.println("Starting cloud → local database restoration...");
        File cloudBackup = new File("cloud_backup_" + clouddb_name + ".sql");
        try {
            // 1) Dump the cloud database to a SQL file (note the –P cloudPort!)
            ProcessBuilder cloudDumpBuilder = new ProcessBuilder(
                    "mysqldump",
                    "-h", cloudHost,
                    "-P", cloudPort, // ← specify cloud port
                    "-u", cloudUsername,
                    "-p" + cloudPassword,
                    clouddb_name
            )
                    .redirectOutput(cloudBackup)
                    .redirectErrorStream(true);    // merge stderr so you can read errors

            Process dumpProcess = cloudDumpBuilder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(dumpProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println("[mysqldump] " + line);
                }
            }
            int dumpResult = dumpProcess.waitFor();
            if (dumpResult != 0) {
                System.err.println("❌ mysqldump failed with exit code " + dumpResult);
                return;
            }

            System.out.println("✔ Cloud backup successful: " + cloudBackup.getName());

            // 2) Restore that dump into the local database
            ProcessBuilder localRestoreBuilder = new ProcessBuilder(
                    "mysql",
                    "-h", localHost,
                    "-P", localPort, // ← specify local port too (if non-standard)
                    "-u", localUsername,
                    "-p" + localPassword,
                    localdb_name
            )
                    .redirectInput(cloudBackup)
                    .redirectErrorStream(true);

            Process restoreProcess = localRestoreBuilder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(restoreProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println("[mysql] " + line);
                }
            }
            int restoreResult = restoreProcess.waitFor();
            if (restoreResult != 0) {
                System.err.println("❌ mysql restore failed with exit code " + restoreResult);
            } else {
                System.out.println("✔ Local database restoration complete.");
                Notifications.getInstance().show(
                        Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER,
                        "Local database restored from cloud."
                );
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error during restoration process.");
            e.printStackTrace();
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER,
                    "Error during local restore."
            );
        }
    }

    // Periodic Task to Sync Database
    public static void startSyncTask() {

        if (autoBackupToCloud == true) {
            Runnable task = () -> {
                while (true) {
                    syncToCloud(); // Attempt to sync
                    try {
                        Thread.sleep(SYNC_INTERVAL_MS); // Wait for the next interval
                    } catch (InterruptedException e) {
                        System.out.println("Sync task interrupted.");
                        e.printStackTrace();
                    }
                }
            };
            new Thread(task).start();
        } else {
            System.out.println("backup skipped");
        }

    }

}
