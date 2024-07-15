package ag.vario.snickers.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MONEYPOSITION")
public class Moneyposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "COUNT")
    private Long count;
}
