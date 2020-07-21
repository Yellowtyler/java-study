package study.daniil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  Main {
    public static int countSeq(Path file, String seq) {
        StringBuilder stringBuilder = new StringBuilder();
        try(RandomAccessFile aFile = new RandomAccessFile(file.toAbsolutePath().toString(), "rw")){
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
            int bytesRead = inChannel.read(byteBuffer);
            while(bytesRead != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    stringBuilder.append((char)byteBuffer.get());
                }
                byteBuffer.clear();
                bytesRead = inChannel.read(byteBuffer);
            }
            inChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        int amountSeq = stringBuilder.indexOf(seq);
        while(amountSeq != -1) {
            count++;
            amountSeq = stringBuilder.indexOf(seq, amountSeq+1);
        }
        return count;
    }

    public static void mergeFiles(Path folder, String newFileName) throws IOException {
        ArrayList<Path> files = (ArrayList<Path>) Files.list(folder)
                .filter(f->f.toString().endsWith(".txt"))
                .collect(Collectors.toList());

        Path newFile = Paths.get(folder.toAbsolutePath().toString(), newFileName + ".txt");
        Files.createFile(newFile);
        StringBuilder stringBuilder = new StringBuilder();
        for (Path file : files) {
            try(RandomAccessFile aFile = new RandomAccessFile(file.toAbsolutePath().toString(), "rw")){
                FileChannel inChannel = aFile.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                int bytesRead = inChannel.read(byteBuffer);
                while(bytesRead != -1) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        stringBuilder.append((char)byteBuffer.get());

                    }
                    byteBuffer.clear();
                    bytesRead = inChannel.read(byteBuffer);
                }
                inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Files.delete(file);
            stringBuilder.append('\n');
        }
        try(RandomAccessFile aFile = new RandomAccessFile(newFile.toAbsolutePath().toString(), "rw")) {
            FileChannel outChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.wrap(stringBuilder.toString().getBytes());
            outChannel.write(byteBuffer);
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> searchFilesLess100KB(Path folder) {
        List<Path> filesToFind = new ArrayList<>();
        try {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    RandomAccessFile aFile = new RandomAccessFile(file.toAbsolutePath().toString(), "rw");
                    FileChannel inChannel = aFile.getChannel();
                    if(inChannel.size()<100000){
                       filesToFind.add(file);
                    }
                    inChannel.close();
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch(IOException e){
            e.printStackTrace();
        }
        return filesToFind;
    }

    public static void main(String[] args) {
        System.out.println(countSeq(Paths.get("text.txt"), "af"));
        System.out.println(searchFilesLess100KB(Paths.get("folder")));
        try {
            mergeFiles(Paths.get("folder"),"all");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
