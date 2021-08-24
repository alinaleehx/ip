import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public ArrayList<Task> getTasks() throws IOException {
        ArrayList<Task> records = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] entry = line.split("/next", 4);
            String taskType = entry[0].trim();
            String isDone = entry[1].trim();
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(entry[2]);
                if (isDone.equals("true")) {
                    task.markAsDone();
                }
                records.add(task);
                break;
            case "D":
                task = new Deadline(entry[2], LocalDate.parse(entry[3]));
                if (isDone.equals("true")) {
                    task.markAsDone();
                }
                records.add(task);
                break;
            case "E":
                task = new Event(entry[2], LocalDate.parse(entry[3]));
                if (isDone.equals("true")) {
                    task.markAsDone();
                }
                records.add(task);
                break;
            }
        }
        reader.close();
        return records;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        StringBuilder textToAdd = new StringBuilder();
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.getTask(i);
            textToAdd.append(task.toFileEntry());
            textToAdd.append("\n");
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd.toString());
        fw.close();
    }
}
