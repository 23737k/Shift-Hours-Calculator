package com.shiftHoursCalculatorApp.cron;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("Restarting server");
    String backendUrl = "https://shift-hours-calculator.onrender.com/api/home";

    try {
      // Perform an HTTPS GET request to hit any backend api
      URL url = new URL(backendUrl);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int statusCode = con.getResponseCode();
      if (statusCode == 200) {
        System.out.println("Server restarted");
      } else {
        System.err.println("Failed to restart server with status code: " + statusCode);
      }
    } catch (IOException e) {
      System.err.println("Error during restart: " + e.getMessage());
    }
  }
}
