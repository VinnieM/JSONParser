package code.java.jsonp.test;

import static org.junit.Assert.assertEquals;

import code.java.jsonp.Constants;
import code.java.jsonp.JSONP;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class JSONPTest {

  private JSONP jsonP = new JSONP();
  private JSONObject JSON_Structure = getInputTestStrucure();

  /**
   * This method loads the JSON
   *
   * @return A String with the test data
   */
  private JSONObject getInputTestStrucure() {
    String jsonObject;
    try {
      jsonObject = new String(Files.readAllBytes(Paths.get("etc/TestData/Json.json")));
      return new JSONObject(jsonObject);
    } catch (IOException | JSONException ioException) {
      throw new FileSystemNotFoundException(
          "Unable to find the JSON File in etc/TestData/Json.json!!!" + "\n" + ioException);
    }
  }

  /**
   * This function is responsible to load different nodes which needs to be searched.
   */
  private Object getNodesToCheck(int indexNumber) {
    try {
      String nodeToSearch = new String(
          Files.readAllBytes(Paths.get("etc/TestData/NodesToCheck.txt")));
      String[] nodeArrayList = nodeToSearch.split(Constants.NODE_DELIMITER);
      return nodeArrayList[indexNumber];
    } catch (IOException ioException) {
      ioException.printStackTrace();
      return ioException;
    }
  }

  /**
   * This function asserts if the value of the cartId in the JSON Structure.
   */
  @Test
  public void checkJsonObject() {
    Object nodeIndex = getNodesToCheck(6);
    String valToCheck = nodeIndex.toString().substring(0, nodeIndex.toString().length() - 2);
    Object val = jsonP.getNodeValue(getInputTestStrucure(), valToCheck);
    assertEquals("8ladf51ds65ga6", val.toString());

  }

  @Test
  public void checkJsonObject1() {
    Object nodeIndex = getNodesToCheck(0);
    String valToCheck = nodeIndex.toString();
    Object val = jsonP.getNodeValue(JSON_Structure, valToCheck);
    assertEquals("MotoXPlay", val.toString());
  }

  @Test
  public void checkJsonObject2() {
    Object nodeIndex = getNodesToCheck(1);
    String valToCheck = nodeIndex.toString();
    Object val = jsonP.getNodeValue(getInputTestStrucure(), valToCheck);
    assertEquals("192.168.3.155", val.toString());
  }

  @Test
  public void checkJsonArray() {
    Object nodeIndex = getNodesToCheck(3);
    String valToCheck = nodeIndex.toString();
    Object val = jsonP.getNodeValue(getInputTestStrucure(), valToCheck);
    assertEquals(true, val);
  }

  @Test
  public void checkJsonArray1() {
    Object nodeIndex = getNodesToCheck(5);
    String valToCheck = nodeIndex.toString();
    Object val = jsonP.getNodeValue(getInputTestStrucure(), valToCheck);
    assertEquals("Val8", val.toString());
  }
}