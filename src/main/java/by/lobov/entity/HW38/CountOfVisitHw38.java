package by.lobov.entity.HW38;

import lombok.Data;

@Data
public class CountOfVisitHw38 {

    private long count;

    public long getIterableCount() {
        return count++;
    }
}
