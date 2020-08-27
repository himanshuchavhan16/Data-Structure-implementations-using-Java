import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Anagrams {
    private final Integer[] primes;
    private Map<Character,Integer> letterTable;
    private Map<Long, ArrayList<String>> anagramTable;

/**
         * Constructor of class Anagram
         * Initializes instance variables
         */
    public Anagrams() {
        this.primes = new Integer[]{2 , 3 , 5 , 7 , 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
        this.letterTable = new HashMap<Character, Integer>();
        buildLetterTable();
        this.anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    /**
     * Build Letter Table
     * Puts prime number against each character in instance variable letterTable
     */
    private void buildLetterTable(){
        for(int i=0;i<26; i++)
        {
            letterTable.put(Character.toChars(i+97)[0],primes[i]);
        }
    }

    /**
     * Add word
     * @param s String to be added to map
     * Puts word against its hash in map anagramTable
     */
    private void addWord(String s)
    {
        long myHash = myHashCode(s);
        if (anagramTable.containsKey(myHash))
        {
            anagramTable.get(myHash).add(s);
        }
        else
        {
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            anagramTable.put(myHash,list);
        }

    }

    /**
     * myHashCode
     * @param s String for which hash value has to be generated
     * @return hash value generated for String s
     */
    private Long myHashCode(String s)
    {
        long myHash = 1;
        for(int i = 0; i<s.length(); i++)
        {
            myHash = myHash * letterTable.get(s.charAt(i));
        }
        return myHash;
    }

    /**
     * processFile
     * @param s Filename from which words are to be read
     */
    public void processFile(String s) throws IOException
    {
       FileInputStream fstream = new FileInputStream(s);
       BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
       String strLine;
       while ((strLine = br.readLine())!=null)
       {
           this.addWord(strLine);
       }
       br.close();
    }

    /**
     * getMaxEntries
     * @return Arraylist of all the lists of anagrams with maxSize
     */
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
    {
        int maxSize = anagramTable.values().stream().mapToInt(ArrayList::size).max().getAsInt();
        return anagramTable.entrySet().stream()
                .filter(entry -> entry.getValue().size() == maxSize)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args)
    {
        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();
        try{
            a.processFile("words_alpha.txt");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time: "+seconds);
        for(Map.Entry<Long,ArrayList<String>> entry : maxEntries) {
            System.out.println("Key of max anagrams: " + entry.getKey());
            System.out.println("List of max anagrams: " + entry.getValue());
            System.out.println("Length of list of max anagram: "+entry.getValue().size());
        }

    }
}
