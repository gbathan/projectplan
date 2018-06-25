
package com.projectplan.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.sound.InvalidDataException;

public class JSONUtilities
{

    public static Double getDoubleValue(JSONObject aJson, String aKey) throws Exception
    {
        return getDoubleValue(aJson, aKey, true);
    }

    public static Double getDoubleValue(JSONObject aJson, String aKey, boolean aMandatory) throws Exception
    {
        // Check the JSON value exists.
        if (aJson == null)
        {
            if (aMandatory)
            {

                throw new Exception(aKey);
            }

            return null;
        }

        // Read the value from the JSON input using the key.
        Long lValue = null;

        lValue = (Long)aJson.get(aKey);

        // Attempt to parse string into a double.
        Double lDoubleValue = null;
        try
        {
            lDoubleValue = new Double(lValue);
        }
        catch (NullPointerException lNpe)
        {
            // Value doesn't exist. If mandatory, throw an exception
            if (aMandatory && lValue == null)
            {

                throw new Exception(aKey);
            }
        }
        catch (NumberFormatException lNfe)
        {
            // Value exists but is not numeric.
            throw new InvalidDataException(aKey);
        }

        return lDoubleValue;
    }

    public static String getStringValue(JSONObject aRequestJSON, String aKey) throws Exception
    {
        return getStringValue(aRequestJSON, aKey, true);
    }

    public static String getStringValue(JSONObject aJson, String aKey, boolean aMandatory) throws Exception
    {
        // Check the JSON value exists.
        if (aJson == null)
        {
            if (aMandatory)
            {
                throw new Exception(aKey);
            }

            return "";
        }

        // Read the value from the JSON input using the key.
        String lValue = null;

        lValue = (String)aJson.get(aKey);

        // Check if the value exists.
        if (aMandatory && (lValue == null || lValue.trim().length() == 0))
        {

            throw new Exception(aKey);
        }

        if (lValue == null)
        {

            lValue = "";
        }

        return lValue;
    }

    public static JSONObject getJSONObjectValue(JSONObject aJson, String aKey) throws Exception
    {
        return getJSONObjectValue(aJson, aKey, true);
    }

    public static JSONObject getJSONObjectValue(JSONObject aJson, String aKey, boolean aMandatory) throws Exception
    {
        // Check the JSON value exists.
        if (aJson == null)
        {
            if (aMandatory)
            {

                throw new Exception(aKey);
            }

            return null;
        }

        // Read the value from the JSON input using the key.
        JSONObject lValue = null;

        lValue = (JSONObject)aJson.get(aKey);

        // Check if the value exists.
        if (aMandatory && lValue == null)
        {

            throw new Exception(aKey);
        }

        return lValue;
    }

    public static JSONArray getJSONArrayValue(JSONObject aJson, String aKey) throws Exception
    {
        return getJSONArrayValue(aJson, aKey, true);
    }

    public static JSONArray getJSONArrayValue(JSONObject aJson, String aKey, boolean aMandatory) throws Exception
    {
        // Check the JSON value exists.
        if (aJson == null)
        {
            if (aMandatory)
            {
                throw new Exception(aKey);
            }

            return null;
        }

        // Read the value from the JSON input using the key.
        JSONArray lValue = null;

        lValue = (JSONArray)aJson.get(aKey);

        // Check if the value exists.
        if (aMandatory && lValue == null)
        {
            throw new Exception(aKey);
        }

        return lValue;
    }

    public static Boolean getBooleanValue(JSONObject aJson, String aKey) throws Exception
    {
        return getBooleanValue(aJson, aKey, true);
    }

    public static Boolean getBooleanValue(JSONObject aJson, String aKey, boolean aMandatory) throws Exception
    {
        // Check the JSON value exists.
        if (aJson == null)
        {
            if (aMandatory)
            {
                throw new Exception(aKey);
            }

            return null;
        }

        // Read the value from the JSON input using the key.
        String lValue = null;

        //lValue = (String)aJson.get(aKey);
        // Attempt to parse string into a Boolean.
        Boolean lBooleanValue = (Boolean)aJson.get(aKey);
        try
        {
            lBooleanValue = Boolean.valueOf(lValue);
        }
        catch (NullPointerException lNpe)
        {
            // Value doesn't exist. If mandatory, throw an exception
            if (aMandatory && lValue == null)
            {

                throw new Exception(aKey);
            }
        }
        catch (NumberFormatException lNfe)
        {
            // Value exists but is not numeric.
            throw new InvalidDataException(aKey);
        }

        return lBooleanValue;
    }

    public static <T> Object getObject(final String aInputString, final Class<T> aClazz)
    { // this is the key object to convert JSON to Java
        Object lObject = null;

        try
        {
            lObject = new ObjectMapper().readValue(aInputString, aClazz);
        }
        catch (Exception ex)
        {

        }

        return lObject;
    }

    public static String getStringDate(JSONObject aJson, String aKey) throws JSONException
    {
        String lDateString = "";

        long lValue = (Long)aJson.get(aKey);
        Date date = new Date(lValue);
        SimpleDateFormat lDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        lDateString = lDateFormat.format(date);

        return lDateString;
    }

    public static Date getDateValue(JSONObject aJson, String aKey) throws JSONException
    {

        long lValue = (Long)aJson.get(aKey);
        Date date = new Date(lValue);

        return date;
    }
}
