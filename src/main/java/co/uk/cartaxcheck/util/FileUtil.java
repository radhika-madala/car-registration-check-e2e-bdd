package co.uk.cartaxcheck.util;

import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    public List<String> readInputFileAndGetRegistrationNumbers(String filePath){
       String text =  readFileContent(filePath);
        String patternString = "([A-Z][A-Z]\\d\\d\\s*[A-Z][A-Z][A-Z])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        List<String> registrationNumbers = new ArrayList<String>();
        while(matcher.find()) {
            registrationNumbers.add(matcher.group(1));
        }
        return registrationNumbers;
    }
    private String readFileContent(String filename) {
            InputStreamReader in = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename));
            StringBuffer fileData = new StringBuffer(1000);
            BufferedReader reader = new BufferedReader(in);

            char[] buf = new char[1024];
            int numRead;
            try {
                while ((numRead = reader.read(buf)) != -1) {
                    String readData = String.valueOf(buf, 0, numRead);
                    fileData.append(readData);
                    buf = new char[1024];
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileData.toString();

        }

    public List<CarDetails> readExpectedCarDetails(String csvFilePath) throws URISyntaxException, IOException {
        try {
            URI uri = ClassLoader.getSystemResource(csvFilePath).toURI();
            Reader reader = Files.newBufferedReader(Paths.get(uri));

            return new CsvToBeanBuilder(reader)
                    .withType(CarDetails.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    //.withMappingStrategy(mappingStrategy)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    }

