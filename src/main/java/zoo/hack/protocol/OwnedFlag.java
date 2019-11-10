package zoo.hack.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnedFlag {
    private Long id;
	private User user;
	private Long userId;
	private Date expires;
    private String name;
    private String licensePlateNumer;
    private String location;
    private String phoneNumber;
}


