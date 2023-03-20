package Kolokvium_1.FileSystem;

import java.util.*;
import java.util.stream.Collectors;

public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input

        Scanner sc = new Scanner (System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());

    }
}
class FileNameExistsException extends Exception{
    private String fileName="";
    private String folderName="";

    public FileNameExistsException(String fileName, String folderName) {
        super();
        this.fileName = fileName;
        this.folderName = folderName;
    }
    public String getMessage(){
        return String.format("There is already a file named %s in the folder %s",fileName, folderName);
    }

}
interface IFile{
    public String getFileName();
    public long getFileSize();
    public String getFileInfo(String tabs);
    public void sortBySize();
    public long findLargestFile ();
    public String getType();
}
class File implements IFile{
    String fileName;
    long fileSize;

    public File(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String getFileInfo(String tabs) {
        return tabs + String.format("File name: %10s File size: %10d\n", fileName, fileSize);
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return fileSize;
    }

    @Override
    public String getType() {
        return "File";
    }


}

class Folder implements IFile{
    String folderName;

    List<IFile> files=new ArrayList<>();

    public Folder(String folderName) {

        this.folderName = folderName;
    }

    void addFile (IFile file) throws FileNameExistsException {
        //FileNameExistsException
        for(IFile i:files){
            if(i.getFileName().equals(file.getFileName())){
                throw new FileNameExistsException(i.getFileName(), folderName);
            }
        }
        files.add(file);

    }

    @Override
    public String getFileName() {
        return folderName;
    }

    @Override
    public long getFileSize() {
        return files.stream().mapToLong(p->p.getFileSize()).sum();
    }

    @Override
    public String getFileInfo(String tabs) {
        String result = tabs + String.format("Folder name: %10s Folder size: %10d\n", folderName, getFileSize());
        for(IFile file : files) {
            result +=  file.getFileInfo(tabs + "\t");
        }
        return result;
    }

    public List<IFile> getFiles() {
        return files;
    }

    @Override
    public void sortBySize() {
        List<IFile> sortedFiles = files.stream().sorted(Comparator.comparing(IFile::getFileSize)).collect(Collectors.toList());
        files.stream().filter(p->p.getType().equals("Folder")).forEach(IFile::sortBySize);
        files = (ArrayList<IFile>) sortedFiles;
    }

    @Override
    public long findLargestFile() {
        List<Long> sizes = new ArrayList<>();
        sizes.add(files.stream().filter(file -> file.getType().equals("File"))
                .mapToLong(IFile::getFileSize)
                .max().orElse(0));
        for(IFile file : files) {
            sizes.add(file.findLargestFile());
        }
        return sizes.stream().mapToLong(Long::longValue).max().orElse(0);
    }

    @Override
    public String getType() {
        return "Folder";
    }

    public String getFileType() {
        return "Folder";
    }
}
class FileSystem{
    Folder rootDirectory = new Folder("root");

    FileSystem(){}
    void addFile (IFile file) throws FileNameExistsException {
        rootDirectory.addFile(file);
    }
    long findLargestFile (){
        // long max=0;
        return rootDirectory.findLargestFile();
    }
    void sortBySize(){
        rootDirectory.sortBySize();
    }

    @Override
    public String toString() {
        return rootDirectory.getFileInfo("");
    }
}

