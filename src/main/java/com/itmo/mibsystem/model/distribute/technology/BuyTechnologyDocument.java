package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_buy_technology_documents")
@Getter
@Setter
public class BuyTechnologyDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "count")
    private Long count;
    @Column(name = "id_delivery_type")
    private Long idDeliveryType;
    @Column(name = "id_payment_type")
    private Long idPaymentType;
    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuyTechnologyDocument document = (BuyTechnologyDocument) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BuyTechnologyDocument.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("id_technology=" + idTechnology)
                .add("count=" + count)
                .add("id_delivery_type=" + idDeliveryType)
                .add("id_payment_type=" + idPaymentType)
                .add("description=" + description)
                .toString();
    }
}
