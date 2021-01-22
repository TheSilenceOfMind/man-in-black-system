package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Getter
@Setter
public class BuyTechnologyDocument {
    private Long buyTechnologyDocumentId;
    private Long idTechnology;
    private Long count;
    private Long idDeliveryType;
    private Long idPaymentType;
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
        return Objects.equal(buyTechnologyDocumentId, document.buyTechnologyDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(buyTechnologyDocumentId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BuyTechnologyDocument.class.getSimpleName() + "[", "]")
                .add("buyTechnologyDocumentId=" + buyTechnologyDocumentId)
                .add("id_technology=" + idTechnology)
                .add("count=" + count)
                .add("id_delivery_type=" + idDeliveryType)
                .add("id_payment_type=" + idPaymentType)
                .add("description=" + description)
                .toString();
    }
}
