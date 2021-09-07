package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Duke.Duke.task.Deadline class extends Duke.Duke.task.Task class and encapsulate a deadline Duke.Duke.task
 * that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a deadline object.
     *
     * @param description String description of deadline.
     * @param by LocalDate of deadline due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string for saving deadline Duke.Duke.task to data file.
     *
     * @return string for saving deadline Duke.Duke.task to data file.
     */
    @Override
    public String toFileEntry() {
        return "D" +"/next"+ super.isDone + "/next" + super.description + "/next" + this.by.toString();
    }

    /**
     * Returns string representation of deadline object.
     *
     * @return string representation of deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
    }
}