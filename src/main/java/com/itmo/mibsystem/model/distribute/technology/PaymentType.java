package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_payment_type")
@Getter
@Setter
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id")
    private Long paymentTypeId;
    @Column(name = "type")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentType document = (PaymentType) o;
        return Objects.equal(paymentTypeId, document.paymentTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(paymentTypeId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PaymentType.class.getSimpleName() + "[", "]")
                .add("paymentTypeId=" + paymentTypeId)
                .add("type=" + type)
                .toString();
    }
}
