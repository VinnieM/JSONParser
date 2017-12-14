package code.java.jsonp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONP {

  public Object getNodeValue(JSONObject inputJson, String objectKey) {
      Object currentObject = inputJson;
      String[] fields = objectKey.split(Constants.FIELD_DELIMITER);
      for (String eachField : fields) {
        if (eachField.contains("\n")) {
          eachField = eachField.substring(0, eachField.length() - 1);
          if (eachField.isEmpty()) {
            return null;
          }
        }
        if (JSONObject.class.isInstance(currentObject)) {
          currentObject = getFromJSONObject(currentObject, eachField);
        } else if (JSONArray.class.isInstance(currentObject)) {
          currentObject = getFromJSONArray(currentObject, eachField);
        } else {
          return currentObject;
        }
      }
      return currentObject;
  }

  private Object getFromJSONObject(Object inputObj, String key) {
    try {
      return ((JSONObject) inputObj).get(key);
    } catch (NullPointerException | JSONException exception) {
      exception.printStackTrace();
      return exception;
    }
  }

  private Object getFromJSONArray(Object inputObj, String key) {
    try {
      int index = Integer.parseInt(key);
      return ((JSONArray) inputObj).get(index);
    } catch (NullPointerException | JSONException exception) {
      exception.printStackTrace();
      return exception;
    }
  }
}
