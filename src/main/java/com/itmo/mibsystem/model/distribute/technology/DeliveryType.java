package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_delivery_type")
@Getter
@Setter
public class DeliveryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_type_id")
    private Long deliveryTypeId;
    @Column(name = "type")
    private String type;

    public DeliveryType() {
        this.deliveryTypeId = null;
        this.type = "";
    }

    public DeliveryType(Long deliveryTypeId, String type) {
        this.deliveryTypeId = deliveryTypeId;
        this.type = type;
    }

    public DeliveryType(String type) {
        this.deliveryTypeId = null;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliveryType document = (DeliveryType) o;
        return Objects.equal(deliveryTypeId, document.deliveryTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deliveryTypeId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeliveryType.class.getSimpleName() + "[", "]")
                .add("delivery_type_id=" + deliveryTypeId)
                .add("type=" + type)
                .toString();
    }
}
