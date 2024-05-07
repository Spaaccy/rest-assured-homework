package tbc.tbcacademy.ge.data.constants;

public class PetStoreData {
    public static final String
            PET_STORE_URL = "https://petstore.swagger.io",
            PET_ID_ENDPOINT = "v2/pet/{petId}",
            ORDER_ENDPOINT = "v2/store/order",
            USER_ENDPOINT = "/v2/user/",
            CONTAINS_TEN_DIGIT_REGEX = ".*\\d{10}.*",
            ONLY_NUMBERS_REGEX = "\\d+";

    // PET ORDER DATA
    public static final int
            PET_ID = 10,
            PET_ORDER_ID = 25,
            PET_QUANTITY = 10;
    public static final String
            PET_SHIP_DATE = "2024-05-03T12:51:00.690+0000",
            PET_ORDER_STATUS = "placed";
    public static final Boolean
            PET_ORDER_COMPLETE = true;

    // PET FORM DATA
    public static final int
            FORM_ID = 15,
            INCORRECT_FORM_ID = -1;
    public static final String
            FORM_PET_NAME = "Lucy",
            FORM_PET_STATUS = "sold";

    // USER DATA
    public static final String
            USER_NAME = "Luka",
            PASSWORD = "Password123";
}
