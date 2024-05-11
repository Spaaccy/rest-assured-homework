package tbc.tbcacademy.ge.data.constants;

import java.util.List;

public class SwapiData {
    public static final String
            SWAPI_URL = "https://swapi.dev/api",
            SWAPI_ENDPOINT = "/planets/?format=json";

    public static final String BOBA_FETT_NAME = "Boba Fett",
    BOBA_FETT_HEIGHT = "183",
    BOBA_FETT_MASS = "78.2",
    BOBA_FETT_HAIR_COLOR = "black",
    BOBA_FETT_SKIN_COLOR = "fair",
    BOBA_FETT_EYE_COLOR = "brown",
    BOBA_FETT_BIRTH_YEAR = "31.5BBY",
    BOBA_FETT_GENDER = "male",
    BOBA_FETT_HOMEWORLD = "https://swapi.dev/api/planets/10/",
    BOBA_FETT_CREATED = "2014-12-15T12:49:32.457000Z",
    BOBA_FETT_EDITED = "2014-12-20T21:17:50.349000Z",
    BOBA_FETT_URL = "https://swapi.dev/api/people/22/";

    public static final List<String> BOBA_FETT_FILMS = List.of(
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/5/"
    );
    public static final List<String> BOBA_FETT_STARSHIPS = List.of("https://swapi.dev/api/starships/21/");
}
