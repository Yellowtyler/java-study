package study.daniil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int countSeq(String seq, String file) {
        int count = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str;
            while((str = bufferedReader.readLine()) != null){
                if(str.contains(seq)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void mergeFiles(String folder, String filename) {
        File file = new File(folder);
        if(file.isDirectory()) {
            File[] files = file.listFiles(f -> f.isFile() && f.getName().contains(".txt"));
            for (int i = 0; i < files.length; i++) {
                String fileStr="";
                String str;
                try(BufferedReader reader = new BufferedReader(new FileReader(files[i]))) {
                    while((str = reader.readLine()) != null) {
                        fileStr+=str + "\n";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(i == files.length-1) {
                    fileStr = fileStr.substring(0,fileStr.length()-1);
                }
                files[i].delete();

                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "\\" + filename + ".txt", true))) {
                    writer.write(fileStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFolder(File folder) throws IOException {
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                deleteFolder(files[i]);
            }
            files[i].delete();
        }
        folder.delete();
    }

    public static void main(String[] args) {
        System.out.println(countSeq("ab", "folder\\file.txt"));

        mergeFiles("folder", "all");

        try {
            deleteFolder(new File("folder\\folder1"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}



