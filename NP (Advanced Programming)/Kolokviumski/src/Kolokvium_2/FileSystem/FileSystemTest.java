package Kolokvium_2.FileSystem;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */
public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

// Your code here
class File implements Comparable<File>{
    String name;
    int size;
    LocalDateTime creationDate;

    public File(String name, int size, LocalDateTime creationDate) {
        this.name = name;
        this.size = size;
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(File o) {
        int rule1= this.creationDate.compareTo(o.creationDate);
        if(rule1==0){
            int rule2 = this.name.compareTo(o.name);
            if(rule2==0){
                return Integer.compare(this.size, o.size);
            }return rule2;
        }return  rule1;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        //.a.txt       250B 2016-12-19T00:00

        return String.format("%-11s %4dB %d-%02d-%02dT00:00",name, size,
                creationDate.getYear(), creationDate.getMonth().getValue(), creationDate.getDayOfMonth());
    }

    public String getName() {
        return name;
    }
}

class FileSystem{
    Map<Character, TreeSet<File>> fileMap;
    Map<Integer, Set<File>> mapByYear;
    Map<String, Long> mapByMonthAndDay;

    public FileSystem() {
        this.fileMap=new HashMap<>();
        this.mapByYear=new HashMap<>();
        this.mapByMonthAndDay=new HashMap<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt){
        int year = createdAt.getYear();
        String month = createdAt.getMonth().toString().toUpperCase();
        int day = createdAt.getDayOfMonth();
        String monthAndDay=month+"-"+day;
        if(!fileMap.containsKey(folder)){
            fileMap.put(folder, new TreeSet<>());
        }if(!mapByYear.containsKey(year)){
            mapByYear.put(year, new TreeSet<>());
        }if(!mapByMonthAndDay.containsKey(monthAndDay)){
            mapByMonthAndDay.put(monthAndDay, (long)0);
        }
        fileMap.get(folder).add(new File(name,size, createdAt));
        mapByYear.get(year).add(new File(name,size, createdAt));
        long newSize = (long)(mapByMonthAndDay.get(monthAndDay)+size);
        mapByMonthAndDay.put(monthAndDay, newSize);
    }
    public List<File> findAllHiddenFilesWithSizeLessThen(int size){
        return fileMap.values().stream().flatMap(Collection::stream).filter(file->file.getSize()<size)
                .filter(file->file.getName().startsWith("."))
                .collect(Collectors.toList());
    }
    public int totalSizeOfFilesFromFolders(List<Character> folders){
        //fileMap.forEach((k,v)->folders.contains(k))
         //       .map(v->v.stream().mapToInt(f->f.getSize()).sum())
//        fileMap.keySet().stream().filter(key->folders.contains(key))
        Map<Character, TreeSet<File>> newMap = new HashMap<>();
        fileMap.forEach((k,v)-> {
            if (folders.contains(k)) {
                newMap.put(k,v);
            }
        });
        return newMap.values().stream().map(v->v.stream().mapToInt(f->f.getSize()).sum()).mapToInt(i->i).sum();
    }
    public Map<Integer, Set<File>> byYear(){
        return mapByYear;
    }
    public Map<String, Long> sizeByMonthAndDay(){
        return mapByMonthAndDay;
    }
}


