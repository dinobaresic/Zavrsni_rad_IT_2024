package org.eclipse.scout.apps.budgetbuddy.client.reports;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
public class ReceiptAiOcr {
  public static Map<String, String> processFile(File file){
    OkHttpClient client = new OkHttpClient();
    Map<String, String> receiptMap = new HashMap<String, String>();
    RequestBody body = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("file", "receipt.jpg", RequestBody.create(MediaType.parse("image/jpeg"), file))
      .addFormDataPart("refresh", "false")
      .addFormDataPart("incognito", "false")
      .addFormDataPart("extractTime", "false")
      .build();

    Request request = new Request.Builder()
      .url("https://api.taggun.io/api/receipt/v1/verbose/file")
      .post(body)
      .addHeader("accept", "application/json")
      .addHeader("apikey", "aec05140d4c111eeb72409b0b60cbbed")
      .build();

    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String responseData = response.body().string();
        receiptMap = ProcessReceipt.getReceiptData(responseData);
      } else {
        System.out.println("Error: " + response.code() + " " + response.message());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (receiptMap == null) {
      throw new RuntimeException("Failed to process the receipt");
    }
    return receiptMap;
  }
}
