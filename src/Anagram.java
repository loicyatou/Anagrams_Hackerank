import java.util.HashMap;

public class Anagram {

    //Solved in O(1)

    public static void main(String[] args){

        System.out.println(anagram("aaabbb"));

    }

    public static int anagram(String s) {
        // Write your code here

        //constraint handling. If the size of the string is not odd then it should fail
        if(s.length() % 2 != 0){
            return -1;
        }

        //split the string into two equal lengths
        int middle = s.length() / 2;

        String tmp = s.substring(0,middle);
        String tmp2 = s.substring(middle);


        //add ecery character into the hashmap only once.
        HashMap<Character,Integer> letters = new HashMap<>();
        HashMap<Character,Integer> letters2 = new HashMap<>();


        for(int i = 0; i < tmp.length(); i++){
            Character c = tmp.charAt(i);
            Character b = tmp2.charAt(i);

            if(letters.containsKey(c)){
                letters.put(c,letters.get(c) + 1);
            } else {
                letters.put(c,1);
            }

            if(letters2.containsKey(b)){
                letters2.put(b,letters2.get(b) + 1);
            } else {
                letters2.put(b,1);
            }
        }

        //calculate how many letters you would need to change to match the other substring
        int firstChange = 0;
        int secondChange = 0;

        HashMap<Character, Integer> test = new HashMap<>();
        HashMap<Character, Integer> test2 = new HashMap<>();


        //for the first substring
        for(int i = 0; i < tmp.length(); i++) {
            Character c = tmp.charAt(i);

            if (letters.containsKey(c) && letters2.containsKey(c) && !test.containsKey(c)) {
                if (letters.get(c) > letters2.get(c)) {
                    firstChange += (letters.get(c) - letters2.get(c));
                    test.put(c,i);
                }
            } else if (letters.containsKey(c) & !letters2.containsKey(c) && !test.containsKey(c)) {
                firstChange += letters.get(c);
                test2.put(c,i);
            }
        }

        //for the second substring
        for(int i = 0; i < tmp2.length(); i++) {
            Character d = tmp2.charAt(i);

            if (letters.containsKey(d) && letters2.containsKey(d) && !test2.containsKey(d)) {
                if (letters.get(d) < letters2.get(d)) {
                    secondChange += (letters2.get(d) - letters.get(d));
                    test2.put(d, i);
                }
            } else if (!letters.containsKey(d) & letters2.containsKey(d) && !test2.containsKey(d) ) {
                secondChange += letters2.get(d);
                test2.put(d,i);
            }
        }

        //return the smallest of the two
        return Math.min(firstChange, secondChange);

    }

}
