package com.example.RecordScanner.Service.impl;

import com.example.RecordScanner.Service.recordService;
import com.example.RecordScanner.model.Record;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Configuration
@EnableSwagger2
@Component
public class ServiceImpl implements recordService {

    //Gets users based on the URL and runs the find users method in Record class
    @Override
    public ArrayList<JSONObject> retrieveUsersInArea(String url, String cityUrl) throws IOException {
        //Creates an array of the find users method
        ArrayList<JSONObject> records = Record.findUsers(url,cityUrl);

        //TESTING PRINT
        //System.out.println(records);
    return records;
    }
}
