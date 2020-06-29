package com.example.RecordScanner.Service;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public interface recordService {

ArrayList<JSONObject> retrieveUsersInArea(String url, String cityUrl) throws IOException;
}
