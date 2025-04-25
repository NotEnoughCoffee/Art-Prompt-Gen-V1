package dev.tg;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

public class Rolls {
    private static boolean normalRarity;
    private static boolean rareRarity;
    private static boolean superRareRarity;
    private static boolean rarityOverride = false;
    protected static String nextLine = "\n";

    public static String simpleChallengeRollGUI(){
        String species = "SPECIES";

        boolean flag = true;
        int count = rollCount();
        int count1 = count;
        AllCategories[] selection = AllCategories.values();
        String selection1 = String.valueOf(selection[count1]);
        String selection2 = "null";
        count = newCount();

        do{
            if(count != count1) {
                selection2 = String.valueOf(selection[count]);
                flag = false;
            } else count = newCount();
        } while(flag);
        String simpleThemes = rollList(selection1) + " and " + rollList(selection2);
        String species1 = String.valueOf(rollList(species));
        String species2 = String.valueOf(rollList(species));
        String species3 = String.valueOf(rollList(species));
        species1 = aOrAn(species1) + " " + species1;
        species2 = aOrAn(species2) + " " + species2;
        species3 = aOrAn(species3) + " " + species3;

        return nextLine + nextLine
                + "*<><>*<><><==|[]|==><><>*<><>*"
                + nextLine
                + "Your Simple Challenge Today is:"
                + nextLine
                + "Draw something with at least one of these:"
                + nextLine
                + species1 + ", " + species2 + ", and/or " + species3
                + nextLine
                + "Your Themes are: "
                + nextLine
                + simpleThemes
                + nextLine
                + "*<><>*<><><==|[]|==><><>*<><>*"
                + nextLine;
    }
    public static void simpleChallengeRoll(){
        String species = "SPECIES";

        System.out.println("Your Simple Challenge Today is:");
        System.out.println("Draw something with at least one of these:");
        System.out.println("A " + rollList(species) + ", " + rollList(species) + ", and/or " + rollList(species));
        System.out.println("Your Challenges are: ");

        boolean flag = true;
        int count = rollCount();
        int count1 = count;
        AllCategories[] selection = AllCategories.values();
        String selection1 = String.valueOf(selection[count1]);
        String selection2 = "null";
        count = newCount();

        do{
            if(count != count1) {
                selection2 = String.valueOf(selection[count]);
                flag = false;
            } else count = newCount();
        } while(flag);
        System.out.println(rollList(selection1) + " and " + rollList(selection2));
    }

    public static String dailyChallengeRollGUI() {
        String species = "SPECIES";
        boolean flag = true;
        int characterCount = rollCount();

        StringBuilder speciesSelections = new StringBuilder();
        String rollResult;
        for(int i = characterCount; i >= 0; i--) {

            if (i >= 2) {
                rollResult = String.valueOf(rollList(species));
                speciesSelections.append(aOrAn(rollResult)).append(" ").append(rollResult).append(", ");
            } else if (i == 1) {
                rollResult = String.valueOf(rollList(species));
                speciesSelections.append(aOrAn(rollResult)).append(" ").append(rollResult).append(", and ");
            } else {
                rollResult = String.valueOf(rollList(species));
                speciesSelections.append(aOrAn(rollResult)).append(" ").append(rollResult);
            }
        }


        StringBuilder speciesModSelection = new StringBuilder(nextLine);
        if (new Random().nextInt(7) == 0) {
            var speciesMod = SpeciesModifier.values();
            String newMod = String.valueOf(speciesMod[new Random().nextInt(speciesMod.length - 1)]);
            if(newMod.equals(String.valueOf(SpeciesModifier.HYBRID))) {
                //rarityOverride = true; //leave in place to override rarity for Rare_Hybrid if wanted
                rollResult = String.valueOf(rollList(species));
                speciesModSelection.append("Modification: ").append(fixStringFormat(newMod)).append(" - ").append(rollResult).append(nextLine);
            } else {
                speciesModSelection.append("Modification: ").append(fixStringFormat(newMod)).append(nextLine);
            }
        }
        int count1 = newCount();
        AllCategories[] selection = AllCategories.values();
        String selection1 = String.valueOf(selection[count1]);
        String selection2 = "null";
        int count = newCount();
        do {
            if (count != count1) {
                selection2 = String.valueOf(selection[count]);
                flag = false;
            } else count = newCount();
        } while (flag);


        return    nextLine
                + "*<><>*<><><==|[]|==><><>*<><>*"
                + nextLine
                + "For Today's Drawing Challenge,"
                + nextLine
                + "I would like you to draw:"
                + nextLine
                + "A " + characterCount(characterCount) + " piece."
                + nextLine
                + "With " + speciesSelections
                + speciesModSelection
                + "Your challenges are: " + selection1 + " and " + selection2
                + nextLine
                + selection1 + ": " + rollList(selection1)
                + nextLine
                + selection2 + ": " + rollList(selection2)
                + nextLine
                + "*<><>*<><><==|[]|==><><>*<><>*";

    }
    public static void dailyChallengeRoll(){
        String species = "SPECIES";
        boolean flag = true;
        int count = rollCount();
        // this sets count to a weighted roll to determine # of characters.

        System.out.println();
        System.out.println("For Today's Drawing Challenge I would like you to draw:");
        System.out.println("A " + characterCount(count) + " piece");

        System.out.print("With: " );
        for(int i = 0; i <= count; i++){
            //this creates a list of species based on # of characters with weighted rolls.
            switch(count - i){
                case 4, 3, 2 -> System.out.print(rollList(species) + ", ");
                case 1 -> System.out.print(rollList(species) + ", and ");
                case 0 -> System.out.println(rollList(species));
                default -> System.out.println("Error");
            }
        }
        if(new Random().nextInt(8) == 0){
            //this adds a small chance for a character modification roll
            var speciesMod = SpeciesModifier.values();
            System.out.println("Modification: " + speciesMod[new Random().nextInt(speciesMod.length -1)]);
        }

        System.out.println("Your Challenges are:");
        count = newCount();
        int count1 = count;
        AllCategories[] selection = AllCategories.values();

        //roll for theme selection1
        String selection1 = String.valueOf(selection[count1]);
        System.out.print(selection1 + " and ");

        //roll for theme selection2, checking it's not a duplicate of selection1
        String selection2 = "null";
        count = newCount();
        do{
            if(count != count1) {
                selection2 = String.valueOf(selection[count]);
                System.out.println(selection2);
                flag = false;
            } else count = newCount();
        } while(flag);

        //using selections above, makes a roll to determine specific challenge
        System.out.print(selection1 + ": ");
        System.out.println(rollList(selection1));

        System.out.print(selection2 + ": ");
        System.out.println(rollList(selection2));
    }

    private static void resetRarity(){
        // resets all booleans to false
        normalRarity = false;
        rareRarity = false;
        superRareRarity = false;
    }
    private static void rollRarity(){
        Random rand = new Random();
        resetRarity();
        normalRarity = rand.nextInt(10) <=5;
        rareRarity = rand.nextInt(15) <=5;
        superRareRarity = rand.nextInt(50) <=5;
    }

    private static int newCount(){
        // returns a normal roll for numbers 0-4
        return new Random().nextInt(5);
    }
    private static int rollCount(){
        // returns a weighted roll for numbers 0-4
        // Average rolls are roughly:
        // 0 87-90%
        // 1-3 2-4% each
        // 4 1-1.5%
        Random rand = new Random();
        int count = newCount();

        boolean zero = rand.nextInt(10) <= 7;
        boolean one = rand.nextInt(25) <= 5;
        boolean two = rand.nextInt(35) <= 5;
        boolean three = rand.nextInt(50) <= 5;
        boolean four = rand.nextInt(100) <= 5;

        switch(count){
            case 0 -> { if(zero){ return 0; } }
            case 1 -> { if(one){ return 1; } }
            case 2 -> { if(two){ return 2; } }
            case 3 -> { if(three){ return 3; } }
            case 4 -> { if(four){ return 4; } }
            default -> { return 0; }
        }
        return 0;
    }

    private static String characterCount(int count){
        // returns a String based on rollCount input
        switch(count){
            case 0 -> { return "1 Character"; }
            case 1 -> { return "2 Character"; }
            case 2 -> { return "3 Character"; }
            case 3 -> { return "4 Character"; }
            case 4 -> { return "Crowd"; }
            default -> { return "null"; }
        }
    }
    private static Enum<? extends Enum<?>>[] getRollType (String rollType){
        return
                switch (rollType.toUpperCase()) {
                    case "SPECIES" -> Species.values();
                    case "SPECIES_MODIFIER" -> SpeciesModifier.values();
                    case "THEMES" -> Themes.values();
                    case "COLOR" -> Color.values();
                    case "SETTING" -> Setting.values();
                    case "TIME" -> Time.values();
                    case "STYLE" -> Style.values();
                    default -> null;
                };

    }

    private static String getRarityOfRoll(Enum<? extends Enum<?>>[] roll, int randomSelection) {
        String rarity = "null";
        if (Arrays.equals(roll, Species.values())) {
            var species = Species.values();
            rarity = species[randomSelection].getRarity();
        } else if (Arrays.equals(roll, Themes.values())) {
            var themes = Themes.values();
            rarity = themes[randomSelection].getRarity();
        } else if (Arrays.equals(roll, Color.values())) {
            var color = Color.values();
            rarity = color[randomSelection].getRarity();
        } else if (Arrays.equals(roll, Setting.values())) {
            var setting = Setting.values();
            rarity = setting[randomSelection].getRarity();
        } else if (Arrays.equals(roll, Time.values())){
            var time = Time.values();
            rarity = time[randomSelection].getRarity();
        } else if (Arrays.equals(roll, Style.values())){
            var style = Style.values();
            rarity = style[randomSelection].getRarity();
        }
        return rarity;
    }
    private static Object rollList(String rollType) {
        resetRarity();
        boolean flag = true;
        var roll = getRollType(rollType);
        int typeCount = 0;

        if (roll != null) {
            typeCount = roll.length;
        }

        if (roll != null) {
            do {
                flag = false;
                rollRarity();
                int randomSelection = new Random().nextInt(typeCount);
                String rarity = getRarityOfRoll(roll, randomSelection);

                if(rarityOverride) {
                    rarity = "OVERRIDE";
                }

                switch (rarity) {
                    case "OVERRIDE" -> {
                        superRareRarity = true;
                        if(Objects.equals(getRarityOfRoll(roll, randomSelection), "SUPER_RARE")) {
                            return fixStringFormat(String.valueOf(roll[randomSelection]));
                        } else {
                            flag = true;
                        }
                    }
                    case "NORMAL" -> {
                        if (normalRarity) {
                            resetRarity();
                            return fixStringFormat(String.valueOf(roll[randomSelection]));
                        } else {
                            flag = true;
                        }
                    }
                    case "RARE" -> {
                        if (rareRarity) {
                            resetRarity();
                            return fixStringFormat(String.valueOf(roll[randomSelection]));
                        } else {
                            flag = true;
                        }
                    }
                    case "SUPER_RARE" -> {
                        if (superRareRarity) {
                            resetRarity();
                            return fixStringFormat(String.valueOf(roll[randomSelection]));
                        } else {
                            flag = true;
                        }
                    }
                    case "null" -> {
                        resetRarity();
                        return fixStringFormat(String.valueOf(roll[randomSelection]));
                    }
                    default -> {
                        flag = true;
                    }
                }
            } while (true);
        }
        return null;
    }

    public static String fixStringFormat(String rollResult) {

        String toFix = rollResult;

        if(toFix == null || toFix.isEmpty()) {
            return null;
        }

        //if(!toFix.contains("_")){

        //}

        if(toFix.contains("_")) {
            toFix = rollResult.replace("_", " ");
            String[] words = toFix.split(" ");
            StringJoiner multiWord = new StringJoiner(" ");

            for(String word : words) {
                word = word.toLowerCase();
                word = word.substring(0,1).toUpperCase()
                        + word.substring(1);
                multiWord.add(word);
            }
            toFix = String.valueOf(multiWord);
            int lastChar = toFix.length();
            if(String.valueOf(toFix.charAt(lastChar - 1)).equals(" ")) {
                toFix = toFix.substring(lastChar - 2, lastChar -1);
            }

        } else {
            toFix = toFix.toLowerCase();
            toFix = toFix.substring(0, 1).toUpperCase() + toFix.substring(1);
        }
        return toFix;
    }
    private static String aOrAn(String string) {

        switch(String.valueOf(string.charAt(0)).toUpperCase()) {
            case "A", "E", "I", "O", "U" -> { return "an"; }
            default -> { return "a"; }
        }

    }
}