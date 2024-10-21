import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file (beserta path jika tidak di direktori yang sama): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah bagian yang diinginkan: ");
        int numberOfParts = scanner.nextInt();

        try {
            String content = readFile(fileName);
            Queue<String> partsQueue = splitFileContent(content, numberOfParts);

            int partNumber = 1;
            while (!partsQueue.isEmpty()) {
                System.out.println("Bagian " + partNumber + ":");
                System.out.println(partsQueue.poll());
                System.out.println("-----------");
                partNumber++;
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static Queue<String> splitFileContent(String content, int numberOfParts) {
        Queue<String> partsQueue = new LinkedList<>();
        int contentLength = content.length();
        int partSize = contentLength / numberOfParts;

        for (int i = 0; i < numberOfParts; i++) {
            int start = i * partSize;
            int end = (i == numberOfParts - 1) ? contentLength : start + partSize;
            String part = content.substring(start, end);
            partsQueue.add(part);
        }

        return partsQueue;
    }
}