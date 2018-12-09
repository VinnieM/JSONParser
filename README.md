Introduction
------------

An entire JSON Object is traversed and the required JSON object or array (no matter the depth) is searched, and the result is given out as a Java Object. The proposed solution takes in a JSON input, and produces an output(Java Object). The input would be as a JSON Object, searches for the nodes specified, and gives out the result as a Java Object as the output.

Details of the Problem
----------------------

For the following JSON Structure, if you want to find the value of TestKey4, which is inside TestArray, you would end up writing a loop or some boiler plate code. The proposed solution helps you to get the value like this

getNodeValue(JSONStructure, perms.0.testArray.0.TestKey4);

As per the current implementation the getNodeValue(String obj1, String obj2) would return an Java Object (java.lang.object). The advantage of returning as an Object is that whatever type of value the key holds, the user could get that without anticipating the return value.
```
{
	"perms": [{
		"changeDefault": "Yes",
		"visible": true,
		"notused": true,
		"testArray": [{
			"TestKey1": "TestVal1",
			"TestKey2": "TestVal2",
			"TestKey3": "TestVal3",
			"TestKey4": "TestVal4"
		}],
		"selectable": true,
		"allAccess": true,
		"defaultOnly": true
	}],
	"avatarUrl": "",
	"cartId": "8ladf51ds65ga6",
	"DeviceDetails": {
		"DeviceName": "MotoXPlay",
		"UDID": "459f8202b2n92h1",
		"Info": {
			"IPDetails": "192.168.3.155",
			"TestMgmtID": "8080",
			"IsPhone": true
		}
	}
}
```
Building the code
-----------------

Download the project and make sure you have Apache Maven installed.

Example
-------

The below example shows how to use the utility.
```
public ApplicaitonMain(){
	try{
		String testJSON = new String(Files.readAllBytes(Paths.get("TestJson.txt")));
		JSONP parser = new JSONP();
		Object object = parser.getNodeValue(new JSONObject(testJSON), "perms.0.testArray.0.TestKey4");
		System.out.println("Output "+object.toString());
	} catch (IOException | JSONException exceptions){
		exceptions.printStackTrace();
	}
}
```
