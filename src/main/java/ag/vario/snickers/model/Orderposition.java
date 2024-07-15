package ag.vario.snickers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERPOSITION")
public class Orderposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COUNT")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

}
