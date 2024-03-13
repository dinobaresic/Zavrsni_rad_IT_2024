package org.eclipse.scout.apps.budgetbuddy.client.reports;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ProcessReceipt {
  public static Map<String, String> getReceiptData(String jsonString){

    Gson gson = new Gson();
    ReceiptData receiptData = gson.fromJson(jsonString, ReceiptData.class);

    Map<String, String> receiptMap = new HashMap<>();
    if (receiptData.merchantName != null) {
      receiptMap.put("merchantName", receiptData.merchantName.data);
    }
    if (receiptData.merchantAddress != null) {
      receiptMap.put("merchantAddress", receiptData.merchantAddress.data);
    }
    if (receiptData.totalAmount != null && receiptData.totalAmount.data != null) {
      receiptMap.put("totalAmount", receiptData.totalAmount.data);
    }
    if (receiptData.taxAmount != null && receiptData.taxAmount.data != null) {
      receiptMap.put("taxAmount", receiptData.taxAmount.data);
    }
    if (receiptData.date != null && receiptData.date.data != null) {
      receiptMap.put("date", receiptData.date.data);
    }

    return receiptMap;

  }

  static class ReceiptData {
    TotalAmount totalAmount;
    TaxAmount taxAmount;
    DateData date;
    MerchantName merchantName;
    MerchantAddress merchantAddress;


  }

  static class TotalAmount {
    String data;
  }

  static class TaxAmount {
    String data;
  }

  static class DateData {
    String data;
  }

  static class MerchantName {
    String data;
  }

  static class MerchantAddress {
    String data;
  }


}
