package uz.pdp.appnewsstatus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDTO {

    private Long id;
    private String originalName;
    private Long size;
    private String contentType;
    private String url;

}
