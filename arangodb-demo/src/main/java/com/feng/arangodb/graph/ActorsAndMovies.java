package com.feng.arangodb.graph;

import com.arangodb.*;
import com.arangodb.entity.*;
import com.arangodb.model.CollectionCreateOptions;
import com.feng.arangodb.base.ArangoDbConfig;

public class ActorsAndMovies {

    private static final String TEST_DB = "actors_movies_db";
    private static ArangoDB arangoDB;
    private static ArangoDatabase db;

    public static void main(String... args) {
        try {
            arangoDB = new ArangoDB.Builder()
                    .host(ArangoDbConfig.host, ArangoDbConfig.port)
                    .user(ArangoDbConfig.user)
                    .password(ArangoDbConfig.password)
                    .build();
            try {
                arangoDB.db(TEST_DB).drop();
            } catch (final ArangoDBException e) {
            }
            arangoDB.createDatabase(TEST_DB);
            db = arangoDB.db(TEST_DB);
            initData();
        }
        catch (ArangoDBException e) {
            e.printStackTrace();
        }
        finally {
            arangoDB.shutdown();
        }
    }

    /**
     * 初始化数据
     */
    private static void initData() {

        // 演员信息
        db.createCollection("actors");
        final ArangoCollection actors = db.collection("actors");
        // 电影信息
        db.createCollection("movies");
        final ArangoCollection movies = db.collection("movies");
        // 电影中演员角色信息
        db.createCollection("actsIn", new CollectionCreateOptions().type(CollectionType.EDGES));
        final ArangoCollection actsIn = db.collection("actsIn");

        // 电影以及演员信息
        final String theMatrix = saveMovie(movies, "TheMatrix", "The Matrix", 1999, "欢迎来到真实的世界").getId();
        final String keanu = saveActor(actors, "Keanu", "Keanu Reeves", 1964).getId();
        final String carrie = saveActor(actors, "Carrie", "Carrie-Anne Moss", 1967).getId();
        final String laurence = saveActor(actors, "Laurence", "Laurence Fishburne", 1961).getId();
        final String hugo = saveActor(actors, "Hugo", "Hugo Weaving", 1960).getId();
        final String emil = saveActor(actors, "Emil", "Emil Eifrem", 1978).getId();

        saveActsIn(actsIn, keanu, theMatrix, new String[] { "Neo" }, 1999);
        saveActsIn(actsIn, carrie, theMatrix, new String[] { "Trinity" }, 1999);
        saveActsIn(actsIn, laurence, theMatrix, new String[] { "Morpheus" }, 1999);
        saveActsIn(actsIn, hugo, theMatrix, new String[] { "Agent Smith" }, 1999);
        saveActsIn(actsIn, emil, theMatrix, new String[] { "Emil" }, 1999);

        final String theMatrixReloaded = saveMovie(movies, "TheMatrixReloaded", "The Matrix Reloaded", 2003,
                "Free your mind").getId();
        saveActsIn(actsIn, keanu, theMatrixReloaded, new String[] { "Neo" }, 2003);
        saveActsIn(actsIn, carrie, theMatrixReloaded, new String[] { "Trinity" }, 2003);
        saveActsIn(actsIn, laurence, theMatrixReloaded, new String[] { "Morpheus" }, 2003);
        saveActsIn(actsIn, hugo, theMatrixReloaded, new String[] { "Agent Smith" }, 2003);

        final String theMatrixRevolutions = saveMovie(movies, "TheMatrixRevolutions", "The Matrix Revolutions", 2003,
                "Everything that has a beginning has an end").getId();
        saveActsIn(actsIn, keanu, theMatrixRevolutions, new String[] { "Neo" }, 2003);
        saveActsIn(actsIn, carrie, theMatrixRevolutions, new String[] { "Trinity" }, 2003);
        saveActsIn(actsIn, laurence, theMatrixRevolutions, new String[] { "Morpheus" }, 2003);
        saveActsIn(actsIn, hugo, theMatrixRevolutions, new String[] { "Agent Smith" }, 2003);

        final String theDevilsAdvocate = saveMovie(movies, "TheDevilsAdvocate", "The Devil's Advocate", 1997,
                "Evil has its winning ways").getId();
        final String charlize = saveActor(actors, "Charlize", "Charlize Theron", 1975).getId();
        final String al = saveActor(actors, "Al", "Al Pacino", 1940).getId();
        saveActsIn(actsIn, keanu, theDevilsAdvocate, new String[] { "Kevin Lomax" }, 1997);
        saveActsIn(actsIn, charlize, theDevilsAdvocate, new String[] { "Mary Ann Lomax" }, 1997);
        saveActsIn(actsIn, al, theDevilsAdvocate, new String[] { "John Milton" }, 1997);

        final String AFewGoodMen = saveMovie(movies, "AFewGoodMen", "A Few Good Men", 1992,
                "In the heart of the nation's capital, in a courthouse of the U.S. government, one man will stop at nothing to keep his honor, and one will stop at nothing to find the truth.")
                .getId();
        final String tomC = saveActor(actors, "TomC", "Tom Cruise", 1962).getId();
        final String jackN = saveActor(actors, "JackN", "Jack Nicholson", 1937).getId();
        final String demiM = saveActor(actors, "DemiM", "Demi Moore", 1962).getId();
        final String kevinB = saveActor(actors, "KevinB", "Kevin Bacon", 1958).getId();
        final String kieferS = saveActor(actors, "KieferS", "Kiefer Sutherland", 1966).getId();
        final String noahW = saveActor(actors, "NoahW", "Noah Wyle", 1971).getId();
        final String cubaG = saveActor(actors, "CubaG", "Cuba Gooding Jr.", 1968).getId();
        final String kevinP = saveActor(actors, "KevinP", "Kevin Pollak", 1957).getId();
        final String jTW = saveActor(actors, "JTW", "J.T. Walsh", 1943).getId();
        final String jamesM = saveActor(actors, "JamesM", "James Marshall", 1967).getId();
        final String christopherG = saveActor(actors, "ChristopherG", "Christopher Guest", 1948).getId();
        saveActsIn(actsIn, tomC, AFewGoodMen, new String[] { "Lt. Daniel Kaffee" }, 1992);
        saveActsIn(actsIn, jackN, AFewGoodMen, new String[] { "Col. Nathan R. Jessup" }, 1992);
        saveActsIn(actsIn, demiM, AFewGoodMen, new String[] { "Lt. Cdr. JoAnne Galloway" }, 1992);
        saveActsIn(actsIn, kevinB, AFewGoodMen, new String[] { "Capt. Jack Ross" }, 1992);
        saveActsIn(actsIn, kieferS, AFewGoodMen, new String[] { "Lt. Jonathan Kendrick" }, 1992);
        saveActsIn(actsIn, noahW, AFewGoodMen, new String[] { "Cpl. Jeffrey Barnes" }, 1992);
        saveActsIn(actsIn, cubaG, AFewGoodMen, new String[] { "Cpl. Carl Hammaker" }, 1992);
        saveActsIn(actsIn, kevinP, AFewGoodMen, new String[] { "Lt. Sam Weinberg" }, 1992);
        saveActsIn(actsIn, jTW, AFewGoodMen, new String[] { "Lt. Col. Matthew Andrew Markinson" }, 1992);
        saveActsIn(actsIn, jamesM, AFewGoodMen, new String[] { "Pfc. Louden Downey" }, 1992);
        saveActsIn(actsIn, christopherG, AFewGoodMen, new String[] { "Dr. Stone" }, 1992);

        final String topGun = saveMovie(movies, "TopGun", "Top Gun", 1986, "I feel the need, the need for speed.")
                .getId();
        final String kellyM = saveActor(actors, "KellyM", "Kelly McGillis", 1957).getId();
        final String valK = saveActor(actors, "ValK", "Val Kilmer", 1959).getId();
        final String anthonyE = saveActor(actors, "AnthonyE", "Anthony Edwards", 1962).getId();
        final String tomS = saveActor(actors, "TomS", "Tom Skerritt", 1933).getId();
        final String megR = saveActor(actors, "MegR", "Meg Ryan", 1961).getId();
        saveActsIn(actsIn, tomC, topGun, new String[] { "Maverick" }, 1986);
        saveActsIn(actsIn, kellyM, topGun, new String[] { "Charlie" }, 1986);
        saveActsIn(actsIn, valK, topGun, new String[] { "Iceman" }, 1986);
        saveActsIn(actsIn, anthonyE, topGun, new String[] { "Goose" }, 1986);
        saveActsIn(actsIn, tomS, topGun, new String[] { "Viper" }, 1986);
        saveActsIn(actsIn, megR, topGun, new String[] { "Carole" }, 1986);

        final String jerryMaguire = saveMovie(movies, "JerryMaguire", "Jerry Maguire", 2000,
                "The rest of his life begins now.").getId();
        final String reneeZ = saveActor(actors, "ReneeZ", "Renee Zellweger", 1969).getId();
        final String kellyP = saveActor(actors, "KellyP", "Kelly Preston", 1962).getId();
        final String jerryO = saveActor(actors, "JerryO", "Jerry O'Connell", 1974).getId();
        final String jayM = saveActor(actors, "JayM", "Jay Mohr", 1970).getId();
        final String bonnieH = saveActor(actors, "BonnieH", "Bonnie Hunt", 1961).getId();
        final String reginaK = saveActor(actors, "ReginaK", "Regina King", 1971).getId();
        final String jonathanL = saveActor(actors, "JonathanL", "Jonathan Lipnicki", 1996).getId();
        saveActsIn(actsIn, tomC, jerryMaguire, new String[] { "Jerry Maguire" }, 2000);
        saveActsIn(actsIn, cubaG, jerryMaguire, new String[] { "Rod Tidwell" }, 2000);
        saveActsIn(actsIn, reneeZ, jerryMaguire, new String[] { "Dorothy Boyd" }, 2000);
        saveActsIn(actsIn, kellyP, jerryMaguire, new String[] { "Avery Bishop" }, 2000);
        saveActsIn(actsIn, jerryO, jerryMaguire, new String[] { "Frank Cushman" }, 2000);
        saveActsIn(actsIn, jayM, jerryMaguire, new String[] { "Bob Sugar" }, 2000);
        saveActsIn(actsIn, bonnieH, jerryMaguire, new String[] { "Laurel Boyd" }, 2000);
        saveActsIn(actsIn, reginaK, jerryMaguire, new String[] { "Marcee Tidwell" }, 2000);
        saveActsIn(actsIn, jonathanL, jerryMaguire, new String[] { "Ray Boyd" }, 2000);

        final String standByMe = saveMovie(movies, "StandByMe", "Stand By Me", 1986,
                "For some, it's the last real taste of innocence, and the first real taste of life. But for everyone, it's the time that memories are made of.")
                .getId();
        final String riverP = saveActor(actors, "RiverP", "River Phoenix", 1970).getId();
        final String coreyF = saveActor(actors, "CoreyF", "Corey Feldman", 1971).getId();
        final String wilW = saveActor(actors, "WilW", "Wil Wheaton", 1972).getId();
        final String johnC = saveActor(actors, "JohnC", "John Cusack", 1966).getId();
        final String marshallB = saveActor(actors, "MarshallB", "Marshall Bell", 1942).getId();
        saveActsIn(actsIn, wilW, standByMe, new String[] { "Gordie Lachance" }, 1986);
        saveActsIn(actsIn, riverP, standByMe, new String[] { "Chris Chambers" }, 1986);
        saveActsIn(actsIn, jerryO, standByMe, new String[] { "Vern Tessio" }, 1986);
        saveActsIn(actsIn, coreyF, standByMe, new String[] { "Teddy Duchamp" }, 1986);
        saveActsIn(actsIn, johnC, standByMe, new String[] { "Denny Lachance" }, 1986);
        saveActsIn(actsIn, kieferS, standByMe, new String[] { "Ace Merrill" }, 1986);
        saveActsIn(actsIn, marshallB, standByMe, new String[] { "Mr. Lachance" }, 1986);

        final String asGoodAsItGets = saveMovie(movies, "AsGoodAsItGets", "As Good as It Gets", 1997,
                "A comedy from the heart that goes for the throat.").getId();
        final String helenH = saveActor(actors, "HelenH", "Helen Hunt", 1963).getId();
        final String gregK = saveActor(actors, "GregK", "Greg Kinnear", 1963).getId();
        saveActsIn(actsIn, jackN, asGoodAsItGets, new String[] { "Melvin Udall" }, 1997);
        saveActsIn(actsIn, helenH, asGoodAsItGets, new String[] { "Carol Connelly" }, 1997);
        saveActsIn(actsIn, gregK, asGoodAsItGets, new String[] { "Simon Bishop" }, 1997);
        saveActsIn(actsIn, cubaG, asGoodAsItGets, new String[] { "Frank Sachs" }, 1997);

        final String whatDreamsMayCome = saveMovie(movies, "WhatDreamsMayCome", "What Dreams May Come", 1998,
                "After life there is more. The end is just the beginning.").getId();
        final String annabellaS = saveActor(actors, "AnnabellaS", "Annabella Sciorra", 1960).getId();
        final String maxS = saveActor(actors, "MaxS", "Max von Sydow", 1929).getId();
        final String wernerH = saveActor(actors, "WernerH", "Werner Herzog", 1942).getId();
        final String robin = saveActor(actors, "Robin", "Robin Williams", 1951).getId();
        saveActsIn(actsIn, robin, whatDreamsMayCome, new String[] { "Chris Nielsen" }, 1998);
        saveActsIn(actsIn, cubaG, whatDreamsMayCome, new String[] { "Albert Lewis" }, 1998);
        saveActsIn(actsIn, annabellaS, whatDreamsMayCome, new String[] { "Annie Collins-Nielsen" }, 1998);
        saveActsIn(actsIn, maxS, whatDreamsMayCome, new String[] { "The Tracker" }, 1998);
        saveActsIn(actsIn, wernerH, whatDreamsMayCome, new String[] { "The Face" }, 1998);

        final String snowFallingonCedars = saveMovie(movies, "SnowFallingonCedars", "Snow Falling on Cedars", 1999,
                "First loves last. Forever.").getId();
        final String ethanH = saveActor(actors, "EthanH", "Ethan Hawke", 1970).getId();
        final String rickY = saveActor(actors, "RickY", "Rick Yune", 1971).getId();
        final String jamesC = saveActor(actors, "JamesC", "James Cromwell", 1940).getId();
        saveActsIn(actsIn, ethanH, snowFallingonCedars, new String[] { "Ishmael Chambers" }, 1999);
        saveActsIn(actsIn, rickY, snowFallingonCedars, new String[] { "Kazuo Miyamoto" }, 1999);
        saveActsIn(actsIn, maxS, snowFallingonCedars, new String[] { "Nels Gudmundsson" }, 1999);
        saveActsIn(actsIn, jamesC, snowFallingonCedars, new String[] { "Judge Fielding" }, 1999);

        final String youveGotMail = saveMovie(movies, "YouveGotMail", "You've Got Mail", 1998,
                "At odds in life... in love on-line.").getId();
        final String parkerP = saveActor(actors, "ParkerP", "Parker Posey", 1968).getId();
        final String daveC = saveActor(actors, "DaveC", "Dave Chappelle", 1973).getId();
        final String steveZ = saveActor(actors, "SteveZ", "Steve Zahn", 1967).getId();
        final String tomH = saveActor(actors, "TomH", "Tom Hanks", 1956).getId();
        saveActsIn(actsIn, tomH, youveGotMail, new String[] { "Joe Fox" }, 1998);
        saveActsIn(actsIn, megR, youveGotMail, new String[] { "Kathleen Kelly" }, 1998);
        saveActsIn(actsIn, gregK, youveGotMail, new String[] { "Frank Navasky" }, 1998);
        saveActsIn(actsIn, parkerP, youveGotMail, new String[] { "Patricia Eden" }, 1998);
        saveActsIn(actsIn, daveC, youveGotMail, new String[] { "Kevin Jackson" }, 1998);
        saveActsIn(actsIn, steveZ, youveGotMail, new String[] { "George Pappas" }, 1998);

        final String sleeplessInSeattle = saveMovie(movies, "SleeplessInSeattle", "Sleepless in Seattle", 1993,
                "What if someone you never met, someone you never saw, someone you never knew was the only someone for you?")
                .getId();
        final String ritaW = saveActor(actors, "RitaW", "Rita Wilson", 1956).getId();
        final String billPull = saveActor(actors, "BillPull", "Bill Pullman", 1953).getId();
        final String victorG = saveActor(actors, "VictorG", "Victor Garber", 1949).getId();
        final String rosieO = saveActor(actors, "RosieO", "Rosie O'Donnell", 1962).getId();
        saveActsIn(actsIn, tomH, sleeplessInSeattle, new String[] { "Sam Baldwin" }, 1993);
        saveActsIn(actsIn, megR, sleeplessInSeattle, new String[] { "Annie Reed" }, 1993);
        saveActsIn(actsIn, ritaW, sleeplessInSeattle, new String[] { "Suzy" }, 1993);
        saveActsIn(actsIn, billPull, sleeplessInSeattle, new String[] { "Walter" }, 1993);
        saveActsIn(actsIn, victorG, sleeplessInSeattle, new String[] { "Greg" }, 1993);
        saveActsIn(actsIn, rosieO, sleeplessInSeattle, new String[] { "Becky" }, 1993);

        final String joeVersustheVolcano = saveMovie(movies, "JoeVersustheVolcano", "Joe Versus the Volcano", 1990,
                "A story of love, lava and burning desire.").getId();
        final String nathan = saveActor(actors, "Nathan", "Nathan Lane", 1956).getId();
        saveActsIn(actsIn, tomH, joeVersustheVolcano, new String[] { "Joe Banks" }, 1990);
        saveActsIn(actsIn, megR, joeVersustheVolcano,
                new String[] { "DeDe', 'Angelica Graynamore', 'Patricia Graynamore" }, 1990);
        saveActsIn(actsIn, nathan, joeVersustheVolcano, new String[] { "Baw" }, 1990);

        final String whenHarryMetSally = saveMovie(movies, "WhenHarryMetSally", "When Harry Met Sally", 1998,
                "At odds in life... in love on-line.").getId();
        final String billyC = saveActor(actors, "BillyC", "Billy Crystal", 1948).getId();
        final String carrieF = saveActor(actors, "CarrieF", "Carrie Fisher", 1956).getId();
        final String brunoK = saveActor(actors, "BrunoK", "Bruno Kirby", 1949).getId();
        saveActsIn(actsIn, billyC, whenHarryMetSally, new String[] { "Harry Burns" }, 1998);
        saveActsIn(actsIn, megR, whenHarryMetSally, new String[] { "Sally Albright" }, 1998);
        saveActsIn(actsIn, carrieF, whenHarryMetSally, new String[] { "Marie" }, 1998);
        saveActsIn(actsIn, brunoK, whenHarryMetSally, new String[] { "Jess" }, 1998);



    }

    private static DocumentCreateEntity<BaseDocument> saveMovie(
            final ArangoCollection movies,
            final String key,
            final String title,
            final int released,
            final String tagline) {
        final BaseDocument value = new BaseDocument();
        value.setKey(key);
        value.addAttribute("title", title);
        value.addAttribute("released", released);
        value.addAttribute("tagline", tagline);
        return movies.insertDocument(value);
    }

    private static DocumentCreateEntity<BaseDocument> saveActor(
            final ArangoCollection actors,
            final String key,
            final String name,
            final int born) {
        final BaseDocument value = new BaseDocument();
        value.setKey(key);
        value.addAttribute("name", name);
        value.addAttribute("born", born);
        return actors.insertDocument(value);
    }

    private static DocumentCreateEntity<BaseEdgeDocument> saveActsIn(
            final ArangoCollection actsIn,
            final String actor,
            final String movie,
            final String[] roles,
            final int year) {
        final BaseEdgeDocument value = new BaseEdgeDocument();
        value.setFrom(actor);
        value.setTo(movie);
        value.addAttribute("roles", roles);
        value.addAttribute("year", year);
        return actsIn.insertDocument(value);
    }

    private static EdgeDefinition saveEdgeDefinition(final ArangoGraph graph,
                                                     final String actor,
                                                     final String movie,
                                                     final String[] roles,
                                                     final int year) {
        return null;
    }
}
