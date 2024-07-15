package ag.vario.snickers.model;

import jakarta.persistence.*;
import ag.vario.snickers.model.Orderposition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SNACK_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "order")
    private Set<Orderposition> orderpositions;

    @OneToMany(mappedBy = "order")
    private Set<Moneyposition> moneypositions;

}
