package code.java.jsonp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonP {


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
   * This function is used to set a value to for a given Key. If the Key is not present a new Key is
   * added. A new JSON structure with the changed value is returned.
   *
   * @param inputJson The JSON which needs to be modified.
   * @param objectKey The Key which needs to be found.
   * @param value The new value which needs to be set.
   * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html">Illegal
   * State Exception</a>
   * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html">Illegal
   * Argument Exception</a>
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

  /**
   * This is a helper function will add a key if the specified key is not present. If the key is
   * present the value of the key will be modified.
   *
   * @param inputObj The Json Object which needs to be manipulated.
   * @param key The key which needs to be added or modified.
   * @param value The value which needs to be added or modified.
   * @return A new JSON stucture with the modified value.
   */
  private Object setJSONObject(Object inputObj, String key, String value) {
    try {
      if (((JSONObject) inputObj).has(key)) {
        return ((JSONObject) inputObj).put(key, value);
      }
      return ((JSONObject) inputObj).put(key, value);
    } catch (NullPointerException | JSONException jsonException) {
      jsonException.printStackTrace();
      return jsonException;
    }
  }

  /**
   * This is a helper function which retrieves the value of the specified Key from a JSON Object
   *
   * @param inputObj The JSON object to be searched.
   * @param key The key which needs to be found.
   * @return A Java object which contains the key.
   */
  private Object getFromJSONObject(Object inputObj, String key) {
    try {
      return ((JSONObject) inputObj).get(key);
    } catch (NullPointerException | JSONException exception) {
      exception.printStackTrace();
      return exception;
    }
  }

  /**
   * This is a helper function which retrieves the value of a specified key from a JSON Array.
   * @param inputObj The JSON Array which needs to be searched
   * @param key The key which needs to be found.
   * @return A Java object which contains the key
   */
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
