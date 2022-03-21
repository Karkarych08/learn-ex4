import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ex4 {

    public static List<String> getAllWords(String filename) throws IOException{
        var splitter = Pattern.compile("[\\p{Punct}\\d\\s-]+]");
        return Files.lines((Path.of(filename))).flatMap(splitter::splitAsStream).collect(Collectors.toList());
    }

    public static void writeResultToFile(String filename, Collection<String> lines) throws IOException{
        Files.write(Path.of(filename),lines);
    }

    public static void deleteAllDuplicates(List<String> words){
        var set = new LinkedHashSet<>(words);
        words.clear();
        words.addAll(set);
    }

    public static LinkedList<String> toListofWords(List<String> list){
        var list_of_words = new LinkedList<String>();
        for (String s:list) {
            if (!(s.isEmpty())) {
                s = s.toLowerCase();
                String[] words = s.split("[\\s\\p{Punct}\\d]++");
                for (String w:words) {
                    if (w.length()>=1)
                    list_of_words.add(w);
                }
            }
        }
        return list_of_words;
    }

    public static TreeMap<String, Integer> toMapofSymbols(List<String> list){
        var map_of_words = new TreeMap<String, Integer>();
        for (String s:list) {
            if (!(s.isEmpty())) {
                s = s.toLowerCase();
                String[] words = s.split("[\\s\\p{Punct}\\d]++");
                for (String w:words) {
                    for (String sym: w.split((""))) {
                        if (sym.length() >= 1) {
                            if (map_of_words.containsKey(sym)) {
                                map_of_words.put(sym, map_of_words.get(sym) + 1);
                            } else {
                                map_of_words.put(sym, 1);
                            }
                        }
                    }
                }
            }
        }
        return map_of_words;
    }

    public static TreeMap<String, Integer> toMapofWords(List<String> list){
        var map_of_words = new TreeMap<String, Integer>();
        for (String s:list) {
            if (!(s.isEmpty())) {
                s = s.toLowerCase();
                String[] words = s.split("[\\s\\p{Punct}\\d]++");
                for (String w:words) {
                    if (w.length()>=1)
                        if (map_of_words.containsKey(w)){
                            map_of_words.put(w,map_of_words.get(w)+1);
                        }else{
                            map_of_words.put(w,1);
                        }
                }
            }
        }
        return map_of_words;
    }

    public static TreeMap<Integer, Integer> toMapofWordslength(List<String> list){
        var map_of_words = new TreeMap<Integer, Integer>();
        int length;
        for (String s:list) {
            if (!(s.isEmpty())) {
                s = s.toLowerCase();
                String[] words = s.split("[\\s\\p{Punct}\\d]++");
                for (String w:words) {
                    length = w.length();
                    if (w.length()>=1)
                        if (map_of_words.containsKey(length)){
                            map_of_words.put(length,map_of_words.get(length)+1);
                        }else{
                            map_of_words.put(length,1);
                        }
                }
            }
        }
        return map_of_words;
    }

    public static Map<String, Integer> SortMapbyValue(Map<String, Integer> map){
        List<Integer> values = new ArrayList<>(map.values());
        List<String> keys = new ArrayList<>(map.keySet());
        values.sort(((o1, o2) -> o2-o1));
        Map<String, Integer> res = new LinkedHashMap<>();
        for (Integer value : values) {
            for (String s : keys) {
                if (map.get(s) != null) {
                    Integer str = map.get(s);
                    if (str.equals(value)) {
                        res.put(s, value);
                        map.remove(s);
                    }
                }
            }
        }
        return res;
    }

    public static void deleteByLength(List<String> words, Integer min_length, Integer max_length){
        words.removeIf(s -> (s.length() < min_length) || (s.length() > max_length));
    }

    public static void deleteByLength(TreeMap<String, Integer> words, Integer min_length, Integer max_length){
        List<String> list_of_keys = new LinkedList< >();
        for (Map.Entry<String, Integer> pair : words.entrySet()) {
            if ((pair.getKey().length()<min_length) || (pair.getKey().length()>max_length)){
                list_of_keys.add(pair.getKey());
            }
        }
        for (String s:list_of_keys){
            words.remove(s);
        }
    }

    public static void ex1() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result1.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words = toListofWords(list_All_words);
        deleteAllDuplicates(list_of_words);
        writeResultToFile(result,list_of_words);
        System.out.println("ex1 is done");
    }

    public static void ex1a() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result1a.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words = toListofWords(list_All_words);
        deleteAllDuplicates(list_of_words);
        Collections.sort(list_of_words);
        writeResultToFile(result,list_of_words);
        System.out.println("ex1a is done");
    }

    public static void ex1b() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result1b.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words = toListofWords(list_All_words);
        deleteAllDuplicates(list_of_words);
        list_of_words.sort((o1, o2) -> o2.length() - o1.length());
        writeResultToFile(result,list_of_words);
        System.out.println("ex1b is done");
    }

    public static void ex1c() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result1c.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words = toListofWords(list_All_words);
        deleteAllDuplicates(list_of_words);
        list_of_words.sort(Comparator.comparingInt(String::length));
        deleteByLength(list_of_words,4,7);
        writeResultToFile(result,list_of_words);
        System.out.println("ex1c is done");
    }

    public static void ex2() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result2.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words =toListofWords(list_All_words);
        List<String> count_of_words = new ArrayList<>();
        Collections.sort(list_of_words);
        int count = 1;
        for (int i=0; i<list_of_words.size()-1;i++){
            if (list_of_words.get(i).equals(list_of_words.get(i+1))){
                count++;
            }else{
                count_of_words.add(list_of_words.get(i) + " - " + count);
                count = 1;
            }
        }
        count_of_words.add(list_of_words.get(list_of_words.size()-1) + " - " + count);
        writeResultToFile(result,count_of_words);
        System.out.println("ex2 is done");
    }

    public static void ex2a() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result2a.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<String, Integer> map_of_words = toMapofWords(list_All_words);
        writeResultToFile(result,map_of_words.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex2a is done");
    }

    public static void ex2b() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result2b.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<String, Integer> map_of_words = toMapofWords(list_All_words);
        deleteByLength(map_of_words, 3, 7);
        writeResultToFile(result,map_of_words.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex2b is done");
    }

    public static void ex3() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result3.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<Integer, Integer> map_of_words = toMapofWordslength(list_All_words);
        writeResultToFile(result,map_of_words.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex3 is done");
    }

    public static void ex04() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result04.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<Integer, Integer> map_of_words = toMapofWordslength(list_All_words);
        int symbols = 0;
        for (Map.Entry entry:map_of_words.entrySet()) {
            symbols = symbols + (entry.getKey().hashCode() * entry.getValue().hashCode());
        }
        List<Integer> res = new ArrayList<>();
        res.add(symbols);
        writeResultToFile(result,res.stream().map(Object::toString).toList());
        System.out.println("ex04 is done");
    }

    public static void ex5() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result5.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<String, Integer> map_of_symbols = toMapofSymbols(list_All_words);
        writeResultToFile(result,map_of_symbols.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex5 is done");
    }

    public static void ex5c() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result5с.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<String, Integer> map_of_symbols = toMapofSymbols(list_All_words);
        Map<String, Integer> res =SortMapbyValue(map_of_symbols);
        writeResultToFile(result,res.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex5c is done");
    }

    public static void ex6() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\lenin.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Result6.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<Integer, Integer> map_of_symbols = toMapofWordslength(list_All_words);
        List<Integer> LengthWords = new ArrayList(map_of_symbols.keySet());
        Collections.sort(LengthWords);
        Map<String, Integer> WordTopMap = SortMapbyValue(toMapofWords(list_All_words));
        List<String> WordTop = new ArrayList(WordTopMap.keySet());
        Map<Integer, List<String>> res = new HashMap<>();
        for (int Length : LengthWords) {
            List<String> top_of_words = new ArrayList<>();
            int count = 0;
            int i = 0;
            while ((count !=15) && (i!= (WordTop.size()))){
                if (WordTop.get(i).length() == Length){
                    top_of_words.add(WordTop.get(i));
                    count++;
                }
                i++;
            }
            res.put(Length,top_of_words);
        }
        writeResultToFile(result,res.entrySet().stream().map(Object::toString).toList());
        System.out.println("ex6 is done");
    }

    public static void testex2() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\test.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Resulttest2.txt";
        List<String> list_All_words = getAllWords(source);
        List<String> list_of_words =toListofWords(list_All_words);
        List<String> count_of_words = new ArrayList<>();
        Collections.sort(list_of_words);
        int count = 1;
        for (int i=0; i<list_of_words.size()-1;i++){
            if (list_of_words.get(i).equals(list_of_words.get(i+1))){
                count++;
            }else{
                count_of_words.add(list_of_words.get(i) + " - " + count);
                count = 1;
            }
        }
        count_of_words.add(list_of_words.get(list_of_words.size()-1) + " - " + count);
        writeResultToFile(result,count_of_words);
        System.out.println("testex2 is done");
    }

    public static void testex6() throws IOException {
        String source = "D:\\rakmo\\Documents\\ex4\\test.txt";
        String result = "D:\\rakmo\\Documents\\ex4\\Resulttest6.txt";
        List<String> list_All_words = getAllWords(source);
        TreeMap<Integer, Integer> map_of_symbols = toMapofWordslength(list_All_words);
        List<Integer> LengthWords = new ArrayList(map_of_symbols.keySet());
        Collections.sort(LengthWords);
        Map<String, Integer> WordTopMap = SortMapbyValue(toMapofWords(list_All_words));
        List<String> WordTop = new ArrayList(WordTopMap.keySet());
        Map<Integer, List<String>> res = new HashMap<>();
        for (int Length : LengthWords) {
            List<String> top_of_words = new ArrayList<>();
            int count = 0;
            int i = 0;
            while ((count !=15) && (i!= (WordTop.size()))){
                if (WordTop.get(i).length() == Length){
                    top_of_words.add(WordTop.get(i));
                    count++;
                }
                i++;
            }
            res.put(Length,top_of_words);
        }
        writeResultToFile(result,res.entrySet().stream().map(Object::toString).toList());
        System.out.println("testex6 is done");
    }

    public static void main(String[] args) throws IOException {
        ex1();
        ex1a();
        ex1b();
        ex1c();
        testex2();
        ex2a();
        ex2b();
        ex3();
        ex04();
        ex5();
        ex5c();
        testex6();
    }
}
