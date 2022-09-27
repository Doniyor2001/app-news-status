package uz.pdp.appnewsstatus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDTO {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private Long attachmentId;

}
