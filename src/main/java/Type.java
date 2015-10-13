public class Type {
    // Message type constants
    public static final int CHAT_MESSAGE = 11;

    public static final int CREATE_ROOM_REQUEST = 21;
    public static final int JOIN_ROOM_REQUEST = 22;
    public static final int LIST_OF_USERS_IN_ROOM_REQUEST = 23;
    public static final int LIST_OF_ALL_CHATROOMS_REQUEST = 26;
    public static final int LEAVE_ROOM_REQUEST = 29;
    public static final int CHANGE_GROUP_ID = 24;

    public static final int CHOOSE_NICKNAME_REQUEST = 31;
    public static final int NICKNAME_AVAILABLE_CHECK = 32;

    //Notification number that's being sent back
    public static final int NEW_USER_IN_CHAT_NOTIFICATION = 101;
    public static final int LIST_OF_CHATROOMS_NOTIFICATION = 102;
    public static final int LIST_OF_USERS_IN_CHAT_NOTIFICATION = 103;
    public static final int CREDENTIAL_CHANGE_NOTIFICATION = 104;
    public static final int CHAT_MESSAGE_NOTIFICATION = 105;
    public static final int USER_LEFT_ROOM_NOTIFICATION = 106;
    public static final int ROOM_DELETED_NOTIFICATION = 107;
    public static final int ROOM_CREATED_NOTIFICATION = 108;
}
