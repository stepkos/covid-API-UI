package com.example.covidapiui;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;


public class DayOfCovid {

    private final static String baseUrl = "https://api.quarantine.country/api/v1/spots/";

    // To fetch
    private long total_cases;
    private long deaths;
    private long recovered;
    private long tested;

    // To count
    private double death_ratio;      // wspolczynnik zgonow
    private double recovery_ratio;   // wspolczynnik wyzdrowien
    private double tests_ratio;      // wspolczynnik testow na ilosc przypadkow


    public DayOfCovid(String restOfUrl, String date) throws IOException, ParseException, ExceptionInInitializerError {
        String jsonStr = FetchHttp.getJsonAsStr(baseUrl.concat(restOfUrl));

        JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
        System.out.println("Status: ".concat(jsonObj.get("status").toString()));

        JSONObject data = (JSONObject) jsonObj.get("data");
        if (data.get(date) == null) {
            System.out.println("Brak infomacji o danych z tego dnia");
            throw new ExceptionInInitializerError();
        }

        JSONObject day = (JSONObject) data.get(date);
        this.total_cases = Long.parseLong(day.get("total_cases").toString());
        this.deaths = Long.parseLong(day.get("deaths").toString());
        this.recovered = Long.parseLong(day.get("recovered").toString());
        this.tested = Long.parseLong(day.get("tested").toString());

        countRatio();
    }

    public void countRatio() {
        death_ratio = (double) deaths / total_cases;
        recovery_ratio = (double) recovered / total_cases;
        tests_ratio = (double) tested / total_cases;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getInJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total_cases", getTotal_cases());
        jsonObject.put("deaths", getDeaths());
        jsonObject.put("recovered", getRecovered());
        jsonObject.put("tested", getTested());
        jsonObject.put("death_ratio", getDeath_ratio());
        jsonObject.put("recovery_ratio", getRecovery_ratio());
        jsonObject.put("tests_ratio", getTests_ratio());

        return jsonObject;
    }

    public void saveToJSONFile(String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(getInJSONObject().toJSONString());
            file.flush();
            System.out.println("Files has saved successfull!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DayOfCovid{" +
                "total_cases=" + total_cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", tested=" + tested +
                ", death_ratio=" + death_ratio +
                ", recovery_ratio=" + recovery_ratio +
                ", tests_ratio=" + tests_ratio +
                '}';
    }

    public void setTotal_cases(long total_cases) {
        this.total_cases = total_cases;
        countRatio();
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
        countRatio();
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
        countRatio();
    }

    public void setTested(long tested) {
        this.tested = tested;
        countRatio();
    }

    public long getTotal_cases() {
        return total_cases;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getTested() {
        return tested;
    }

    public double getDeath_ratio() {
        return death_ratio;
    }

    public double getRecovery_ratio() {
        return recovery_ratio;
    }

    public double getTests_ratio() {
        return tests_ratio;
    }
}
