package by.lobov.entity.HW38;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Data
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
public class CountOfVisitHw38 {

    private long count;

    public long incrementAndGetCount() {
        return count++;
    }
}
