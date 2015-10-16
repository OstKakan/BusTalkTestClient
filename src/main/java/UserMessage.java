import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class will wrap a Json object and help getting access to it's info using json methods
 */
public class UserMessage {

    private final JSONObject messageInfo;

    public UserMessage(JSONObject obj){
        messageInfo = obj;
    }

    /**
     *
     * @param key is a key value. Think of it as a key for a Map.
     * @return The value the json object relates to the key. If no value is connected to the key, null will be returned
     * @throws IllegalArgumentException if the key cannot be found
     */
    public String getString(String key) throws IllegalArgumentException{
        try {
            return messageInfo.getString(key);
        }catch(JSONException e){
            throw new IllegalArgumentException(key);
        }
    }

    /**
     * @param key is a key value. Think of it as a key for a Map.
     * @return The value the json object relates to the key. If no value is connected to the key, null will be returned
     * @throws IllegalArgumentException if key can not be found or value associated with key cant be cast to integer.
     */
    public int getInt(String key) throws IllegalArgumentException{
        try {
            return messageInfo.getInt(key);
        }catch(JSONException e){
            throw new IllegalArgumentException(key);
        }
    }

    public boolean getBoolean(String key) throws  IllegalArgumentException{
        try {
            return messageInfo.getBoolean(key);
        } catch (JSONException e) {
            throw new IllegalArgumentException(key);
        }
    }

    public JSONArray getJSONArray(String key) throws IllegalArgumentException{
        try {
            return messageInfo.getJSONArray(key);
        } catch (JSONException e) {
            throw new IllegalArgumentException(key);
        }
    }

    @Override
    public String toString(){
        return messageInfo.toString();
    }

}
