package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_sell_technology_document")
@Getter
@Setter
public class SellTechnologyDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_technology_document_id")
    private Long sellTechnologyDocumentId;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "cost_for_one")
    private String costForOne;
    @Column(name = "count")
    private Long count;
    @Column(name = "id_type_contract")
    private Long idTypeContract;
    @Column(name = "id_alien")
    private Long idAlien;
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
        SellTechnologyDocument document = (SellTechnologyDocument) o;
        return Objects.equal(sellTechnologyDocumentId, document.sellTechnologyDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sellTechnologyDocumentId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SellTechnologyDocument.class.getSimpleName() + "[", "]")
                .add("sell_technology_document_id=" + sellTechnologyDocumentId)
                .add("id_technology=" + idTechnology)
                .add("cost_for_one=" + costForOne)
                .add("count=" + count)
                .add("id_type_contract=" + idTypeContract)
                .add("id_alien=" + idAlien)
                .add("description=" + description)
                .toString();
    }
}
