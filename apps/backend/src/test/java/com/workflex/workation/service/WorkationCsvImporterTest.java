package com.workflex.workation.service;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkationCsvImporterTest {

    private final WorkationCsvImporter importer = new WorkationCsvImporter();

    @Test
    void parsesWorkationsFromCsv() throws Exception {
        String csv = """
                workationId,employee,origin,destination,start,end,workingDays,risk
                w1,Steffen Jacobs,Germany,United States,2024-01-02,2024-12-31,65,HIGH
                w4,Andre Fischer,Germany,Greece,2023-05-22,2023-06-30,50,LOW_RISK
                w5,Ayushi Singh,Germany,India,2023-03-13,2023-04-30,35,NO_RISK
                """;

        List<Workation> workations = importer.parse(new StringReader(csv));

        assertThat(workations).hasSize(3);
        Workation first = workations.getFirst();
        assertThat(first.getId()).isEqualTo("w1");
        assertThat(first.getEmployee()).isEqualTo("Steffen Jacobs");
        assertThat(first.getOrigin()).isEqualTo("Germany");
        assertThat(first.getDestination()).isEqualTo("United States");
        assertThat(first.getStartDate()).isEqualTo(LocalDate.of(2024, 1, 2));
        assertThat(first.getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(first.getWorkingDays()).isEqualTo(65);
        assertThat(first.getRisk()).isEqualTo(Risk.HIGH);
        assertThat(workations.get(1).getRisk()).isEqualTo(Risk.LOW);
        assertThat(workations.get(2).getRisk()).isEqualTo(Risk.NO);
    }

    @Test
    void rejectsMissingColumns() {
        String csv = """
                workationId,employee,origin,destination,start,end,workingDays,risk
                w1,Steffen Jacobs,Germany,United States,2024-01-02,2024-12-31,,HIGH
                """;

        assertThatThrownBy(() -> importer.parse(new StringReader(csv)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("workingDays");
    }
}
