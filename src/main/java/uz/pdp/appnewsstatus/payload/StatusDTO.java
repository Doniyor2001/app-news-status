package uz.pdp.appnewsstatus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDTO {

    private Long id;
    private String name;
    private Integer number;

}
