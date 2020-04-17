package com.slack.api.automation.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.slack.api.automation.constants.PropertyFilePath.CONFIG_PROPERTY_FILE_PATH;
import static com.slack.api.automation.constants.PropertyFilePath.QA_API_PROPERTY_FILE_PATH;

public class PropertyFileReader {
    private static Properties prop;
    private static Logger logger = Logger.getLogger(PropertyFileReader.class.getName());
    private static String environment = null;

    public static void loadPropertyFile() {
        prop = new Properties();
        FileReader reader;
        try {
            reader = new FileReader(CONFIG_PROPERTY_FILE_PATH);
            prop.load(reader);
            reader.close();
            environment = prop.getProperty("portal.ENVIRONMENT");

            if (environment.equalsIgnoreCase("qa"))
                reader = new FileReader(QA_API_PROPERTY_FILE_PATH);
            prop.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File Not Found!", e);
            System.exit(1);
        } catch (IOException e) {
            logger.log(Level.INFO, "IOException Found!", e);
        }
    }

    public static String getBASE_URI() {
        return prop.getProperty(environment.concat(".BASE_URI"));
    }

    public static String getPATH_CREATE_CHANNEL() {
        return prop.getProperty(environment.concat(".PATH_CREATE_CHANNEL"));
    }
    public static String getPATH_JOIN_CHANNEL() {
        return prop.getProperty(environment.concat(".PATH_JOIN_CHANNEL"));
    }

    public static String getPATH_RENAME_CHANNEL() {
        return prop.getProperty(environment.concat(".PATH_RENAME_CHANNEL"));
    }
    public static String getPATH_LIST_CHANNELS() {
        return prop.getProperty(environment.concat(".PATH_LIST_CHANNELS"));
    }

    public static String getPATH_ARCHIVE_CHANNEL() {
        return prop.getProperty(environment.concat(".PATH_ARCHIVE_CHANNEL"));
    }

}