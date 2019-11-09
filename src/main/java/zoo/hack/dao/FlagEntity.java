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

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity=UserEntity.class)
	private long userId;
    
    @Column(name = "flag")
	private String flag;
}