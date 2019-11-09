package zoo.hack.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "flag")
public class FlagEntity {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//    @JoinColumn(name = "user_id")
//    @ManyToOne(targetEntity=UserEntity.class)
    @Column
	private long userId;
    
    @Column(name = "name")
	private String name;

    @Column(name = "license_plate_number")
    private String licensePlateNumer;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_number")
    private String phoneNumber;
}