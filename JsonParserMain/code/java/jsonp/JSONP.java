package code.java.jsonp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONP {

  /**
   * This function get's the node value of the JSON Object/Array.
   *
   * @param inputJson The JSON Structure from which the value needs to be obtained
   * @param objectKey The key for which value is required
   * @return A Java Object which contains the value.
   */
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

  /**
   * This function is used to set a value to for a given Key. If the Key is not present a
   * new Key is added. A JSON structure with the changed value is returned.
   *
   * @param inputJson The JSON which needs to be modified.
   * @param objectKey The Key which needs to be found.
   * @param value The new value which needs to be set.
   * @see <a href="">Illegal State Exception</a>
   * @see <a href="">Illegal Argument Exception</a>
   */
  public JSONObject setNodeValue(JSONObject inputJson, String objectKey, String value) {
    Object currentObject = inputJson;
    String[] fields = objectKey.split(Constants.FIELD_DELIMITER);
    for (int i = 0; i < fields.length; i++) {
      if (i == fields.length - 1) {
        if (JSONObject.class.isInstance(currentObject)) {
          setJSONObject(currentObject, fields[i], value);
          return inputJson;
        } else {
          throw new IllegalStateException("Only JSON Objects can be modified!!!");
        }
      }
      if (fields[i].contains("\n")) {
        fields[i] = fields[i].substring(0, fields[i].length() - 1);
        if (fields[i].isEmpty()) {
          throw new IllegalStateException();
        }
      }
      if (JSONObject.class.isInstance(currentObject)) {
        currentObject = getFromJSONObject(currentObject, fields[i]);
      } else if (JSONArray.class.isInstance(currentObject)) {
        currentObject = getFromJSONArray(currentObject, fields[i]);
      } else {
        throw new IllegalArgumentException();
      }
    }
    throw new IllegalArgumentException("Key cannot be empty!!!");
  }

  private Object setJSONObject(Object inputObj, String key, String value) {
    try {
      if (((JSONObject) inputObj).has(key)) {
        return ((JSONObject) inputObj).put(key, value);
      }
      return ((JSONObject) inputObj).put(key, value);
    } catch (Exception w) {
      w.printStackTrace();
      return w;
    }
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
