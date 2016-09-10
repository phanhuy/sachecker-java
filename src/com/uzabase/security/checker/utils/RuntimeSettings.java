package com.uzabase.security.checker.utils;

import com.uzabase.entity.Url;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Trung on 4/7/2016 12:54 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 *
 * This class includes all methods for loading resource config for fuzzing technology.
 */
public class RuntimeSettings {

    /**
     * Method for loading all setting about Fuzzing in application.properties
     *
     * @return Settings
     */
    public static Settings getRuntimeSettings(Url url) throws IOException {
        Settings settings = new Settings();
        Properties properties = new Properties();


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("fuzzingData/runtime.properties");

        properties.load(inputStream);

        String website = url.getUrl();

        settings.setHome(website);
        settings.setCompleteness(properties.getProperty("completeness"));
        settings.setTimeGap(Integer.parseInt(properties.getProperty("timeGap")));

        settings.setSensitiveData(parseFile(properties.getProperty("sensitive.data.file")));
        settings.setXssFuzzVectors(parseFile(properties.getProperty("xss.fuzz.vectors.file")));
        settings.setIntegerOverflowFuzzVectors(parseFile(properties.getProperty("integer.overflow.fuzz.vectors.file")));
        settings.setSqlInjectionFuzzVectors(parseFile(properties.getProperty("sql.injection.fuzz.vectors.file")));
        settings.setLdapInjectionFuzzVectors(parseFile(properties.getProperty("ldap.injection.fuzz.vectors.file")));
        settings.setXpathInjectionFuzzVectors(parseFile(properties.getProperty("xpath.injection.fuzz.vectors.file")));
        settings.setXmlInjectionFuzzVectors(parseFile(properties.getProperty("xml.injection.fuzz.vectors.file")));

        settings.setCommonUsernames(parseFile(properties.getProperty("usernames.file")));
        settings.setCommonPasswords(parseFile(properties.getProperty("passwords.file")));
        settings.setPagesGuesses(parseFile(properties.getProperty("pageguesses.file")));

        System.out.println(settings.getHome());
        return settings;
    }

    private static List<String> parseFile(String fileName) throws IOException {
        List<String> vectors = new ArrayList<>();
        String vector;

            FileInputStream fstream	= new FileInputStream("D:\\Code\\ASChecker\\src\\main\\resources\\fuzzingData\\" + fileName);
            DataInputStream in 		= new DataInputStream(fstream);
            BufferedReader br 		= new BufferedReader(new InputStreamReader(in));

            while ((vector = br.readLine()) != null) {
                vectors.add(vector);
            }

            in.close();

        return vectors;
    }
}
