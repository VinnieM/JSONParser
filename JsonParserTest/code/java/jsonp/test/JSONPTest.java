package code.java.jsonp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import code.java.jsonp.Constants;
import code.java.jsonp.JSONP;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

public class JSONPTest {

  private JSONP jsonP = new JSONP();

  /**
   * This method loads the test data
   *
   * @return A String with the test data
   */
  private String getInputTestStrucure() {
    String inputJson = "";
    if (inputJson.length() == 0) {
      try {
        inputJson = new String(Files.readAllBytes(Paths.get("etc/TestData/Json.json")));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    return inputJson;
  }

  /**
   * This function is responsible to load different nodes which needs to be searched.
   */
  private Object getNodesToCheck(int indexNumber) {
    try {
      String nodeToSearch = new String(
          Files.readAllBytes(Paths.get("etc/TestData/NodesToCheck.txt")));
      String[] nodeArrayList = nodeToSearch.split(Constants.NODE_DELIMITER);
      return nodeToSearch = nodeArrayList[indexNumber];
    } catch (IOException ioException) {
      ioException.printStackTrace();
      return ioException;
    }
  }

  /**
   * This function checks if in input is an Object of the Exception Class.
   *
   * @param val Any Java object.
   * @return Return true, if the input parameter is an exception object, else false.
   */
  private boolean isException(Object val) {
    if (val instanceof Exception) {
      return true;
    }
    return false;
  }

  @Test
  public void checkJsonObject() {
    String input = getInputTestStrucure();
    Object nodeIndex = getNodesToCheck(6);
    if (!isException(nodeIndex)) {
      String valToCheck = nodeIndex.toString();
      Object val = jsonP.getNodeValue(input, valToCheck);
      if (valToCheck.contains("\n")) {
        valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
      }
      assertEquals("8ladf51ds65ga6", val.toString());
    } else {
      fail("An exception has occurred " + nodeIndex.toString());
    }
  }

  @Test
  public void checkJsonObject1() {
    String input = getInputTestStrucure();
    Object nodeIndex = getNodesToCheck(0);
    if (!isException(nodeIndex)) {
      String valToCheck = nodeIndex.toString();
      Object val = jsonP.getNodeValue(input, valToCheck);
      if (valToCheck.contains("\n")) {
        valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
      }
      assertEquals("MotoXPlay", val.toString());
    } else {
      fail("An Exception occurred " + nodeIndex.toString());
    }
  }

  @Test
  public void checkJsonObject2() {
    String input = getInputTestStrucure();
    Object nodeIndex = getNodesToCheck(1);
    if (!isException(nodeIndex)) {
      String valToCheck = nodeIndex.toString();
      Object val = jsonP.getNodeValue(input, valToCheck);
      if (valToCheck.contains("\n")) {
        valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
      }
      assertEquals("192.168.3.155", val.toString());
    } else {
      fail("An Exception occurred " + nodeIndex.toString());
    }
  }

  @Test
  public void checkJsonArray() {
    String input = getInputTestStrucure();
    Object nodeIndex = getNodesToCheck(3);
    if (!isException(nodeIndex)) {
      String valToCheck = nodeIndex.toString();
      Object val = jsonP.getNodeValue(input, valToCheck);
      if (valToCheck.contains("\n")) {
        valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
      }
      assertEquals(true, val);
    } else {
      fail("An exception has occurred " + nodeIndex);
    }
  }

  @Test
  public void checkJsonArray1() {
    String input = getInputTestStrucure();
    Object nodeIndex = getNodesToCheck(5);
    if (!isException(nodeIndex)) {
      String valToCheck = nodeIndex.toString();
      Object val = jsonP.getNodeValue(input, valToCheck);
      if (valToCheck.contains("\n")) {
        valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
      }
      assertEquals("Val8", val.toString());
    } else {
      fail("An exception has occurred " + nodeIndex);
    }
  }
}