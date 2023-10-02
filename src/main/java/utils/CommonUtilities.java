package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import testData.AddUser;

public class CommonUtilities {

    public static String readFileAndReturnAString(String filePath) throws IOException {
        byte[] fileConstants = Files.readAllBytes(Paths.get(filePath));
        return new String(fileConstants, StandardCharsets.UTF_8);
    }


    //object to string conversion = serialization
    public static String serializeObject(Object user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE);
        String sJsonPayload = objectMapper.writeValueAsString(user);
        System.out.println(sJsonPayload);
        return sJsonPayload;
    }

    public static Object deserializeJson(String sJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AddUser addUser = objectMapper.readValue(sJson, AddUser.class);
        return addUser;
    }
}
