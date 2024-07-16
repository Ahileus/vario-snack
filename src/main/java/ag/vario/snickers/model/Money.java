package ag.vario.snickers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MONEY")
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNT")
    private Long count;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WORTH")
    private BigDecimal worth;

    @OneToMany(mappedBy = "money")
    private Set<MoneyPosition> moneypositions = new HashSet<MoneyPosition>();
}
