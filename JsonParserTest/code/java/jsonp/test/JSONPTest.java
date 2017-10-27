package code.java.jsonp.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import code.java.jsonp.JSONP;
import code.java.jsonp.Constants;

public class JSONPTest {
	private JSONP jsonP = new JSONP();
	
	private String getInputTestStrucure() {
		String inputJson = "";
		if(inputJson.length()==0){
			try {
				inputJson = new String(Files.readAllBytes(Paths.get("etc/TestData/Json.json")));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return inputJson;
	}

	private String getNodesToCheck(int indexNumber) {
		String nodeToSearch = "";
		try {
			nodeToSearch = new String(Files.readAllBytes(Paths.get("etc/TestData/NodesToCheck.txt")));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		String[] nodeArrayList = nodeToSearch.split(Constants.NODE_DELIMITER);
		return nodeToSearch = nodeArrayList[indexNumber];
	}

	@Test
	public void checkJsonObject() {
		String input = getInputTestStrucure();
		String valToCheck = getNodesToCheck(6);
		Object val = jsonP.getNodeValue(input, valToCheck);
		if (valToCheck.contains("\n")) {
			valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
		}
		assertEquals("8ladf51ds65ga6", val.toString());
	}

	@Test
	public void checkJsonObject1() {
		String input = getInputTestStrucure();
		String valToCheck = getNodesToCheck(0);
		Object val = jsonP.getNodeValue(input, valToCheck);
		if (valToCheck.contains("\n")) {
			valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
		}
		assertEquals("MotoXPlay", val.toString());
	}

	@Test
	public void checkJsonObject2() {
		String input = getInputTestStrucure();
		String valToCheck = getNodesToCheck(1);
		Object val = jsonP.getNodeValue(input, valToCheck);
		if (valToCheck.contains("\n")) {
			valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
		}
		assertEquals("192.168.3.155", val.toString());
	}

	@Test
	public void checkJsonArray() {
		String input = getInputTestStrucure();
		String valToCheck = getNodesToCheck(3);
		Object val = jsonP.getNodeValue(input, valToCheck);
		if (valToCheck.contains("\n")) {
			valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
		}
		assertEquals(true, val);
	}
	
	@Test
	public void checkJsonArray1() {
		String input = getInputTestStrucure();
		String valToCheck = getNodesToCheck(5);
		Object val = jsonP.getNodeValue(input, valToCheck);
		if (valToCheck.contains("\n")) {
			valToCheck = valToCheck.substring(0, valToCheck.length() - 1);
		}
		assertEquals("Val8", val.toString());
	}
}