package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.service.DistributeTechnologyService;
import com.itmo.mibsystem.service.PassporterService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_buy_technology_document")
@Getter
@Setter
public class BuyTechnologyDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_technology_document_id")
    private Long buyTechnologyDocumentId;
    @Column(name = "count")
    private Long count;
    @Column(name = "id_payment_type")
    private Long idPaymentType;
    @Column(name = "id_delivery_type")
    private Long idDeliveryType;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "description")
    private String description;

    @Transient
    private Long buyTechnologyMarketId;
    @Transient
    private String technologyName;
    @Transient
    private String deliveryTypeName;
    @Transient
    private String paymentTypeName;
    @Transient
    private String use;
    @Transient
    private Long idRace;

    public BuyTechnologyDocument() {
        buyTechnologyDocumentId = null;
        count = 0L;
        idPaymentType = 0L;
        idDeliveryType = 0L;
        description = "";
    }

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
        return new StringJoiner(", ", BuyTechnologyMarket.class.getSimpleName() + "[", "]")
                .add("buy_technology_document_id=" + buyTechnologyDocumentId)
                .add("count=" + count)
                .add("id_payment_type=" + idDeliveryType)
                .add("id_delivery_type=" + idDeliveryType)
                .add("description=" + description)
                .toString();
    }

}
