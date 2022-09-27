package uz.pdp.appnewsstatus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachersDTO {

    private Long id;
    private String fullName;
    private String subject;
    private LocalDateTime date;
    private Long attachmentId;

}
